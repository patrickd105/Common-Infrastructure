package CommonInfrastructure;

import java.lang.Thread;
import Reporting.ReportInterface;
import Messages.*;

public class ServerInterface {

	private static MessageHandler mh;
	private static ClientManager cm;
	private static boolean isInitialized;


	private ServerInterface() {
	
	}
	
	public static void init() {
		
		if(!isInitialized){
			mh = new MessageHandler();
			cm = new ClientManager(4444, 20, mh);
		
			//Set all the references
			mh.setCM(cm);
		
			//Put MessageHandler in its own thread too
			Thread mhThread = new Thread(mh);
			mhThread.start();
		
			
			//Wait for connections
			Thread cmThread = new Thread(cm);
			cmThread.start();
			
			isInitialized = true;
		}	
	}	
	
	public static void sendMessage(Message m, int clientID) {
		
		Listener destination = cm.getListener(clientID);
		destination.sendMessage(m);
	}
	
	
	
}