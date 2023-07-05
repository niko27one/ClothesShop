package Entity;

import java.io.Serializable;

public abstract class Person<T> implements Serializable {
    private T firstName;
    private T lastName;
    private T DOB;

  public Person(T firstName, T lastName, T DOB) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.DOB = DOB;
  }

  public T getFirstName() {
    return firstName;
  }

  public void setFirstName(T firstName) {
    this.firstName = firstName;
  }

  public T getLastName() {
    return lastName;
  }

  public void setLastName(T lastName) {
    this.lastName = lastName;
  }

  public T getDOB() {
    return DOB;
  }

  public void setDOB(T DOB) {
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
