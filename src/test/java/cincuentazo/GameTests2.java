package cincuentazo;

import com.example.cincuentazo.model.Classes.Deck;
import com.example.cincuentazo.model.Classes.Game;
import com.example.cincuentazo.model.Classes.Machine;
import com.example.cincuentazo.model.Classes.Player;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/*
 HOW TO CREATE A UNIT TEST (JUnit 5)

 1. Create a method with @Test
 2. Create the object to test
 3. Execute the function
 4. Validate the result with assertions

 Main assertions:

 assertEquals(expected, result);
 assertNotNull(object);
 assertTrue(condition);
 assertFalse(condition);

 Example:

 @org.junit.Test
 public void testExample(){
     assertEquals(4, 2 + 2);
 }

*/
public class GameTests2 {


   //Player
   Boolean compare( String[] hand , String[] expectHand){
       for (int i = 0; i < hand.length; i++) {
           if(!(Objects.equals(hand[i], expectHand[i]))){
               return false;
           }
       }
       return true;
   }
    @org.junit.Test
    public void testingHandSwitch(){

        Player player = new Player();
        player.switchCard("AP" , 0);String[] expectHand ={"AP", "", "", ""};assertTrue(compare(player.getHand(), expectHand));
        player.switchCard("AP" , 1);expectHand = new String[]{"AP", "AP", "", ""};assertTrue(compare(player.getHand(), expectHand));
        player.switchCard("AP" , 2);expectHand = new String[]{"AP", "AP", "AP", ""};assertTrue(compare(player.getHand(), expectHand));
        player.switchCard("AP" , 3);expectHand = new String[]{"AP", "AP", "AP", "AP"};assertTrue(compare(player.getHand(), expectHand));
    }
    @org.junit.Test
    public void testingHand(){
        Player player = new Player();
        assertTrue(player.switchCard("AP" , 0));
        assertTrue(player.switchCard("AP" , 1));
        assertTrue(player.switchCard("AP" , 2));
        assertTrue(player.switchCard("AP" , 3));
        assertFalse(player.switchCard("AP" , 6));

    }

}
