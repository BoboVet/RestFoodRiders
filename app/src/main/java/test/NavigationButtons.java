package test;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public interface NavigationButtons {

    /*
     * This interface lists the default implementations for the navigation buttons
     * at the bottom of most of the pages of the app. 
     * This provides the default implementations for the home, add dish, gift and account button. 
    */

    // Creates the Home button and navigates to the homepage when clicked.
    default Button homeButton(Stage primaryStage, User appUser) {
        Button homeButton = new Button("Home");
        homeButton.setOnAction(e -> {
            HomePage newHomePage = new HomePage(primaryStage, appUser);
            newHomePage.homepage();
        });
        return homeButton;
    };

    // Creates the add dish button with a '+' as image, and navigates to the addDish page when clicked.
    default Button addDishButton(Stage primaryStage, User appUser) {
        Button addDishButton = new Button();
        Image addIconImage = new Image(getClass().getResourceAsStream("/add_icon.png"));
        ImageView addIcon = new ImageView(addIconImage);
        addIcon.setFitWidth(20);
        addIcon.setFitHeight(20);
        addDishButton.setGraphic(addIcon);
        addDishButton.setOnAction(e -> {
            AddDish addDish = new AddDish(primaryStage, appUser);
            addDish.addDishScreen();
        });
        return addDishButton;
    };
    
    // Creates the account button and navigates to the accountpage when clicked.
    default  Button accountButton(Stage primaryStage, User appUser) {
        Button accountButton = new Button("My Account");
        accountButton.setOnAction(e -> {
            AccountPage account = new AccountPage(primaryStage, appUser);
            account.accountPage();
        });
        return accountButton;
    };

    // Creates the gift button and navigates to the giftpage when clicked.
    default Button giftButton(Stage primaryStage, User appUser) {
        Button giftButton = new Button("Gifts");
        giftButton.setOnAction(e -> {
            GiftPage gifts = new GiftPage (primaryStage, appUser);
            gifts.giftpage();
        });
        return giftButton;
    }
}
