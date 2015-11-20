package com.dotenv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class DotEnv {

	private static final String DOT_ENV_FILENAME = ".env";
	private Map<String, String> params = new HashMap<String, String>();

	public DotEnv() {
		super();
		loadParams("");
	}
	
	public DotEnv(String path) {
		super();
		loadParams(path);
	}

	private void loadParams(String path) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path+File.separator+DOT_ENV_FILENAME));
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
