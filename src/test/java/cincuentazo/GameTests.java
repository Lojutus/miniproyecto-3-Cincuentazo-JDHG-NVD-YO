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
    @org.junit.Test
    public void testingAAdd(){
        Game game = Game.getInstance();
        Game.restartInstance();
        game.add("A");
        assertEquals(10 , game.getSum());
        game.add("A");
        assertEquals(20 , game.getSum());
        game.add("A");
        assertEquals(30 , game.getSum());
        game.add("A");
        assertEquals(40 , game.getSum());
        game.add("A");
        assertEquals(50 , game.getSum());
        game.add("A");
        assertEquals(50 , game.getSum());

    }
    @org.junit.Test
    public void testingNewPlayers(){
        Game game = Game.getInstance();
        Game.restartInstance();
        assertTrue(game.newPlayer());
        assertTrue(game.newPlayer());
        assertTrue(game.newPlayer());
        assertFalse(game.newPlayer());
    }
    @org.junit.Test
    public void testingGamePlayers(){
        Game game = Game.getInstance();
        Game.restartInstance();
        assertEquals(1 , game.getPlayers());
        assertTrue(game.newPlayer());
        assertEquals(2 , game.getPlayers());
        assertTrue(game.newPlayer());
        assertEquals(3 , game.getPlayers());
        assertTrue(game.newPlayer());


    }
    @org.junit.Test
    public void testingWin(){
        Game game = Game.getInstance();
        Game.restartInstance();
        game.initGame();
        assertTrue(game.checkWin());

        Game.restartInstance();
         game = Game.getInstance();
        assertTrue(game.newPlayer());
        game.getPlayer(1).playing = false;
        assertTrue(game.newPlayer());
        game.getPlayer(2).playing = false;
        game.initGame();
        assertTrue(game.checkWin());
    }
    @org.junit.Test
    public void testingCardSwicht(){
        
        Game game = Game.getInstance();
        Game.restartInstance();
        String s = game.getPlayer(0).getHand()[0];
        game.changeHandCard(0 , s);
        assertNotEquals(s, game.getPlayer(0).getHand()[0] );
        String ss = game.getPlayer(0).getHand()[0];
        game.changeHandCard(0 , 0);
        assertNotEquals(ss, game.getPlayer(0).getHand()[0] );
        
       // --------------------------
        Game.restartInstance();
        game = Game.getInstance();
        game.initGame();
        String[] expectHand = game.getPlayer(0).getHand();

        game.changeHandCard(0 , 0);

        String sss = game.getPlayer(0).getHand()[0];


        expectHand[0]= sss;

        assertTrue(compare( game.getPlayer(0).getHand(), expectHand));
        
        // --------------------------

        Game.restartInstance();
        game = Game.getInstance();
        game.initGame();
        expectHand = game.getPlayer(0).getHand();

        game.changeHandCard(0 ,expectHand[0]);

        String ssss = game.getPlayer(0).getHand()[0];


        expectHand[0]= ssss;

        assertTrue(compare( game.getPlayer(0).getHand(), expectHand));

    }

}
