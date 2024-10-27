package hexsook.originext.config;

import java.util.*;

/**
 * Base class of configuration.
 */
public class BaseConfiguration {

    private final Map<String, Object> map = new LinkedHashMap<>();

    public BaseConfiguration(Map<?, ?> map) {
        load(map);
    }

    public void load(Map<?, ?> map) {
        map.forEach((key, value) -> {
            String strKey = key == null ? "NULL" : key.toString();
            this.map.put(strKey, value instanceof Map<?, ?> ? new BaseConfiguration((Map<?, ?>) value) : value);
        });
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public String getChild(String path) {
        int index = path.indexOf(".");
        return (index == -1) ? null : path.substring(index + 1);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String path, T def) {
        int firstDot = path.indexOf(".");
        if (firstDot == -1) {
            return (T) Optional.ofNullable(map.get(path)).orElse(def);
        }
        BaseConfiguration section = (BaseConfiguration) map.get(path.substring(0, firstDot));
        return (section == null) ? def : section.get(getChild(path), def);
    }

    public Object get(String path) {
        return get(path, null);
    }

    public <T> T getOrSet(String path, T newValue) {
        T original = get(path, null);
        if (original != null) {
            return original;
        }
        set(path, newValue);
        return newValue;
    }

    public void set(String path, Object value) {
        int firstDot = path.indexOf(".");
        if (firstDot == -1) {
            if (value == null) {
                map.remove(path);
            } else {
                map.put(path, value instanceof Map<?, ?> ? new BaseConfiguration((Map<?, ?>) value) : value);
            }
        } else {
            BaseConfiguration section = (BaseConfiguration) map.get(path.substring(0, firstDot));
            if (section == null) {
                section = new BaseConfiguration(new LinkedHashMap<>());
                map.put(path.substring(0, firstDot), section);
            }
            section.set(getChild(path), value);
        }
    }

    public boolean containsKey(String path) {
        return get(path) != null;
    }

    public void setIfAbsent(String path, Object value) {
        if (!containsKey(path)) {
            set(path, value);
        }
    }

    public BaseConfiguration getSection(String path) {
        return (BaseConfiguration) get(path);
    }

    public Collection<String> getKeys() {
        return new LinkedHashSet<>(map.keySet());
    }

    public byte getByte(String path) {
        return getByte(path, (byte) -1);
    }

    public byte getByte(String path, byte def) {
        Number val = get(path, def);
        return (val != null) ? val.byteValue() : def;
    }

    public List<Byte> getByteList(String path) {
        return getList(path, Byte.class);
    }

    public short getShort(String path) {
        return getShort(path, (short) -1);
    }

    public short getShort(String path, short def) {
        Number val = get(path, def);
        return (val != null) ? val.shortValue() : def;
    }

    public List<Short> getShortList(String path) {
        return getList(path, Short.class);
    }

    public int getInteger(String path) {
        return getInteger(path, -1);
    }

    public int getInteger(String path, int def) {
        Number val = get(path, def);
        return (val != null) ? val.intValue() : def;
    }

    public List<Integer> getIntegerList(String path) {
        return getList(path, Integer.class);
    }

    public long getLong(String path) {
        return getLong(path, -1);
    }

    public long getLong(String path, long def) {
        Number val = get(path, def);
        return (val != null) ? val.longValue() : def;
    }

    public List<Long> getLongList(String path) {
        return getList(path, Long.class);
    }

    public float getFloat(String path) {
        return getFloat(path, -1);
    }

    public float getFloat(String path, float def) {
        Number val = get(path, def);
        return (val != null) ? val.floatValue() : def;
    }

    public List<Float> getFloatList(String path) {
        return getList(path, Float.class);
    }

    public double getDouble(String path) {
        return getDouble(path, -1);
    }

    public double getDouble(String path, double def) {
        Number val = get(path, def);
        return (val != null) ? val.doubleValue() : def;
    }

    public List<Double> getDoubleList(String path) {
        return getList(path, Double.class);
    }

    public boolean getBoolean(String path) {
        return getBoolean(path, false);
    }

    public boolean getBoolean(String path, boolean def) {
        Boolean val = get(path, def);
        return (val != null) ? val : def;
    }

    public List<Boolean> getBooleanList(String path) {
        return getList(path, Boolean.class);
    }

    public char getChar(String path) {
        return getChar(path, '\u0000');
    }

    public char getChar(String path, char def) {
        Character val = get(path, def);
        return (val != null) ? val : def;
    }

    public List<Character> getCharList(String path) {
        return getList(path, Character.class);
    }

    public String getString(String path) {
        return getString(path, "");
    }

    public String getString(String path, String def) {
        String val = get(path, def);
        return (val != null) ? val : def;
    }

    public List<String> getStringList(String path) {
        return getList(path, String.class);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(String path, Class<T> type) {
        List<?> list = get(path, Collections.emptyList());
        List<T> result = new ArrayList<>();
        for (Object object : list) {
            if (type.isInstance(object)) {
                result.add((T) object);
            }
        }
        return result;
    }

    public List<?> getList(String path) {
        return getList(path, Object.class);
    }

    public List<?> getList(String path, List<?> def) {
        List<?> val = get(path, def);
        return (val != null) ? val : def;
    }
}
