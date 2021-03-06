package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Represents a gamestate having a collection of players

/*
 * CITATION: GameState code obtained (and modified) from JsonSerializationDemo - WorkRoom class
 *           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

public class GameState implements Writeable {
    private String name;
    private List<Player> currentPlayers;

    // EFFECTS: constructs gamestate with a name and empty list of players
    public GameState(String name) {
        this.name = name;
        currentPlayers = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds current players to this gamestate
    public void addPlayer(Player player) {
        currentPlayers.add(player);
    }

    // EFFECTS: returns an unmodifiable list of players in this gamestate
    public List<Player> getCurrentPlayers() {
        return Collections.unmodifiableList(currentPlayers);
    }

    //EFFECTS: returns number of players in this gamestate
    public int numCurrentPlayers() {
        return currentPlayers.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("currentPlayers", currentPlayersToJson());
        return json;
    }

    //EFFECTS: returns Players in this GameState as a JSON array
    private JSONArray currentPlayersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : currentPlayers) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

    //getters

    //EFFECTS: gets the name of the gamestate
    public String getName() {
        return name;
    }




}
