package hexsook.originext.object;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods for processing lists.
 */
public class ListUtil {

    /**
     * Finds an element from a list by reflecting over the fields
     * in the element and matching them against the expected values.
     */
    public static <T> List<T> getElementByFieldValue(List<T> list, String field, Object expectedValue) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        List<T> elements = new ArrayList<>();
        for (T obj : list) {
            Field f;
            try {
                f = obj.getClass().getDeclaredField(field);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            f.setAccessible(true);
            try {
                if (f.get(obj).equals(expectedValue)) {
                    elements.add(obj);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return elements;
    }

    /**
     * Checks if it is a list of a specified generic type.
     */
    public static <T> boolean isList(List<Object> list, Class<T> clazz) {
        if (list.isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return ObjectUtil.isObject(list.get(0), clazz);
    }
}
