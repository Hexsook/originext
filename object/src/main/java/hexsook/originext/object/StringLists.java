package hexsook.originext.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides methods for processing string lists.
 */
public class StringLists {

    /**
     * Case ignoring version of {@link List#contains(Object)}.
     */
    public static boolean containsIgnoreCase(List<String> list, String pattern) {
        for (String obj : list) {
            if (pattern.equalsIgnoreCase(obj)) return true;
        }
        return false;
    }

    /**
     * Splits the string into a list by using the line break character \n in the string as the delimiter.
     */
    public static List<String> splitByNewline(String string) {
        return new ArrayList<>(Arrays.asList(string.split("\n")));
    }

    /**
     * Uses the line break character \n of each string in the list as the delimiter
     * to split the list internally and merge it into the original list.
     *
     * @see #splitByNewline(String)
     */
    public static List<String> splitByNewline(List<String> originalList) {
        List<String> modifiedList = new ArrayList<>();

        for (String item : originalList) {
            if (item.contains("\n")) {
                String[] splitLines = item.split("\n");
                modifiedList.addAll(Arrays.asList(splitLines));
            } else {
                modifiedList.add(item);
            }
        }

        return modifiedList;
    }

    /**
     * Splits a string into multiple strings of a specified size and add them into a list.
     */
    public static List<String> splitIntoChunks(String string, int chunkSize) {
        List<String> chunks = new ArrayList<>();
        int length = string.length();

        for (int i = 0; i < length; i += chunkSize) {
            int end = Math.min(i + chunkSize, length);
            String chunk = string.substring(i, end);
            chunks.add(chunk);
        }

        return chunks;
    }

    /**
     * Splits the strings that exceed the length limit and merge them into the original list.
     * @see #splitIntoChunks(String, int)
     */
    public static List<String> splitInList(List<String> originalList, int chunkSize) {
        List<String> resultList = new ArrayList<>();

        for (String originalString : originalList) {
            for (int i = 0; i < originalString.length(); i += chunkSize) {
                int end = Math.min(i + chunkSize, originalString.length());
                resultList.add(originalString.substring(i, end));
            }
        }

        return resultList;
    }
    
    private StringLists() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
