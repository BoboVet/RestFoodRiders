package test;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// GiftInfo is implemented in a similar way as DishInfo.
public class GiftInfo implements NavigationButtons {
  private Stage primaryStage;
  private Gift gift;
  private User user;
  private BorderPane giftInfoScreen = new BorderPane();
  private Label userLabel = new Label();
  private Label giftNameLabel = new Label();
  private Label giftDescription = new Label();
  private Label pointsLabel = new Label();

  public GiftInfo (Stage primStage, Gift gift, User user) {
    this.primaryStage = primStage;
    this.gift = gift;
    this.user = user;
  }

  public void giftInfoScreen() {
    giftInfoScreen.setPadding(new Insets(20));

    ImageView giftimage = GiftImages.createImageView(gift.getImage());
    giftimage.setFitHeight(200);
    giftimage.setFitWidth(200);

    VBox giftBox = new VBox(5, giftimage, giftInfo(), addCartButton());
    giftBox.setAlignment(Pos.TOP_CENTER);

    HBox navBox = new HBox(15, homeButton(primaryStage, user), addDishButton(primaryStage, user), giftButton(primaryStage, user), accountButton(primaryStage, user));
    navBox.setAlignment(Pos.CENTER);
    this.giftInfoScreen.setBottom(navBox); 

    ScrollPane scrollPane = new ScrollPane(giftBox);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    scrollPane.setPadding(new Insets(10));

    this.giftInfoScreen.setCenter(scrollPane);

    Scene scene = new Scene(this.giftInfoScreen, 340, 500);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private VBox giftInfo() {
    userLabel.setText("By: " + gift.getUploader().getName());
    giftNameLabel.setText("Name: " + gift.getProductName());
    giftDescription.setText("Description: " + gift.getDescription());
    giftDescription.setWrapText(true);
    giftDescription.setMaxWidth(340);

    pointsLabel.setText(gift.getGiftPoints() + " POINTS");

    VBox infoBox = new VBox(15, userLabel,giftNameLabel, giftDescription, pointsLabel);
    return infoBox;
  }
  
  private Button addCartButton() {
    Button orderButton = new Button("ADD TO GIFTCART");
    orderButton.setOnAction(e -> {
        GiftCart.addToCart(gift);
        GiftPage newGiftPage = new GiftPage(primaryStage, user);
        newGiftPage.giftpage();
    });
    return orderButton;
  }
}
