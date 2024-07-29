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

public class RegisterPage {
    private Stage primaryStage;
    private User user;
    private TextField usernameField = new TextField();
    private String username;
    private TextField passwordField = new PasswordField();
    private String password;
    private TextField confirmPassField = new PasswordField();
    private String confirmPassword;
    private TextField adressField = new TextField();
    private String adres;

    public RegisterPage (Stage primstage) {
        this.primaryStage = primstage;
    }

    // Displays the registration screen.
    public void registerScreen() {
        // Creating the main plane.
        BorderPane registerRoot = new BorderPane();
        registerRoot.setPadding(new Insets(20));

        VBox topContent = new VBox();
        topContent.setAlignment(Pos.CENTER);

        // Set welcome text in different font and size.
        Text welcomeText = new Text("Welcome new user!");
        welcomeText.setFont(Font.font("Arial", FontWeight.MEDIUM, 18));
        
        // Set logo name in different font and size.
        Text logoName = new Text("RESTFOOD RIDERS");
        logoName.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Create ImageView of the app and resizes it. 
        Image logo = new Image(getClass().getResourceAsStream("/RestFoodRiderslogo.png"));
        ImageView imageview = new ImageView(logo);
        imageview.setFitWidth(200);
        imageview.setPreserveRatio(true);

        // Add welcome text, logo name and logo image to VBox topContent.
        topContent.getChildren().addAll(welcomeText, logoName, imageview);

        registerRoot.setTop(topContent); // Set topContent at the top of registerRoot.
        
        // Set 4 fields for username, password, confirm password and adress beneath each other respectively in VBox centerRegisterField.
        VBox centerRegisterField = new VBox(10, usernameTextField(), passwordTextField(), confirmPassField(), adressTextField());
        centerRegisterField.setAlignment(Pos.CENTER);
        registerRoot.setCenter(centerRegisterField); // Set centerRegisterField at the center of RegisterRoot.

        // Set register button and already have an account button beneath each other respectively in a VBox registerButtonField and sets it at the bottom of registerRoot.
        VBox registerButtonField = new VBox(10, registerButton(), alreadyHaveAnAccButton());
        registerButtonField.setAlignment(Pos.BOTTOM_CENTER);
        registerRoot.setBottom(registerButtonField);

        Scene scene = new Scene(registerRoot, 340, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TextField usernameTextField() {
        this.usernameField.setPromptText("Please enter a username");
        return this.usernameField; // Returns a TextField for entering the username.
    }

    private TextField passwordTextField() {
        this.passwordField.setPromptText("Please enter a password");
        return this.passwordField; // Returns a TextField for entering the password.
    }

    private TextField confirmPassField() {
        this.confirmPassField.setPromptText("Please enter your password again");
        return this.confirmPassField; // Returns a TextField for entering the password again as confirmation.
    } 

    private TextField adressTextField() {
        this.adressField.setPromptText("Please enter your addres here");
        return this.adressField; // Returns a TextField for entering the adress.
    }

    // Creates and returns a Button for registering the user.
    private Button registerButton() {
        Button register = new Button("Register");
        register.setOnAction(e -> {
            // Saves all the inputs from the user.
            this.username = usernameTextField().getText();
            this.password = passwordTextField().getText();
            this.confirmPassword = confirmPassField().getText();
            this.adres = adressTextField().getText();
            // Calls the function to check the inputs.
            errorMessage();
        });
        return register;
    }
    
    // Creates and returns a Button for redirecting to the login page.
    private Button alreadyHaveAnAccButton() {
        Button alreadyHaveAnAcc = new Button("Already have an account? Click here to Login");
        alreadyHaveAnAcc.setOnAction(e -> {
            LoginPage login = new LoginPage(primaryStage);
            login.LoginScreen();
        });
        return alreadyHaveAnAcc;
    }

    // Displays an error message if there are any invalid registration credentials.
    private Alert errorMessage() {
        // Creates an error pop up.
        Alert invalidLoginCredential = new Alert(AlertType.ERROR);
        invalidLoginCredential.setTitle("Invalid register credentials");
        invalidLoginCredential.setHeaderText(null);

        if (this.username.isEmpty()) {
            // Sets the content of the error popup to 'No username is given' when no username is undefined.
            invalidLoginCredential.setContentText("No username is given.");
        }
        else if (this.password.isEmpty()) {
            // Sets the content of the error popup to 'No password is given' when password is undefined.
            invalidLoginCredential.setContentText("No password is given.");
        }
        else if (confirmPassword.isEmpty() || !(this.password.equals(this.confirmPassword))) {
            // Sets the content of the error popup to 'Password could not be confirmed.' when password is undefined or not the same as confirm password.
            invalidLoginCredential.setContentText("Password could not be confirmed.");
        }
        else if (this.adres.isEmpty()) {
            // Sets the content of the error popup to 'No adress is given' when adress is undefined.
            invalidLoginCredential.setContentText("No adress is given.");
        }
        else {
            registerUser();
            return null; // returns no error message if everything is valid.
        }
        invalidLoginCredential.showAndWait(); // Shows error message.
        return invalidLoginCredential;
    }

    private void registerUser() {
        this.user = new User(username, password); // Creates a new user.
        UserDB.registerDataBase(user); // Adds user to the User list.
        this.user.setAdress(adres); // Sets the adress of the user in the list.
        HomePage newHomePage = new HomePage(primaryStage, this.user);
        newHomePage.homepage(); // Redirects to the home page.
    }
}
