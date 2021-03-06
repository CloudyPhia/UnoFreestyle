package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Tests for GameState class. Note that most of the methods are adequately tested in the JsonTest class.

public class GameStateTest {

    public GameState gs;

    @BeforeEach
    public void setUp() {
        gs = new GameState("My game state");
    }

    @Test
    public void testGetName() {
        assertEquals("My game state", gs.getName());
    }
}
