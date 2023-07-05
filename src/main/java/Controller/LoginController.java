package Controller;
import Entity.Login;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;


public class LoginController implements Serializable {
  private List<Login> login;


  public static Login createLogin(int id, String password){
    String filePath = "src/main/resources/Login.txt";
    try (FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader)) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        // Process each line of the file
        String[] data = line.split(","); // Assuming the data is comma-separated
        int loginId = Integer.parseInt(data[0].trim());
        String loginPassword = data[1].trim();

        if (loginId == id && loginPassword.equals(loginPassword)) {
          // Creazione dell'oggetto Login
          return new Login(id, password);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}