package CommonInfrastructure;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import Reporting.*;
import Messages.*;

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

	public static boolean isClient(){
		return isInitialized;
	}
	
	
	public static void init() throws Exception {
        //set initialized to true
        
        
        if(!isInitialized){
            try{
            isInitialized = true;
            System.out.println("Client init");
            //create the socket, initialize ID
            mainSocket = new Socket("127.0.0.1" , 4444);
            currentID = 0;
            ReportInterface.setVerbosity(3);
            
            //connect with server to get Client ID assigned
            System.out.println("after socket is avail" + ((mainSocket.isBound() ) ? " yes" : " no"));
            
            
            //Make the components
            System.out.println("Creating necessary components");
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
                System.out.println("IOException: " + e);
            }
            
        }
	}
	
	public static void shutdown(){
		try{
		
			mainListener.close();
			System.out.println("Just closed listener");
			listenerThread.join();
			System.out.println("Joined listener");
			mh.stop();
			System.out.println("Stopped mh");
			mhThread.join();
			System.out.println("Everything stopped");
		}
		catch(Exception e) {
			System.out.println("Error in client shutdown: "+ e);
			ReportInterface.logError("Error in client shutdown: "+ e);
		 }

	}
    
    //this method accepts a message and sends it to the server
    public static void sendMessage(Message messageToSend){
			messageToSend.clientID = currentID;
			mainListener.sendMessage(messageToSend);
   
    }

}