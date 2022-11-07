package frontend.fxmlsControllers;

import backend.Post;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class PostController {
	@FXML private Label usernameLabel;
	@FXML private Label titleLabel;
	@FXML private ImageView profilePic;
	@FXML private Label dateLabel;
	@FXML private TextArea descriptionField;
	
	private Post post;
	
	public void setPostData(Post post) {
		this.post = post;
		usernameLabel.setText(post.getUsername());
		titleLabel.setText(post.getTitle());
		descriptionField.setText(post.getDescription());
		dateLabel.setText(post.getPostDate().toString());	
	}
}
