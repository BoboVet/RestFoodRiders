package test;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.text.DecimalFormat;

// Represents a product, which can be either a dish or a gift.
public class Product {
    private String name;
    private Image image;
    private double price;
    private Label priceLabel = new Label();

    public Product (String name, Image image, double price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getProductName(){
        return this.name; // Retrieves the name of the product.
    }

    public Image getImage(){
        return this.image; // Retrieves the image representing the product.
    }

    public double getPrice() {
        return this.price; // Retrieves the price of the product.
    }

    // Creates a pane for the product containing an ImageView, pricelabel and namelabel.
    public Pane productView(){
        BorderPane productView = new BorderPane();
        productView.setPadding(new Insets(10));

        productView.setLeft(FoodImages.createImageView(image)); // Sets an ImageView of the product to the left side of the pane.
        if (this instanceof Dish) {
            // If this (product) is a dish then we put the pricelabel with 2 decimals and we put 'EUR' after the price.
            DecimalFormat df = new DecimalFormat("0.00");
            this.priceLabel.setText(df.format(((Dish)this).getPrice()) + " EUR");
        }
        else {
            // Else it is a gift and we put 'Points' after the price.
            this.priceLabel.setText(String.valueOf(price) + " Points");
        }
        
        // We set the price to the right of the pane.
        BorderPane.setAlignment(priceLabel, Pos.CENTER_RIGHT);
        productView.setRight(priceLabel);

        // We create a nameLabel containing the name of the product and resizes it.
        Label nameLabel = new Label(name);
        nameLabel.resize(80, 50);
        nameLabel.setCenterShape(true);
        nameLabel.setPadding(new Insets(10));

        // We set the namelabel in the center of the pane and resizes the left and right.
        productView.setCenter(nameLabel);
        productView.getLeft().resize(100,100);
        productView.getRight().resize(100,100);
        productView.getCenter().resize(100,100);
        
        return productView;
    }
}
