package frontend.fxmlsControllers;

import java.io.IOException;

import backend.Post;
import backend.PostList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

public class HomeFeedController {
	@FXML private Label userLabel;
	@FXML private ScrollPane scroll;
	@FXML private TilePane tilePane;
	
	public HomeFeedController() {
		
	}
	
	public void initialize() {
		userLabel.setText("Welcome Back " + SignInController.currentUser.getUsername() + " !");
		loadAllPosts(SignInController.allPosts);
	}
	
	public void loadAllPosts(PostList postList) {
		for (int i = 0; i < postList.size(); i++) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/frontend/fxmls/Post.fxml"));
				AnchorPane ap = fxmlLoader.load();
				PostController postController = fxmlLoader.getController();
				postController.setPostData(postList.get(i));	
				tilePane.getChildren().add(ap);	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void loadNewPost(Post post) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("/frontend/fxmls/Post.fxml"));
			AnchorPane ap = fxmlLoader.load();
			PostController postController = fxmlLoader.getController();
			postController.setPostData(post);	
			tilePane.getChildren().add(0,ap);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
