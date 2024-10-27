package hexsook.originext.config;

import java.io.File;

/**
 * ConfigurationCreationException can be thrown when there are exceptions occurred while creating a profile,
 * Its constructor accepts a raw exception and the file where the exception occurred.
 */
public class ConfigurationCreationException extends Exception {

    private final File file;

    public ConfigurationCreationException(Throwable cause, File file) {
        super(cause);
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
