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
        String dir = System.getProperty("user.dir");
        loadParams(dir);
    }

    public static void loadParams(String dir) {
        String path = dir + File.separator + DOT_ENV_FILENAME;
        File f = new File(path);

        if (!f.exists()) {
            if (DEBUG) {
                System.err.println("File not found: " + path);
            }
            return;
        } else {
            System.out.println("Loading .ENV: " + path);
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));

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
        String[] parts = line.split("=");
        params.put(fix(parts[0]), fix(parts[1]));
    }

    public static String fix(String value) {
        return value.trim();
    }

    public static String get(String key) {
        if (params.containsKey(key)) {
            return params.get(key);
        } else {
            return System.getenv(key);
        }
    }
}
