package controllers;

import model.AppState;
import model.Post;
import model.ReplyPost;
import model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class PostReplyController {
	@FXML private Button cancelBtn;
    @FXML private TextArea descriptionField;
    @FXML private Button replyBtn;
    
    private Post post;
    private User currentUser;
    private LandingController landingController;
    private PostWithRepliesController postRepliesController;
    
    public PostReplyController() {} 
    
    public void initialize() {
    	currentUser = AppState.getInstance().getUserCenter().getCurrentUser();
    	Platform.runLater(() -> {
    		((Stage)cancelBtn.getScene().getWindow()).setOnCloseRequest(e -> landingController.getPane().setEffect(null));
    	});
    }
    
    @FXML public void cancelBtnOnAction(ActionEvent event) {
    	landingController.getPane().setEffect(null);
    	((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML public void replyBtnOnAction(ActionEvent event) {
    	ReplyPost newReply = new ReplyPost(currentUser, post, descriptionField.getText());
    	post.reply(newReply, currentUser);
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

	public void setPostRepliesController(PostWithRepliesController postRepliesController) {
		this.postRepliesController = postRepliesController;
	}
}
