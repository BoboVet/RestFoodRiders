package test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.image.Image;

public class Dish extends Product{
    private Tags tag;
    private User uploadedUser;
    private String mealDescription;
    private int amountPerson;
    private List<String> ingredientsList = new ArrayList<>();
    
    // Create a Dish object.
    public Dish(Tags foodTag, User upUser, Image dishImage, String name, double price) {
        super(name, dishImage, price);
        this.tag = foodTag;
        this.uploadedUser = upUser;
    }

    public void setDescription(String mealDescr) {
        this.mealDescription = mealDescr; // Set the dish description of the dish.
    }

    public String getDescription() {
        if (this.mealDescription == null) {
            this.mealDescription = "No description was given"; // If there was no mealdescription given, set a basic value for the description.
        }
        return this.mealDescription;
    }

    public void setAmountPerson(int amount) {
        this.amountPerson = amount; // Set the amount of persons of the dish.
    }

    public int getAmountPerson() {
        return this.amountPerson; // Get the amount of persons of the dish.
    }

    public User getUploader() {
        return this.uploadedUser; // Get the uploader of the dish.
    }

    public Tags getTag() {
        return this.tag; // Get the tag of the dish.
    }

    // Returns the Tags of the dish as a string.
    public String getTagsAsString() {
        return List.of(tag).
                stream().
                map(Tags::toString).
                collect(Collectors.
                joining(", ")
                );
    }

    public void setIngredients(String ingredient) {
        ingredientsList.add(ingredient); // Add a ingredient (string) to the ingredients list.
    }

    public List<String> getIngredients() {
        return this.ingredientsList; // return the ingredient list.
    }
}
