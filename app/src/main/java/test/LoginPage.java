package test;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * The LoginPage class represents the login screen of the app.
 * It provides fields for the user to enter their username and password,
 * and buttons to log in or register a new account.
 * If the login credentials are valid, then they are redirected to the HomePage.
 */
public class LoginPage {
    private String username;
    private String password;
    private Stage primaryStage;
    private BorderPane loginRoot;
    private TextField loginUsername = new TextField();
    private TextField loginPassword = new PasswordField();
    
    public LoginPage (Stage primStage) {
        this.primaryStage = primStage;
    }

    // Initializes and displays the login screen.
    public void LoginScreen(){
        loginRoot = new BorderPane();
        loginRoot.setPadding(new Insets(20));

        VBox topContent = new VBox();
        topContent.setAlignment(Pos.CENTER);
        
        // Set 'RESTFOOD RIDERS' at the top of the page.
        Text logoName = new Text("RESTFOOD RIDERS");
        logoName.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        // Set the app logo beneath the app name.
        Image logo = new Image(getClass().getResourceAsStream("/RestFoodRiderslogo.png"));
        ImageView imageview = new ImageView(logo);
        imageview.setFitWidth(200);
        imageview.setPreserveRatio(true);

        topContent.getChildren().addAll(logoName, imageview); // Add logoName and app logo to the topContent VBox.

        loginRoot.setTop(topContent); // Set topContent at the top of the BorderPane.

        // Creates the center of the login page with the username and password TextFields and the login and register button.        
        VBox centerloginField = new VBox(10, loginUser(), loginPassword(), loginButton(), registerButton());
        centerloginField.setAlignment(Pos.CENTER);
        loginRoot.setCenter(centerloginField);

        Scene scene = new Scene(loginRoot, 340, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TextField loginUser() {
        this.loginUsername.setPromptText("Please enter your username"); // Returns a TextField for entering the username.
        return this.loginUsername;
    }

    private TextField loginPassword() {
        this.loginPassword.setPromptText("Please enter your password"); // Returns a TextField for entering the password.
        return this.loginPassword;
    }

    /*
     * Creates and returns a Button for logging in.
     * The button checks the entered credentials and navigates to the home page if the credentials are valid
     * and a user with the same credentials has been found in the User List,
     * otherwise it shows an error alert.
     */
    private Button loginButton() {
        Button login = new Button("Login");
        login.setOnAction(e -> {
            this.username = loginUsername.getText();
            this.password = loginPassword.getText();
            if ((this.username.isEmpty()) || (this.password.isEmpty()) ){
                // Shows an 
                Alert invalidLoginCredential = new Alert(AlertType.ERROR);
                invalidLoginCredential.setTitle("Invalid login credentials");
                invalidLoginCredential.setHeaderText(null);
                invalidLoginCredential.setContentText("Login and Password fields are empty");
                invalidLoginCredential.showAndWait();
            }
            else if ((UserDB.userFound(username, password))) {
                    HomePage home = new HomePage(primaryStage, UserDB.foundUserInfo(username, password));
                    home.homepage();
                }
                else {
                    Alert userNotFound = new Alert(AlertType.ERROR);
                    userNotFound.setTitle("User doesn't exists");
                    userNotFound.setContentText("No user with this information exists. \n Please enter your correct login credentials or make an account.");
                    userNotFound.showAndWait();
                }
            }
        );
        return login;
    }

    //  Creates and returns a Button for navigating to the registration screen.
    private Button registerButton() {
        Button register = new Button("Don't have an account? Register here");
        register.setOnAction(e -> {
            RegisterPage registerPage = new RegisterPage(primaryStage);
            registerPage.registerScreen();
        });
        return register;
    }
}
