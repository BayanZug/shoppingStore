

package exceptions;
/** This class an error based on what happened while running.
 *  handles exceptions of bad URL input,

 *@see Exception
 */
public class BadUrlException extends Exception {

    public BadUrlException() {
        super("bad url");
    }
}
