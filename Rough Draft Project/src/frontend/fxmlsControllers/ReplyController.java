package frontend.fxmlsControllers;

import backend.Post;
import frontend.GUIBackend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class ReplyController {
	@FXML private Button cancelBtn;
    @FXML private TextArea descriptionField;
    @FXML private Button replyBtn;
    
    private Post post;
    private TilePane tilePane;
    private LandingController landingController;
    
    public ReplyController() {	
    } 
    
    @FXML public void cancelBtnOnAction(ActionEvent event) {
    	((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML public void replyBtnOnAction(ActionEvent event) {
    	Post newPost = new Post(SignInController.currentUser.getUsername() + "'s Reply:", null, descriptionField.getText(), SignInController.currentUser, null);
    	post.getPostReplies().add(newPost);
    	SignInController.currentUser.getUserPosts().add(newPost);
    	GUIBackend.displayNewPost(newPost, tilePane, landingController);
    	((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
    public void passData(Post post, TilePane tilePane) {
    	this.tilePane = tilePane; 	
    	this.post = post;
    }
    
    public void setLandingController(LandingController landingController) {
		 this.landingController = landingController;
	 }
}
