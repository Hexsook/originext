package hexsook.originext.object;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides methods for processing durations
 */
public class Durations {

    /**
     * Parses duration from string.
     */
    public static Duration parseDuration(String input) {
        Pattern pattern = Pattern.compile(
                "(?:(\\d+)\\s*y)?\\s*" +
                        "(?:(\\d+)\\s*M)?\\s*" +
                        "(?:(\\d+)\\s*w)?\\s*" +
                        "(?:(\\d+)\\s*d)?\\s*" +
                        "(?:(\\d+)\\s*h)?\\s*" +
                        "(?:(\\d+)\\s*m)?\\s*" +
                        "(?:(\\d+)\\s*s)?"
        );
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid duration format: " + input);
        }

        int years = parseGroup(matcher, 1);
        int months = parseGroup(matcher, 2);
        int weeks = parseGroup(matcher, 3);
        int days = parseGroup(matcher, 4);
        int hours = parseGroup(matcher, 5);
        int minutes = parseGroup(matcher, 6);
        int seconds = parseGroup(matcher, 7);

        long totalDays = years * 365L + months * 30L + weeks * 7L + days;

        return Duration.ofDays(totalDays)
                .plusHours(hours)
                .plusMinutes(minutes)
                .plusSeconds(seconds);
    }

    private static int parseGroup(Matcher matcher, int group) {
        String value = matcher.group(group);
        return value == null ? 0 : Integer.parseInt(value);
    }
    
    private Durations() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
