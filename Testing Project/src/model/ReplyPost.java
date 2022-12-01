package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.UUID;

public class ReplyPost extends Post implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//UUID of the post this ReplyPost was added to
	private UUID repliedPostUUID;

	public ReplyPost(String title, String topic, String description, LinkedList<FXImage> postImages, User poster, UUID uuid, UUID repliedPostUUID) {
		super(title, topic, description, postImages, poster, uuid);
		this.repliedPostUUID = repliedPostUUID;
	}

	public UUID getRepliedPostUUID() {
		return repliedPostUUID;
	}

	public void setRepliedPostUUID(UUID repliedPostUUID) {
		this.repliedPostUUID = repliedPostUUID;
	}

}
