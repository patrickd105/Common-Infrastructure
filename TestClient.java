import java.io.*;
import java.net.*;
import java.util.Scanner;


public class TestClient {

	public static void main (String args[]) throws Exception {
	
		System.out.println("Making connection");
		Socket s = new Socket("127.0.0.1" , 4444);
		int option = 0;
		int currentID = 0;
		
		ObjectOutputStream objOut = new ObjectOutputStream(s.getOutputStream() );
		ObjectInputStream objIn = new ObjectInputStream(s.getInputStream() );
		
		Message outMessage = null;
		Message idAssignment = null;

		Scanner scan = new Scanner(System.in);
		
		idAssignment = (Message) objIn.readObject();
		currentID = ((RegistrationMessage) idAssignment).receivedID;
		
		System.out.println("Was given ID = "+currentID);
		
		while( option != 3) {
		
			System.out.println("1 for video, 2 audio, 3 quit");
			option = scan.nextInt();
			
			//Note: Only did one case for now out of laziness.
			switch(option) {
				case 1:
					outMessage = new VideoMessage(currentID);
					break;
				default:
					System.out.println("not an option");
			
			}
			
			if(outMessage != null) objOut.writeObject(outMessage);
			
		};		 

	}

}