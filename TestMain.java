import java.lang.Thread;

public class TestMain {

	public static void main(String [] args) {
	
		System.out.println("Creating necessary components");
		MessageHandler mh = new MessageHandler();
		ClientManager cm = new ClientManager(4444, 20, mh);
		
		
		//Put MessageHandler in its own thread too
		Thread mhThread = new Thread(mh);
		mhThread.start();
		
		//Wait for connections
		cm.acceptConnections();
		
	}
	
}