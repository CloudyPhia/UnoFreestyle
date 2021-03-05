package persistence;

import model.GameState;
import model.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
//
//    @Test
//    void testWriterInvalidFile() {
//        try {
//            GameState gs = new GameState("My game state");
//            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
//            writer.open();
//            fail("IOException was expected");
//        } catch (IOException e) {
//            // pass
//        }
//    }
//
//    @Test
//    void testWriterEmptyGameState() {
//        try {
//            GameState gs = new GameState("My game state");
//            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGameState.json");
//            writer.open();
//            writer.write(gs);
//            writer.close();
//
//            JsonReader reader = new JsonReader("./data/testWriterEmptyGameState.json");
//            gs = reader.read();
//            assertEquals("My game state", gs.getName());
//            assertEquals(0, gs.numCurrentPlayers());
//        } catch (IOException e) {
//            fail("Exception should not have been thrown");
//        }
//    }
//
//    @Test
//    void testWriterGeneralGameState() {
//        try {
//            GameState gs = new GameState("My game state");
//            gs.addPlayer(new Player("Sam"));
//            gs.addPlayer(new Player("Sophia"));
//            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGameState.json");
//            writer.open();
//            writer.write(gs);
//            writer.close();
//
//            JsonReader reader = new JsonReader("./data/testWriterGeneralGameState.json");
//            gs = reader.read();
//            assertEquals("My game state", gs.getName());
//            List<Player> currentPlayers = gs.getCurrentPlayers();
//            assertEquals(2, currentPlayers.size());
//            checkPlayer("Sam", currentPlayers.get(0));
//            checkPlayer("Sophia", currentPlayers.get(1));
//
//        } catch (IOException e) {
//            fail("Exception should not have been thrown");
//        }
//    }
}
