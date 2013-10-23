import java.io.*;

public class Message implements Serializable  {

	public int typeID;
	public int clientID;
	
	//Other data members to be added later
	
	public Message(int t, int c) {
		typeID = t;
		clientID = c;
		
	}


}