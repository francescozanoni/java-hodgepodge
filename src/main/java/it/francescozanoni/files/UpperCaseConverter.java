package it.francescozanoni.files;

import it.francescozanoni.Utils;

import java.io.*;

/**
 * This class created a copy of a text file, by converting all characters to upper-case.
 *
 * Since start and end date/time are displayed, it's useful to benchmark several implementations with large input files.
 */
public class UpperCaseConverter {

    public static void main(String[] args) throws IOException {

        System.out.println(Utils.getCurrentDateTime());

        String inputFilePath = Utils.getAbsoluteFilePathFromBase("storage/input.txt");
        String outputFilePath = Utils.getAbsoluteFilePathFromBase("storage/output.txt");

        UpperCaseConverter.implementationA(inputFilePath, outputFilePath);

        System.out.println(Utils.getCurrentDateTime());

    }

    /**
     * This implementation takes about 37 seconds with .
     *
     * @param inputFilePath  path ot file to read text from
     * @param outputFilePath path to file to write upper-case text to
     * @throws IOException if something goes wrong with file access
     * @see <a href="https://www.baeldung.com/reading-file-in-java"></a>
     */
    public static void implementationA(String inputFilePath, String outputFilePath) throws IOException {

        try (BufferedReader inputReader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = inputReader.readLine()) != null) {
                outputWriter.append(line.toUpperCase()).append("\n");
            }

        }

    }

}