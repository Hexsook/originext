package hexsook.originext.format;

import hexsook.originext.format.presets.OrderedEmptyFormat;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderedEmptyFormatTest {

    @Test
    public void testString() {
        String input = "Here is something {} is {} cool";
        String expected = "Here is something that is very cool";
        String result = Formatter.format(input, OrderedEmptyFormat.create("that", "very"));
        assertEquals(expected, result);
    }

    @Test
    public void testList() {
        List<String> input = Arrays.asList(
                "Here is something {} is {} cool",
                "This is not {}"
        );
        List<String> expected = Arrays.asList(
                "Here is something that is very cool",
                "This is not nice"
        );
        List<String> result = Formatter.format(input, OrderedEmptyFormat.create("that", "very", "nice"));
        assertEquals(expected, result);
    }
}
