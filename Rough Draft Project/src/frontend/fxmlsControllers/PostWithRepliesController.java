package frontend.fxmlsControllers;

import backend.Post;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class PostWithRepliesController {
	@FXML private Label usernameLabel;
	@FXML private Label titleLabel;
	@FXML private ImageView profilePic;
	@FXML private Label dateLabel;
	@FXML private TextArea descriptionField;
	
	public PostWithRepliesController() {
		
	}
	
	public void setMainPostData(Post post) {
		usernameLabel.setText(post.getUsername());
		titleLabel.setText(post.getTitle());
		descriptionField.setText(post.getDescription());
		dateLabel.setText(post.getPostDate().toString());
	}
	
	
}
