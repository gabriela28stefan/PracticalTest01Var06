package ro.pub.cs.systems.eim.practicaltest01var06;


import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.util.Log;



public class ProcessingThread extends Thread {

	
	final public static String[] actionTypes = {
		"ro.pub.cs.systems.eim.practicaltest01.actionType1",
		"ro.pub.cs.systems.eim.practicaltest01.actionType2",

	};
	
	
	private Context context = null;
	private boolean isRunning = true;
	
	private int firstOrSecond = 0;;
	
	private String internetAddress = null;
	
	public ProcessingThread(Context context, String internetAddress) {
		this.context = context;
		this.internetAddress = internetAddress;

	}
	
	@Override
	public void run() {
		Log.d("[ProcessingThread]", "Thread has started!");
			sendMessage();
			sleep();
		
		Log.d("[ProcessingThread]", "Thread has stopped!");
	}
	
	private void sendMessage() {
		
		if (firstOrSecond == 0)
			firstOrSecond = 1;
		else
			firstOrSecond = 0;
		
		Intent intent = new Intent();
		intent.setAction(actionTypes[firstOrSecond]);
		intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + internetAddress);
		context.sendBroadcast(intent);
	}
	
	private void sleep() {
		try {
			Thread.sleep(50000);
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}
	
	public void stopThread() {
		isRunning = false;
	}

}