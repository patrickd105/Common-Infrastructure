package Messages;

/**
 * ConfirmationMessage
 * Program extends Messages class -- a ConfirmationMessage is the message confirming that the client has connected to the server
 * @author Common Infrastructure
 */
public class ConfirmationMessage extends Message {

	public int confirmedMessageType;
	
	/**
     * Constructor for ConfirmationMessage
     * @param client An int that contains the client ID
     * @param conf An integer describing the recieved message's type
     * @return No return value
     */
	public ConfirmationMessage(int client, int conf) {
		super(5, client);
		confirmedMessageType = conf;
	}
	
}