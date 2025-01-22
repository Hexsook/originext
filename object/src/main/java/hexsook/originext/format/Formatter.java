package hexsook.originext.format;

import hexsook.originext.object.ListUtil;
import hexsook.originext.object.Strings;

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
        if (Strings.isNullOrBlank(input) || formats == null) {
            return input;
        }
        for (Format format : formats) {
            try {
                input = format.format(input);
            } catch (Exception e) {
                System.err.println("Error applying format " + format.getClass().getSimpleName());
                e.printStackTrace();
            }
        }
        return input;
    }

    /**
     * Format a string list.
     * @see #format(String, Format...)
     */
    public static List<String> format(List<String> input, Format... formats) {
        if (ListUtil.isNullOrEmpty(input) || formats == null) {
            return input;
        }
        for (Format format : formats) {
            try {
                input = format.format(input);
            } catch (Exception e) {
                System.err.println("Error applying format " + format.getClass().getSimpleName());
                e.printStackTrace();
            }
        }
        return input;
    }

    private Formatter() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

}
