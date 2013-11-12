package Messages;

import java.io.*;
import java.util.Date;

/**
 *Message
 *The base Message class designed to carry information between the client and server
 @author Common Infrastructure
 */
public class Message implements Serializable  {

	public int typeID;
	public int clientID;
    public Date dateTime;
	
	//Other data members to be added later
	
    /**
     *The constructor
     *@param t: the Message type id. c: the clientID it came from
     */
	public Message(int t, int c) {
		typeID = t;
		clientID = c;
        dateTime = new Date();
		
	}


}