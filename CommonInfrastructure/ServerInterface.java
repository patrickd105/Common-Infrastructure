package CommonInfrastructure;

import java.lang.Thread;
import Reporting.ReportInterface;
import Messages.*;

/**
 *ServerInterface
 *The server interface to send messages to the clients
 *@author Common Infrastructure
 */
public class ServerInterface {

	private static MessageHandler mh;
	private static ClientManager cm;
	private static boolean isInitialized;


    /**
     *The constructor. It's empty.
     */
	private ServerInterface() {
	
	}
	
    /**
     *Initializes the ServerInterface's global variables
     */
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
	
    /**
     *Sends a Message to the client
     *@param m: the message to send. clientID: the intended recipient client
     */
	public static void sendMessage(Message m, int clientID) {
		
		Listener destination = cm.getListener(clientID);
		destination.sendMessage(m);
	}
	
	
	
}