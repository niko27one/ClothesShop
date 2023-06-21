package Entity;

import java.io.Serializable;

public class Supervisor implements Serializable {

  private int supervisorID;
  private String password;

  public Supervisor(int supervisorID, String password) {
    this.supervisorID = supervisorID;
    this.password = password;
  }


  public int getSupervisorID() {
    return supervisorID;
  }

  public void setSupervisorID(int supervisorID) {
    this.supervisorID = supervisorID;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
