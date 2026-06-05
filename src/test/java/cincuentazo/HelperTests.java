package cincuentazo;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.cincuentazo.controller.helpers.HandSpritesHelper;

import com.example.cincuentazo.controller.helpers.CardViewManager;
import com.example.cincuentazo.model.Clases.Deck;
import com.example.cincuentazo.model.Clases.Game;
import com.example.cincuentazo.model.Clases.Player;
import javafx.scene.image.Image;


import java.util.Arrays;
import java.util.Objects;
public class HelperTests {
    //CardViewManager

    @org.junit.Test
    public void testImageCharge(){
        CardViewManager manager = new CardViewManager();

        Image image = manager.chargeImage("AP");

        assertNotNull(image);
    }
}
