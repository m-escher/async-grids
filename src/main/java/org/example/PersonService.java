package org.example;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.data.provider.DataProvider;
import org.vaadin.flow.helper.AsyncManager;

import java.util.*;
import java.util.concurrent.*;

public class PersonService
{
  private final ExecutorService executor = Executors.newSingleThreadExecutor();
  private DataProvider<Person, Void> dataProvider;
  private List<Person> currentPersons = new ArrayList<>();
  private boolean requested;

  public List<Person> getCurrentPersons()
  {
    return currentPersons;
  }

  public int count()
  {
    return currentPersons.size();
  }

  public void request(int pOffset, int pLimit, UI pUi, PersonGrid pPersonGrid)
  {
    requested = true;

    AsyncManager.getInstance().registerAsync(pPersonGrid, pTask -> {
      Thread.sleep(5000);
      currentPersons = new ArrayList<>();

      for (int i = pOffset; i < pOffset + pLimit; i++)
        currentPersons.add(new Person("Huber" + i, "Sepp", i));

      pTask.push(() -> dataProvider.refreshAll());
    });
  }

  public void setProvider(DataProvider<Person, Void> pDataProvider)
  {
    dataProvider = pDataProvider;
  }

  public boolean wasRequested()
  {
    return requested;
  }

  public void setRequested(boolean pRequested)
  {
    requested = pRequested;
  }
}
