package testing;

import exceptions.NotExecutedException;

/**
 * Provides functionality to handle performance test element.
 * @author Yury_Suponeu
 */
public abstract class TestElement {
    /**
     * Executes certain test.
     */
    public abstract void executeTest();

    /**
     * Allows to get log.
     * @throws NotExecutedException if trying to get log before execution
     * @return log string
     */
    public abstract String getLog() throws NotExecutedException;

    protected abstract void setLog();
}
