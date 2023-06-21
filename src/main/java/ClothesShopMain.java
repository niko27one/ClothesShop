import Controller.ControllerInterface;
import Controller.FloorController;
import Entity.FloorSeller;
import Entity.Item;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ClothesShopMain {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


    private void Shop(){


    }



    public static void main(String[] args) {



    }
        /*ArrayList<Item> items = addItemToList();
        ArrayList<FloorSeller> floorSellers = addFloorSellers();

        for (Item Item : items) {
            System.out.println(Item); // Assuming YourObject class has a meaningful toString() method
        }

        for (FloorSeller floorSeller : floorSellers) {
            System.out.println(floorSeller); // Assuming YourObject class has a meaningful toString() method
        }

    }

    public static ArrayList<FloorSeller> addFloorSellers(){
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

    public static FloorSeller createFloorSellers(String[] data) {
        // Create and return an object based on the data
        String firstName = data[0]; // Modify the index based on your data structure
        String lastName = data[1];
        String DOB = data[2]; // Assuming the third parameter is an integer
        int floorSellerNo = Integer.parseInt(data[3]);

        return new FloorSeller(firstName, lastName, DOB,floorSellerNo); // Modify the constructor parameters accordingly
    }

    public static ArrayList<Item> addItemToList(){
        String filePath = "src/main/resources/Items.txt";
        ArrayList<Item> items = new ArrayList<>();

        try (FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Process each line of the file
                String[] data = line.split(","); // Assuming the data is comma-separated

                // Create an object using the data from the file
                Item item = createItems(data);

                // Add the object to the ArrayList
                items.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }



    public static Item createItems(String[] data) {
        // Create and return an object based on the data
        int barcode = Integer.parseInt(data[0]); // Modify the index based on your data structure
        String name = data[1];
        int price = Integer.parseInt(data[2]); // Assuming the third parameter is an integer
        int quantity = Integer.parseInt(data[3]);

        return new Item(barcode, name, price,quantity); // Modify the constructor parameters accordingly
    }



    public static void loadWeek(){


    }

    public static void saveWeek(){


    }*/


}
