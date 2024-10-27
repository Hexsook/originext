package hexsook.originext.object;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Provides methods for processing date.
 */
public class DateUtil {

    /**
     * Formats the provided Date as a string from the time zone.
     */
    public static String format(String pattern, Date date, TimeZone timezone) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        if (timezone != null) {
            format.setTimeZone(timezone);
        }
        return format.format(date);
    }

    /**
     * Formats the provided Date as a string from the time zone.
     * @see #format(String, Date, TimeZone)
     */
    public static String formatCurrent(String pattern, TimeZone timezone) {
        return format(pattern, new Date(), timezone);
    }
    
    private DateUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
