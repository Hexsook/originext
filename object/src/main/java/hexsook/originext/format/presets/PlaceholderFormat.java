package hexsook.originext.format.presets;

import hexsook.originext.format.Format;
import hexsook.originext.object.Strings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Preset rule of string format.
 */
public class PlaceholderFormat extends Format {

    public static PlaceholderFormat create(String placeholderSection, Map<String, Object> comparison) {
        return new PlaceholderFormat(placeholderSection, comparison);
    }

    public static Builder builder() {
        return new Builder();
    }

    private final String placeholderSection;
    private final Map<String, Object> comparison;

    private PlaceholderFormat(String placeholderSection, Map<String, Object> comparison) {
        this.placeholderSection = placeholderSection == null ? "{@*}" : placeholderSection;
        this.comparison = comparison;
    }

    @Override
    public String format(String input) {
        if (Strings.isNullOrBlank(input)){
            return handleNullOrEmpty(input);
        }

        if (comparison.isEmpty()) {
            return input;
        }

        String startSymbol;
        String endSymbol;

        try {
            startSymbol = placeholderSection.substring(0, placeholderSection.indexOf("@*"));
            endSymbol = placeholderSection.substring(placeholderSection.indexOf("@*") + 2);
        } catch (StringIndexOutOfBoundsException e) {
            startSymbol = "{";
            endSymbol = "}";
        }

        Pattern pattern = Pattern.compile(Pattern.quote(startSymbol) + "(.*?)" + Pattern.quote(endSymbol));
        Matcher matcher = pattern.matcher(input);
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String placeholder = matcher.group(1);
            matcher.appendReplacement(result, comparison.getOrDefault(placeholder, "").toString());
        }
        matcher.appendTail(result);
        return result.toString();
    }

    @Override
    public List<String> format(List<String> input) {
        return input.stream().map(this::format).collect(Collectors.toList());
    }

    public static class Builder {

        private String placeholderSection = "{@*}";
        private final Map<String, Object> comparison = new HashMap<>();

        private Builder() {
        }

        public Builder placeholder(String placeholderSection) {
            this.placeholderSection = placeholderSection;
            return this;
        }

        public Builder append(String placeholder, Object replacer) {
            comparison.put(placeholder, replacer);
            return this;
        }

        public PlaceholderFormat build() {
            return new PlaceholderFormat(placeholderSection, comparison);
        }
    }
}
