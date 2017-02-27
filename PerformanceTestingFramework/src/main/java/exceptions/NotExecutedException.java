package exceptions;

/**
 * Can be thrown while trying get log before executing tests.
 * @author Yury_Suponeu
 */
public class NotExecutedException extends Exception {
    private static final String ERROR_MESSAGE = "Error: Trying to ger log before test execution!";

    public NotExecutedException(){
        super(ERROR_MESSAGE);
    }
}
