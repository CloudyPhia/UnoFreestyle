package persistence;

import model.Card;
import model.GameState;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Tests for both the JsonReader and JsonWriter class (as they both rely on each other)
//also partially tests the GameState class.

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
    public Card testCard;

    @BeforeEach
    public void setUp() {
         gs = new GameState("My game state");
         writer = new JsonWriter("./data/ReaderTest.json");
         reader = new JsonReader("./data/ReaderTest.json");

         player1 = new Player("tester1");
         player2 = new Player("tester2");
         testCard = new Card("Yellow", 0);

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
            assertEquals(2, gs.numCurrentPlayers());
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
            assertEquals(2, gs.numCurrentPlayers());
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
            assertEquals(2, gs.numCurrentPlayers());
            assertEquals(player1.getPlayerHandSize(), 0);
            assertEquals(player2.getPlayerHandSize(), 3);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
