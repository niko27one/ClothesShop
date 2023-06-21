package Controller;

import Entity.FloorSeller;
import Entity.Item;
import Entity.Login;

public interface ControllerInterface {

  FloorSeller createFloorSellers(String[] data);
  <T> T addToList(FileType fileType);
  Item createItems(String[] data);
  boolean loginVerification (Login login,int id,String passw);
  Login createLoginDetails(String[] data);
  void saveSells(String fname);
  FloorController loadWeeklySells(String fname);
  void addSales(int id,int qty);
  void pirntDailySingleSell(int id);
  void printDailyTeamSells();
}
