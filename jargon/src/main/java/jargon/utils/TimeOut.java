package jargon.utils;

import java.util.Timer;
import java.util.TimerTask;

public class TimeOut extends TimerTask {
	private boolean flag = false;
	
	public TimeOut(int milliseconds) {
		new Timer().schedule(this, milliseconds);
	}
	
	public void run() {
        this.flag = true;
    }
	
	public boolean hasTimedOut() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.flag;
	}
}
