package UIcontroller;

import Controller.FloorControllerInterface;
import Controller.FloorFloorController;
import Controller.LoginController;
import Entity.Login;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FloorUI {
    public void RunShop() throws IOException {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      LoginController loginController = new LoginController();
      FloorControllerInterface shop = new FloorFloorController();
      boolean runShop = true;

      while(runShop){
        System.out.println("Enter id number: ");
        int id = Integer.parseInt(reader.readLine());
        System.out.println("Enter password");
        String password= reader.readLine();
        Login login = LoginController.createLogin(id, password);
        if (login != null) {
          System.out.println("Logged in!");
          shop.printFloorSellerDetails(id);
          int choise;
          String name;
          int qty;
          do {
            System.out.println("Menu:");
            System.out.println("1. load the week");
            System.out.println("2. add sale");
            System.out.println("3. save sales");
            System.out.println("4. print your details");
            System.out.println("5. print your items sold and total value");
            System.out.println("6. list team sales");
            System.out.println("7. list items and quantity left");
            System.out.println("8. print items sold and value");
            System.out.println("9. Exit");
            try {
              choise = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
              choise = 0;
              e.printStackTrace();
            }

            switch (choise) {
              case 1:
                shop.loadWeeklySells("src/main/resources/Saving.txt");
                System.out.println("week loaded.");
                break;
              case 2:
                System.out.println("name of the item: ");
                name = reader.readLine();
                System.out.println("ho many?: ");
                qty = Integer.parseInt(reader.readLine());
                shop.addSales(id,qty,name);
                System.out.println("sales added.");
                break;
              case 3:
                shop.saveSells("src/main/resources/Saving.txt");
                System.out.println("sales saved");
                break;
              case 4:
                shop.printFloorSellerDetails(id);
                break;
              case 5:
                shop.printDailySingleSell(id);
                break;
              case 6:
                shop.printItemsAndQty();
                break;
              case 7:
                // Esci dal menu
                System.out.println("bye bye.");
                reader.close();
                runShop = false;
                break;
              default:
                System.out.println("not a valid choise, try again.");
            }
          } while (choise != 7);
        } else {
          System.out.println("ID or password not correct.");
        }
      }

      }



}



