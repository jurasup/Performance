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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static util.CSVReporter.SEPARATOR;

/**
 * Provides functionality to test performance of open page operation.
 * @author Yury_Suponeu
 */
public class OpenPageTestElement extends TestElement {
    //URL to test
    private static final String URL = "https://www.google.com";
    //Search bar element id
    private static final String ELEMENT_ID = "lst-ib";
    private static final int TIMEOUT = 15;

    private String driverName;
    private WebDriver driver;
    private List<Double> durations = new ArrayList<>();

    /**
     * Creates test element instance with defined {@code WebDriver}.
     * @param driverName WebDriver's name (ex. "chrome");
     */
    public OpenPageTestElement(String driverName){
        this.driverName = driverName;
    }

    @Override
    public void executeTest() {
        driver = new WebDriverProvider().getWebDriver(driverName);
        durations.add(measurePageOpening());
        clearCookiesAndQuit();
    }

    @Override
    public String getLog() throws NotExecutedException {
        if (durations.isEmpty()) {
            throw new NotExecutedException();
        } else {
            Capabilities capabilities = ((RemoteWebDriver)driver).getCapabilities();
            NumberFormat formatter = new DecimalFormat("#0.000");
            return capabilities.getBrowserName() + SEPARATOR + capabilities.getVersion() + SEPARATOR +
                    formatter.format(getMinimalDuration()) + SEPARATOR + formatter.format(getAverageDuration()) +
                    SEPARATOR + formatter.format(get90Percentile()) + System.lineSeparator();
        }
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
        //driver.quit();
        return (double)(endTime - startTime) / 1000000000;
    }

    private double getAverageDuration(){
        Double sum = new Double(0.0);
        for (Double duration : durations) {
            sum += duration;
        }
        return sum / durations.size();
    }

    private double get90Percentile(){
        List<Double> sortedDurations = new ArrayList<>(durations);
        Collections.sort(sortedDurations);
        int index = (int)Math.ceil(sortedDurations.size() * 0.9);
        return sortedDurations.get(index - 1);
    }

    private double getMinimalDuration(){
        return Collections.min(durations);
    }

    private void clearCookiesAndQuit(){
        driver.manage().deleteAllCookies();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error: interrupted while clearing cookies!");
        }
        driver.quit();
    }
}
