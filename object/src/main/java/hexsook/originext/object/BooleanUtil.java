package hexsook.originext.object;

/**
 * Provides methods for processing boolean.
 */
public class BooleanUtil {

    /**
     * Checks if an object is a boolean.
     * @see #getBoolean(Object)
     */
    public static boolean isBoolean(Object object) {
        try {
            getBoolean(object);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

   /**
     * Gets boolean from the Object. If the string "true"/"false" is used, 
    * the corresponding Boolean value is returned.
     * If it is Boolean itself, it will be forcibly converted to Boolean and returned.
     */
    public static boolean getBoolean(Object object) {
        if (ObjectUtil.isObject(object, String.class)) {
            String string = (String) object;
            return "true".equalsIgnoreCase(string);
        }

        if (ObjectUtil.isObject(object, Boolean.class)) {
            return (Boolean) object;
        }

        if (ObjectUtil.isObject(object, boolean.class)) {
            return (boolean) object;
        }

        throw new IllegalArgumentException("Object is not a boolean or a string contains boolean");
    }

    private BooleanUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
