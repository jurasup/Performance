package util;

import java.io.IOException;
import java.util.List;

/**
 * Provides functionality for tests reporting.
 * @author Yury_Suponeu
 */
public abstract class Reporter {
    /**
     * Allows to report test log.
     * @param log
     */
    public abstract void report(List<String> log) throws IOException;
}
