package hexsook.originext.format.presets;

import hexsook.originext.format.Format;

import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Preset rule of string format.
 */
public class NumberFormattedFormat extends Format {

    public static NumberFormattedFormat create(String format) {
        return new NumberFormattedFormat(format);
    }

    private final String format;

    private NumberFormattedFormat(String format) {
        this.format = format;
    }

    @Override
    public String format(String input) {
        return formatNumbersInString(input, format);
    }

    @Override
    public List<String> format(List<String> input) {
        return input.stream().map(this::format).collect(Collectors.toList());
    }

    public String formatNumbersInString(String input, String format) {
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(input);
        StringBuffer result = new StringBuffer();

        DecimalFormat decimalFormat = new DecimalFormat(format);

        while (matcher.find()) {
            String number = matcher.group();
            double parsedNumber = Double.parseDouble(number);
            String formattedNumber = decimalFormat.format(parsedNumber);
            matcher.appendReplacement(result, formattedNumber);
        }
        matcher.appendTail(result);

        return result.toString();
    }
}
