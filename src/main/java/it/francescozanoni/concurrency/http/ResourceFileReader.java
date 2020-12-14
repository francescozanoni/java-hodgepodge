package it.francescozanoni.concurrency.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Read a file resource as string.
 */
public class ResourceFileReader {

    // @see https://www.baeldung.com/reading-file-in-java
    public static String run(String resourceFileName) throws IOException {

        // @see https://stackoverflow.com/questions/2593154/get-a-resource-using-getresource
        InputStream inputStream = ResourceFileReader.class.getClassLoader().getResourceAsStream(resourceFileName);

        StringBuilder stringBuilder = new StringBuilder();
        assert inputStream != null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        }

        return stringBuilder.toString();
    }

}