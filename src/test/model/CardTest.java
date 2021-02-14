package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    private Card testCard1;

    @BeforeEach
    public void setUpCardTest() {
        testCard1 = new Card("Red", 9);
    }

    @Test
    public void testGetCardColour() {
        assertEquals("Red", testCard1.getColour());

    }

    @Test
    public void testGetCardNumber() {
        assertEquals(9, testCard1.getNumber());
    }



}