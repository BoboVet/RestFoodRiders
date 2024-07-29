package test;

import javafx.scene.image.Image;

// The User class represents a user of the application.
public class User {
    private String name;
    private String password;
    private String adress;
    private Image profilePicture;

    // Constructs a new User object with the specified name and password.
    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String getName(){
        return this.name; // Retrieves the name of the user.
    }

    public String getPassword(){
        return password; // Retrieves the password of the user.
    }

    public void setName(String name){
        this.name = name; // Sets the name of the user.
    }

    public void setPassword(String password){
        this.password = password; // Sets the password of the user.
    }

    public String getAdress() {
        return this.adress; // Retrieves the adress of the user.
    }

    public void setAdress(String newAdress) {
        this.adress = newAdress; // Sets the adress of the user.
    }

    public void setProfilePicture(Image image){
        this.profilePicture = image; // Sets the profile picture of the user as an image.
    }

    public Image getProfilePicture(){
        return profilePicture; // Retrieves the profilepicture of the user as an image.
    }
}
