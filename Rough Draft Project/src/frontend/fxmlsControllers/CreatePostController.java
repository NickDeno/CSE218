package frontend.fxmlsControllers;

import backend.Post;
import backend.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreatePostController {
	 @FXML private Button backBtn;
	 @FXML private Button createPostButton;
	 @FXML private TextArea descriptionField;
	 @FXML private TextField titleField;
	 
	 public void initialize() {
		 
	 }

	 @FXML public void createPostButtonOnAction(ActionEvent event) {
		 Post newPost = new Post(SignInController.currentUser.getUsername(), titleField.getText(), descriptionField.getText());
		 SignInController.allPosts.add(newPost);
		 SignInController.currentUser.getUserPosts().add(newPost);
		 Utilities.backupPosts(SignInController.allPosts);
		 Utilities.backupUsers(SignInController.users);	 
		 ((Stage)((Node)event.getSource()).getScene().getWindow()).close();	 
	 }
	 
	 @FXML public void backBtnOnAction(ActionEvent event) {
		((Stage)((Node)event.getSource()).getScene().getWindow()).close();
	 }
}
