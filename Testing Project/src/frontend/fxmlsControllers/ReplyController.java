package frontend.fxmlsControllers;

import java.util.UUID;

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
    	Post newReply = new Post(SignInController.currentUser.getUsername() + "'s Reply:", null, descriptionField.getText(), null, SignInController.currentUser, UUID.randomUUID()); 	
    	post.reply(newReply);
//    	SignInController.globalUsers.getUser(post.getPoster().getUsername()).getUserPosts().add(newReply);
    	SignInController.currentUser.getUserPosts().add(newReply);
    	GUIBackend.displayPost(newReply, tilePane, landingController);
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
