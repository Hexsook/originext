package hexsook.originext;

import java.util.regex.Pattern;

/**
 * Class for parsing address into host and port.
 */
public class HostPort {

    private final String host;
    private final Integer port;
    private static final int MAX_PORT = 65535;
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(\\d{1,3}\\.){3}\\d{1,3}$");
    private static final Pattern IPV6_PATTERN = Pattern.compile("^\\[([a-fA-F0-9:]+)]$");

    private HostPort(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public static HostPort fromString(String input) {
        String trimmedInput = input.trim();

        String hostPart;
        String portPart = null;

        // Check if it's an IPv6 address with a port (e.g., [fe80::1]:8080)
        if (trimmedInput.startsWith("[") && trimmedInput.contains("]:")) {
            int endIndex = trimmedInput.indexOf(']');
            hostPart = trimmedInput.substring(1, endIndex);
            portPart = trimmedInput.substring(endIndex + 2);
        } else if (trimmedInput.contains(":") && trimmedInput.indexOf(':') == trimmedInput.lastIndexOf(':')) {
            // IPv4 address with port (e.g., 192.168.0.1:8080)
            String[] parts = trimmedInput.split(":");
            hostPart = parts[0];
            portPart = parts[1];
        } else {
            // Only host, no port
            hostPart = trimmedInput;
        }

        // Validate the host
        if (!isValidIPv4(hostPart) && !isValidIPv6(hostPart)) {
            throw new IllegalArgumentException("Invalid host: " + hostPart);
        }

        // Parse port if provided
        Integer port = null;
        if (portPart != null) {
            try {
                port = Integer.parseInt(portPart);
                if (port < 0 || port > MAX_PORT) {
                    throw new IllegalArgumentException("Port out of range: " + port);
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid port: " + portPart);
            }
        }

        return new HostPort(hostPart, port);
    }

    private static boolean isValidIPv4(String host) {
        if (!IPV4_PATTERN.matcher(host).matches()) return false;
        String[] parts = host.split("\\.");
        for (String part : parts) {
            int num = Integer.parseInt(part);
            if (num < 0 || num > 255) return false;
        }
        return true;
    }

    private static boolean isValidIPv6(String host) {
        return IPV6_PATTERN.matcher("[" + host + "]").matches();
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    @Override
    public String toString() {
        if (port != null) {
            return isValidIPv6(host) ? "[" + host + "]:" + port : host + ":" + port;
        }
        return host;
    }
}
