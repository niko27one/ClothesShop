package Controller;
import Entity.FloorSeller;
import Entity.Item;
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
    this.items= addToList(FileType.ITEMS,Item.class);
    this.floorSellers = addToList(FileType.FLOOR_SELLER,FloorSeller.class);
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
      List<T> db = new ArrayList<>();

    try (FileReader fileReader = new FileReader(fileType.getType());
        BufferedReader bufferedReader = new BufferedReader(fileReader)) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] data = line.split(","); // Assuming the data is comma-separated

        if (fileType == FileType.ITEMS) {
          Item item = createItems(data);
          db.add((T) item);
          //return  items;
        } else if (fileType == FileType.FLOOR_SELLER) {
          FloorSeller floorSeller =  createFloorSellers(data);
          db.add((T) floorSeller);
          //return  floorSellers;
        } /*else if (fileType == FileType.SOLD_ITEMS) {
          SoldItem soldItem = createSoldItems(data);
          db.add((T)soldItem);
            //return  soldItems;
        }*/
      }
      return db;
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

  public Item createItems(String[] data) {
    // Create and return an object based on the data
    int barcode = Integer.parseInt(data[0]); // Modify the index based on your data structure
    String name = data[1];
    int price = Integer.parseInt(data[2]); // Assuming the third parameter is an integer
    int quantity = Integer.parseInt(data[3]);

    return new Item(barcode, name, price,quantity); // Modify the constructor parameters accordingly
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
    boolean isFound = false;
    for(FloorSeller floorSeller: floorSellers){
      if (id == floorSeller.getFloorSellerNo()) {
        for(Item item:this.items){
          if(item.getName().equals(name)){
            SoldItem soldItem = new SoldItem(item.getBarcode(),item.getName(),item.getPrice(),qty);
            floorSeller.setSoldItem(soldItem);
            floorSeller.setTotalItemsSold(qty);
          }
        }
        this.removeItemsQty(name,qty);
        System.out.println(name+" sold");
        isFound = true;
      }
    }if (!isFound) {
      System.out.println("seller not found");
    }
  }

  public void removeItemsQty(String name, int soldQty){
    boolean isItemFound = false;
    for(Item item:items){
      if (item.getName().equals(name)) {
        item.soldItem(soldQty);
        //this.addSoldItemsQty(name,soldQty);
        isItemFound = true;
      }
    }if (!isItemFound) {
      System.out.println("item not found");
    }
  }

  public void addSoldItemsQty(String name,int soldQty) {
    for(SoldItem soldItem:soldItems){
      if(soldItem.getName().equals(name)){
        soldItem.soldItem(soldQty);
      }
    }
  }

  public void printDailySingleSell(int id) {//to add total price of the items sold anche change in the the interface
    for(FloorSeller floorSeller: floorSellers){
      if (id == floorSeller.getFloorSellerNo()) {// to check comparison
          for (SoldItem soldItem: floorSeller.getSoldItemList()){
            System.out.println(soldItem.toString());
          }
        }
      }
    }

  public void printItemsAndQty() {
    for(Item item: items){
      System.out.println("barcode: " +item.getBarcode() +", Name: "+item.getName()+ ", quantity left: "+ item.getQuantity() );
    }
  }

  public void printFloorSellerDetails(int id) {
    for(FloorSeller floorSeller:floorSellers){
      if (id == floorSeller.getFloorSellerNo()) {
        System.out.println("Hello "+ floorSeller.getFloorSellerNo() + " "+ " "+ floorSeller.getFirstName()+ " " +floorSeller.getLastName());
      }
    }
  }


}
