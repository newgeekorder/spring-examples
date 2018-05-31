/**
 * 
 */
package zipi.concurrent.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import zipi.concurrent.exception.InvalidTokenSubmissionException;

@Component
public class TokenCounter {

    /**
     * Number of tokens available.
     */
    private int numberOfTokens = 20;

    /**
     * First token index in list.
     */
    private static final int FIRST_TOKEN_INDEX = 0;
    /**
     * Tokens that can be availed for resource access.
     */
    private volatile List<Token> tokens;

    public TokenCounter() {
        super();
        init();
    }

    /**
     * Constructor.
     * 
     * @param numberOfTokens
     *            Number of available tokens.
     */
    public TokenCounter(final int numberOfTokens) {
        super();
        this.numberOfTokens = numberOfTokens;
        init();
    }

    /**
     * Initialize tokens list.
     */
    private void init() {
        tokens = new ArrayList<>(numberOfTokens);
        for (int tokenCount = 1; tokenCount <= numberOfTokens; tokenCount++) {
            tokens.add(new Token(this));
        }
    }

    /**
     * Gets the token to access the resource if available.
     * 
     * @return Token required to access resource.
     * @throws InterruptedException
     *             Thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted,
     *             either before or during the activity
     */
    public synchronized Token getToken() throws InterruptedException {
        Token token = null;
        if (tokens.isEmpty()) {
            this.wait();
        }
        token = tokens.remove(FIRST_TOKEN_INDEX);

        return token;
    }

    /**
     * Submits the token back to token counter for reuse.
     * 
     * @param token
     *            Token to be submitted.
     * @throws InvalidTokenSubmissionException
     *             If token submitted is invalid.
     */
    public synchronized void submitToken(final Token token) throws InvalidTokenSubmissionException {
        if (this.tokens.size() == numberOfTokens) {
            throw new InvalidTokenSubmissionException("Number of tokens is already complete in token counter pool");
        } else {
            this.tokens.add(token);
            this.notifyAll();
        }

    }

    /**
     * Build a token counter instance.
     * 
     * @param numberOfTokens
     *            number of tokens it should contain.
     * @return Token counter instance
     */
    public static TokenCounter build(final int numberOfTokens) {
        return new TokenCounter(numberOfTokens);
    }
}
