package Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FloorSeller extends Person implements Serializable {
  private int floorSellerNo;
  private List<SoldItem> soldItemList = new ArrayList<>();
  private int totalItemsSold = 0;
  private int totalAmountSold = 0;

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

  public List<SoldItem> getSoldItemList() {
    return soldItemList;
  }

  public void setSoldItem(SoldItem soldItem) {
    this.getSoldItemList().add(soldItem);
  }

  public int getTotalItemsSold() {

    return totalItemsSold;
  }

  public void setTotalItemsSold(int totalItemsSold) {
    this.totalItemsSold += totalItemsSold;
  }

  public int getTotalAmountSold(){
    this.totalAmountSold = this.getSoldItemList().stream().mapToInt(soldItem->(int)soldItem.getPrice()*soldItem.getQuantity()).sum();
    return  this.totalAmountSold;
  }

  @Override
  public String toString() {
    return "FloorSeller{" +
        "floorSellerNo=" + floorSellerNo +
        ", soldItemList=" + soldItemList +
        ", totalItemsSold=" + totalItemsSold +
        ", totalAmountSold=" + this.getTotalAmountSold()+
        '}';
  }
}


