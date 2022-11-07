package frontend.fxmlsControllers;

import java.io.IOException;

import backend.Post;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class HomeFeedController {
	@FXML private Label userLabel;
	@FXML private GridPane grid;
	@FXML private ScrollPane scroll;
	
	private int nPosts;
	
	public void initialize() {
		userLabel.setText("Welcome Back " + SignInController.currentUser.getUsername() + " !");
		grid.setGridLinesVisible(true);
		loadAllPosts();
	}
	
	public void loadAllPosts() {
		for (int i = 0; i < SignInController.allPosts.size(); i++) {
			loadPost(SignInController.allPosts.get(i));
		}
	}
	
	//Takes "Post Object" and creates a FXML pane/box to be displayed on the HomeFeed. 
	public void loadPost(Post post) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/frontend/fxmls/Post.fxml"));
			AnchorPane ap = fxmlLoader.load();
			PostController postController = fxmlLoader.getController();
			postController.setPostData(post);
			
			grid.add(ap, 0, nPosts++);
			GridPane.setHalignment(ap, HPos.CENTER);
			GridPane.setMargin(ap, new Insets(10));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
