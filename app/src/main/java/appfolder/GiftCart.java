package appfolder;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import java.util.List;
import java.util.ArrayList;


// The GiftCart is implemented in the same way as Cart for the dishes.
public class GiftCart {
    private static List<Gift> cartList = new ArrayList<>();
    private static GridPane cartDisplay = new GridPane();
    private static IntegerProperty totalPrice = new SimpleIntegerProperty(0);

    public static ScrollPane cartScreen() {
        cartDisplay.getChildren().clear();

        int row = 0;
        for (Gift gift : cartList){
            cartDisplay.add(gift.productView(), 0, row++);
            cartDisplay.add(removeButton(gift), 0, row++);
        }

        ScrollPane cartPane = new ScrollPane(cartDisplay);
        return cartPane;
    }

    public static void addToCart(Gift gift) {
        cartList.add(gift);
        GiftDB.getGiftDB().remove(gift);
    }

    public static IntegerProperty getTotalPrice(){
        totalPrice.bind(getSum(0));
        return totalPrice;
    }

    private static IntegerProperty getSum(int index){
        if(cartList.size() == 0){
            return new SimpleIntegerProperty(0);
        }
        if (index >= cartList.size() - 1){
            return new SimpleIntegerProperty(cartList.get(index).getGiftPoints());
        }
        else{
            IntegerProperty sum = new SimpleIntegerProperty();
            sum.bind(getSum(index + 1).add(cartList.get(index).getGiftPoints()));
            return sum;
        }
    }

    public static void orderCart(){
        cartList.clear();
    }

    public static void removeFromCart(Gift gift){
        GiftDB.getGiftDB().add(gift);
        cartList.remove(gift);
        cartScreen();
    }

    public static Button removeButton(Gift gift){
        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            removeFromCart(gift);
            getTotalPrice();
        });
        return removeButton;
    }      
}
