package frontend.fxmlsControllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import backend.Post;
import frontend.GUIBackend;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

public class PostRepliesController {
	@FXML private Label usernameLabel;
	@FXML private Label titleLabel;
	@FXML private ImageView profilePic;
	@FXML private Label dateLabel;
	@FXML private TextArea descriptionField;
	@FXML private AnchorPane mainPostPane;
    @FXML private ScrollPane scrollPane;
    @FXML private TilePane tilePane;
    @FXML private ImageView replyButton;
    
    private Post post;
    private AnchorPane contentPane;
    
	//Initializer
	public PostRepliesController() {	
	}
	
	public void initialize() {
		Platform.runLater(() -> {
			GUIBackend.displayPosts(post.getPostReplies(), tilePane, contentPane);
		});
	}
	
	@FXML public void replyButtonOnClicked(MouseEvent event) {
		ReplyController replyController = GUIBackend.loadNewWindow(GUIBackend.ReplyScene);
		replyController.passData(post, tilePane);
		
	}
	
	public void passData(Post post, AnchorPane contentPane) {
		this.post = post;
		this.contentPane = contentPane;
		usernameLabel.setText(post.getUsername());
		titleLabel.setText(post.getTitle());
		descriptionField.setText(post.getDescription());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm aa");
		dateLabel.setText(df.format(post.getPostDate()));
	}
	
	
}
