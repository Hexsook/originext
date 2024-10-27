package hexsook.originext.object;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Provides methods for processing maps.
 */
public class Maps {

    /**
     * Uses the value to query all entries that meet the conditions in the map.
     */
    public static <K, V> Map.Entry<K, V>[] reverseEntriesByValue(Map<K, V> map, V value) {
        List<Map.Entry<K, V>> entries = new ArrayList<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                entries.add(entry);
            }
        }

        @SuppressWarnings("unchecked")
        Map.Entry<K, V>[] array = (Map.Entry<K, V>[]) Array.newInstance(Map.Entry.class, entries.size());
        return entries.toArray(array);
    }
    
    private Maps() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
