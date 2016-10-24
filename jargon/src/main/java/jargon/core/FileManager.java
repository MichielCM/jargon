package jargon.core;

import java.io.File;
import java.util.HashMap;

public class FileManager {

	private HashMap<String, File> files = new HashMap<String, File>();
	private HashMap<String, Long> sizes = new HashMap<String, Long>();
	private HashMap<String, Object> data = new HashMap<String, Object>();
	
	public FileManager() {}
	
	public void manage(File file) {
		this.files.put(file.getAbsolutePath(), file);
		this.sizes.put(file.getAbsolutePath(), file.length());
	}
	
	public void manage(File file, Object data) {
		this.manage(file);
		this.data.put(file.getAbsolutePath(), data);
	}
	
	public boolean sizeDiffers(File file) {
		if (this.sizes.get(file.getAbsolutePath()) == null)
			return true;
		else
			return file.length() != this.sizes.get(file.getAbsolutePath());
	}
	
	public Object getData(File file) {
		return this.data.get(file.getAbsolutePath());
	}
	
}
