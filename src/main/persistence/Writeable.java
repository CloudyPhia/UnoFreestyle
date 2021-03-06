package persistence;

import org.json.JSONObject;

//Represents a writeable interface

/*
 * CITATION: Writeable code obtained (and modified) from JsonSerializationDemo - Writeable interface
 *           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

public interface Writeable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
