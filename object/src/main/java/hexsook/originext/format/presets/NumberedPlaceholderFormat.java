package hexsook.originext.format.presets;

import hexsook.originext.format.Format;
import hexsook.originext.format.Formatter;

import java.util.List;

/**
 * Preset rule of string format.
 */
public class NumberedPlaceholderFormat extends Format {

    public static NumberedPlaceholderFormat create(Object... replacers) {
        return new NumberedPlaceholderFormat(replacers);
    }

    private final PlaceholderFormat placeholderFormat;

    public NumberedPlaceholderFormat(Object... replacers) {
        PlaceholderFormat.Builder builder = PlaceholderFormat.builder();
        int index = 0;
        for (Object replacer : replacers) {
            builder.append(String.valueOf(index), replacer);
            index ++;
        }
        placeholderFormat = builder.build();
    }

    @Override
    public String format(String input) {
        return Formatter.format(input, placeholderFormat);
    }

    @Override
    public List<String> format(List<String> input) {
        return Formatter.format(input, placeholderFormat);
    }
}
