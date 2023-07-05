package Entity;

import java.io.Serializable;

public class Item  implements Serializable {
  private int barcode;
  private String name;
  private int price;
  private int quantity;

  public Item(int barcode, String name, int price, int quantity){
    this.barcode = barcode;
    this.name = name;
    this.price = price;
    this.quantity = quantity;

  }
  public int getBarcode() {
    return barcode;
  }

  public void setBarcode(int barcode) {
    this.barcode = barcode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void soldItem(){
    quantity--;
  }


  @Override
  public String toString() {
    return "Entity.Item{" +
        "barcode=" + barcode +
        ", name='" + name + '\'' +
        ", price=" + price +
        ", quantity=" + quantity +
        '}';
  }
}
