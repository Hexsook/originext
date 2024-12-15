package hexsook.originext.format.presets;

import hexsook.originext.format.Format;
import hexsook.originext.object.Strings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        this.placeholderSection = placeholderSection;
        this.comparison = comparison;
    }

    @Override
    public String format(String input) {
        if (Strings.isNullOrWhite(input)){
            return handleNullOrEmpty(input);
        }

        String startSymbol = placeholderSection.substring(0, placeholderSection.indexOf("@a"));
        String endSymbol = placeholderSection.substring(placeholderSection.indexOf("@a") + 2);

        for (String placeholder : comparison.keySet()) {
            input = input.replace(startSymbol + placeholder + endSymbol, comparison.get(placeholder).toString());
        }

        return input;
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
