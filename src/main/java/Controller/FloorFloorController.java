package Controller;
import Entity.FloorSeller;
import Entity.Item;
import Entity.Login;
import Entity.SoldItem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FloorFloorController implements FloorControllerInterface, Serializable {
  private List<Item> items;
  private List<FloorSeller> floorSellers;
  private List<SoldItem> soldItems;

  public FloorFloorController(){
    this.floorSellers = addToList(FileType.FLOOR_SELLER,FloorSeller.class);
    this.items= addToList(FileType.ITEMS,Item.class);
    this.soldItems = addToList(FileType.SOLD_ITEMS,SoldItem.class);
  }

  public ArrayList<FloorSeller> addFloorSellers(){
    String filePath = "src/main/resources/FloorSellers.txt";
    ArrayList<FloorSeller> floorSellers = new ArrayList<>();
    try (FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader)) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        // Process each line of the file
        String[] data = line.split(","); // Assuming the data is comma-separated

        // Create an object using the data from the file
        FloorSeller floorSeller = createFloorSellers(data);

        // Add the object to the ArrayList
        floorSellers.add(floorSeller);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return floorSellers;
  }

  public <T> List<T> addToList(FileType fileType,Class<T> tClass) {
      List<T> floorSellers = new ArrayList<>();
      List<T> items = new ArrayList<>();
      List<T> loginDetails = new ArrayList<>();
      List<T>soldItems = new ArrayList<>();
    try (FileReader fileReader = new FileReader(fileType.getType());
        BufferedReader bufferedReader = new BufferedReader(fileReader)) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] data = line.split(","); // Assuming the data is comma-separated

        if (fileType == FileType.ITEMS) {
          Item item = createItems(data);
          items.add((T) item);
          return  items;
        } else if (fileType == FileType.FLOOR_SELLER) {
          FloorSeller floorSeller =  createFloorSellers(data);
          floorSellers.add((T) floorSeller);
          return  floorSellers;
        } else if (fileType == FileType.SOLD_ITEMS) {
          SoldItem soldItem = createSoldItems(data);
          soldItems.add((T)soldItem);
            return  soldItems;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  public FloorSeller createFloorSellers(String[] data) {
    // Create and return an object based on the data
    String firstName = data[0]; // Modify the index based on your data structure
    String lastName = data[1];
    String DOB = data[2]; // Assuming the third parameter is an integer
    int floorSellerNo = Integer.parseInt(data[3]);

    return new FloorSeller(firstName, lastName, DOB,floorSellerNo); // Modify the constructor parameters accordingly
  }

  public SoldItem createSoldItems(String[] data) {
    int barcode = Integer.parseInt(data[0]); // Modify the index based on your data structure
    String name = data[1];
    int price = Integer.parseInt(data[2]); // Assuming the third parameter is an integer
    int quantity = Integer.parseInt(data[3]);

    return new SoldItem(barcode, name, price,quantity);
  }

  public Login createLoginDetails(String[] data){
    int id = Integer.parseInt(data[0]); // Modify the index based on your data structure
    String password = data[1];
    return new Login(id, password); // Modify the constructor parameters accordingly
  }

  public Item createItems(String[] data) {
    // Create and return an object based on the data
    int barcode = Integer.parseInt(data[0]); // Modify the index based on your data structure
    String name = data[1];
    int price = Integer.parseInt(data[2]); // Assuming the third parameter is an integer
    int quantity = Integer.parseInt(data[3]);

    return new Item(barcode, name, price,quantity); // Modify the constructor parameters accordingly
  }

  public boolean loginVerification (List <Login> logins,int id,String passw){
     for(Login login: logins){
       if(login.getId() == id && login.getPassword().equals(passw)){
         return true;
       }
     }
     return false;   // return id == login.getId() && passw.equals(login.getPassword());
  }

  public void saveSells(String fname){   // uses object serialisation
    try
    {
      //Saving.txt of object in a file
      FileOutputStream file = new FileOutputStream(fname);
      ObjectOutputStream out = new ObjectOutputStream(file);

      // Method for serialization of object
      out.writeObject(this);
      out.close();
      file.close();
      System.out.println("Object has been serialized");
    }
    catch(IOException ex)
    {
      System.out.println("IOException is caught");
    }
  }

  public Optional<FloorFloorController> loadWeeklySells(String fname){   // uses object serialisation
    Optional<FloorFloorController> obj = Optional.empty();
    try {
      File fileObj = new File(fname);
      if (fileObj.length() == 0) {
        System.out.println("the file is empty");
        System.out.println("Creation of new week file");
        this.saveSells(fname);
      }
      FileInputStream file = new FileInputStream(fileObj);
      ObjectInputStream in = new ObjectInputStream(file);
      obj = Optional.of((FloorFloorController) in.readObject());
      in.close();
      file.close();
      System.out.println("Object has been deserialized");
    } catch (IOException ex) {
      System.out.println("IOException is caught");
      ex.printStackTrace();
    } catch (ClassNotFoundException ex) {
      System.out.println("ClassNotFoundException is caught");
      ex.printStackTrace();
    }
    return obj;
  }

  public void addSales(int id, int qty,String name) {
    for(FloorSeller floorSeller: floorSellers){
      if (id == floorSeller.getFloorSellerNo()) {
        floorSeller.setTotalItemsSold(qty);
        this.removeItemsQty(name);
        System.out.println(name+" sold");
      } else {
        System.out.println("seller not found");
      }
    }
  }

  public void removeItemsQty(String name){
    for(Item item:items){
      if (item.getName().equals(name)) {
        item.soldItem();
        this.addSoldItemsQty(name);
      } else {
        System.out.println("item not found");
      }
    }
  }

  public void addSoldItemsQty(String name) {
    for(SoldItem soldItem:soldItems){
      if(soldItem.getName().equals(name)){
        soldItem.soldItem();
      }
    }
  }

  public void printDailySingleSell(int id) {//to add total price of the items sold anche change in the the interface
    double itemsValue = 0.0;
    for(FloorSeller floorSeller: floorSellers){
      if (id == floorSeller.getFloorSellerNo()) {// to check comparison
        for(SoldItem item:soldItems){
          itemsValue += floorSeller.getTotalItemsSold() * Integer.parseInt(item.getPrice().toString());
        }
        System.out.println("total items sold from " + floorSeller.getFirstName() + " " + floorSeller.getLastName() + " :" + floorSeller.getTotalItemsSold()+ " total value of: "+ itemsValue);
      } else {
        System.out.println("floor seller not found");
      }
    }
  }

  public void printDailyTeamSells() {
    int totalItemsSold = 0;
    double itemsValue = 0.0;
    for(FloorSeller floorSeller: floorSellers){
        totalItemsSold+= floorSeller.getTotalItemsSold();// totalItemSold = totalItemSold + floorSeller.getTotalItemsSold();
    }
    for(SoldItem item:soldItems){
      itemsValue += totalItemsSold * Integer.parseInt(item.getPrice().toString());
    }
    System.out.println("total team items sold: " +totalItemsSold + ", for the total price sold of: " +itemsValue);
  }

  public void printItemsAndQty() {
    for(Item item: items){
      System.out.println("barcode: " +item.getBarcode() +", Name: "+item.getName()+ ", quantity left: "+ item.getQuantity() );
    }
  }

  public void printItemsSold() {
    System.out.println("all the items sold are: ");
    for(SoldItem soldItem:soldItems){
      System.out.println(soldItem.getBarcode() + ", "+ soldItem.getName() + ", quantity:"+ soldItem.getQuantity()+ " "+ soldItem.getPrice());
    }
  }

  public void printFloorSellerDetails(int id) {
    for(FloorSeller floorSeller:floorSellers){
      if (id == floorSeller.getFloorSellerNo()) {
        System.out.println(floorSeller.getFirstName()+ " "+ floorSeller.getLastName());
      }
    }
  }
}



