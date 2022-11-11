package frontend.fxmlsControllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import backend.Post;
import frontend.GUIBackend;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PostController {
	@FXML private Label usernameLabel;
	@FXML private Label titleLabel;
	@FXML private ImageView profilePic;
	@FXML private Label dateLabel;
	@FXML private TextArea descriptionField;
	@FXML private AnchorPane postPane;
	
	private Post post;
	private AnchorPane contentPane;
	
	//Initializer
	public PostController() {
	}	
	
	@FXML public void postOnClicked(MouseEvent event) {
		System.out.println(post.toString());
		contentPane.getChildren().clear();
		PostRepliesController postRepliesController = GUIBackend.loadPane(contentPane, GUIBackend.PostRepliesScene);
		postRepliesController.passData(post, contentPane);
    }
	
	public void setContentPane(AnchorPane contentPane) {
		this.contentPane = contentPane;
	}
	
	public void setPostData(Post post) {
		this.post = post;
		usernameLabel.setText(post.getUsername());
		titleLabel.setText(post.getTitle());
		descriptionField.setText(post.getDescription());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm aa");
		dateLabel.setText(df.format(post.getPostDate()));	
	}
	
	
}
