package org.example;

import com.vaadin.aggrid.AgGrid;
import com.vaadin.flow.data.provider.DataProvider;

public class PersonAgGrid extends AgGrid<Person>
{
  PersonAgService service = new PersonAgService();

  public PersonAgGrid()
  {
    setSizeFull();
    _initColumns();
    _initDataProvider();
  }

  private void _initDataProvider()
  {
    setDataProvider(DataProvider.fromCallbacks(
        query -> service.fetch(query.getOffset(), query.getLimit()),
        query -> service.count()));
  }

  private void _initColumns()
  {
    addColumn("firstname", Person::getFirstname);
    addColumn("lastname", Person::getLastname);
    addColumn("age", Person::getAge);
  }
}
