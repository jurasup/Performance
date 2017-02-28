package testing;

import exceptions.NotExecutedException;
import util.WebDriverProvider;

import java.util.ArrayList;
import java.util.List;

import static util.CSVReporter.SEPARATOR;

/**
 * Provides functionality to handle (execute and log) test scenarios.
 * @author Yury_Suponeu
 */
public class TestScenarioHandler {
    private int iterationsNumber;
    private List<String> log = new ArrayList<>();

    public TestScenarioHandler(int iterationsNumber){
        this.iterationsNumber = iterationsNumber;
    }

    /**
     * Allows to execute test scenarios.
     */
    public void executeScenarios() throws NotExecutedException {
        List<TestElement> testElements = new ArrayList<>();
        testElements.addAll(initializeOpenPageTestElements());
        int testIndex = 0;
        for (TestElement element : testElements) {
            testIndex++;
            for (int i = 0; i < iterationsNumber; i++){
                element.executeTest();
            }
            log.add(testIndex + SEPARATOR + element.getLog());
        }
    }

    /**
     * Allows to get log.
     * @return log
     * @throws NotExecutedException if trying to get log before test execution
     */
    public List<String> getLog() throws NotExecutedException {
        if (log.isEmpty()) {
            throw new NotExecutedException();
        } else {
            return log;
        }
    }

    /**
     * Creates test elements for opening page.
     * @return list of test elements
     */
    private List<TestElement> initializeOpenPageTestElements(){
        List<TestElement> testElements = new ArrayList<>();
        testElements.add(new OpenPageTestElement(WebDriverProvider.EDGE));
        testElements.add(new OpenPageTestElement(WebDriverProvider.CHROME));
        testElements.add(new OpenPageTestElement(WebDriverProvider.GECKO));
        return testElements;
    }
}
