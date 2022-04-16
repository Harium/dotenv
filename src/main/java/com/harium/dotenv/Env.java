package com.harium.dotenv;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Env {

    private static final String SEPARATOR = "=";
    private static final String DOT_ENV_FILENAME = ".env";

    private static String path = System.getProperty("user.dir");
    private static boolean loaded = false;
    static Map<String, String> params = new HashMap<>();

    // Dummy Error Listener
    private static ErrorListener errorListener = new ErrorListener() {
        @Override
        public void onError(Throwable e) {}
    };

    private Env(String path) {
        loadParams(path);
    }

    /**
     * Method to change the .env path
     * @param path
     * @return
     */
    public static Env path(String path) {
        if (!Env.path.equals(path)) {
            clearVariables();
        }
        return new Env(path);
    }

    private static void clearVariables() {
        params.clear();
        loaded = false;
    }

    public static Env errorListener(ErrorListener errorListener) {
        Env.errorListener = errorListener;
        return new Env(path);
    }

    public static String get(String key) {
        loadParams(path);

        String value = params.get(key);
        if (value != null) {
            return value;
        }
        // Fallback
        return System.getenv(key);
    }

    private static void loadParams(String path) {
        if (loaded) {
            return;
        }
        loadParams(path, DOT_ENV_FILENAME);
        loaded = true;
    }

    private static void loadParams(String path, String filename) {
        Path p = Paths.get(path, filename);
        try {
            List<String> lines = Files.readAllLines(p, StandardCharsets.UTF_8);
            for (String line : lines) {
                parseLine(line);
            }
        } catch (IOException e) {
            // Cannot find .env file
            errorListener.onError(e);
        }
    }

    private static void parseLine(String line) {
        if (!line.contains(SEPARATOR)) {
            return;
        }
        String trimmed = line.trim();
        if (trimmed.startsWith("#")) {
            return;
        }

        String[] parts = trimmed.split(SEPARATOR);

        if (parts.length < 1) {
            return;
        } else if (parts.length < 2) {
            addParam(parts[0], "");
            return;
        }

        addParam(parts[0], parts[1]);
    }

    private static void addParam(String key, String value) {
        String fixedKey = sanitize(key);
        if (fixedKey.isEmpty()) {
            return;
        }
        params.put(fixedKey, sanitize(value));
    }

    private static String sanitize(String param) {
        String trimmed = param.trim();
        if (trimmed.startsWith("#")) {
            return "";
        }
        return trimmed;
    }

    public interface ErrorListener {
        void onError(Throwable e);
    }

}
