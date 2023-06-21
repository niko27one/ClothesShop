package Controller;
import Entity.FloorSeller;
import Entity.Item;
import Entity.Login;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class FloorController implements Serializable,ControllerInterface {
  private ArrayList<Item> items;
  private ArrayList<FloorSeller> floorSellers;
  private Login login;

  public FloorController(){
    this.items = new ArrayList<>();
    this.floorSellers = addToList(FileType.FLOOR_SELLER);
    this.items= addToList(FileType.ITEMS);
    this.login = addToList(FileType.LOGIN);
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

  public FloorSeller createFloorSellers(String[] data) {
    // Create and return an object based on the data
    String firstName = data[0]; // Modify the index based on your data structure
    String lastName = data[1];
    String DOB = data[2]; // Assuming the third parameter is an integer
    int floorSellerNo = Integer.parseInt(data[3]);

    return new FloorSeller(firstName, lastName, DOB,floorSellerNo); // Modify the constructor parameters accordingly
  }

  public <T> T addToList(FileType fileType) {
    try (FileReader fileReader = new FileReader(fileType.getType());
        BufferedReader bufferedReader = new BufferedReader(fileReader)) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        String[] data = line.split(","); // Assuming the data is comma-separated

        if (fileType == FileType.ITEMS) {
          T item = (T) createItems(data);
          items.add((Item) item);
          return item;
        } else if (fileType == FileType.FLOOR_SELLER) {
          T floorSeller = (T) createFloorSellers(data);
          floorSellers.add((FloorSeller) floorSeller);
          return floorSeller;
        } else if (fileType == FileType.LOGIN) {
            T login = (T) createLoginDetails(data);
            return login;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  public Item createItems(String[] data) {
    // Create and return an object based on the data
    int barcode = Integer.parseInt(data[0]); // Modify the index based on your data structure
    String name = data[1];
    int price = Integer.parseInt(data[2]); // Assuming the third parameter is an integer
    int quantity = Integer.parseInt(data[3]);

    return new Item(barcode, name, price,quantity); // Modify the constructor parameters accordingly
  }

  public boolean loginVerification (Login login,int id,String passw){
     if(id == login.getId() && passw.equals(login.getPassword())) return true;
     else
       return false;
    // return id == login.getId() && passw.equals(login.getPassword());
  }

  public Login createLoginDetails(String[] data){
    int id = Integer.parseInt(data[0]); // Modify the index based on your data structure
    String password = data[1];
    return new Login(id, password); // Modify the constructor parameters accordingly
  }


  public void saveSells(String fname){   // uses object serialisation
    try
    {
      //Saving of object in a file
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

  public FloorController loadWeeklySells(String fname){   // uses object serialisation
    FloorController obj = null;
    try
    {
      // Reading the object from a file
      FileInputStream file = new FileInputStream(fname);
      ObjectInputStream in = new ObjectInputStream(file);
      // Method for deserialization of object
      obj = (FloorController) in.readObject();
      in.close();
      file.close();
      System.out.println("Object has been deserialized ");
    }
    catch(IOException ex)
    {
      System.out.println("IOException is caught");
    }
    catch(ClassNotFoundException ex)
    {
      System.out.println("ClassNotFoundException is caught");
    }
    return obj;
  }

  public void addSales(int id,int qty){
    for(FloorSeller floorSeller: floorSellers){
      if (id == floorSeller.getFloorSellerNo()) {
        floorSeller.setTotalItemsSold(qty);
      } else {
        System.out.println("seller not found");
      }
    }
  }

  public void pirntDailySingleSell(int id) {
    for(FloorSeller floorSeller: floorSellers){
      if (id == floorSeller.getFloorSellerNo()){
        System.out.println("total items sold from " + floorSeller.getFirstName() + " " + floorSeller.getLastName() + " :" + floorSeller.getTotalItemsSold());
      }
    }
  }

  public void printDailyTeamSells() {
    for(FloorSeller floorSeller: floorSellers){
      System.out.println("total team items sold: " +floorSeller.getTotalItemsSold());
    }
  }


}



