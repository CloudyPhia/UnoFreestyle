package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Tests for Player class
public class PlayerTest {

    private Player testPlayerMultipleSameCards;
    private Player testPlayerAllDifferentCards;
    private Player testPlayerOneCardLeft;
    private Player testPlayerNoCardsLeft;

    private Card testCard1;
    private Card testCard2;
    private Card testCard3;
    private Card testCard4;
    private Card testCard5;
    private Card testCard6;
    private Card testCard7;

    private static int STARTING_CARD_AMOUNT = 7;


    @BeforeEach
    public void setUpPlayerTest() {
        testPlayerMultipleSameCards = new Player("Player1");

        testCard1 = new Card("Yellow", 0);
        testCard2 = new Card("Red", 1);
        testCard3 = new Card("Green", 2);
        testCard4 = new Card("Blue", 3);
        testCard5 = new Card("Yellow", 4);
        testCard6 = new Card("Red", 5);
        testCard7 = new Card("Green", 6);

        for (int i = 0; i <= 6; i ++) {
            testPlayerMultipleSameCards.addCardToHand(testCard1);
        } //this will add 7 of the test card

        testPlayerAllDifferentCards = new Player("Player2");

        testPlayerAllDifferentCards.addCardToHand(testCard1);
        testPlayerAllDifferentCards.addCardToHand(testCard2);
        testPlayerAllDifferentCards.addCardToHand(testCard3);
        testPlayerAllDifferentCards.addCardToHand(testCard4);
        testPlayerAllDifferentCards.addCardToHand(testCard5);
        testPlayerAllDifferentCards.addCardToHand(testCard6);
        testPlayerAllDifferentCards.addCardToHand(testCard7);


        testPlayerOneCardLeft = new Player("Player3");

        testPlayerOneCardLeft.addCardToHand(testCard6);

        testPlayerNoCardsLeft = new Player("Player4");


    }

    @Test
    public void testPlayerHasSevenCardsAtStart() {
        //test the player has STARTING_CARD_AMOUNT cards at the start
        assertEquals(STARTING_CARD_AMOUNT, testPlayerMultipleSameCards.getPlayerHandSize());

    }

    @Test
    public void testPlayerDiscardOneOfMultiple() {
        assertTrue(testPlayerMultipleSameCards.possibleToDiscard());

        testPlayerMultipleSameCards.removeCardFromHand(testCard1);

        ArrayList<Card> testPlayerSameCardsHand = testPlayerMultipleSameCards.getPlayerHand();

        assertEquals((STARTING_CARD_AMOUNT - 1), testPlayerSameCardsHand.size());

    }

    @Test
    public void testPlayerDiscardNoMultiples() {
        assertTrue(testPlayerAllDifferentCards.possibleToDiscard());

        testPlayerAllDifferentCards.removeCardFromHand(testCard3);

        ArrayList<Card> testPlayerDifferentCardsHand = testPlayerAllDifferentCards.getPlayerHand();

        assertEquals((STARTING_CARD_AMOUNT - 1), testPlayerDifferentCardsHand.size());


    }

    @Test
    public void testPlayerDiscardLastCard() {

        assertTrue(testPlayerOneCardLeft.possibleToDiscard());

        testPlayerOneCardLeft.removeCardFromHand(testCard6);

        ArrayList<Card> testPlayerOneCardLeftHand = testPlayerOneCardLeft.getPlayerHand();

        assertEquals(0, testPlayerOneCardLeftHand.size());

    }

    @Test
    public void testPlayerNotPossibleToDiscard() {
        assertFalse(testPlayerNoCardsLeft.possibleToDiscard());
    }



    @Test
    public void testPlayerDraw () {
        Card testCardDraw = new Card("Blue", 3);

        testPlayerMultipleSameCards.addCardToHand(testCardDraw);

        ArrayList<Card> testPlayerSameCardsHand = testPlayerMultipleSameCards.getPlayerHand();

        assertTrue(testPlayerSameCardsHand.contains(testCardDraw));


    }



}
