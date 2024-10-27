package hexsook.originext.format.presets;

import hexsook.originext.format.Format;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Preset rule of string format.
 */
public class ListOrderFormat extends Format {

    public static ListOrderFormat create(ListOrderRule rule) {
        return new ListOrderFormat(rule);
    }

    private final ListOrderRule rule;

    private ListOrderFormat(ListOrderRule rule) {
        this.rule = rule;
    }

    @Override
    public String format(String input) {
        throw new UnsupportedOperationException("Formatting string is not supported");
    }

    public List<String> format(List<String> input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        switch (rule) {
            case A_Z:
                Collections.sort(input);
                break;
            case Z_A:
                input.sort(Collections.reverseOrder());
                break;
            case LONGEST:
                input.sort(Comparator.comparingInt(String::length).reversed());
                break;
            case SHORTEST:
                input.sort(Comparator.comparingInt(String::length));
                break;
            default:
                throw new IllegalArgumentException("Unsupported order rule: " + rule);
        }
        return input;
    }

    public enum ListOrderRule {
        A_Z,
        Z_A,
        LONGEST,
        SHORTEST
    }
}
