package com.dotenv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class DotEnv {

	private Map<String, String> params;

	private DotEnv() {
		super();
		params = new HashMap<String, String>();
	}

	public static DotEnv load(String path) {
		DotEnv env = new DotEnv();
		env.loadParams(path);
		return env;
	}

	private void loadParams(String path) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line = null;
			while(true) {
				line = br.readLine();
				if(line == null) {
					break;
				}
				parseLine(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try{
					br.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void parseLine(String line) {
		String[] parts = line.split("=");
		params.put(fix(parts[0]), fix(parts[1]));
	}
	
	public String fix(String value) {
		return value.trim();
	}

	public String get(String key) {
		return params.get(key);
	}
}
