import java.lang.Thread;
import Reporting.ReportInterface;

public class TestMain {

	public static void main(String [] args) {
	
		//Make the components
		System.out.println("Creating necessary components");
		MessageHandler mh = new MessageHandler();
      	ClientManager cm = new ClientManager(4444, 20, mh);
      	ReportInterface.init();
      	ReportInterface.setVerbosity(3);
		
		//Set all the references
		mh.setCM(cm);
		
		//Put MessageHandler in its own thread too
		Thread mhThread = new Thread(mh);
		mhThread.start();
		
		//Wait for connections
		cm.acceptConnections();
		
	}
	
}
