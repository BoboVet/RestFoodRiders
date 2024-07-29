package test;

import java.util.List;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FoodImages {
    private static List<String> initialMeals = new ArrayList<>(); // Create a list for all the meals.
    private static Boolean mealsInitialized = false; // Set a boolean to false for the initializeMeals() function.
    private static User alwaysAvailable = new User("Dishes", "password"); // Create a basic user for initial meals, user is not added to allUsers.
    private static Image image;

    public FoodImages() {
        if (initialMeals.isEmpty()) {
            initializeMeals();
        }
    }

    /*
     * Initializes the meals by adding default dishes to the database.
     * This method ensures that the meals are only initialized once.
     * It is synchronized to prevent multiple threads from initializing the meals simultaneously. 
     */
    public static void initializeMeals() {
        if (!mealsInitialized) { // Check if the meals have already been initialized.
            synchronized (FoodImages.class) { // Synchronize to prevent concurrent initialization.
                if (!mealsInitialized) {
                    if (initialMeals.isEmpty()) { // Check if the initalMeals list is empty.
                        // Add image paths of default dishes to the initial meals list.
                        initialMeals.add("/rendang.png");
                        initialMeals.add("/lasagna.png");
                        initialMeals.add("/fajitas.png");
                        initialMeals.add("/fried_rice.png");
                        initialMeals.add("/biryani.png");
                        initialMeals.add("/fried_chicken.png");
                        initialMeals.add("/AVG'tje.png");
                        initialMeals.add("/kebab.png");
                    }

                    /*
                     * Iterate through each imagepath in the initalMeals list.
                     * Create and edit a dish for each of the 8 imagepaths in the list and add them to the dishDB.
                     */
                    for (String imagepath : initialMeals) {
                        if (imagepath.equals("/rendang.png")) {
                            Dish rendang = new Dish(Tags.INDONESIAN, alwaysAvailable, createImage(imagepath), imagepath.substring(imagepath.lastIndexOf("/") + 1, imagepath.lastIndexOf(".")), 7.50);
                            rendang.setDescription("This is a very delicious, authentic Indonesian Rendang.");
                            rendang.setAmountPerson(2);
                            rendang.setIngredients("Coconut milk");
                            rendang.setIngredients("Beef rib steaks");
                            rendang.setIngredients("Cashew nuts");
                            rendang.setIngredients("Coconut cream");
                            rendang.setIngredients("Sambal badjak");
                            rendang.setIngredients("Ginger");
                            DishDB.addToDishDB(rendang);
                        }
                        else if (imagepath.equals("/lasagna.png")) {
                            Dish lasagna = new Dish(Tags.ITALIAN, alwaysAvailable, createImage(imagepath), imagepath.substring(imagepath.lastIndexOf("/") + 1, imagepath.lastIndexOf(".")), 10);
                            lasagna.setDescription("This is a authentic, Italian, delicious lasagna. It is made out of 6 amazing layers of pasta sheets, meat, and a secret sause.");
                            lasagna.setAmountPerson(1);
                            lasagna.setIngredients("Half-and-half minced meat");
                            lasagna.setIngredients("Tomatoes");
                            lasagna.setIngredients("Milk");
                            lasagna.setIngredients("Lasagna sheets");
                            lasagna.setIngredients("Cheese");
                            lasagna.setIngredients("Butter");
                            DishDB.addToDishDB(lasagna);
                        }
                        else if (imagepath.equals("/fajitas.png")) {
                            Dish fajitas = new Dish(Tags.MEXICAN, alwaysAvailable, createImage(imagepath), imagepath.substring(imagepath.lastIndexOf("/") + 1, imagepath.lastIndexOf(".")), 5.50);
                            fajitas.setDescription("This delicious dish brings the Mexican food vibe straight to your home. This delicious dish will surely leave you stunned!");
                            fajitas.setAmountPerson(1);
                            fajitas.setIngredients("Fajita seasoning mix");
                            fajitas.setIngredients("Grilled vegetables");
                            fajitas.setIngredients("Sour cream");
                            fajitas.setIngredients("Whole wheat tortilla wraps");
                            fajitas.setIngredients("Baby romaine lettuce");
                            fajitas.setIngredients("Turkey breast");
                            DishDB.addToDishDB(fajitas);
                        }
                        else if (imagepath.equals("/fried_rice.png")) {
                            Dish friedRice = new Dish(Tags.ASIAN, alwaysAvailable, createImage(imagepath), "Fried rice", 7.50);
                            friedRice.setDescription("Amazing fried rice.");
                            friedRice.setAmountPerson(1);
                            friedRice.setIngredients("Eggs");
                            friedRice.setIngredients("Carrots");
                            friedRice.setIngredients("Rice");
                            friedRice.setIngredients("Peas");
                            friedRice.setIngredients("Corn");
                            DishDB.addToDishDB(friedRice);
                        }
                        else if (imagepath.equals("/biryani.png")) {
                            Dish biryani = new Dish(Tags.INDIAN, alwaysAvailable, createImage(imagepath), "Biryani", 12.50);
                            biryani.setDescription("Delicious Indian Hyderabadi biryani");
                            biryani.setAmountPerson(1);
                            biryani.setIngredients("Basmati Rice");
                            biryani.setIngredients("Chicken");
                            biryani.setIngredients("Curd");
                            biryani.setIngredients("Saffron (Indian spice for the yellow color)");
                            biryani.setIngredients("Biryani Masala");
                            DishDB.addToDishDB(biryani);
                        }
                        else if (imagepath.equals("/fried_chicken.png")) {
                            Dish friedChicken = new Dish(Tags.AMERICAN, alwaysAvailable, createImage(imagepath), "American fried chicken", 10);
                            friedChicken.setDescription("Delicious KFC-style fried chicken, for 2 persons!");
                            friedChicken.setAmountPerson(2);
                            friedChicken.setIngredients("Chicken");
                            friedChicken.setIngredients("All-Purpose Flour");
                            friedChicken.setIngredients("Milk");
                            friedChicken.setIngredients("Salt and Pepper");
                            DishDB.addToDishDB(friedChicken);                           
                        }
                        else if (imagepath.equals("/AVG'tje.png")) {
                            Dish avg = new Dish(Tags.DUTCH, alwaysAvailable, createImage(imagepath), "AVG", 4.50);
                            avg.setDescription("Typical Dutch AVG'tje");
                            avg.setAmountPerson(1);
                            avg.setIngredients("Potatoes");
                            avg.setIngredients("Beef");
                            avg.setIngredients("Green beans");
                            DishDB.addToDishDB(avg);
                        }
                        else if (imagepath.equals("/kebab.png")) {
                            Dish kebab = new Dish(Tags.MIDLLE_EASTERN, alwaysAvailable, createImage(imagepath), "Kebab", 5);
                            kebab.setDescription("Beef kebab");
                            kebab.setAmountPerson(1);
                            kebab.setIngredients("Beef");
                            kebab.setIngredients("Onion");
                            kebab.setIngredients("Bell pepper");
                            kebab.setIngredients("Cucumber");
                            kebab.setIngredients("Eggplant");
                            DishDB.addToDishDB(kebab);
                        }
                    }
                    mealsInitialized = true; // After initializing, set the boolean to true.
                }
            }
        }
    }

    // Creates an Image object from the given image path.
    public static Image createImage(String imagepath) {
        return new Image(FoodImages.class.getResourceAsStream(imagepath)); 
    }

    /*
     * Create an ImageView for the given image.
     * Set the fit widt and height of the ImageView.
     */
    public static ImageView createImageView(Image dishImage) {
        ImageView imageView = new ImageView(dishImage); 
        
        imageView.setFitWidth(100); 
        imageView.setFitHeight(100); 
        return imageView;
    }

    /*
     * Creates a button with the imageview as its graphic.
     * Sets button pref size to 100x100 pixels.
     * When clicked, it creates an instance of DishInfo,
     * and goes to the dishInfo screen.
     */
    public static Button createImageButton(ImageView imageview, Stage primStage, Dish dish, User user) {
        Button mealButton = new Button();
        mealButton.setGraphic(imageview);
        mealButton.setPrefSize(100, 100);
        mealButton.setOnAction(e -> {
            DishInfo dishInfo = new DishInfo(primStage, dish, user);
            dishInfo.dishInfoScreen();
        });
        return mealButton;
    }

    /*
     * This function opens a fileschooser popup so that the user can
     * select his own image, the function then returns an image of that
     * selected image.
     */
    public static Image chooseImage(Stage primaryStage){
        FileChooser fileChooser = new FileChooser(); // Create an instance of FileChooser. 
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile == null) {
            // If no file is selected, an alert is displayed and a default profile picture is returned.
            try {
                image = new Image(AccountPage.defaultProfilePic.toString());
            } catch (Exception e) {
                Alert noFileSelected = new Alert(AlertType.ERROR);
                noFileSelected.setTitle("No File Selected");
                noFileSelected.setContentText("Please select an image file");
                noFileSelected.showAndWait();
            }
        }
        else {
            // If a file is selected, an Image object is created from the selected file.
            image = new Image(selectedFile.toURI().toString());
        }
        return image;
    }
}
