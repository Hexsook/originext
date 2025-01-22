package hexsook.originext.format;

import java.util.List;

/**
 * Root string format class.
 */
public abstract class Format {

    protected String handleNullOrEmpty(String input) {
        if (input == null) {
            return "";
        }
        return input;
    }

    public abstract String format(String input);
    public abstract List<String> format(List<String> input);
}
