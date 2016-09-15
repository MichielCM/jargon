package jargon.core;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SingletonFactory {

	static private SingletonFactory singletonFactory = new SingletonFactory();
	
	private Socket laMachine;
	
	private SingletonFactory() {}
	 
    public static SingletonFactory getSingletonFactory() {
    	return singletonFactory;
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
	
}
