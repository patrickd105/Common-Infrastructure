import java.io.*;
import java.net.*;
import java.util.Scanner;
import Messages.*;
import CommonInfrastructure.*;


public class TestClient {

	public static void main (String args[]) throws Exception {
	
		/*
		System.out.println("Making connection");
		Socket s = new Socket("127.0.0.1" , 4444);
		
		int currentID = 0;
		
		ObjectOutputStream objOut = new ObjectOutputStream(s.getOutputStream() );
		ObjectInputStream objIn = new ObjectInputStream(s.getInputStream() );
		
		

		
		
		idAssignment = (Message) objIn.readObject();
		currentID = ((RegistrationMessage) idAssignment).receivedID;
		
		System.out.println("Was given ID = "+currentID);
		*/
		
		Scanner scan = new Scanner(System.in);
		System.out.println("before");
		ClientInterface.init();
		System.out.println("after");
		Message outMessage = null;
		Message idAssignment = null;
		int option = 0;
		
		while( option != 3) {
		
			System.out.println("1 for video, 2 audio, 3 quit");
			option = scan.nextInt();
			
			//Note: Only did one case for now out of laziness.
			switch(option) {
				case 1:
					outMessage = new VideoMessage(0);
					break;
				case 3:
					outMessage = new DeregisterMessage(0);
					break;
				default:
					System.out.println("not an option");
			
			}
			
			if(outMessage != null) ClientInterface.sendMessage(outMessage);
			
			
		};
		
		
		ClientInterface.shutdown();
		/*
		Message closeConfirm = (Message) objIn.readObject();
		if(  ((ConfirmationMessage) closeConfirm).confirmedMessageType == 4)
			System.out.println("Exit confirmed");
		else
			System.out.println("Exit hosed up");
			
		*/	 

	}

}