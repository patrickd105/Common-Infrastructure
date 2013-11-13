package Messages;

/**
 * DeregisterMessage
 * Program extends Messages class -- a DeregisterMessage is the message telling the server to quit and disconnect the client
 * @author Common Infrastructure
 */
public class DeregisterMessage extends Message {

	//Any specific data?
	
	/**
     * Constructor for DeregisterMessage
     * @param client An int that contains the client ID
     * @return No return value
     */
	public DeregisterMessage(int client) {
		super(4, client);
		
	}
	
}