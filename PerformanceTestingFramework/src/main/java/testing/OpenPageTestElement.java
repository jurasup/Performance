package testing;

import exceptions.NotExecutedException;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebDriverProvider;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Provides functionality to test performance of open page operation.
 * @author Yury_Suponeu
 */
public class OpenPageTestElement extends TestElement {
    private static final String SEPARATOR = ",";
    //URL to test
    private static final String URL = "https://www.google.com";
    //Search bar element id
    private static final String ELEMENT_ID = "lst-ib";
    private static final int TIMEOUT = 10;

    private String driverName;
    private WebDriver driver;
    private String log;
    private double duration;

    /**
     * Creates test element instance with defined {@code WebDriver}.
     * @param driverName
     */
    public OpenPageTestElement(String driverName){
        this.driverName = driverName;
    }

    @Override
    public void executeTest() {
        driver = new WebDriverProvider().getWebDriver(driverName);
        //driver.manage().deleteAllCookies();
        duration = measurePageOpening();
        setLog();
    }

    @Override
    public String getLog() throws NotExecutedException {
        if (log == null) {
            throw new NotExecutedException();
        } else {
            return log;
        }
    }

    @Override
    protected void setLog(){
        Capabilities capabilities = ((RemoteWebDriver)driver).getCapabilities();
        NumberFormat formatter = new DecimalFormat("#0.000");
        log = capabilities.getBrowserName() + SEPARATOR + capabilities.getVersion() + SEPARATOR + formatter.format(duration) + System.lineSeparator();
    }

    /**
     * Measures duration of page loading.
     * @return duration in seconds
     */
    private double measurePageOpening() {
        long startTime = System.nanoTime();
        driver.get(URL);
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(ELEMENT_ID)));
        long endTime = System.nanoTime();
        driver.quit();
        return (double)(endTime - startTime) / 1000000000;
    }
}
