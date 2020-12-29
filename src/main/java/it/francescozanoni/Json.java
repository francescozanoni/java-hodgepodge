package it.francescozanoni;

import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;

import it.francescozanoni.concurrency.http.ResourceFileReader;

/**
 * Read JSON files
 */
public class Json {

    public static void main(String[] args) throws IOException {

        // Load content of JSON array file.
        String jsonArrayString = ResourceFileReader.run("array.json");

        // @see https://github.com/stleary/JSON-java
        JSONArray jsonArray = new JSONArray(jsonArrayString);

        System.out.println(jsonArray);
        
        for (Object o : jsonArray) {
            System.out.println(o.getClass());
        }
        
        // Load content of JSON object file.
        String jsonObjectString = ResourceFileReader.run("object.json");

        JSONObject jsonObject = new JSONObject(jsonObjectString);

        System.out.println(jsonObject);
        System.out.println(jsonObject.get("a"));
        System.out.println(jsonObject.opt("inexistent"));

    }

}