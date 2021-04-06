package model;


import exceptions.IllegalNumberException;
import exceptions.IncorrectColourException;
import org.json.JSONObject;
import persistence.Writeable;

//Represents an uno card with a colour (as a string) and a value (as a number)

/*
 * CITATION: toJson(); method code obtained (and modified) from JsonSerializationDemo - Thingy class
 *           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

public class Card implements Writeable {
    // CONSTANT:
    // card numbers 0-9
    // card colours (red, green, blue, yellow) ~ maybe choose just one colour?
    // previous cards in hand
    private final String colour;
    private final int number;

    //MODIFIES: this
    //EFFECTS: instantiates a card with colour c and value n
    public Card(String c, int n) throws IllegalNumberException, IncorrectColourException {
        if (n > 9 || n < 0) {
            throw new IllegalNumberException();
        }

        if (!isCorrectColour(c)) {
            throw new IncorrectColourException();
        }

        colour = c;
        number = n;
    }

    public Boolean isCorrectColour(String c) {
        return c.equals("Blue") || c.equals("Red") || c.equals("Yellow") || c.equals("Green");
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("colour", colour);
        json.put("number", number);

        return json;
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


}