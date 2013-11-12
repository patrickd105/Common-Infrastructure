package CommonInfrastructure;

import java.util.concurrent.ArrayBlockingQueue;
import Reporting.ReportInterface;
import Messages.*;

/**
 *MessageHandler
 *Handles and appropriately responds to Messages instances it is passed
 *@author Common Infrastructure
 */
class MessageHandler implements Runnable {

	ArrayBlockingQueue<Message> messages;
	private static int messageCapacity = 200;
	public int numMessages = 0;
	private boolean cont;
	
	//We'll have to give it references to whichever parts it needs to communicate with
	private ClientManager cm;
	
	
	
	
	/**
     *Constructor that initializes the global variables
     */
	public MessageHandler () {
		messages = new ArrayBlockingQueue<Message> (messageCapacity);
		cont = true;
		
	}
	
    /**
     *Sets the ClientManager
     */
	public void setCM(ClientManager cm){
		this.cm = cm;
	}
	
	
	/**
     *The part that actually needs to just be in a thread is the loop that checks for messages
     */
	public void run() {
		this.acceptMessages();
		
	}
	
	/**
     *Stick a new message in the queue to be handled.
     *@param m: the Message to be added
     *@return true if message was added successfully, false if not
     */
	public boolean addMessage( Message m) {

		//Might eventually change this to a blocking add
		if( m == null)
			System.out.println("Null message, something broke.");
		else if( messages.offer(m) ){
			numMessages++;
			return true;
		}
		else{
			System.out.println("Message queue is full.");
			return false;
		}	
			
		return false;
	}
	
	/**
     *Loops (for now permanently, can make it stop later) constantly taking messages 
     *and then handling them
     */
	public void acceptMessages ( ) {
		
		Message current = null;
		while (cont) {
			try{
				current = messages.take();
			}
			catch(InterruptedException e) {System.out.println("Queue error: " + e);}	
			
			processMessage(current);
			numMessages--;
		}
	}
	
    /**
     *Stops the MessageHandler from accepting messages
     */
	public void stop() {
		cont = false;
		messages.offer(new Message(99,99));
	}
	
	/**
     *Big old switch statement where every type of message is dealt with
     *@param m: the Message instance to be handled
     */
	public boolean processMessage(Message m) {
	
		switch(m.typeID) {
			case 0:
				ReportInterface.logInfo(2, "New client assigned id : " + m.clientID);
            break;
			case 2:
				System.out.println("Client " + m.clientID + " sent video data at "+m.dateTime);
				System.out.println("Test num: " +((VideoMessage) m).testNum);
				break;
			case 3:
				System.out.println("Client " + m.clientID + " sent audio data");
				break;
			case 4:
				cm.getListener(m.clientID).sendMessage(new ConfirmationMessage(m.clientID,4));
				cm.stopThread(m.clientID);
				ReportInterface.logInfo(2, "Client logout: client "+m.clientID);
                break;
			default:
				ReportInterface.logError("Unknown message ID type received. Type: " + m.typeID);
				return false;
		}	
		
		return true;
	}


}
	
	
