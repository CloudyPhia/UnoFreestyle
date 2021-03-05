package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameState implements Writeable {
    private String name;
    private List<Player> currentPlayers;

    // EFFECTS: constructs gamestate with a name and empty list of players
    public GameState(String name) {
        this.name = name;
        currentPlayers = new ArrayList<>();
    }

    public String getName() {
        return name;
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

    private JSONArray currentPlayersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : currentPlayers) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }




}
