package hexsook.originext.format;

import hexsook.originext.format.presets.PlaceholderFormat;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaceholderFormatTest {

    @Test
    public void testString() {
        String input = "Here is something <what> is <thing> cool";
        String expected = "Here is something that is very cool";
        String result = Formatter.format(input, PlaceholderFormat.builder()
                .placeholder("<@*>")
                .append("what", "that")
                .append("thing", "very")
                .build());
        assertEquals(expected, result);
    }

    @Test
    public void testList() {
        List<String> input = Arrays.asList(
                "Here is something <what> is <thing> cool",
                "This is not <thing> <good>"
        );
        List<String> expected = Arrays.asList(
                "Here is something that is very cool",
                "This is not very nice"
        );
        List<String> result = Formatter.format(input, PlaceholderFormat.builder()
                .placeholder("<@*>")
                .append("what", "that")
                .append("thing", "very")
                .append("good", "nice")
                .build());
        assertEquals(expected, result);
    }
}
