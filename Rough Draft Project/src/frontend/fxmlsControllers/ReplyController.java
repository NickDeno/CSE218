package frontend.fxmlsControllers;

import backend.Post;
import backend.Utilities;
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
    
    private Post postToReply;
    private TilePane tilePane;
    
    public ReplyController() {	
    }
    
    public void initialize() {
    	
    }
    
    
    @FXML public void cancelBtnOnAction(ActionEvent event) {
    	((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML public void replyBtnOnAction(ActionEvent event) {
    	Post newPost = new Post(SignInController.currentUser.getUsername(), SignInController.currentUser.getUsername() + "'s Reply:", 
    			descriptionField.getText());
    	postToReply.getPostReplies().add(newPost);
    	SignInController.currentUser.getUserPosts().add(newPost);
    	Utilities.backupPosts(SignInController.allPosts);
		Utilities.backupUsers(SignInController.users);
    	GUIBackend.displayPost(newPost, tilePane);
    	((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
    public void passData(Post postToReply, TilePane tilePane) {
    	this.tilePane = tilePane; 	
    	this.postToReply = postToReply;
    }
}
