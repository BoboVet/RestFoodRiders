package test;

import java.util.ArrayList;

// The UserDB class manages user data and provides methods to interact with the user database.
public class UserDB {
    private static ArrayList<User> allUsers;

    // Registers a new user into the user database.
    public static void registerDataBase(User user) {
        if (allUsers == null) {
            allUsers = new ArrayList<>();  // Initialize the user list if it's null.
        }
        allUsers.add(user); // Add the user to the list.
    }

    //  Checks if a user with the given username and password exists in the user database.
    public static Boolean userFound(String username, String password) {
        if (allUsers == null) {
            return false; // Return false if the user database is empty.
        }
        // Iterate through the list of users and check if the username and password match.
        for (User user : allUsers) {
            if (user.getName().equals(username)  && user.getPassword().equals(password)){
                return true; // Return true if a match is found.
            }
        }
        return false; // Return false otherwise.
    }

    //  Retrieves the User object for the user with the given username and password.
    public static User foundUserInfo(String username, String password) {
        // Iterate through the list of users and return the User object if username and password match.
        for (User user : allUsers) {
            if (user.getName().equals(username)  && user.getPassword().equals(password)){
                return user;  // Return the User object if found.
            }
        }
        return null; // Return null otherwise.
    }
}
