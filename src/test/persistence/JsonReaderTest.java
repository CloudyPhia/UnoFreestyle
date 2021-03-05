package persistence;

import model.GameState;
import model.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

//    @Test
//    void testReaderNonExistentFile() {
//        JsonReader reader = new JsonReader("./data/noSuchFile.json");
//        try {
//            GameState gs = reader.read();
//            fail("IOException expected");
//        } catch (IOException e) {
//            // pass
//        }
//    }
//
//    @Test
//    void testReaderEmptyGameState() {
//        JsonReader reader = new JsonReader("./data/testReaderEmptyGameState.json");
//        try {
//            GameState gs = reader.read();
//            assertEquals("My game state", gs.getName());
//            assertEquals(0, gs.numCurrentPlayers());
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
//
//    @Test
//    void testReaderGeneralGameState() {
//        JsonReader reader = new JsonReader("./data/testReaderGeneralGameState.json");
//        try {
//            GameState gs = reader.read();
//            assertEquals("My game state", gs.getName());
//            List<Player> currentPlayers = gs.getCurrentPlayers();
//
//            assertEquals(2, currentPlayers.size());
//            checkPlayer("Sam", currentPlayers.get(0));
//            checkPlayer("Sophia", currentPlayers.get(1));
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }



}
