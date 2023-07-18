package Controller;

import java.io.Serializable;

public enum FileType implements Serializable {
    FLOOR_SELLER("src/main/resources/FloorSellers.txt"), ITEMS("src/main/resources/Items.txt");
  private String type;

  private FileType (String ty)
  {
    type = ty;
  }

  public String getType()
  {
    return type;
  }
}
