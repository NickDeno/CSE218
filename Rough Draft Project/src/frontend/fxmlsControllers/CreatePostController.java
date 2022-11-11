package frontend.fxmlsControllers;

import backend.Post;
import backend.Utilities;
import frontend.GUIBackend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CreatePostController {
	 @FXML private Button backBtn;
	 @FXML private Button createPostButton;
	 @FXML private TextArea descriptionField;
	 @FXML private TextField titleField;
	 @FXML private Label msgLabel;
	
	 private AnchorPane contentPane;
	 
	 public CreatePostController() {

	 }

	 @FXML public void createPostButtonOnAction(ActionEvent event) {
		 if(!titleField.getText().isEmpty() && !descriptionField.getText().isEmpty()) {
			 Post newPost = new Post(SignInController.currentUser.getUsername(), titleField.getText(), descriptionField.getText());
			 SignInController.allPosts.add(newPost);
			 SignInController.currentUser.getUserPosts().add(newPost);
			 Utilities.backupPosts(SignInController.allPosts);
			 Utilities.backupUsers(SignInController.users);	 
			 GUIBackend.loadPane(contentPane, GUIBackend.HomeFeedScene); 
		 } else {
			 titleField.clear();
			 descriptionField.clear();
			 msgLabel.setText("Error. Please Try Again.");
			 msgLabel.setVisible(true);
		 }
	 }
	 
	 @FXML public void backBtnOnAction(ActionEvent event) {
		 GUIBackend.loadPane(contentPane, GUIBackend.HomeFeedScene);
	 }
	 
	 public void setContentPane(AnchorPane contentPane) {
		 this.contentPane = contentPane;
	 }

}
