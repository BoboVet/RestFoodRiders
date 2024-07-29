package appfolder;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class DishDB extends Dish {
    private static ArrayList<Dish> dishDB; // List to store all the dishes in a list.

    public DishDB(Tags foodTag, User upUser, Image dishImage, String name, double price) {
        super(foodTag, upUser, dishImage, name, price);
        if (dishDB == null) {
            dishDB = new ArrayList<>();
        }        
    }

    public static void addToDishDB(Dish dish) {
        if (dishDB == null) {
            // Create a new ArrayList if the dishDB list is undefined.
            dishDB = new ArrayList<>();
        }
        // Add dish to dishDB.
        dishDB.add(dish);
    }
    
    public static ArrayList<Dish> getDishDB() {
        return dishDB; // return the dishDB ArrayList.
    }
}
