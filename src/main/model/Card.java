package model;

public class Card {
    // CONSTANT:
    // card numbers 0-9
    // card colours (red, green, blue, yellow) ~ maybe choose just one colour?
    // previous cards in hand
    private String colour;
    private int number;


    //REQUIRES: string to be one of "Red" "Blue" "Yellow" "Green" and int to be 0 <= n <= 9
    //MODIFIES:
    //EFFECTS: instantiates a card with colour c and value n
    public Card(String c, int n) {
        colour = c;
        number = n;
    }


    //getters

    //EFFECTS: returns the card's colour as a string
    public String getColour() {
        return this.colour;
    }

    //EFFECTS: returns the card's value between 0 and 9
    public int getNumber() {
        return this.number;
    }

    //setters

    //EFFECTS: returns
    //public


    //CHANGING:
    // amount of cards in list
    // cards in the list (cards added/removed)

}


//checkCardNumberSame(){}
// later
//checkCardColourSame(){}
