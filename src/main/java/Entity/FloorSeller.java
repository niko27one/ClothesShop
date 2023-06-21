package Entity;

import java.io.Serializable;

public class FloorSeller extends Person implements Serializable {



  private int floorSellerNo;

  private int totalItemsSold = 0;

  public FloorSeller(String firstName, String lastName, String DOB,int floorSellerNo) {
    super(firstName, lastName, DOB);
    this.floorSellerNo = floorSellerNo;
  }
  public int getFloorSellerNo() {
    return floorSellerNo;
  }

  public void setFloorSellerNo(int floorSellerNo) {
    this.floorSellerNo = floorSellerNo;
  }

  public int getTotalItemsSold() {
    return totalItemsSold;
  }

  public void setTotalItemsSold(int totalItemsSold) {
    this.totalItemsSold = totalItemsSold;
  }


  @Override
  public String toString() {
    System.out.println(super.toString());
    return "Employee{" +
        "firstName='" + this.getFirstName() + '\'' +
        ", lastName='" + this.getLastName() + '\'' +
        ", DOB='" + this.getDOB() + '\'' +
        "employeeNo=" + floorSellerNo +
        '}';
  }


}


