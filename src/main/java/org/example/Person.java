package org.example;

public class Person
{
  private String firstname;
  private String lastname;
  private int age;

  public Person(String pFirstname, String pLastname, int pAge)
  {
    firstname = pFirstname;
    lastname = pLastname;
    age = pAge;
  }

  public String getFirstname()
  {
    return firstname;
  }

  public void setFirstname(String pFirstname)
  {
    firstname = pFirstname;
  }

  public String getLastname()
  {
    return lastname;
  }

  public void setLastname(String pLastname)
  {
    lastname = pLastname;
  }

  public int getAge()
  {
    return age;
  }

  public void setAge(int pAge)
  {
    age = pAge;
  }
}
