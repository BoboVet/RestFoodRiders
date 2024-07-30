package test;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class AddDish implements NavigationButtons {
    private User uploader;
    private Stage primaryStage;
    private Tags tag;
    private double price = 0;
    private ComboBox<String> tagsComboBox = new ComboBox<>();
    private ImageView dishImageView = new ImageView();
    private TextField mealsDesc = new TextField();
    private TextField personField = new TextField();
    private TextField dishNameField = new TextField();
    private TextField priceField = new TextField();
    private GridPane ingredientsGrid = new GridPane();

    public AddDish(Stage primstage, User uploadUser) {
        this.uploader = uploadUser;
        this.primaryStage = primstage;
    }

    // This function creates a page with the content of the add dish page.
    public void addDishScreen() {
        // Create the main layout components.
        BorderPane borderPane = new BorderPane();
        VBox root = new VBox(10);

        // Set padding and alignment for the root VBox.
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        // Add all the components and content of the page to the root VBox.
        root.getChildren().addAll(
            DishImage(), 
            tagBox(), 
            ingriedientsPane(), 
            mealDescription(), 
            personNo(), 
            dishNameTextField(), 
            dishPriceField(), 
            addDishbutton()
        );
        borderPane.setTop(root);

        // Setting the navigation buttons at the bottom of the page.
        HBox navBox = new HBox(15, 
            homeButton(primaryStage, this.uploader), 
            giftButton(primaryStage, this.uploader), 
            accountButton(primaryStage, this.uploader)
        );
        navBox.setAlignment(Pos.CENTER);
        borderPane.setBottom(navBox);

        // Set the scene and show the stage.
        Scene scene = new Scene(borderPane, 340, 500);
        primaryStage.setTitle("Dish App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private StackPane DishImage() {
        // Set the size of the imageview of the dish.
        dishImageView.setFitWidth(170);
        dishImageView.setFitHeight(150);
        dishImageView.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-alignment: center;");
        
        // Create an StackPane for with the dish imageview and the selectImage() button.
        StackPane imageStack = new StackPane(dishImageView, selectImage());
        imageStack.setAlignment(Pos.CENTER);
        return imageStack;
    }

    private Button selectImage() {
        Button imagePlaceholder = new Button("Add a picture of your dish"); // Create a button with the given text.

        // Define the action that needs to be done when the button is clicked.
        imagePlaceholder.setOnAction(e -> {
            Image selectImage = FoodImages.chooseImage(primaryStage); // Create an image of the selceted image.
            if (selectImage != null) { 
                // If an image is actually selected:  
                dishImageView = FoodImages.createImageView(selectImage); // Create an imageview of that image.
                imagePlaceholder.setText(null); // Delete the text of the button.
                imagePlaceholder.setGraphic(dishImageView); // Set the button to show the dishimage.
            }
        });
        imagePlaceholder.setMaxWidth(dishImageView.getFitWidth());
        imagePlaceholder.setAlignment(Pos.CENTER);
        return imagePlaceholder;
    }

    // This function creats the tags section.
    private HBox tagBox() {
        HBox tagsBox = new HBox(10);
        tagsBox.setAlignment(Pos.CENTER_LEFT);

        Label tagsLabel = new Label("Tags:");
        tagsComboBox.getItems().addAll(Tags.getValues()); // Add all the Tags to the ComboBox.
        tagsComboBox.setPromptText("Tags");
        tagsComboBox.setOnAction(e -> {
            // The action that has to be done for the ComboBox:
            String selectedTag = tagsComboBox.getValue(); // Get the selected Tag.
            if (selectedTag != null) {
                // If the Tag isn't null, set the tag to the selected tag.
                tag = Tags.valueOf(selectedTag);
            }
        });
        tagsBox.getChildren().addAll(tagsLabel, tagsComboBox);
        return tagsBox;
    }

    // This function sets the 6 Ingredient TextFields.
    private GridPane ingriedientsPane() {
        ingredientsGrid.setAlignment(Pos.CENTER_LEFT); // Set the ingredientsGrid to the left center of the page.
        ingredientsGrid.setHgap(5); // Set the horizontal gap between the TextFields to 5.
        ingredientsGrid.setVgap(5); // Set the vertical gap between the TextFields to 5.

        int columnIndex = 0;
        int rowIndex = 0;

        for (int i = 0; i < 6; i++) {
            // Create 6 TextFields,  set a temporary text of each field to 'Ingredient' and set the width of each TextField to 150.
            TextField ingredientField = new TextField();
            ingredientField.setPromptText("Ingredient");
            ingredientField.setPrefWidth(150);

            // Add each TextField to the grid with the given coordinates.
            ingredientsGrid.add(ingredientField, columnIndex, rowIndex);
            columnIndex++;

            // If the columnIndex is 3, reset it to 0 and go to the next row.
            if (columnIndex == 3) {
                columnIndex = 0;
                rowIndex++;
            }
        }
        return ingredientsGrid;
    }

    // Set the text field for the dish name.
    private TextField dishNameTextField() {
        this.dishNameField.setPromptText("Please enter the name of your dish");
        return this.dishNameField;
    }

    // Set the text field for the dish description.
    private TextField mealDescription() {
        this.mealsDesc.setPromptText("Here you can provide a short description of your meal");
        return this.mealsDesc;
    }

    // Set the text field for the amount of persons the meal suffices.
    private TextField personNo() {
        this.personField.setPromptText("Kindly specify for how many persons this meal suffices");
        return this.personField;
    }

    // Set the text field for the dish price.
    private TextField dishPriceField() {
        this.priceField.setPromptText("Please give a price for your dish");
        return this.priceField;
    }

    // Create the add dish button.
    private Button addDishbutton() {
        Button addDishButton = new Button("Add dish"); // Create a button with the text 'Add dish'.
        addDishButton.setPrefWidth(200); // Set the widt of the button.
        addDishButton.setStyle("-fx-background-color: black; -fx-text-fill: white;"); // Set the button background color to black, and the text color to white.
        addDishButton.setOnAction(e -> {
            buttonAction(); // Call the buttonAction() function when the addDishButton is pressed.
        });
        return addDishButton;
    }

    /*
     * Performs the action when the button is clicked. It validates the input fields and 
     * displays error messages if necessary. If all input is valid, it creates a new Dish
     * object with the provided information and adds it to the database. Then, it navigates
     * back to the home page.  
     */
    private void buttonAction() {
        if (tagsComboBox.getValue() == null) {
            // Show an error message if no Tag was selected for the dish.
            Alert tagError = new Alert(AlertType.ERROR);
            tagError.setTitle("No Tags selected");
            tagError.setContentText("Please select a tag for your dish.");
            tagError.showAndWait();
        }
        else if (dishNameTextField().getText() == null) {
            // Show an error message if there was no name given for the dish.
            Alert tagError = new Alert(AlertType.ERROR);
            tagError.setTitle("Meal has no name");
            tagError.setContentText("Please give the name of your dish.");
            tagError.showAndWait();
        }
        else if (!validPrice()) { 
            // Show an error message based on the input for the price.
            Alert priceError = new Alert(AlertType.ERROR);
            priceError.setTitle("Invalid price");
            priceError.setContentText("Please enter your price as numbers. \n The price must be 0 or higher.");
            priceError.showAndWait();
        }
        else if (!validPersonNo()) {
            // Show an error message based on the input for the amount of persons.
            Alert personNoError = new Alert(AlertType.ERROR);
            personNoError.setTitle("Invalid Number of Persons give");
            personNoError.setContentText("Please enter the amount of persons that this meal suffices for, as a number that is 1 or higher. \n The number can be a maximum of 4 digits");
            personNoError.showAndWait();
        }
        else {
            // Create a new Dish object with the provided arguments and add the extra information.
            Dish newDish = new Dish(tag, uploader, dishImageView.getImage(), dishNameTextField().getText(), price);
            newDish.setDescription(mealDescription().getText());
            newDish.setAmountPerson(Integer.valueOf(this.personField.getText()));

            // Loop through ingredients and add them to the Dish object.
            for (Node node : ingredientsGrid.getChildren()) {
                if (node instanceof TextField) {
                    TextField mediator = (TextField) node;
                    newDish.setIngredients(mediator.getText());
                }
            }
            DishDB.addToDishDB(newDish); // Add the new dish to the database.
            
            // Navigate back to the home page.
            HomePage homePage = new HomePage(primaryStage, uploader);
            homePage.homepage();
        }
    }

    // This boolean checks if the amount of person that is given is an Integer greater than 0 and has a maximum of 4 digits.
    private Boolean validPersonNo() {
        String personNoText = personField.getText();
        if ((personNoText != null && personNoText.matches("[1-9]\\d{0,3}")) && Integer.valueOf(this.personField.getText()) > 0) {
            return true;
        }
        return false;
    }

    // This boolean checks if the price greater than 0 by trying to parse the input as a Double.
    private Boolean validPrice() {
        Boolean succeed = false;
        String priceText = priceField.getText();
        try {
            price = Double.parseDouble(priceText);
            if (price < 0) {
                succeed = false;
            }
            else {
                succeed = true;
            }
        } catch (NumberFormatException e) {
            succeed = false;
        }
        return succeed;
    }
}
