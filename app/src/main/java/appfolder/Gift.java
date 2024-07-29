package appfolder;

import javafx.scene.image.Image;
import java.lang.Math;


public class Gift extends Product {
    private String giftDescription;
    private User uploadedSponsor;

    // Create a Gift object.
    public Gift(User upSponsor, String name, Image image, int points) {
        super(name, image, points);
        this.uploadedSponsor = upSponsor;
    }

    public void setDescription(String giftDescription) {
        this.giftDescription = giftDescription; // Set gift description.
    }

    public String getDescription() {
        if (this.giftDescription == null) {
            this.giftDescription = "No description was given"; // If there was no gift description given, set a basic value for the description.
        }
        return this.giftDescription;
    }

    public int getGiftPoints() {
        return (int)Math.round(getPrice()); // Get the amount of points that is needed to buy the gift.
    }

    public User getUploader() {
        return this.uploadedSponsor; // Get the uploaders of the gift.
    }
}
