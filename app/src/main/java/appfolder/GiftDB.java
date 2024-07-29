package appfolder;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class GiftDB extends Gift {
  private static ArrayList <Gift> giftDB;

  // The GiftDB is implemented in the same way as DishDB, but now for the Gift class.
  public GiftDB(User upSponsor, String name, Image image, int points) {
    super(upSponsor, name, image, points);
    if(giftDB == null) {
       giftDB = new ArrayList<>();
    }
  }

  public static void addToGiftDB(Gift gift) {
    if(giftDB == null) {
      giftDB = new ArrayList<>();
    }
    giftDB.add(gift);
  }

  public static ArrayList<Gift> getGiftDB() {
    return giftDB;
  }
}
