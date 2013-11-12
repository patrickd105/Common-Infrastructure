package CommonInfrastructure;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.concurrent.locks.ReentrantLock;
import Reporting.ReportInterface;
import Messages.*;

class Listener implements Runnable {
	public int clientID;
    private Socket server;
    private MessageHandler ourMessageHandler;
    private boolean open;
    public ReentrantLock lock;
    
    ObjectInputStream objIn;
    ObjectOutputStream objOut;

    public Listener(Socket server, int id, MessageHandler mh) {
      this.server=server;
      this.clientID = id;
      this.ourMessageHandler = mh;
      this.open = true;
      this.lock = new ReentrantLock();
      
      try{
      		objOut = new ObjectOutputStream(this.server.getOutputStream());
      		objOut.flush();
		  	objIn = new ObjectInputStream(this.server.getInputStream() );
		  	
		  	//System.out.println("Got streams");
		  	if(ClientInterface.isClient() )
		  		register();
		  	else{
		  		Message initial = new RegistrationMessage(clientID);
       			objOut.writeObject(initial);
		  	}
		  
	  }
	  catch(Exception e) {
	  	//System.out.println("Couldn't get socket streams");
	  	ReportInterface.logError("Couldn't obtain socket streams: "+e); }
			  
    }
    
    private void register() {
    
    	try{
			Message idAssignment = (Message) objIn.readObject();
			clientID = ((RegistrationMessage) idAssignment).receivedID;
			
			ReportingInterface.logInfo("Was given ID = "+clientID);
		}
		catch(Exception e) {ReportingInterface.logError("Problem during registration");}
	}

	public void close(){
		ReportInterface.logInfo("Closing listener");
		open = false;
	}
	
	public void sendMessage(Message m) {
		try{
			objOut.writeObject(m);
		}
		catch(Exception e){ReportInterface.logError("Sending error: "+e); }
	}

	//The main listening loop
    public void run () {


      try {
        // Set up the streams for reading/writing. You guys might review if it's been a while
        
        
        
		
		while(open){

			lock.lock();
			
		    if(open ) {
				try {
					Message m = null;
			
					//try to read a message, then pass it along
					try{
						m = (Message) objIn.readObject();
						ReportInterface.logInfo("Message received: client = "+ m.clientID + " type = "+m.typeID);
						ourMessageHandler.addMessage(m);
					}
					catch (ClassNotFoundException e) {ReportInterface.logError("Class error: "+e);}
					catch (EOFException e) {ReportInterface.logError("Socket error, closed? "+e);}
					
				}
				//Unlock lock no matter what
				finally { lock.unlock();}
			}
		}
		
		ReportInterface.logInfo("Client #: "+clientID+" is shutting down");
        server.close();
      } catch (IOException ioe) {
        ReportInterface.logError("IOException on socket listen: " + ioe);
        ioe.printStackTrace();
      }
    }
}
