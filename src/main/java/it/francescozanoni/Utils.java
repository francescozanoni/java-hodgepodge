package it.francescozanoni;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class Utils {

    /**
     * @return current date/time is YYYY-MM-DD HH:MM:SS format
     * @see <a href="https://www.javatpoint.com/java-get-current-date"></a>
     */
    public static String getCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);
    }

    /**
     * BE AWARE: THIS METHOD WORKS ONLY IF APPLICATION IS EXECUTED FROM APPLICATION ROOT
     *
     * @param rawRelativeFilePath file path related to application base path, with any path separator
     * @return absolute file path, with OS-compliant path separators
     */
    public static String getAbsoluteFilePathFromBase(String rawRelativeFilePath) {
        // https://stackoverflow.com/questions/4871051/how-to-get-the-current-working-directory-in-java
        String basePath = System.getProperty("user.dir");
        char pathSeparator = System.getProperty("file.separator").toCharArray()[0];
        // https://stackoverflow.com/questions/2242417/how-to-remove-the-backslash-in-string-using-regex-in-java
        String relativeFilePath = rawRelativeFilePath.replace("/", String.valueOf(pathSeparator))
                .replace("\\", String.valueOf(pathSeparator));
        if (relativeFilePath.charAt(0) == pathSeparator) {
            relativeFilePath = relativeFilePath.substring(1);
        }
        return basePath + pathSeparator + relativeFilePath;
    }
    
    public static String getBasePath() throws IOException {
        return new File(".").getCanonicalPath();
    }

}
