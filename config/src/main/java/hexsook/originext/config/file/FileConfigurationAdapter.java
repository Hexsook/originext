package hexsook.originext.config.file;

import hexsook.originext.config.Configuration;
import hexsook.originext.config.file.adapter.JsonFileConfiguration;
import hexsook.originext.config.file.adapter.YamlFileConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class of configuration file format adapter.
 */
public abstract class FileConfigurationAdapter {

    private static final Map<Class<? extends FileConfigurationAdapter>, FileConfigurationAdapter> registeredAdapters = new HashMap<>();

    static {
        registerAdapter(YamlFileConfiguration.class, YamlFileConfiguration.INSTANCE);
        registerAdapter(JsonFileConfiguration.class, JsonFileConfiguration.INSTANCE);
    }

    public static FileConfigurationAdapter getAdapter(Class<? extends FileConfigurationAdapter> clazz) {
        return registeredAdapters.get(clazz);
    }

    public static void registerAdapter(Class<? extends FileConfigurationAdapter> clazz, FileConfigurationAdapter adapter) {
        registeredAdapters.put(clazz, adapter);
    }

    public static Map<Class<? extends FileConfigurationAdapter>, FileConfigurationAdapter> getAdapters() {
        return registeredAdapters;
    }

    public abstract Configuration load(File file) throws Exception;

    public abstract Configuration load(InputStream in);

    public abstract void save(Configuration config, File file) throws IOException;

    public abstract void save(Configuration config, Writer writer);

}
