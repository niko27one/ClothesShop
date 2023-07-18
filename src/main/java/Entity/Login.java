package Entity;

import java.io.Serializable;

public class Login  implements Serializable {
  private int id;
  private String password;

  public Login(int id, String password){
    this.id = id;
    this.password = password;

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  }

