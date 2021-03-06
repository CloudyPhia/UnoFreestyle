package model;


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
    private String colour;
    private int number;


    //REQUIRES: string to be one of "Red" "Blue" "Yellow" "Green" and int to be 0 <= n <= 9
    //MODIFIES: this
    //EFFECTS: instantiates a card with colour c and value n
    public Card(String c, int n) {
        colour = c;
        number = n;
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