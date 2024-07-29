package test;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

// GiftImages is coded in a simmilar way as FoodImages.
public class GiftImages {
    private static List<String> initialGifts = new ArrayList<>();
    private static Boolean giftsInitialized = false;
    private static User Sponsor1 = new User("Cutlery & CO", "root");
    private static User Sponsor2 = new User("LunchBox", "root");
    private static User Sponsor3 = new User("MovieCard", "root");
    private static User Sponsor4 = new User("Stuffed Animals & More", "root");

    public GiftImages() {
      if (initialGifts.isEmpty()) {
        initializeGifts();
      }
    }

    public static void initializeGifts() {
      if(!giftsInitialized) {
          synchronized (GiftImages.class) {
             if(!giftsInitialized) {
                if (initialGifts.isEmpty()) {
                    initialGifts.add("/Cutlery1.png");
                    initialGifts.add("/lunchbox.png");
                    initialGifts.add("/moviecard.png");
                    initialGifts.add("/waterbottle.png");
                    initialGifts.add("/teddybear.png");
                    initialGifts.add("/knife.png");
                }
                for (String imagepath : initialGifts) {
                  if (imagepath.equals("/Cutlery1.png")) {
                      Gift cutlery = new Gift(Sponsor1, "Cutlery", createImage(imagepath), 7 );
                      cutlery.setDescription("An amazing set of cutlery by our amazing sponsor Cutlery & CO.\n This set consists of a fork, a little fork,  a knife, a tablespoon and a teaspoon.\n Everything is created with stainless steel to ensure enjoyment for a lifetime. \n Once you have this set, you will not want any other set!");
                      GiftDB.addToGiftDB(cutlery);
                  }
                  else if (imagepath.equals("/lunchbox.png")) {
                      Gift lunchbox = new Gift(Sponsor2, "lunchbox", createImage(imagepath), 5 );
                      lunchbox.setDescription("This lunchbox is sponsored by our sponsor LunchBox.\n It's an amazing Lunchbox to take to school or work!");
                      GiftDB.addToGiftDB(lunchbox);
                  }
                  else if (imagepath.equals("/moviecard.png")) {
                      Gift cinematicket = new Gift(Sponsor3, "cinematicket", createImage(imagepath), 8);
                      cinematicket.setDescription ("A ticket for a movie of your choice by our sponsor MovieCard!");
                      GiftDB.addToGiftDB(cinematicket);
                  }  
                  else if (imagepath.equals("/waterbottle.png")) {
                      Gift watterbottle = new Gift(Sponsor2, "waterbottle", createImage(imagepath), 4);
                      watterbottle.setDescription ("A aluminium waterbottle by our sponsor LunchBox");
                      GiftDB.addToGiftDB(watterbottle);
                  } 
                  else if (imagepath.equals("/teddybear.png")) {
                      Gift teddybear = new Gift(Sponsor4, "teddybear", createImage(imagepath), 18);
                      teddybear.setDescription ("A teddy bear by our sponsor Stuffed Animals & More ");
                      GiftDB.addToGiftDB(teddybear);
                  } 
                  else if (imagepath.equals("/knife.png")) {
                      Gift knife = new Gift(Sponsor1, "knife", createImage(imagepath), 18);
                      knife.setDescription ("A sharp cooking knife that every kitchen needs\nby our sponsor Cutlery & CO");
                      GiftDB.addToGiftDB(knife);
                  } 

                  
                }
                giftsInitialized = true;
             }
          }
      }
    }

    public static Image createImage(String imagepath) {
      return new Image(GiftImages.class.getResourceAsStream(imagepath));
    }

    public static ImageView createImageView(Image giftImage) {
        ImageView imageView = new ImageView(giftImage);
        imageView.setFitWidth(100); 
        imageView.setFitHeight(100); 
        return imageView;
    }

    public static Button createImageButton(ImageView imageview, Stage primStage, Gift gift, User user) {
        Button giftButton = new Button();
        giftButton.setGraphic(imageview);
        giftButton.setPrefSize(100, 100);
        
        giftButton.setOnAction(e -> {
            GiftInfo giftInfo = new GiftInfo(primStage, gift, user);
            giftInfo.giftInfoScreen();
        });
        
        return giftButton;
    }
}
