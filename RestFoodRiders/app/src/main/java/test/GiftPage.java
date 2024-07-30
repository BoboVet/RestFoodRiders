package test;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GiftPage implements NavigationButtons{
    private Stage primaryStage;
    private BorderPane giftRoot = new BorderPane();
    private GridPane grid = new GridPane();
    private User user;
    private String searchString;
    private Search search = new Search();
    
    // Constructor for the GiftPage class.
    public GiftPage (Stage primStage, User appUser) {
      this.primaryStage = primStage;
      this.user = appUser;
      GiftImages.initializeGifts();
    }

    // Initializes and displays the gift page with its content.
    public void giftpage() {
        giftRoot = new BorderPane();
        this.giftRoot.getChildren().clear();
        this.grid.getChildren().clear();

        this.giftRoot.setPadding(new Insets(20));
        createTopBar(); // Create the top bar with search and cart button

        gifts(); // Populate the grid with gifts

        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane(this.grid);
        scrollPane.setFitToWidth(true);

        this.giftRoot.setCenter(scrollPane);

        HBox navBox = new HBox(15, homeButton(primaryStage, this.user), addDishButton(primaryStage, this.user), giftButton(primaryStage, this.user), accountButton(primaryStage, this.user));
        navBox.setAlignment(Pos.CENTER);
        this.giftRoot.setBottom(navBox);

        Scene scene = new Scene(this.giftRoot, 340, 500);
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    /*
     * Populates the grid with available gifts.
     * Filters gifts based on the search string if provided through the searchbar.
     */
    private void gifts() {
        int columnIndex = 0;
        int rowIndex = 0;

        for (Gift gift : GiftDB.getGiftDB()) {
            if(searchString == null || gift.getProductName().contains(searchString)){ 
                /*
                 * If there was nothing being searched, we show every gift in the giftPage.
                 * If something was being searched, only that gift is being showed.
                 */
                grid.add(GiftImages.createImageButton(GiftImages.createImageView(gift.getImage()), primaryStage, gift, user), columnIndex, rowIndex);  
            }
            columnIndex++;

            // This makes sure that there are 2 gifts displayed per row.
            if (columnIndex == 2) {
                columnIndex = 0;
                rowIndex++;
            }
        }
    }

    // Creates the top bar with a search box and a view cart button.
    private void createTopBar(){
        BorderPane topBar = new BorderPane();
        topBar.setPadding(new Insets(10));

        Button viewCartButton = new Button("GiftCart"); // Create a new Button with the text 'GiftCart'.
        viewCartButton.setOnAction(e -> viewCart()); // When pressed on the button, we show the cart.
        topBar.setRight(viewCartButton); // Set the viewCartButton on the right of the topBar.
        topBar.setCenter(search.getSearchBox()); // Set the searchbar in the center of the topBar.

        search.getSearchButton().setOnAction(e -> {
            searchString = search.getTextField().getText(); // Get the text from the searchbar when used.
            giftpage(); // Refresh the giftpage with the searched product.
        });
        giftRoot.setTop(topBar);
    }

    /*
     * Displays the gift cart with the option to order all items.
     * Also shows the total price of items in the cart.
     */
    private void viewCart(){
        BorderPane cartView = new BorderPane();
        cartView.setCenter(GiftCart.cartScreen());

        Button orderCartButton = new Button("Order all"); // Creates button with the text 'Order all'.
        orderCartButton.setOnAction(e -> {
            // Deletes all gifts in the cart and shows the giftpage again when pressed.
            GiftCart.orderCart();
            giftpage();
        });

        Label priceLabel = new Label();
        priceLabel.textProperty().bind(Bindings.format("Total price: %d points", GiftCart.getTotalPrice())); // Sets pricelabel to the total number of points in the cart.
        HBox orderPane = new HBox(20, priceLabel, orderCartButton);
        orderPane.setAlignment(Pos.CENTER);
        cartView.setBottom(orderPane);
        giftRoot.setCenter(cartView);
    }
}
