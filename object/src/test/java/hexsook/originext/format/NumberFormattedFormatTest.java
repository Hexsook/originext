package hexsook.originext.format;

import hexsook.originext.format.presets.NumberFormattedFormat;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberFormattedFormatTest {

    @Test
    public void testString() {
        String input = "123456789.12121";
        String expected = "123,456,789.12";
        String result = Formatter.format(input, NumberFormattedFormat.create("#,###.##"));
        assertEquals(expected, result);
    }

    @Test
    public void testList() {
        List<String> input = Arrays.asList(
                "123456789.15121",
                "1234.89191946"
        );
        List<String> expected = Arrays.asList(
                "123,456,789.15",
                "1,234.89"
        );
        List<String> result = Formatter.format(input, NumberFormattedFormat.create("#,###.##"));
        assertEquals(expected, result);
    }
}
