package hexsook.originext.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.LinkedHashMap;

/**
 * Derived class of BaseConfiguration.
 * This class can be used for loading configuration from file.
 */
public class Configuration extends BaseConfiguration {

    private final File file;
    private final ConfigurationAdapter adapter;
    private boolean isLoading = false;

    public Configuration(File file, InputStream source, Class<? extends ConfigurationAdapter> adapter)
            throws ConfigurationCreationException {
        super(new LinkedHashMap<>());
        this.file = file;
        this.adapter = ConfigurationAdapter.getAdapter(adapter);

        if (!file.exists()) {
            try {
                if (source == null) {
                    file.createNewFile();
                } else {
                    Files.copy(source, file.toPath());
                }
            } catch (IOException e) {
                throw new ConfigurationCreationException(e, file);
            }
        }

        loadConfiguration();
    }

    public Configuration(File file, Class<? extends ConfigurationAdapter> adapter)
            throws ConfigurationCreationException {
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