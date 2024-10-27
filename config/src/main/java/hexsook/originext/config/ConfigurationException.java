package hexsook.originext.config;

import java.io.File;

/**
 * ConfigurationException can be thrown when there are exceptions occurred while executing config operations,
 * Its constructor accepts a raw exception and the file where the exception occurred.
 */
public class ConfigurationException extends Exception {

    private final File file;

    public ConfigurationException(Throwable cause, File file) {
        super(cause);
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
