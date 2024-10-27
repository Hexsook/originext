package hexsook.originext.reflection;

/**
 * Reflection operations.
 */
public class Reflection {

    public static String getPackageName(Class<?> clazz) {
        String[] packagePathSplit = getPackagePath(clazz).split("\\.");
        return packagePathSplit[packagePathSplit.length - 1];
    }

    public static String getPackagePath(Class<?> clazz) {
        return clazz.getPackage().getName();
    }

    public static String getClassName(Class<?> clazz) {
        String[] nameSplit = clazz.getName().split("\\.");
        return nameSplit[nameSplit.length -1];
    }
}
