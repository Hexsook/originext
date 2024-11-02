package hexsook.originext.config.file;

import hexsook.originext.config.Configuration;
import hexsook.originext.config.ConfigurationException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.LinkedHashMap;

/**
 * Derived class of BaseConfiguration.
 * This class can be used for loading configuration from file.
 */
public class FileConfiguration extends Configuration {

    private final File file;
    private final FileConfigurationAdapter adapter;
    private boolean isLoading = false;

    public FileConfiguration(File file, InputStream source, Class<? extends FileConfigurationAdapter> adapter)
            throws ConfigurationException {
        super(new LinkedHashMap<>());
        this.file = file;
        this.adapter = FileConfigurationAdapter.getAdapter(adapter);

        if (this.adapter == null) {
            throw new ConfigurationException(new IllegalArgumentException("No such adapter: " + adapter), file);
        }

        if (!file.exists()) {
            try {
                if (source == null) {
                    file.createNewFile();
                } else {
                    Files.copy(source, file.toPath());
                }
            } catch (IOException e) {
                throw new ConfigurationException(e, file);
            }
        }

        loadConfiguration();
    }

    public FileConfiguration(File file, Class<? extends FileConfigurationAdapter> adapter)
            throws ConfigurationException {
        this(file, null, adapter);
    }

    private void loadConfiguration() {
        isLoading = true;
        try {
            load(this.adapter.load(file).getMap());
        }  catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            isLoading = false;
        }
    }

    public void save() {
        if (isLoading) {
            return;
        }
        try {
            adapter.save(this, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reload() {
        loadConfiguration();
    }
}