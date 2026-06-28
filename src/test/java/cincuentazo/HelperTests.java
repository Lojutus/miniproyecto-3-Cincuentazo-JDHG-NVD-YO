package cincuentazo;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.cincuentazo.controller.helpers.CardViewManager;
import javafx.scene.image.Image;

public class HelperTests {
    //CardViewManager

    @org.junit.Test
    public void testImageCharge(){
        CardViewManager manager = new CardViewManager();

        Image image = manager.chargeImage("AP");

        assertNotNull(image);
    }
}
