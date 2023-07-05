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
  boolean loginVerification (List <Login> logins,int id,String passw);
  SoldItem createSoldItems(String[] data);
  Login createLoginDetails(String[] data);
  void addSoldItemsQty(String name);
  void saveSells(String fname);
  Optional<FloorFloorController> loadWeeklySells(String fname);
  void addSales(int id,int qty,String name);
  void removeItemsQty(String name);
  void printDailySingleSell(int id);
  void printDailyTeamSells();
  void printItemsAndQty();
  void printItemsSold();
  void printFloorSellerDetails(int id);

}
