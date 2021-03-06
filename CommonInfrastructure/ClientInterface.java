package CommonInfrastructure;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import Reporting.*;
import Messages.*;

/**
 *ClientInterface
 *The interface for the client side where one can send a Message to the server
 *using the method sendMessage.
 *@author Common Infrastructure
 */
public class ClientInterface {
    
    private static Socket mainSocket;
    private static int currentID;
    private static boolean isInitialized;
    private static MessageHandler mh;
    private static Listener mainListener;
    private static Thread listenerThread;
    private static Thread mhThread;
    
    public ClientInterface(){
        
    }

    /**
     *Returns whether the ClientInterface has been initialized or not
     *@return boolean: true if initialized, false if not
     */
	public static boolean isClient(){
		return isInitialized;
	}
	
	/**
     *Initializes the ClientInterface
     */
	public static void init() throws Exception {
        
        if(!isInitialized){
            try{
            isInitialized = true;
            ReportInterface.logInfo(3,"Client starting up");
            //create the socket, initialize ID
            mainSocket = new Socket("127.0.0.1" , 4444);
            currentID = 0;
            ReportInterface.setVerbosity(3);
            
            //connect with server to get Client ID assigned
            //System.out.println("after socket is avail" + ((mainSocket.isBound() ) ? " yes" : " no"));
            
            
            //Make the components
            //System.out.println("Creating necessary components");
            mh = new MessageHandler();
            mainListener = new Listener(mainSocket, 0, mh);
            currentID = mainListener.clientID;
            
            //Put MessageHandler in its own thread too
            mhThread = new Thread(mh);
            mhThread.start();
            
            //start the listener thread
            listenerThread = new Thread(mainListener);
            listenerThread.start();
            }
            catch (IOException e){
                ReportInterface.logError("Error in ClientInterface initialization: " + e);
            }
            
        }
	}
	
    /**
     *Shuts down the ClientInterface
     */
	public static void shutdown(){
		try{
		
			mainListener.close();
			listenerThread.join();
			mh.stop();
			mhThread.join();
			ReportInterface.logInfo(2,"Client successfully shut down");
		}
		catch(Exception e) {
			ReportInterface.logError("Error in client shutdown: "+ e);
		 }

	}
    
    /**
     *Accepts a message and sends it to the server
     */
    public static void sendMessage(Message messageToSend){
			messageToSend.clientID = currentID;
			mainListener.sendMessage(messageToSend);
   
    }

}