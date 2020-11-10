package org.example;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class PersonAgService
{
  private final ExecutorService executor = Executors.newSingleThreadExecutor();

  public int count()
  {
    return 10000;
  }

  public Stream<Person> fetch(int pOffset, int pLimit)
  {
    try
    {
      executor.submit(() -> {
        try
        {
          Thread.sleep(5000);
        }
        catch (InterruptedException pE)
        {
          pE.printStackTrace();
        }
      }).get();
    }
    catch (InterruptedException | ExecutionException pE)
    {
      pE.printStackTrace();
    }

    List<Person> persons = new ArrayList<>();

    for (int i = pOffset; i < pOffset + pLimit; i++)
      persons.add(new Person("Huber" + i, "Sepp", i));

    return persons.stream();
  }
}
