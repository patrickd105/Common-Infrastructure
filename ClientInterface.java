import java.io.*;
import java.net.*;
import java.util.Scanner;
import Reporting.*;


public class ClientInterface {
    
    private static Socket mainSocket;
    private static int currentID;
    private static boolean isInitialized;
    private static MessageHandler mh;
    private static Listener mainListener;
    
    public ClientInterface(){
        
    }

	public static void init() throws Exception {
        //set initialized to true
        isInitialized = true;
        
        if(!isInitialized){
            try{
            //create the socket, initialize ID
            mainSocket = new Socket("127.0.0.1" , 4444);
            currentID = 0;
            
            //connect with server to get Client ID assigned
            ObjectInputStream objIn = new ObjectInputStream(mainSocket.getInputStream() );
            
            Message idAssignment = null;
            
            Scanner scan = new Scanner(System.in);
            
            idAssignment = (Message) objIn.readObject();
            currentID = ((RegistrationMessage) idAssignment).receivedID;
            
            System.out.println("Was given ID = "+currentID);
            
            //Make the components
            System.out.println("Creating necessary components");
            mh = new MessageHandler();
            mainListener = new Listener(mainSocket, currentID, mh);
            ReportInterface.init();
            ReportInterface.setVerbosity(3);
            
            //Put MessageHandler in its own thread too
            Thread mhThread = new Thread(mh);
            mhThread.start();
            
            //start the listener thread
            Thread listenerThread = new Thread(mainListener);
            listenerThread.start();
            }
            catch (IOException e){
                System.out.println("IOException: " + e);
            }
        }
	}
    
    //this method accepts a message and sends it to the server
    public static void sendMessage(Message messageToSend){
		try{
		ObjectOutputStream objOut = new ObjectOutputStream(mainSocket.getOutputStream() );

        if(messageToSend != null)
            objOut.writeObject(messageToSend);
        }
        catch (IOException e){
            System.out.println("IOException: " + e);
        }
    }

}