package hexsook.originext.config;

import hexsook.originext.config.adapter.JsonConfiguration;
import hexsook.originext.config.adapter.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class of configuration file format adapter.
 */
public abstract class ConfigurationAdapter {

    private static final Map<Class<? extends ConfigurationAdapter>, ConfigurationAdapter> registeredAdapters = new HashMap<>();

    static {
        registerAdapter(YamlConfiguration.class, YamlConfiguration.INSTANCE);
        registerAdapter(JsonConfiguration.class, JsonConfiguration.INSTANCE);
    }

    public static ConfigurationAdapter getAdapter(Class<? extends ConfigurationAdapter> clazz) {
        return registeredAdapters.get(clazz);
    }

    public static void registerAdapter(Class<? extends ConfigurationAdapter> clazz, ConfigurationAdapter adapter) {
        registeredAdapters.put(clazz, adapter);
    }

    public static Map<Class<? extends ConfigurationAdapter>, ConfigurationAdapter> getAdapters() {
        return registeredAdapters;
    }

    public abstract BaseConfiguration load(File file) throws Exception;

    public abstract BaseConfiguration load(InputStream in);

    public abstract void save(BaseConfiguration config, File file) throws IOException;

    public abstract void save(BaseConfiguration config, Writer writer);

}
