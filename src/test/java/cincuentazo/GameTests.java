package cincuentazo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.cincuentazo.model.Clases.Deck;
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

}
