package Entity;

import java.io.Serializable;

public class SoldItem <T> implements Serializable {
  private T barcode;
  private T name;
  private T price;
  private int quantity;

  public SoldItem(T barcode, T name,T price,int quantity){
    this.barcode=barcode;
    this.name=name;
    this.price= price;
    this.quantity=quantity;
  }
  public T getBarcode() {
    return barcode;
  }

  public void setBarcode(T barcode) {
    this.barcode = barcode;
  }

  public T getName() {
    return name;
  }

  public void setName(T name) {
    this.name = name;
  }

  public T getPrice() {
    return price;
  }

  public void setPrice(T price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void soldItem(){
    this.quantity++;
  }

}
