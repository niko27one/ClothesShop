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
      System.out.println("Insert id number: ");
      int id = Integer.parseInt(reader.readLine());
      System.out.println("Insert password");
      String password= reader.readLine();


      Login login = LoginController.createLogin(id, password);

      if (login != null) {
        System.out.println("Accesso effettuato!");
        int scelta;

        do {
          System.out.println("Menu:");
          System.out.println("1. Operazione 1");
          System.out.println("2. Operazione 2");
          System.out.println("3. Esci");
          System.out.print("Seleziona un'opzione: ");

          try {
            scelta = Integer.parseInt(reader.readLine());
          } catch (IOException e) {
            scelta = 0;
            e.printStackTrace();
          }

          switch (scelta) {
            case 1:
              // Esegui l'operazione 1
              System.out.println("Eseguita operazione 1.");
            case 2:
              // Esegui l'operazione 2
              System.out.println("Eseguita operazione 2.");

            case 3:
              // Esci dal menu
              System.out.println("Uscendo dal programma.");
              reader.close();
              break;
            default:
              System.out.println("Opzione non valida. Riprova.");
          }
        } while (scelta != 3);
      } else {
        System.out.println("ID o password non validi.");
      }
    }
}



