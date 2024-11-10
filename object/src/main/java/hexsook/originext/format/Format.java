package hexsook.originext.format;

import hexsook.originext.object.Strings;

import java.util.List;

/**
 * Root string format class.
 */
public abstract class Format {

    protected String handleNullOrEmpty(String input) {
        if (input == null) {
            return null;
        }

        if (input.isEmpty() || Strings.isAllWhitespace(input)) {
            return input;
        }

        throw new IllegalStateException("No proper conditions matched");
    }

    public abstract String format(String input);
    public abstract List<String> format(List<String> input);
}
