package jargon.core;

import java.io.IOException;
import java.util.Properties;

public final class Settings {

	private Properties properties;
	
	public Settings(String propertiesFile) {
		try {
			properties = new Properties();
			properties.load(
				this.getClass().getClassLoader().getResourceAsStream(
					"/dq/nlp/resources/".concat(propertiesFile).concat(".properties")
				)
			);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object get(String property) {
		return properties.getProperty(property);
	}
	
	public String getAsString(String property) {
		return this.get(property).toString();
	}
	
	public int getAsInt(String property) {
		return Integer.parseInt(this.getAsString(property));
	}
	
	public long getAsLong(String property) {
		return Long.parseLong(this.getAsString(property));
	}
	
	public boolean getAsBoolean(String property) {
		return Boolean.parseBoolean(this.getAsString(property));
	}
	
	public double getAsDouble(String property) {
		return Double.parseDouble(this.getAsString(property));
	}
	
}
