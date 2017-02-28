import exceptions.NotExecutedException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import testing.OpenPageTestElement;
import testing.TestScenarioHandler;
import util.CSVReporter;
import util.Reporter;
import util.WebDriverProvider;

import java.io.IOException;

/**
 * Defines entry point for application providing functionality to test performance of website.
 * @author Yury_Suponeu
 */
public class Launcher {
    private static final int NUMBER_OF_ITERATIONS = 5;

    public static void main(String[] args) {
        TestScenarioHandler scenarioHandler = new TestScenarioHandler(NUMBER_OF_ITERATIONS);
        Reporter reporter = new CSVReporter();
        try {
            scenarioHandler.executeScenarios();
            reporter.report(scenarioHandler.getLog());
        } catch (NotExecutedException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
