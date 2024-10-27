package hexsook.originext.format;

import java.util.List;

/**
 * Formatter class of string formats.
 */
public class Formatter {

    /**
     * Format a string.
     * The higher the format rule, the higher the priority, that is, the format is executed sequentially
     */
    public static String format(String input, Format... formats) {
        for (Format format : formats) {
            input = format.format(input);
        }
        return input;
    }

    /**
     * Format a string list.
     * @see #format(String, Format...)
     */
    public static List<String> format(List<String> input, Format... formats) {
        for (Format format : formats) {
            input = format.format(input);
        }
        return input;
    }

    private Formatter() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

}
