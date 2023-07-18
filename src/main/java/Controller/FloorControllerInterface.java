package Controller;

import Entity.FloorSeller;
import Entity.Item;
import Entity.Login;
import Entity.SoldItem;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface FloorControllerInterface extends Serializable {
  <T> List<T> addToList(FileType fileType,Class<T> tClass);
  FloorSeller createFloorSellers(String[] data);
  Item createItems(String[] data);
  SoldItem createSoldItems(String[] data);
  void addSoldItemsQty(String name, int soldQty);
  void saveSells(String fname);
  Optional<FloorFloorController> loadWeeklySells(String fname);
  void addSales(int id,int qty,String name);
  void removeItemsQty(String name,int soldQty);
  void printDailySingleSell(int id);
  void printItemsAndQty();
  void printFloorSellerDetails(int id);

}
