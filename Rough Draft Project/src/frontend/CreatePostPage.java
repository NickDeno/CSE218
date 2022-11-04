package frontend;

import backend.Post;
import backend.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreatePostPage {
	 @FXML private Button backBtn;
	 @FXML private Button createPostButton;
	 @FXML private TextArea descriptionField;
	 @FXML private TextField titleField;
	 
	 public void initialize() {
		 
	 }

	 @FXML public void createPostButtonOnAction(ActionEvent event) {
		 Post newPost = new Post(SignInPage.currentUser.getUsername(), titleField.getText(), descriptionField.getText());
		 SignInPage.postList.add(newPost);
		 SignInPage.currentUser.getUserPosts().add(newPost);
		 Utilities.backupPosts(SignInPage.postList);
		 Utilities.backupUsers(SignInPage.users);	 
		 ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
		 
//		 System.out.println("Current Users Posts:");
//		 SignInPage.currentUser.getUserPosts().display();
//		 System.out.println("All Posts:");
//		 SignInPage.postList.display();
		 
	 }
	 
	 @FXML public void backBtnOnAction(ActionEvent event) {
		((Stage)((Node)event.getSource()).getScene().getWindow()).close();
	 }
}
