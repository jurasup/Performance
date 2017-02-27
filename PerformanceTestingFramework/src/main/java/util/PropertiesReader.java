package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Provides basic functionality to handle properties.
 * @author Yury_Suponeu
 */
public class PropertiesReader {
    /**
     * Allows to read properties from resource file.
     * @param fileToReadFrom
     * @return properties
     * @throws IOException if file wasn't found
     */
    public static Properties getProperties(String fileToReadFrom) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(fileToReadFrom);
        properties.load(inputStream);
        return properties;
    }
}
