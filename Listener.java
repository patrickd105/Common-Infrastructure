
import java.io.*;
import java.net.*;
import java.security.*;

class Listener implements Runnable {
	public int clientID;
    private Socket server;
    private String line,input;
    private MessageHandler ourMessageHandler;
    private boolean open;

    public Listener(Socket server, int id, MessageHandler mh) {
      this.server=server;
      this.clientID = id;
      this.ourMessageHandler = mh;
      this.open = true;
    }

    public void run () {


      try {
        // Set up the streams for reading/writing. You guys might review if it's been a while
        ObjectInputStream objIn = new ObjectInputStream(server.getInputStream() );
        PrintStream out = new PrintStream(server.getOutputStream());
		
		while(open){

			Message m = null;
			
			//try to read a message, then pass it along
			try{
				m = (Message) objIn.readObject();
			}
			catch (ClassNotFoundException e) {System.out.println("Class error: "+e);}
		
			System.out.println("Message received: client = "+ m.clientID + " type = "+m.typeID);
			ourMessageHandler.addMessage(m);
		}
		
		
        server.close();
      } catch (IOException ioe) {
        System.out.println("IOException on socket listen: " + ioe);
        ioe.printStackTrace();
      }
    }
}
