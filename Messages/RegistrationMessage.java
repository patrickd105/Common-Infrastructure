package Messages;

/**
 *RegistrationMessage
 *The Message to register a client
 *@author Common Infrastructure
 */
public class RegistrationMessage extends Message {

	//For now, if -1 means a new client
	public int receivedID;
	
    /**
     *The constructor
     *@param id: the clientID that it came from
     */
	public RegistrationMessage(int id) {
		super(0,id);
		receivedID = id;
		
	}
	
}