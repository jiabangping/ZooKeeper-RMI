package zookeeper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertiesConfig {
 
	private static Map<String,String> config = new HashMap<String,String>();
	static {
		try {
			InputStream input = PropertiesConfig.class.getClassLoader().getResourceAsStream("config.properties");
			Properties p = new Properties();
			p.load(input);
			for(Entry<Object,Object> entry : p.entrySet()) {
				config.put(String.valueOf(entry.getKey()).trim(), String.valueOf(entry.getValue()).trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getPropertie("winLogPath"));
	}
	
	public static String getPropertie(String key) {
		return config.get(key);
	}
}
