
public class RegistrationMessage extends Message {

	//For now, if -1 means a new client
	public int receivedID;
	
	public RegistrationMessage(int id) {
		super(0,id);
		receivedID = id;
		
	}
	
}