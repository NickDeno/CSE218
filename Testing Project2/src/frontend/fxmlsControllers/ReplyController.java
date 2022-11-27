package frontend.fxmlsControllers;

import java.util.UUID;

import backend.Post;
import backend.UserCenter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ReplyController {
	@FXML private Button cancelBtn;
    @FXML private TextArea descriptionField;
    @FXML private Button replyBtn;
    
    private Post post;
    private LandingController landingController;
    private PostRepliesController postRepliesController;
    
    public ReplyController() {} 
    
    public void initialize() {
    	Platform.runLater(() -> {
    		((Stage)cancelBtn.getScene().getWindow()).setOnCloseRequest(e -> {
    			landingController.getPane().setEffect(null);
    		});
    	});
    }
    
    @FXML public void cancelBtnOnAction(ActionEvent event) {
    	landingController.getPane().setEffect(null);
    	((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML public void replyBtnOnAction(ActionEvent event) {
    	Post newReply = new Post(UserCenter.getInstance().getCurrentUser().getUsername() + "'s Reply:", "Reply", descriptionField.getText(), 
    			null, UserCenter.getInstance().getCurrentUser(), UUID.randomUUID());
    	post.reply(newReply, post.getPoster());
    	postRepliesController.displayPost(newReply);
    	landingController.getPane().setEffect(null);
    	((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    
    public void setPost(Post post) {	
    	this.post = post;
    }
    
    public void setLandingController(LandingController landingController) {
		 this.landingController = landingController;
	 }

	public void setPostRepliesController(PostRepliesController postRepliesController) {
		this.postRepliesController = postRepliesController;
	}
}
