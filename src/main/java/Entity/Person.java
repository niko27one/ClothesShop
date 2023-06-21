package Entity;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private String firstName;
    private String lastName;
    private String DOB;

  public Person(String firstName, String lastName, String DOB) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.DOB = DOB;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDOB() {
    return DOB;
  }

  public void setDOB(String DOB) {
    this.DOB = DOB;
  }

  @Override
  public String toString() {
    return "Entity.Person{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", DOB='" + DOB + '\'' +
        '}';
  }



}
