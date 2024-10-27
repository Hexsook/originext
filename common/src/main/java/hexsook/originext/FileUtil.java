package hexsook.originext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Provides methods for processing files.
 */
public class FileUtil {

    /**
     * Read file as string, \n as line breaker.
     */
    public static String readContentAsString(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    /**
     * Format file length as string.
     */
    public static String formatFileLength(long contentLength) {
        String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int unitIndex = 0;
        double len = contentLength;

        while (len >= 1024 && unitIndex < units.length - 1) {
            len /= 1024;
            unitIndex++;
        }

        return String.format("%.2f %s", len, units[unitIndex]);
    }
}
