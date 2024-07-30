package test;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Search{
    private TextField searchBar;
    private Button searchButton;
    private HBox searchBox;

    public Search(){
        // Sets the text field for the search bar.
        searchBar = new TextField();
        searchBar.setPromptText("Search for products");
        searchBar.setMinWidth(150);
        
        searchButton = new Button("Search"); // Creates search button.

        // Add searchBar and searchButton to the HBox searchBox and aligns it to the center.
        searchBox = new HBox(0, searchBar, searchButton);
        searchBox.setAlignment(Pos.CENTER);
    };

    public HBox getSearchBox(){
        return searchBox; // Returns the HBox containing the search components.
    }

    public Button getSearchButton(){
        return searchButton; // Returns the search button.
    }

    public TextField getTextField(){
        return searchBar; // Returns the search text field.
    }
}
