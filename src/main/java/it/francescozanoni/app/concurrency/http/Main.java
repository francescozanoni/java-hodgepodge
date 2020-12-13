package it.francescozanoni.app.concurrency.http;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Arrays;
import java.util.Objects;

/**
 * Extract IBAN codes from several web pages.
 */
public class Main {

    public static void main(String[] args) {

        // @see https://stackoverflow.com/questions/2593154/get-a-resource-using-getresource
        // Objects.requireNonNull(...) has been suggested by IntelliJ IDEA.
        String urlJsonFilePath = Objects.requireNonNull(Main.class.getClassLoader().getResource("iban_urls.json")).getPath();

        // Load web pages URLs from JSON file.
        String[] urls = getStringArrayFromJsonFile(urlJsonFilePath);

        String[] ibanCodes = run(urls);

        System.out.println(Arrays.toString(ibanCodes));

    }

    public static String[] run(String[] urls) {

        String[] urlContents = Downloader.run(urls);

        return IbanExtractor.run(urlContents);

    }

    public static String[] getStringArrayFromJsonFile(String jsonFilePath) {

        String[] strings;

        // @see https://howtodoinjava.com/java/library/json-simple-read-write-json-examples
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(jsonFilePath)) {

            JSONArray stringsAsJsonArray = (JSONArray) jsonParser.parse(reader);

            // stringsAsJsonArray.toArray() cannot be used, as it triggers this exception:
            //   Exception in thread "main" java.lang.ClassCastException: class java.lang.Object cannot be cast to class java.lang.String

            strings = new String[stringsAsJsonArray.size()];
            for (int i = 0; i < stringsAsJsonArray.size(); i++) {
                strings[i] = stringsAsJsonArray.get(i).toString();
            }

        } catch (IOException | ParseException e) {
            strings = null;
        }

        return strings;

    }

}