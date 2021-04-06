package model;

import exceptions.IllegalNumberException;
import exceptions.IncorrectColourException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Tests for Card class
public class CardTest {

    private Card testCard1;

    @Test
    public void testGetCardColour() {
        try {
            testCard1 = new Card("Red", 9);
            assertEquals("Red", testCard1.getColour());
        } catch (IncorrectColourException e) {
            fail("Exception caught when not expected (incorrect colour exception)");
        } catch (IllegalNumberException e) {
            fail("Exception caught when not expected (incorrect colour exception)");
        }

    }

    @Test
    public void testGetCardNumber() {
        try {
            testCard1 = new Card("Red", 9);
            assertEquals(9, testCard1.getNumber());
        } catch (IncorrectColourException e) {
            fail("Exception caught when not expected (incorrect colour exception)");
        } catch (IllegalNumberException e) {
            fail("Exception caught when not expected (incorrect colour exception)");
        }
    }

    @Test
    public void testColourException() {
        try {
            testCard1 = new Card("Orange", 9);
            fail("Exception should've been thrown");
        } catch (IncorrectColourException e) {
            //do nothing
        } catch (IllegalNumberException e) {
            fail("Exception caught when not expected (incorrect colour exception)");
        }
    }

    @Test
    public void testNumberException() {
        try {
            testCard1 = new Card("Red", 59);
            fail("Exception should've been thrown");
        } catch (IncorrectColourException e) {
            fail("Exception caught when not expected (incorrect colour exception)");
        } catch (IllegalNumberException e) {
            //do nothing
        }
    }

    @Test
    public void testBothExceptions() {
        try {
            testCard1 = new Card("Rainbow", 68);
            fail("Exception should've been thrown");
        } catch (IncorrectColourException e) {
            fail("IllegalNumberException should've been thrown first (IncorrectColourException thrown)");
        } catch (IllegalNumberException e) {
            //do nothing
        }
    }



}