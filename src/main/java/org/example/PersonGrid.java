package org.example;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.DataProvider;

import java.util.stream.Stream;

public class PersonGrid extends Grid<Person>
{
  PersonService personService = new PersonService();
  private UI ui;

  public PersonGrid()
  {
    _initColumns();
  }

  @Override
  protected void onAttach(AttachEvent attachEvent)
  {
    super.onAttach(attachEvent);
    ui = attachEvent.getUI();
    _initDataProvider();
  }

  private void _initColumns()
  {
    addColumn(Person::getFirstname);
    addColumn(Person::getLastname);
    addColumn(Person::getAge);
  }

  private void _initDataProvider()
  {
    DataProvider<Person, Void> dataProvider =
        DataProvider.fromCallbacks(
            query -> {
              int offset = query.getOffset();
              int limit = query.getLimit();

              if (personService.wasRequested())
              {
                Stream<Person> persons = personService.getCurrentPersons().stream();
                personService.setRequested(false);
                return persons;
              }
              else
              {
                personService.request(offset, limit, ui, this);
                return Stream.empty();
              }
            },
            // Second callback fetches the total number of items currently in the Grid.
            // The grid can then use it to properly adjust the scrollbars.
            query -> personService.count());

    setItems(dataProvider);
    personService.setProvider(dataProvider);
    startReqeust();
  }

  public void startReqeust()
  {
    personService.request(0, 50, ui, this);
  }
}
