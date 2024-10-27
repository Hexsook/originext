package hexsook.originext.object;

/**
 * Provides methods for processing characters.
 */
public class CharacterUtil {

    /**
     * Repeats the specified character and combine them.
     */
    public static String repeat(char c, int amount) {
        return Strings.repeat(String.valueOf(c), amount);
    }
    
    private CharacterUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
