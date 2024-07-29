package appfolder;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("RestFood Riders");

        LoginPage login = new LoginPage(this.primaryStage);
        login.LoginScreen();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
