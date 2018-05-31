/**
 * 
 */
package zipi.concurrent.exception;

/**
 * Exception thrown when attempt to submit invalid token is detected.
 * 
 * @author pbansal
 *
 */
public class InvalidTokenSubmissionException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidTokenSubmissionException(final String message) {
        super(message);
    }

    public InvalidTokenSubmissionException() {
        super();
    }
}
