package frontend.fxmlsControllers;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import backend.Post;
import backend.StackPaneNode;
import frontend.GUIBackend;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PostController {
	@FXML private Label usernameLabel;
	@FXML private Label titleLabel;
	@FXML private ImageView profilePic;
	@FXML private Label dateLabel;
	@FXML private TextArea descriptionField;
	
	private Post post;
	private LandingController landingController;
	
	//Initializer
	public PostController() {}	
	
	@FXML public void postOnClicked(MouseEvent event) {
		//Ensures there is only ever one PostReplies pane loaded into contentPane
		landingController.getContentPane().getChildren().removeIf(pane -> pane.getId().equals("PostRepliesPane"));
		//No need to search contentPane for this pane since it will already be at the top
		StackPaneNode<PostRepliesController> postReplies =  GUIBackend.loadStackPane(landingController.getContentPane(), 
				GUIBackend.PostRepliesScene,"PostRepliesPane");
		postReplies.getFxmlController().setPostData(post);
		postReplies.getFxmlController().setLandingController(landingController);
    }
	
	public void setPostData(Post post) {
		this.post = post;
		profilePic.setImage(new Image(new ByteArrayInputStream(post.getPostUser().getProfilePic().returnBytes())));
		usernameLabel.setText(post.getPostUser().getUsername());
		titleLabel.setText(post.getTitle());
		descriptionField.setText(post.getDescription());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy h:mm aa");
		dateLabel.setText(df.format(post.getPostDate()));	
	}
	
	 public void setLandingController(LandingController landingController) {
		 this.landingController = landingController;
	 }
}
