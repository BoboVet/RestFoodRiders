package appfolder;

import java.text.DecimalFormat;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class DishInfo implements NavigationButtons {
    private Stage primaryStage;
    private Dish dish;
    private User user;
    private BorderPane dishInfoScreen = new BorderPane();
    private Label userLabel = new Label();
    private Label personNo = new Label(); 
    private Label dishNameLabel = new Label();
    private Label tagsLabel = new Label();
    private Label priceLabel = new Label();
 
    public DishInfo (Stage primStage, Dish dish, User user) {
        this.primaryStage = primStage;
        this.dish = dish;
        this.user = user;
    }

    public void dishInfoScreen() {
        dishInfoScreen.setPadding(new Insets(20));

        // Create an ImageView of the dish and set its size.
        ImageView dishimage = FoodImages.createImageView(dish.getImage());
        dishimage.setFitHeight(200);
        dishimage.setFitWidth(200);

        // Add the dishimage, dishInfoImageBox, dishInfo(), addCartButton() vertically underneath each other.
        VBox dishBox = new VBox(5, dishimage, dishInfo(), addCartButton());
        dishBox.setAlignment(Pos.TOP_CENTER);

        // Set the navigation buttons just like the other pages that implement this.
        HBox navBox = new HBox(15, 
            homeButton(primaryStage, user), 
            addDishButton(primaryStage, user), 
            giftButton(primaryStage, user), 
            accountButton(primaryStage, user)
        );
        navBox.setAlignment(Pos.CENTER);
        this.dishInfoScreen.setBottom(navBox);

        /*
         * Create a ScrollPane and set its size equal to the size of its content.
         * This ensures that the content will expand to fill the available size of the page.
         */
        ScrollPane scrollPane = new ScrollPane(dishBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPadding(new Insets(10));

        this.dishInfoScreen.setCenter(scrollPane);

        // A scene is created so that it displays a page with its elements.
        Scene scene = new Scene(this.dishInfoScreen, 340, 500);
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    // This function returns a VBox that contains the information about dish.
    private VBox dishInfo() {
        userLabel.setText("By: " + dish.getUploader().getName()); // Set the userlabel to the name of the user.
        personNo.setText("Persons: " + dish.getAmountPerson()); // Set the personNo label to the amount of persons that the dish suffices for.
        dishNameLabel.setText("Name: " + dish.getProductName()); // Set the DishNameLabel to the name of the dish.
        tagsLabel.setText("Tag: " + dish.getTagsAsString()); // Set the tagsLabel to the tag of the dish.

        // Set a TextFlow for the description so that even long descriptions are displayed. 
        TextFlow mealDescription = new TextFlow(new Text("Description: \n" + dish.getDescription()));
        mealDescription.setMaxWidth(340);
        mealDescription.setPrefWidth(340);

        // Format the price of the dish to a two decimal number like 7.50
        DecimalFormat df = new DecimalFormat("0.00");
        priceLabel.setText(df.format(dish.getPrice()) + " EUR");

        // Create a VBox for all the information
        VBox infoBox = new VBox(15, userLabel, personNo, dishNameLabel, tagsLabel, mealDescription, dishIngredients(), priceLabel);
        return infoBox;
    }

    // Create a VBox to showcase all the ingredients of the dish.
    private VBox dishIngredients() {
        VBox dishIngredientInfo = new VBox();

        // For every ingredient in the dish, create a new Label and add it to the dishIngredientInfo VBox.
        for (String ingredient : dish.getIngredients()) {
            Label ingredientLabel = new Label("Main ingredient: " + ingredient);
            dishIngredientInfo.getChildren().add(ingredientLabel);
        }

        return dishIngredientInfo;
    }

    // Creates a button to add a dish to the cart.
    private Button addCartButton() {
        Button orderButton = new Button("ADD TO CART"); // Create a button with the text 'ADD TO CART'.
        orderButton.setOnAction(e -> {
            // If the button is pressed, add the dish to the cart and go back to the homepage.
            Cart.addToCart(dish);
            HomePage newHomePage = new HomePage(primaryStage, user);
            newHomePage.homepage(); 
        });
        return orderButton;
    }
}
