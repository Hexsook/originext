package hexsook.originext;

/**
 * A singleton class representing the absence of a value.
 * <p>
 * This class can be used as a placeholder where a value is required but
 * there is nothing meaningful to provide.
 * </p>
 */
public class Nothing {

    public static Nothing NOTHING = new Nothing();

    private Nothing() {
    }
}