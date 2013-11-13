package Messages;

/**
 *VideoMessage
 *The Message that is intended to carry video data
 *@author Common Infrastructure
 */
public class VideoMessage extends Message {

	public int testNum = 5;

    /**
     *The constructor
     *@param client: the clientID it came from
     */
	public VideoMessage(int client) {
		super(2, client);
	}
	

}