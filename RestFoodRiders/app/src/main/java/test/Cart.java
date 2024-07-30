package test;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import java.util.List;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


public class Cart {
    private static List<Dish> cartList = new ArrayList<>(); // Create a list to store dishes in the cart.
    private static GridPane cartDisplay = new GridPane();
    private static DoubleProperty totalPrice = new SimpleDoubleProperty(0);

    public static ScrollPane cartScreen() {
        cartDisplay.getChildren().clear(); // Clear previous contents of the cart display.

        int row = 0;
        // Add each dish and its remove button to the cart display.
        for (Dish dish : cartList){
            cartDisplay.add(dish.productView(), 0, row++);
            cartDisplay.add(removeButton(dish), 0, row++);
        }

        ScrollPane cartPane = new ScrollPane(cartDisplay); // Create a ScrollPane with the cart display.
        return cartPane;
    }

    // Add a dish to the cart and removes it from the database.
    public static void addToCart(Dish dish) {
        cartList.add(dish); // Add the dish to the cart list.
        DishDB.getDishDB().remove(dish); // Remove the dish from the database.
    }

    // Get the total price of all items in the cart.
    public static DoubleProperty getTotalPrice(){
        totalPrice.bind(getSum(0)); // Bind the total price property to the sum of dish prices.
        return totalPrice;
    }

    // Recursively calculates the sum of dish prices starting from the specified index.
    private static DoubleProperty getSum(int index){
        if(cartList.size() == 0){
            return new SimpleDoubleProperty(0);  // Return 0 if the cart is empty.
        }
        if (index >= cartList.size() - 1){
            return new SimpleDoubleProperty(cartList.get(index).getPrice()); // Return the price of the last dish.
        }
        else{
            DoubleProperty sum = new SimpleDoubleProperty();
            sum.bind(getSum(index + 1).add(cartList.get(index).getPrice()));  // Recursively calculate the sum.
            return sum;
        }
    }

    // Clears the cart when an order is placed.
    public static void orderCart(){
        cartList.clear(); // Clear the cart.
    }

    // Removes a dish from the cart and adds it back to the database.
    public static void removeFromCart(Dish dish){
        DishDB.getDishDB().add(dish); 
        cartList.remove(dish); 
        cartScreen();
    }

    // Creates a remove button for the specified dish.
    public static Button removeButton(Dish dish){
        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            removeFromCart(dish); // Remove the dish when the button is clicked.
            getTotalPrice(); // Update the total price.
        });
        return removeButton;
    }
}
