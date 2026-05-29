package cincuentazo;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.cincuentazo.model.Clases.Deck;
import com.example.cincuentazo.model.Clases.Game;
import com.example.cincuentazo.model.Clases.Player;

import java.util.Arrays;
import java.util.Objects;

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
public class GameTests {

    //DECK

    @org.junit.Test
    public void testDeck() {

        Deck deck = new Deck();

        assertNotNull(deck.getCard());

    }

    @org.junit.Test
    public void testDeckIsCreated() {

        Deck deck = new Deck();

        assertNotNull(deck);

    }

    @org.junit.Test
    public void testDifferentCards() {

        Deck deck = new Deck();

        String firstCard = deck.getCard();
        String secondCard = deck.getCard();

        assertNotEquals(firstCard, secondCard);

    }
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
        player.swichtCard("AP" , 0);String[] expectHand ={"AP", "", "", ""};assertTrue(compare(player.getHand(), expectHand));
        player.swichtCard("AP" , 1);expectHand = new String[]{"AP", "AP", "", ""};assertTrue(compare(player.getHand(), expectHand));
        player.swichtCard("AP" , 2);expectHand = new String[]{"AP", "AP", "AP", ""};assertTrue(compare(player.getHand(), expectHand));
        player.swichtCard("AP" , 3);expectHand = new String[]{"AP", "AP", "AP", "AP"};assertTrue(compare(player.getHand(), expectHand));
    }
    @org.junit.Test
    public void testingHand(){
        Player player = new Player();
        assertTrue(player.swichtCard("AP" , 0));
        assertTrue(player.swichtCard("AP" , 1));
        assertTrue(player.swichtCard("AP" , 2));
        assertTrue(player.swichtCard("AP" , 3));
        assertFalse(player.swichtCard("AP" , 6));

    }
    //Game
    @org.junit.Test
    public void testingCardCheck(){
        Game game = Game.getInstance();
        Game.restartInstance();
        assertEquals(10 , game.check('A'));
        assertEquals(0 , game.check('9'));
        assertEquals(8 , game.check('8'));
        assertEquals(-10 , game.check('J'));

    }
    @org.junit.Test
    public void testingCardAdd(){
        Game game = Game.getInstance();
        Game.restartInstance();
        game.add("A");
        assertEquals(10 , game.getSum());
        game.add("9");
        assertEquals(10 , game.getSum());
        game.add("8");
        assertEquals(18 , game.getSum());
        game.add("J");
        assertEquals(8 , game.getSum());
        game.add("2");
        game.add("A");
        game.add("A");
        game.add("A");
        game.add("A");
        assertEquals(50 , game.getSum());
        game.add("A");
        assertEquals(50 , game.getSum());

    }

}
