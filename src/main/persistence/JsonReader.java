package persistence;

import model.Card;
import model.GameState;
import model.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;


    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameState read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameState(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses gamestate from JSON object and returns it
    private GameState parseGameState(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        GameState gs = new GameState(name);
        addCurrentPlayers(gs, jsonObject);
        return gs;
    }

    // MODIFIES: gs
    // EFFECTS: parses currentPlayers from JSON object and adds them to gamestate
    private void addCurrentPlayers(GameState gs, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("currentPlayers");

        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(gs, nextPlayer);
        }

    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addPlayer(GameState gs, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Player player = new Player(name);
        addCards(player, jsonObject);
        gs.addPlayer(player);
    }

    private void addCards(Player player, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cards");

        for (Object json : jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            addCard(player, nextCard);
        }
    }

    private void addCard(Player player, JSONObject jsonObject) {
        String colour = jsonObject.getString("colour");
        Integer number = jsonObject.getInt("number");
        Card card = new Card(colour, number);
        player.addCardToHand(card);

    }

}
