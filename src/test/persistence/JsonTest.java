package persistence;

import exceptions.IllegalNumberException;
import exceptions.IncorrectColourException;
import model.Card;
import model.GameState;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Tests for both the JsonReader and JsonWriter class (as they both rely on each other)
//also inherently tests the GameState class.

/*
 * CITATION: JsonReaderTest and JsonWriterTest code obtained (and modified) from JsonSerializationDemo
 *            - both JsonReaderTest class and JsonWriterTest class
 *           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

public class JsonTest {
    //create gamestate object
    protected void checkPlayer(String name, Player player) {
        assertEquals(name, player.getName());
    }

    public JsonWriter writer;
    public JsonReader reader;
    public GameState gs;
    public Player player1;
    public Player player2;
    public Player player3;
    public Player player4;
    public Card testCard;

    @BeforeEach
    public void setUp() {
         gs = new GameState("My game state");
         writer = new JsonWriter("./data/ReaderTest.json");
         reader = new JsonReader("./data/ReaderTest.json");

         player1 = new Player("tester1");
         player2 = new Player("tester2");
         player3 = new Player("tester3");
         player4 = new Player("tester4");

        try {
            testCard = new Card("Yellow", 0);
        } catch (IllegalNumberException e) {
            testCard = null;
            fail("IllegalNumberException thrown.");
        } catch (IncorrectColourException e) {
            testCard = null;
            fail("IncorrectColourExcception thrown.");
        }

    }


    @Test
    public void test() {
        //modify gamestate for what we're testing
        //write that gamestate to a file (call writer.write, pass in the gamestate) and open and close
        //read from that same file - the read function will give a gamestate object
        //now do asserts to test that the gamestate gs has the same fields as the gamestate returned by the read
        // function
    }

    @Test
    public void testReaderNoFile() {
        JsonReader readerFail = new JsonReader("./data/noSuchFile.json");
        try {
            readerFail.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterNoFile() {
        JsonWriter writerFail = new JsonWriter("./data/my\0illegal:fileName.json");
        try {
            writerFail.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testTwoPlayersIllegalNumberCards() {
        try {
            player1.addCardToHand(testCard);
            player1.addCardToHand(new Card("Red", -1));
            fail("Should've thrown IllegalNumberException");
        } catch (IllegalNumberException e) {
            //do nothing
        } catch (IncorrectColourException e) {
            fail("Shouldn't have thrown IncorrectColourException");
        }

        for (int in = 0; in < 7; in++) {
            player2.addCardToHand(testCard);
        }

        try {
            player2.addCardToHand(new Card("Blue", 10));
            fail("Should've thrown IllegalNumberException");
        } catch (IllegalNumberException e) {
            //do nothing
        } catch (IncorrectColourException e) {
            fail("Shouldn't have thrown IncorrectColourException");
        }

        gs.addPlayer(player1);
        gs.addPlayer(player2);

        try {
            writer.open();
            writer.write(gs);
            writer.close();

            gs = reader.read();
            assertEquals("My game state", gs.getName());

            List<Player> currentPlayers = gs.getCurrentPlayers();
            assertEquals(2, gs.numCurrentPlayers());

            assertEquals(player1.getPlayerHandSize(), 1);
            assertEquals(player2.getPlayerHandSize(), 7);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }


    @Test
    public void testTwoPlayersIllegalColourCards() {
        try {
            player1.addCardToHand(testCard);
            player1.addCardToHand(testCard);
            player1.addCardToHand(new Card("Orange", 5));
            fail("Should've thrown IncorrectColourException");
        } catch (IllegalNumberException e) {
            fail("Shouldn't have thrown IllegalNumberException");
        } catch (IncorrectColourException e) {
            //do nothing
        }

        for (int in = 0; in < 5; in++) {
            player2.addCardToHand(testCard);
        }

        try {
            player2.addCardToHand(new Card("Rainbow", 7));
            fail("Should've thrown IncorrectColourException");
        } catch (IllegalNumberException e) {
            fail("Shouldn't have thrown IllegalNumberException");
        } catch (IncorrectColourException e) {
            //do nothing
        }

        gs.addPlayer(player1);
        gs.addPlayer(player2);

        try {
            writer.open();
            writer.write(gs);
            writer.close();

            gs = reader.read();
            assertEquals("My game state", gs.getName());

            List<Player> currentPlayers = gs.getCurrentPlayers();
            assertEquals(2, gs.numCurrentPlayers());

            assertEquals(player1.getPlayerHandSize(), 2);
            assertEquals(player2.getPlayerHandSize(), 5);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

    @Test
    public void testTwoPlayersIllegalColourAndNumberCards() {
        try {
            player1.addCardToHand(testCard);
            player1.addCardToHand(new Card("Purple", -5));
            fail("Should've thrown IllegalNumberException");
        } catch (IllegalNumberException e) {
            //do nothing
        } catch (IncorrectColourException e) {
            fail("Should've thrown IllegalNumberException first");
        }

        for (int in = 0; in < 9; in++) {
            player2.addCardToHand(testCard);
        }

        try {
            player2.addCardToHand(new Card("Pink", 25));
            fail("Should've thrown IllegalNumberException");
        } catch (IllegalNumberException e) {
            //do nothing
        } catch (IncorrectColourException e) {
            fail("Should've thrown IllegalNumberException first");
        }

        gs.addPlayer(player1);
        gs.addPlayer(player2);

        try {
            writer.open();
            writer.write(gs);
            writer.close();

            gs = reader.read();
            assertEquals("My game state", gs.getName());

            List<Player> currentPlayers = gs.getCurrentPlayers();
            assertEquals(2, gs.numCurrentPlayers());

            assertEquals(player1.getPlayerHandSize(), 1);
            assertEquals(player2.getPlayerHandSize(), 9);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }



    @Test
    public void testTwoPlayersFullCards() {
        for (int in = 0; in < 7; in++) {
           player1.addCardToHand(testCard);
        }
        for (int in = 0; in < 7; in++) {
            player2.addCardToHand(testCard);
        }

        gs.addPlayer(player1);
        gs.addPlayer(player2);

        try {
            writer.open();
            writer.write(gs);
            writer.close();

            gs = reader.read();
            assertEquals("My game state", gs.getName());

            List<Player> currentPlayers = gs.getCurrentPlayers();
            assertEquals(2, gs.numCurrentPlayers());

            assertEquals(player1.getPlayerHandSize(), 7);
            assertEquals(player2.getPlayerHandSize(), 7);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

    @Test
    public void testTwoPlayersOneMoreCards() {
        for (int in = 0; in < 18; in++) {
            player1.addCardToHand(testCard);
        }
        for (int in = 0; in < 3; in++) {
            player2.addCardToHand(testCard);
        }

        gs.addPlayer(player1);
        gs.addPlayer(player2);

        try {
            writer.open();
            writer.write(gs);
            writer.close();

            gs = reader.read();
            assertEquals("My game state", gs.getName());

            List<Player> currentPlayers = gs.getCurrentPlayers();
            assertEquals(2, currentPlayers.size());

            assertEquals(player1.getPlayerHandSize(), 18);
            assertEquals(player2.getPlayerHandSize(), 3);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testTwoPlayersOneCardLeft() {
        for (int in = 0; in < 1; in++) {
            player1.addCardToHand(testCard);
        }
        for (int in = 0; in < 1; in++) {
            player2.addCardToHand(testCard);
        }

        gs.addPlayer(player1);
        gs.addPlayer(player2);

        try {
            writer.open();
            writer.write(gs);
            writer.close();

            gs = reader.read();
            assertEquals("My game state", gs.getName());

            List<Player> currentPlayers = gs.getCurrentPlayers();
            assertEquals(2, currentPlayers.size());

            assertEquals(player1.getPlayerHandSize(), 1);
            assertEquals(player2.getPlayerHandSize(), 1);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testTwoPlayersOneWithNoCards() {
        for (int in = 0; in < 3; in++) {
            player2.addCardToHand(testCard);
        }

        gs.addPlayer(player1);
        gs.addPlayer(player2);

        try {
            writer.open();
            writer.write(gs);
            writer.close();

            gs = reader.read();
            assertEquals("My game state", gs.getName());

            List<Player> currentPlayers = gs.getCurrentPlayers();
            assertEquals(2, currentPlayers.size());

            assertEquals(player1.getPlayerHandSize(), 0);
            assertEquals(player2.getPlayerHandSize(), 3);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testThreePlayers() {
        for (int in = 0; in < 1; in++) {
            player1.addCardToHand(testCard);
        }
        for (int in = 0; in < 5; in++) {
            player2.addCardToHand(testCard);
        }
        for (int in = 0; in < 8; in++) {
            player3.addCardToHand(testCard);
        }

        gs.addPlayer(player1);
        gs.addPlayer(player2);
        gs.addPlayer(player3);

        try {
            writer.open();
            writer.write(gs);
            writer.close();

            gs = reader.read();
            assertEquals("My game state", gs.getName());

            List<Player> currentPlayers = gs.getCurrentPlayers();
            assertEquals(3, currentPlayers.size());

            assertEquals(player1.getPlayerHandSize(), 1);
            assertEquals(player2.getPlayerHandSize(), 5);
            assertEquals(player3.getPlayerHandSize(), 8);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testFourPlayers() {
        for (int in = 0; in < 7; in++) {
            player1.addCardToHand(testCard);
        }
        for (int in = 0; in < 6; in++) {
            player2.addCardToHand(testCard);
        }
        for (int in = 0; in < 5; in++) {
            player3.addCardToHand(testCard);
        }
        for (int in = 0; in < 4; in++) {
            player4.addCardToHand(testCard);
        }

        gs.addPlayer(player1);
        gs.addPlayer(player2);
        gs.addPlayer(player3);
        gs.addPlayer(player4);

        try {
            writer.open();
            writer.write(gs);
            writer.close();

            gs = reader.read();
            assertEquals("My game state", gs.getName());

            List<Player> currentPlayers = gs.getCurrentPlayers();
            assertEquals(4, currentPlayers.size());

            assertEquals(player1.getPlayerHandSize(), 7);
            assertEquals(player2.getPlayerHandSize(), 6);
            assertEquals(player3.getPlayerHandSize(), 5);
            assertEquals(player4.getPlayerHandSize(), 4);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
