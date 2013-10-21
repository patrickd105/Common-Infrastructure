import java.io.*;
import java.net.*;
import java.util.Scanner;


public class TestClient {

	public static void main (String args[]) throws Exception {
	
		System.out.println("Making connection");
		Socket s = new Socket("127.0.0.1" , 4444);
		
		ObjectOutputStream objOut = new ObjectOutputStream(s.getOutputStream() );
		
		Message outMessage = null;
		
		int option = 0;
		Scanner scan = new Scanner(System.in);
		
		while( option != 4) {
		
			System.out.println("1 for register 2 for video, 3 audio, 4 quit");
			option = scan.nextInt();
			
			//Note: Only did one case for now out of laziness.
			switch(option) {
				case 1:
					outMessage = new VideoMessage(3);
					break;
				default:
					System.out.println("not an option");
			
			}
			
			if(outMessage != null) objOut.writeObject(outMessage);
			
		};		 

	}

}