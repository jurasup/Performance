package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.util.Properties;

/**
 * Provides functionality to handle WebDriver instances.
 * @author Yury_Suponeu
 */
public class WebDriverProvider {
    private static final String DRIVERS_PROPERTIES_PATH = "src/main/resources/drivers.properties";
    private static Properties driversProperties;

    public static final String CHROME = "chrome";
    public static final String GECKO = "gecko";
    public static final String EDGE = "edge";

    //Static block to read and initialize paths to drivers.
    static {
        try {
            driversProperties = PropertiesReader.getProperties(DRIVERS_PROPERTIES_PATH);
            setSystemPropertyForDriver(CHROME);
            setSystemPropertyForDriver(GECKO);
            setSystemPropertyForDriver(EDGE);
        } catch (IOException e) {
            System.out.println("Error: Can't read drivers properties!");
            System.exit(-1);
        }
    }

    /**
     * Allows to get certain {@code WebDriver} instance by driver's name.
     * @param driverName
     * @return driver instance
     */
    public WebDriver getWebDriver(String driverName){
        switch (driverName){
            case CHROME:
                return new ChromeDriver();
            case GECKO:
                return new FirefoxDriver();
            case EDGE:
                return new EdgeDriver();
            default:
                return new ChromeDriver();
        }
    }

    private static void setSystemPropertyForDriver(String driver){
        System.setProperty("webdriver." + driver + ".driver", driversProperties.getProperty(driver));
    }
}
