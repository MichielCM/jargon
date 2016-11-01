package jargon.utils.servlet;

import java.util.HashMap;

public class MimeType {

	public enum mimeTypes {
		ALL, APPLICATION, AUDIO, EXAMPLE, IMAGE, MESSAGE, MODEL, MULTIPART, TEXT, VIDEO
	}
	
	private HashMap<String,String> parameters;
	private String mimeType;
	private mimeTypes type;
	private String subType;
	private double quality = 1.0;
	private String charset = null;
	
	public MimeType(String sMimeType) {
		this.mimeType = sMimeType;
		
		String sType = sMimeType.substring(0, sMimeType.indexOf("/"));
		if (sType.equals("*"))
			this.type = mimeTypes.ALL;
		else
			this.type = mimeTypes.valueOf(sType.trim().toUpperCase());
		
		String sSubType = sMimeType.substring(sMimeType.indexOf("/") + 1);
		if (sSubType.indexOf(";") < 0)
			this.subType = sSubType.trim().toUpperCase();
		else {
			String[] sParameters = sSubType.split(";");
			this.subType = sParameters[0].trim().toUpperCase();
			
			this.parameters = new HashMap<String,String>();
			for (int i=1; i<sParameters.length; i++) {
				String sId = sParameters[i].substring(0, sParameters[i].indexOf("=") - 1).trim();
				String sValue = sParameters[i].substring(sParameters[i].indexOf("=") + 1).trim();
				
				this.parameters.put(sId, sValue);
				
				if (sId.equalsIgnoreCase("q"))
					this.quality = Double.parseDouble(sValue);
				else if (sId.equalsIgnoreCase("charset"))
					this.charset = sValue;
			}
		}
	}
	
	public String get() {
		return this.mimeType;
	}
	
	public String getType() {
		return this.type.toString().toLowerCase();
	}
	
	public String getSubType() {
		return this.subType.toLowerCase();
	}
	
	public boolean isMultipart() {
		return (this.type == mimeTypes.MULTIPART);
	}
	
	public boolean isXML() {
		return this.subType.equals("XML");
	}
	
	public boolean isHTML() {
		return this.subType.equals("HTML");
	}
	
	public boolean isJSON() {
		return (this.subType.equals("JSON") || this.subType.equals("JSONP"));
	}
	
	public String getParameter(String id) {
		return this.parameters.get(id);
	}
	
	public Double getQuality() {
		return this.quality;
	}
	
}
