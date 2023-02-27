

package exceptions;
/**
 *This class an exception of Invalid Command an error based on what happened while running.
 * This class RuntimeExceptions of Invalid Command input, it presents an invalid command
 *  Tossed inside the parser class and inside the tags factory class
 *  In parser it is discarded when there are a few / too many arguments
 *  and also when some of the syntax is incorrect
 *@see RuntimeException
 */

public class InvalidCommandException extends RuntimeException {

    public InvalidCommandException() {
        super("invalid command");
    }
}
