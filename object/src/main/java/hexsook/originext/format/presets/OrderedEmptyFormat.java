package hexsook.originext.format.presets;

import hexsook.originext.format.Format;
import hexsook.originext.format.Formatter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Preset rule of string format.
 */
public class OrderedEmptyFormat extends Format {

    public static OrderedEmptyFormat create(Object... replacers) {
        return new OrderedEmptyFormat(replacers);
    }

    private final NumberedPlaceholderFormat placeholderFormat;

    public OrderedEmptyFormat(Object... replacers) {
        placeholderFormat = new NumberedPlaceholderFormat(replacers);
    }

    @Override
    public String format(String input) {
        return Formatter.format(replaceBraces(input), placeholderFormat);
    }

    @Override
    public List<String> format(List<String> input) {
        return Formatter.format(input.stream().map(this::replaceBraces).collect(Collectors.toList()), placeholderFormat);
    }

    private String replaceBraces(String input) {
        int index = 0;
        StringBuilder result = new StringBuilder(input);

        int start = result.indexOf("{}");
        while (start != -1) {
            String replacement = String.format("{%d}", index++);
            result.replace(start, start + 2, replacement);
            start = result.indexOf("{}", start + replacement.length());
        }

        return result.toString();
    }
}
