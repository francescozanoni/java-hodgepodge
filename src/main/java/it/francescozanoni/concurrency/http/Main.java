package it.francescozanoni.concurrency.http;

import java.io.*;

import org.json.JSONArray;

import java.util.Arrays;

/**
 * Extract IBAN codes from several web pages.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        // Load content of JSON file reporting URLs.
        String json = ResourceFileReader.run("iban_urls.json");

        // Load web pages URLs from JSON string.
        String[] urls = getStringArrayFromJson(json);

        String[] ibanCodes = run(urls);

        System.out.println(Arrays.toString(ibanCodes));

    }

    /**
     * This is the real logic entry point, added to make testing easier.
     *
     * @param urls URLs of web pages from which IBAN codes are extracted
     * @return array of IBAN codes
     */
    public static String[] run(String[] urls) {

        String[] urlContents = Downloader.run(urls);

        return IbanExtractor.run(urlContents);

    }

    /**
     * Parse a JSON string as array of strings.
     *
     * @param json source JSON string
     * @return array of strings
     * TODO add check that JSON string actually contains an array of strings
     */
    public static String[] getStringArrayFromJson(String json) {

        // @see https://github.com/stleary/JSON-java
        JSONArray stringsAsJsonArray = new JSONArray(json);

        // stringsAsJsonArray.toArray() cannot be used, as it triggers this exception:
        //   Exception in thread "main" java.lang.ClassCastException: class java.lang.Object cannot be cast to class java.lang.String

        String[] stringsAsArray = new String[stringsAsJsonArray.length()];
        for (int i = 0; i < stringsAsJsonArray.length(); i++) {
            stringsAsArray[i] = stringsAsJsonArray.get(i).toString();
        }

        return stringsAsArray;

    }

}