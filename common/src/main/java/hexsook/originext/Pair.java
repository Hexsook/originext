package hexsook.originext;

import java.util.Objects;

/**
 * A simple container to store a pair of related objects, typically used to
 * associate a key with a value.
 *
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public class Pair<K, V> {

    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair<>(key, value);
    }

    private final K key;
    private final V value;

    private Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Pair<?, ?> other = (Pair<?, ?>) obj;
        return (Objects.equals(key, other.key)) && (Objects.equals(value, other.value));
    }

    @Override
    public int hashCode() {
        int result = (key != null) ? key.hashCode() : 0;
        result = 31 * result + ((value != null) ? value.hashCode() : 0);
        return result;
    }
}
