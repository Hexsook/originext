package hexsook.originext.format.presets;

import hexsook.originext.format.Format;
import hexsook.originext.format.Formatter;
import hexsook.originext.object.Strings;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
        this.placeholderFormat = new NumberedPlaceholderFormat(replacers);
    }

    @Override
    public String format(String input) {
        if (Strings.isNullOrBlank(input)) {
            return handleNullOrEmpty(input);
        }

        String formatted = replaceBraces(input, new AtomicInteger());
        return Formatter.format(formatted, placeholderFormat);
    }

    @Override
    public List<String> format(List<String> input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        AtomicInteger index = new AtomicInteger();
        return input.stream()
                .map(s -> replaceBraces(s, index))
                .map(s -> Formatter.format(s, placeholderFormat))
                .collect(Collectors.toList());
    }

    private String replaceBraces(String input, AtomicInteger index) {
        StringBuilder result = new StringBuilder(input);

        int start = result.indexOf("{}");
        while (start != -1) {
            String replacement = String.format("{%d}", index.getAndIncrement());
            result.replace(start, start + 2, replacement);
            start = result.indexOf("{}", start + replacement.length());
        }

        return result.toString();
    }
}