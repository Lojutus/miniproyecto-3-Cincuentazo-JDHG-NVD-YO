package cincuentazo;

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
public class GameTests3 {



   Boolean compare( String[] hand , String[] expectHand){
       for (int i = 0; i < hand.length; i++) {
           if(!(Objects.equals(hand[i], expectHand[i]))){
               return false;
           }
       }
       return true;
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
        Game.restartInstance();
        Game game = Game.getInstance();
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
        Game.restartInstance();
        Game game = Game.getInstance();
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
        Game.restartInstance();
        Game game = Game.getInstance();
        assertTrue(game.newPlayer(new Player()));
        assertTrue(game.newPlayer(new Machine()));
        assertTrue(game.newPlayer(new Machine()));
        assertTrue(game.newPlayer(new Machine()));
        assertFalse(game.newPlayer(new Machine()));
    }
    @org.junit.Test
    public void testingGamePlayers(){
        Game.restartInstance();
        Game game = Game.getInstance();
        assertEquals(0 , game.getPlayers());
        assertTrue(game.newPlayer(new Player()));
        assertEquals(1 , game.getPlayers());
        assertTrue(game.newPlayer(new Machine()));
        assertEquals(2 , game.getPlayers());
        assertTrue(game.newPlayer(new Machine()));


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
        game.newPlayer(new Player());
        game.initGame();
        String[] expectHand = game.getPlayer(0).getHand();

        game.changeHandCard(0 , 0);

        String sss = game.getPlayer(0).getHand()[0];


        expectHand[0]= sss;

        assertTrue(compare( game.getPlayer(0).getHand(), expectHand));
        
        // --------------------------

        Game.restartInstance();
        game = Game.getInstance();
        game.newPlayer(new Player());
        game.initGame();
        expectHand = game.getPlayer(0).getHand();

        game.changeHandCard(0 ,expectHand[0]);

        String ssss = game.getPlayer(0).getHand()[0];


        expectHand[0]= ssss;

        assertTrue(compare( game.getPlayer(0).getHand(), expectHand));

    }

}
