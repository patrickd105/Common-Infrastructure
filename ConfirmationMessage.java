
public class ConfirmationMessage extends Message {

	int confirmedMessageType;
	
	public ConfirmationMessage(int client, int conf) {
		super(5, client);
		confirmedMessageType = conf;
	}
	
}