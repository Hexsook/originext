package hexsook.originext.object;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides methods for processing strings.
 */
public class Strings {

    /**
     * Checks if a string is empty or all filled with whitespaces.
     */
    public static boolean isAllWhitespace(String string) {
        return string.trim().isEmpty();
    }

    /**
     * Checks if a string is null or empty.
     */
    public static boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    /**
     * Checks if a string is null or all filled with whitespaces.
     * @see #isAllWhitespace(String)
     */
    public static boolean isNullOrWhite(String input) {
        return isNullOrEmpty(input) || isAllWhitespace(input);
    }

    /**
     * Case ignoring version of {@link String#contains(CharSequence)}.
     */
    public static boolean containsIgnoreCase(String string, String pattern) {
        if (string == null || pattern == null) {
            return false;
        }

        int patternLength = pattern.length();
        int strLength = string.length();
        int maxIndex = strLength - patternLength;

        for (int i = 0; i <= maxIndex; i++) {
            if (string.regionMatches(true, i, pattern, 0, patternLength)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Case ignoring version of {@link String#replace(CharSequence, CharSequence)}.
     */
    public static String replaceIgnoreCase(String string, String pattern, String replacement) {
        Pattern pat = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pat.matcher(string);
        return matcher.replaceAll(replacement);
    }

    /**
     * Calculates similarity between two strings.
     */
    public static double calculateSimilarity(String s1, String s2) {
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        for (char c : s1.toCharArray()) {
            set1.add(c);
        }

        for (char c : s2.toCharArray()) {
            set2.add(c);
        }

        Set<Character> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<Character> union = new HashSet<>(set1);
        union.addAll(set2);
        return (double) intersection.size() / union.size();
    }

    /**
     * Uses regular expressions to match if a string contains characters.
     */
    public static boolean contains(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    /**
     * Combines all elements in a string list into one string with the specified character as the separate.
     *
     * @param fromIndex From index (include)
     * @param toIndex To index (exclude)
     */
    public static String join(List<String> list, int fromIndex, int toIndex, String pattern) {
        StringBuilder builder = new StringBuilder();
        List<String> indexed = list.subList(fromIndex, toIndex);
        for (int i = 0; i < indexed.size(); i++) {
            builder.append(indexed.get(i));
            if (i < indexed.size() - 1) {
                builder.append(pattern);
            }
        }
        return builder.toString();
    }

    /**
     * Combines all elements in a string array into one string with the specified character as the separate.
     * @see #join(List, int, int, String)
     */
    public static String join(String[] args, int fromIndex, int toIndex, String pattern) {
        return join(Arrays.asList(args), fromIndex, toIndex, pattern);
    }

    /**
     * Combines all elements in a string list into one string with the specified character as the separate.
     * @param fromIndex From index (include)
     */
    public static String join(List<String> list, int fromIndex, String pattern) {
        return join(list, fromIndex, list.size(), pattern);
    }

    /**
     * Combines all elements in a string array into one string with the specified character as the separate.
     * @see #join(List, int, String)
     */
    public static String join(String[] args, int fromIndex, String pattern) {
        return join(Arrays.asList(args), fromIndex, pattern);
    }

    /**
     * Repeats the specified string and combine them.
     */
    public static String repeat(String string, int count) {
        if (string == null) {
            throw new IllegalArgumentException("Cannot repeat a null string");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(string);
        }
        return sb.toString();
    }

    public Strings() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
