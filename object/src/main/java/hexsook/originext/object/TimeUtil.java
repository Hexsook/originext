package hexsook.originext.object;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Provides methods for processing time format.
 */
public class TimeUtil {

    /**
     * Format second-level numbers in the format of hh:mm:ss.
     * @return Formatted time
     */
    public static String formatSeconds(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remainingSeconds = seconds % 60;

        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
        } else {
            return String.format("%02d:%02d", minutes, remainingSeconds);
        }
    }

    /**
     * Format the timestamp from the provided time zone.
     * @return Formatted time
     */
    public static String formatTimestamp(long timestamp, String pattern, String timezone) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDate date = instant.atZone(TimeZone.getTimeZone(timezone).toZoneId()).toLocalDate();
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    private TimeUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

}
