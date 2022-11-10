package frontend;

import java.io.IOException;

import backend.Post;
import backend.PostList;
import frontend.fxmlsControllers.PostController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class GUIBackend {
	public static final String SignInScene = "/frontend/fxmls/SignIn.fxml";
	public static final String SignUpScene = "/frontend/fxmls/SignUp.fxml";
	public static final String LandingScene = "/frontend/fxmls/Landing.fxml";
	public static final String HomeFeedScene = "/frontend/fxmls/HomeFeed.fxml";
	public static final String ProfileScene = "/frontend/fxmls/Profile.fxml";
	public static final String SettingsScene = "/frontend/fxmls/Settings.fxml";
	public static final String CreatePostScene = "/frontend/fxmls/CreatePost.fxml";
	public static final String PostWithRepliesScene = "/frontend/fxmls/PostWithReplies.fxml";
	
	//Loads new window with specified fxml file and returns "Controller" of specified fxml file
	public static <T> T loadNewWindow(String fxmlFileName) {
		try {
//			Parent root = FXMLLoader.load(GUIBackend.class.getResource(fxmlFileName));
			FXMLLoader loader = new FXMLLoader(GUIBackend.class.getResource(fxmlFileName));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			return loader.getController();
		} catch (IOException e) {
			System.out.println("Unable to load new window with " + fxmlFileName);
			e.printStackTrace();
			return null;
		}
	}
	
	// Loads new scene into specified stage with specified fxml file
	public static <T> T loadNewScene(Stage stage, String fxmlFileName) {
		try {
			FXMLLoader loader = new FXMLLoader(GUIBackend.class.getResource(fxmlFileName));
			Parent root = loader.load();
			stage.setScene(new Scene(root));
			stage.show();
			return loader.getController();
		} catch (IOException e) {
			System.out.println("Unable to load scene with " + fxmlFileName);
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T> T loadPane(AnchorPane ap, String fxmlFileName) {
		try {
			FXMLLoader loader = new FXMLLoader(GUIBackend.class.getResource(fxmlFileName));
			AnchorPane pane = loader.load();
			ap.getChildren().clear();
			ap.getChildren().setAll(pane);
			return loader.getController();
		} catch (IOException e) {
			System.out.println("Unable to load content pane with " + fxmlFileName);
			e.printStackTrace();
			return null;
		}
	}
	
	//Takes a list of posts and creates fxml pane for each post. Then loads these posts into a tilePane to be displayed on screen
	public static void displayPosts(PostList postList, TilePane tilePane) {
		for (int i = 0; i < postList.size(); i++) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(GUIBackend.class.getResource("/frontend/fxmls/Post.fxml"));
				AnchorPane ap = fxmlLoader.load();
				PostController postController = fxmlLoader.getController();
				postController.setPostData(postList.get(i));
				tilePane.getChildren().add(ap);	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Takes a singular post and creates fxml pane for post. Then adds fxml pane to top of tilePane since its the newest post and should be displayed 
	// at top. This is done by adding new fxml pane to 0 index of tilePane which shifts all other posts down one.
	public static void displayPost(Post post, TilePane tilePane) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(GUIBackend.class.getResource("/frontend/fxmls/Post.fxml"));
			AnchorPane ap = fxmlLoader.load();
			PostController postController = fxmlLoader.getController();
			postController.setPostData(post);
			tilePane.getChildren().add(0,ap);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
