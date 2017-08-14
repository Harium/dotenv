package com.harium.dotenv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Env {

    public static boolean DEBUG = false;

    private static final String DOT_ENV_FILENAME = ".env";
    private static Map<String, String> params = new HashMap<>();

    static {
        String path = System.getProperty("user.dir");
        loadParams(path);
    }

    public static void loadParams(String path) {
        String dir = path + File.separator + DOT_ENV_FILENAME;
        File file = new File(dir);
        loadParams(file);
    }

    public static void loadParams(File file) {
        if (!file.exists()) {
            if (DEBUG) {
                System.err.println("File not found: " + file.getAbsolutePath());
            }
            return;
        } else {
            if (DEBUG) {
                System.out.println("Loading params from: " + file.getAbsolutePath());
            }
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));

            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                parseLine(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void parseLine(String line) {
        // Ignore comments
        if (line.startsWith("#")) {
            return;
        }
        String[] parts = line.split("=");
        if (parts.length < 2) {
            return;
        }
        addParam(parts[0], parts[1]);
    }

    private static String fix(String value) {
        return value.trim();
    }

    public static void addParam(String key, String value) {
        params.put(fix(key), fix(value));
    }

    public static String get(String key) {
        if (params.containsKey(key)) {
            return params.get(key);
        } else {
            return System.getenv(key);
        }
    }
}
