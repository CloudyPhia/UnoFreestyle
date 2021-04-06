package model;


import exceptions.IllegalNumberException;
import exceptions.IncorrectColourException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Tests for GameState class. Note that most of the methods are adequately tested in the JsonTest class.

public class GameStateTest {

    public Player player1;
    public Player player2;
    public Player player3;
    public Player player4;
    public Card testCard;
    public GameState gameState;

    @BeforeEach
    public void setUp() {
        gameState = new GameState("My game state");

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
    public void testGetName() {
        assertEquals("My game state", gameState.getName());
    }

    @Test
    public void testNumCurrentPlayersTwo() {
        gameState.addPlayer(player1);
        gameState.addPlayer(player2);

        assertEquals(2, gameState.numCurrentPlayers());

    }

    @Test
    public void testNumCurrentPlayersThree() {
        gameState.addPlayer(player1);
        gameState.addPlayer(player2);
        gameState.addPlayer(player3);

        assertEquals(3, gameState.numCurrentPlayers());

    }

    @Test
    public void testNumCurrentPlayersFour() {
        gameState.addPlayer(player1);
        gameState.addPlayer(player2);
        gameState.addPlayer(player3);
        gameState.addPlayer(player4);

        assertEquals(4, gameState.numCurrentPlayers());

    }
}
