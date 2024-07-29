package test;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccountPage implements NavigationButtons {
    private Stage primaryStage;
    private User appUser;
    private BorderPane manageAccount = new BorderPane();
    private TextField userName = new TextField();
    private Label userNameLabel = new Label();
    private TextField passField = new PasswordField();
    private Label passLabel = new Label();
    private TextField adressField = new TextField();
    private Label adressLabel = new Label();
    static Image defaultProfilePic = FoodImages.createImage("/basic_user_profile_picture.png");

    public AccountPage(Stage primStage, User loggedInUser) {
        this.primaryStage = primStage;
        this.appUser = loggedInUser;
    }

    // This creates the scene (the actual page) for the account page.
    public void accountPage() {
        manageAccount.setPadding(new Insets(20));

        VBox data = new VBox();
        data.getChildren().addAll(profilePicture(), username(), password(), adress()); // This adds the profilepicture, username, password and adress to the data VBox. 
        manageAccount.setCenter(data); // The VBox with the set arguments are presented in a vertical manner in the BorderPane.

        HBox navBox = new HBox(15, 
            homeButton(primaryStage, this.appUser), 
            addDishButton(primaryStage, this.appUser), 
            giftButton(primaryStage, this.appUser), 
            accountButton(primaryStage, this.appUser)
        );
        navBox.setAlignment(Pos.CENTER); // Center align the navigation buttons
        this.manageAccount.setBottom(navBox);  // The HomeButton, add dish button and the Gift button are added horizontally in the bottom center of the page.

        VBox signOutBox = new VBox(10, signOutButton()); // The Sign Out button is added to a Vertical Box (VBox).
        signOutBox.setAlignment(Pos.TOP_RIGHT);
        manageAccount.setTop(signOutBox); // The Sign Out button is set to the right corner of the page.

        Scene scene = new Scene(this.manageAccount, 340, 500); // A scene is created so that it displays a page with its elements.
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // This function shows the username in the account page.
    private HBox username() {
        this.userName.setText(this.appUser.getName()); // Set the editable TextField username to the username that is bound the current user of the app.
        this.userNameLabel.setText(this.appUser.getName()); // Set the username Label to the username that is bound the current user of the app.

        this.userNameLabel.setOnMouseClicked(e -> { // Define an action whenever the user clicks with the mouse on the username of the User. The followings happens when clicked:
            this.userNameLabel.setVisible(false); // The username Label is hidden.
            this.userName.setVisible(true); // The editable username TextField is now shown on the place of the Label.
            this.userName.requestFocus(); // Whenever the user clicks out of the box, the TextField is closed.
        }); // So basically, whenever the user clicks on his username, it "changes" into an editable textfield so that the user can edit his username.

        this.userName.setOnAction(e -> saveUsername()); // Setting up an Action Event listener on the userName TextField
        this.userName.focusedProperty().addListener((obs, oldVal, newVal) -> { // Set an listener that checks if the TextField has lost focus
            if (!newVal){ // obs is the observable value (username), the oldVal is the previous focussed state and newVale is the new focus state
                saveUsername(); // if the TextField has lost focus, then the saveUsername() function is called.
            }
        });

        HBox usernameBox = new HBox(10, new Label("Username:"), this.userNameLabel, this.userName);
        usernameBox.setAlignment(Pos.CENTER_LEFT);
        this.userName.setVisible(false);

        return usernameBox;
    }

    // This function shows the password in the account page.
    private HBox password() {
        this.passLabel.setText("*******"); // We don't want to show the real password, so we just show *******

        this.passLabel.setOnMouseClicked(e -> { // Similar function as the one of the username() but now for the password.
            this.passLabel.setVisible(false);
            this.passField.setVisible(true);
            this.passField.requestFocus();
        });

        this.passField.setOnAction(e -> savePassword());
        this.passField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal){
                savePassword();
            }
        });

        HBox passBox = new HBox(10, new Label("Password:"), this.passLabel, this.passField);
        passBox.setAlignment(Pos.CENTER_LEFT);
        this.passField.setVisible(false);

        return passBox;
    }

    /*
     * This function shows the adress in the account page.
     * This is implemented the same way as for the username and password. 
     */
    private HBox adress() {
        this.adressField.setText(this.appUser.getAdress());
        this.adressLabel.setText(this.appUser.getAdress());

        this.adressLabel.setOnMouseClicked(e -> {
            this.adressLabel.setVisible(false);
            this.adressField.setVisible(true);
            this.adressField.requestFocus();
        });

        this.adressField.setOnAction(e -> saveAdress());
        this.adressField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal){
                saveAdress();
            }
        });

        HBox adressBox = new HBox(10, new Label("Adress:"), this.adressLabel, this.adressField);
        adressBox.setAlignment(Pos.CENTER_LEFT);
        this.adressField.setVisible(false);
        return adressBox;
    }

    // This function saves the new username.
    private void saveUsername() {
        String newUsername = this.userName.getText(); // Retreive the text from the userName TextField.
        this.userNameLabel.setText(newUsername); // Set the retreived username now as the label so that the new username is presented.
        this.appUser.setName(newUsername); // Set the newly username of the user in the User ArrayList.
        this.userNameLabel.setVisible(true);
        this.userName.setVisible(false);
    }

    /*
     * This function saves the new password.
     * This is implemented in the same way as for the username in saveUsername(). 
     */
    private void savePassword() {
        String newPassword = this.passField.getText();
        this.passLabel.setText(newPassword);
        this.appUser.setPassword(newPassword);
        this.passLabel.setVisible(true);
        this.passField.setVisible(false);
        this.passLabel.setText("********");
    }

    /*
     * This function saves the new adress
     * This is implemented in the same way as for the username in saveUsername(). 
     */
    private void saveAdress() {
        String newAdress = this.adressField.getText();
        this.adressLabel.setText(newAdress);
        this.appUser.setAdress(newAdress);
        this.adressLabel.setVisible(true);
        this.adressField.setVisible(false);
    }

    // This function creates the sighn out button.
    private Button signOutButton() {
        Button signOut = new Button("Sign out"); // A new button with the text 'Sign out' is created.
        signOut.setOnAction(e -> { // Define an action for when the signOut button is pressed.
            LoginPage loggedOut = new LoginPage(primaryStage); // Create a new instance of the LoginPage class.
            loggedOut.LoginScreen(); // Show the login screen.
        });
        return signOut;
    }

    private VBox profilePicture() {
        Button addProfilePic = new Button(); // A new button is created.
        if (appUser.getProfilePicture() == null) { // If there doesn't exists a profile picture for the user, then
            appUser.setProfilePicture(defaultProfilePic); // set the profile picture to the default picture for that user.
        }
        ImageView profilePicture = new ImageView(appUser.getProfilePicture()); // Create an ImageView of the user's profile picture.
        // Set the size of the profile picture.
        profilePicture.setFitHeight(200);
        profilePicture.setFitWidth(200);
        profilePicture.setPreserveRatio(true);
        addProfilePic.setGraphic(profilePicture); // Set the profile picture as the button.
    
        // This is the code for what needs to happen if we click on the picture to change it:
        addProfilePic.setOnAction(e -> { // Define an action for the the button.
            appUser.setProfilePicture(FoodImages.chooseImage(primaryStage)); // Set the newly selected profile picture of the user in the ArrayList.
            ImageView newProfilePicture = new ImageView(appUser.getProfilePicture()); // Create an ImageView of the new profile picture.
            
            // Set the size of the new profile picture.
            newProfilePicture.setFitHeight(200);
            newProfilePicture.setFitWidth(200);
            newProfilePicture.setPreserveRatio(true);
            addProfilePic.setText(null);
            
            // Update the button.
            addProfilePic.setGraphic(newProfilePicture);
        });
        addProfilePic.setPrefSize(200, 200);
        VBox profPic = new VBox(15, addProfilePic);
        profPic.setAlignment(Pos.TOP_CENTER);
        return profPic;
    }
}
