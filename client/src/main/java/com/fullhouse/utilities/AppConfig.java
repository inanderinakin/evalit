package com.fullhouse.utilities;
import io.github.cdimascio.dotenv.Dotenv;

public class AppConfig {
    private static final Dotenv dotenv = Dotenv.configure().directory("../").load();

    public static String getServerIP() {
        return dotenv.get("SERVER_IP");
    }

    public static String getServerIPWithNip() {
        return dotenv.get("SERVER_IP_WITH_NIP");
    }
}
