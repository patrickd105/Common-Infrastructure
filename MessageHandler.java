import java.util.concurrent.ArrayBlockingQueue;


public class MessageHandler implements Runnable {

	ArrayBlockingQueue<Message> messages;
	private static int messageCapacity = 200;
	public int numMessages = 0;
	private boolean cont;
	
	//We'll have to give it references to whichever parts it needs to communicate with
	private ClientManager cm;
	
	
	
	
	
	public MessageHandler ( ) {
		messages = new ArrayBlockingQueue<Message> (messageCapacity);
		cont = true;
		
	}
	
	public void setCM(ClientManager cm){
		this.cm = cm;
	}
	
	
	//The part that actually needs to just be in a thread is the loop that checks for messages
	public void run() {
		this.acceptMessages();
		
	}
	
	//Stick a new message in the queue to be handled.
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
	
	//Loops (for now permanently, can make it stop later) constantly taking messages and then handling them
	public void acceptMessages ( ) {
		
		Message current = null;
		System.out.println("AcceptingMessages");
		while (cont) {
			try{
				current = messages.take();
			}
			catch(InterruptedException e) {System.out.println("Queue error: " + e);}	
			
			processMessage(current);
			numMessages--;
		}
	}
	
	//Big old switch statement where every type of message is dealt with
	public boolean processMessage(Message m) {
	
		switch(m.typeID) {
			case 1:
				System.out.println("Client registered with id: " + m.clientID);
				break;
			case 2:
				System.out.println("Client " + m.clientID + " sent video data");
				System.out.println("Test num: " +((VideoMessage) m).testNum);
				break;
			case 3:
				System.out.println("Client " + m.clientID + " sent audio data");
				break;
			case 4:
				System.out.println("Client " + m.clientID + " has unregistered");
				cm.stopThread(m.clientID);
				break;
			default:
				System.out.println("Message ID does not match a known type.");
				return false;
		}	
		
		return true;
	}


}
	
	