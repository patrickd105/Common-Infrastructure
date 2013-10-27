import java.io.*;
import java.util.Date;

public class Message implements Serializable  {

	public int typeID;
	public int clientID;
    public Date dateTime;
	
	//Other data members to be added later
	
	public Message(int t, int c) {
		typeID = t;
		clientID = c;
        dateTime = new Date();
		
	}


}