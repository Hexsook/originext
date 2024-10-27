package hexsook.originext.object;

import java.text.DecimalFormat;

/**
 * Provides methods for processing number objects.
 */
public class NumberUtil {

    /**
     * Converts integer to Roman.
     */
    public static String intToRoman(int num) {
        int[] values = {
                1000, 900, 500, 400,
                100, 90, 50, 40,
                10, 9, 5, 4,
                1
        };
        String[] symbols = {
                "M", "CM", "D", "CD",
                "C", "XC", "L", "XL",
                "X", "IX", "V", "IV",
                "I"
        };

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                result.append(symbols[i]);
            }
        }
        return result.toString();
    }

    /**
     * Unitizes double by k, M, B, T.
     */
    public static String unitize(double number) {
        String[] suffixes = {"", "k", "M", "B", "T"};

        int suffixIndex = 0;
        while (number >= 1000 && suffixIndex < suffixes.length - 1) {
            number /= 1000;
            suffixIndex++;
        }

        return String.format("%.2f%s", number, suffixes[suffixIndex]);
    }


    /**
     * Formats double as String.
     */
    public static String format(double number, String pattern) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(number);
    }

    /**
     * Formats double as string in the format 1,234,567.89.
     * @see #format(double, String)
     */
    public static String formatCommon(double number) {
        return format(number, "#,###.##");
    }

    /**
     * Checks if a string is a number.
     * @see #getNumber(String, Class)
     */
    public static <T extends Number> boolean isNumber(String obj, Class<T> clazz) {
        try {
            getNumber(obj, clazz);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Parses number from string.
     */
    public static <T extends Number> T getNumber(String obj, Class<T> clazz) {
        if (clazz == Integer.class || clazz == int.class) {
            return clazz.cast(Integer.parseInt(obj));
        } else if (clazz == Double.class || clazz == double.class) {
            return clazz.cast(Double.parseDouble(obj));
        } else if (clazz == Long.class || clazz == long.class) {
            return clazz.cast(Long.parseLong(obj));
        } else if (clazz == Short.class || short.class == clazz) {
            return clazz.cast(Short.parseShort(obj));
        } else if (clazz == Float.class || float.class == clazz) {
            return clazz.cast(Float.parseFloat(obj));
        } else if (clazz == Byte.class || byte.class == clazz) {
            return clazz.cast(Byte.parseByte(obj));
        } else {
            throw new IllegalArgumentException("Unsupported number class " + clazz.getName());
        }
    }

    /**
     * Gets Number instance from number object.
     */
    public static Number getObject(Object obj) {
        if (!ObjectUtil.isObject(obj, Number.class)) {
            throw new IllegalArgumentException("Object is not a type Number");
        }
        return (Number) obj;
    }
    
    private NumberUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
