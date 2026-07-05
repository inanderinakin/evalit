package com.fullhouse.utilities;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Resolves the server address the client talks to.
 *
 * <p>When run from source (via {@code javafx:run}) the values come from the
 * {@code .env} file in the repository root, one directory above the client
 * module. A packaged installer has no such file, so this loader is configured
 * to tolerate a missing {@code .env} and fall back to OS environment variables
 * and JVM system properties instead of crashing at startup.</p>
 */
public class AppConfig {
    private static final Dotenv dotenv = Dotenv.configure()
            .directory("../")
            .ignoreIfMissing()
            .ignoreIfMalformed()
            .load();

    public static String getServerIP() {
        return resolve("SERVER_IP");
    }

    public static String getServerIPWithNip() {
        return resolve("SERVER_IP_WITH_NIP");
    }

    /**
     * Looks up a configuration value, preferring the {@code .env} file (dev),
     * then OS environment variables, then JVM system properties (packaged app).
     *
     * @param key the configuration key
     * @return the resolved value, or {@code null} if it is set nowhere
     */
    private static String resolve(String key) {
        String value = dotenv.get(key);
        if (value == null) {
            value = System.getenv(key);
        }
        if (value == null) {
            value = System.getProperty(key);
        }
        return value;
    }
}
