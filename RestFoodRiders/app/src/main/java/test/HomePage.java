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

// This is implemented in a very simmilar way as the GiftPage class. 
public class HomePage implements NavigationButtons{
    private Stage primaryStage;
    private BorderPane homeRoot = new BorderPane();
    private GridPane grid = new GridPane();
    private User user;
    private String searchString;
    private Search search = new Search();

    public HomePage (Stage primStage, User appUser) {
        this.primaryStage = primStage;
        this.user = appUser;
        FoodImages.initializeMeals();
    }

    public void homepage() {
        homeRoot = new BorderPane();
        this.homeRoot.getChildren().clear();
        this.grid.getChildren().clear();

        this.homeRoot.setPadding(new Insets(20));
        createTopBar();

        meals();

        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane(this.grid);
        scrollPane.setFitToWidth(true);   

        this.homeRoot.setCenter(scrollPane);

        HBox navBox = new HBox(15, homeButton(primaryStage, this.user), addDishButton(primaryStage, this.user), giftButton(primaryStage, this.user), accountButton(primaryStage, this.user));
        navBox.setAlignment(Pos.CENTER);
        this.homeRoot.setBottom(navBox);

        Scene scene = new Scene(this.homeRoot, 340, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void meals() {
        int columnIndex = 0;
        int rowIndex = 0;

        for (Dish dish : DishDB.getDishDB()) {
            if(searchString == null || dish.getProductName().contains(searchString)){
                grid.add(FoodImages.createImageButton(FoodImages.createImageView(dish.getImage()), primaryStage, dish, user), columnIndex, rowIndex);  
            }
            columnIndex++;
            if (columnIndex == 2) {
                columnIndex = 0;
                rowIndex++;
            }
        }
    }

    private void createTopBar(){
        BorderPane topBar = new BorderPane();
        topBar.setPadding(new Insets(10));
        Button viewCartButton = new Button("Cart");
        viewCartButton.setOnAction(e -> viewCart());
        topBar.setRight(viewCartButton);
        topBar.setCenter(search.getSearchBox());
        search.getSearchButton().setOnAction(e -> {
            searchString = search.getTextField().getText();
            homepage();
        });
        homeRoot.setTop(topBar);
    }

    private void viewCart(){
        BorderPane cartView = new BorderPane();
        cartView.setCenter(Cart.cartScreen());
        Button orderCartButton = new Button("Order all");
        orderCartButton.setOnAction(e -> {
            Cart.orderCart();
            homepage();
        });
        Label priceLabel = new Label();
        priceLabel.textProperty().bind(Bindings.format("Total price: %.2f EUR", Cart.getTotalPrice()));
        HBox orderPane = new HBox(20, priceLabel, orderCartButton);
        orderPane.setAlignment(Pos.CENTER);
        cartView.setBottom(orderPane);
        homeRoot.setCenter(cartView);
    }
}
