package jargon.core;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import jargon.core.RuleEngine.ResourceType;
import jargon.model.folia.FoLiA;
import jargon.utils.fuzzymatcher.FuzzyMatcher;
import jargon.utils.fuzzymatcher.Resource;

public class Singleton {

	static private Singleton singleton = new Singleton();
	
	private Socket laMachine;
	private FileManager fileManager;
	private HashMap<String,RuleEngine> ruleEngines = new HashMap<String,RuleEngine>();
	private HashMap<String,FuzzyMatcher> fuzzyMatchers = new HashMap<String,FuzzyMatcher>();
	private Marshaller foliaMarshaller;
	private Unmarshaller folaUnmarshaller;
	 
    public static Singleton getInstance() {
    	return singleton;
    }
    
    public Socket getLaMachine() throws UnknownHostException, IOException {
    	if (this.laMachine == null) {
    		synchronized (this) {
    			if (this.laMachine == null) {
    				this.laMachine = new Socket( //"localhost", 12345
    					new Settings("lamachine").getAsString("host"),
    					new Settings("lamachine").getAsInt("port")
    				);
    				this.laMachine.setKeepAlive(true);
                }
    		}
    	}
    	
    	return this.laMachine;
    }
    
    public FileManager getFileManager() {
    	if (this.fileManager == null) {
    		synchronized (this) {
    			if (this.fileManager == null)
    				this.fileManager = new FileManager();
    		}
    	}
    	
    	return this.fileManager;
    }
    
    public Marshaller getFoliaMarshaller() {
    	if (this.foliaMarshaller == null) {
    		synchronized (this) {
    			if (this.foliaMarshaller == null)
					try {
						System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");
						this.foliaMarshaller = JAXBContext.newInstance(FoLiA.class).createMarshaller();
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    		}
    	}
    	
    	return this.foliaMarshaller;
    }
    
    public Unmarshaller getFoliaUnmarshaller() {
    	if (this.folaUnmarshaller == null) {
    		synchronized (this) {
    			if (this.folaUnmarshaller == null)
					try {
						System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");
						this.folaUnmarshaller = JAXBContext.newInstance(FoLiA.class).createUnmarshaller();
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    		}
    	}
    	
    	return this.folaUnmarshaller;
    }
    
    public RuleEngine getRuleEngine(String id, ResourceType type, File... resources) {
    	if (!this.ruleEngines.containsKey(id)) {
    		synchronized(this) {
    			if (!this.ruleEngines.containsKey(id))
    				this.ruleEngines.put(id, new RuleEngine(type, resources));
    		}
    	}
    	
    	return this.ruleEngines.get(id).reset();
    }
    
    public FuzzyMatcher getFuzzyMatcher(String id, Resource resource) {
    	if (!this.fuzzyMatchers.containsKey(id)) {
    		synchronized(this) {
    			if (!this.fuzzyMatchers.containsKey(id))
    				this.fuzzyMatchers.put(id, new FuzzyMatcher(resource));
    		}
    	}
    	
    	return this.fuzzyMatchers.get(id);
    }
	
}
