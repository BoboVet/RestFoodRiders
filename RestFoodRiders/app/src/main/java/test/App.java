package test;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Food Riders");

        LoginPage login = new LoginPage(this.primaryStage);
        login.LoginScreen();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
