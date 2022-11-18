package frontend;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import backend.Post;
import backend.PostCenter;
import backend.StackPaneNode;
import backend.User;
import frontend.fxmlsControllers.LandingController;
import frontend.fxmlsControllers.PostController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
	public static final String PostRepliesScene = "/frontend/fxmls/PostReplies.fxml";
	public static final String ReplyScene = "/frontend/fxmls/Reply.fxml";
	
	//Takes a file (for this project mainly image files) and converts it into a byte array. This is done since by default, JavaFX images are not
	//serializable. So instead, we can convert the image into a byte array and save the byte array into a specified file.
	public static byte[] fileToByteArr(File file) {
		try {
			byte[] byteArr = Files.readAllBytes(file.toPath());
			return byteArr;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Loads new window with specified fxmlFile and returns FXMLController of the fxmlFile being loaded
	public static <T> T loadNewWindow(String fxmlFileName) {
		try {
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
	
	// Loads new scene into specified stage with specified fxml file. Returns the FXMLController of the fxmlFile being loaded
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
	
	/*
	 * Loads specified StackPane with specified fxmlFile by creating an AnchorPane with that fmxlFile and loading the AnchorPane into StackPane.
	 * Also, a "paneId" is passed through this method. The paneId is assigned to the id of AnchorPane being created. Doing this allows the option
	 * for a specific AnchorPane to be searched for in StackPane by checking to see if each AnchorPane in StackPane equals the paneId that is
	 * being searched for. Finally, this method returns a "StackPaneNode" which contains the FXMLController instance of the loaded AnchorPane, and the 
	 * ID of the loaded AnchorPane.		
	 */
	public static <T> StackPaneNode<T> loadStackPane(StackPane sp, String fxmlFileName, String paneId) {
		try {
			FXMLLoader loader = new FXMLLoader(GUIBackend.class.getResource(fxmlFileName));
			AnchorPane pane = loader.load();
			pane.setId(paneId);
			sp.getChildren().add(pane);
			return new StackPaneNode<T>(loader.getController(), pane.getId());
			
		} catch (IOException e) {
			System.out.println("Unable to load content pane with " + fxmlFileName);
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * Takes a PostList of posts and creates AnchorPanes for each of the posts. Then, each AnchorPane is added into a TilePane which will display
	 * each post in a vertical list. Also, this method takes in an instance of a LandingController. This is done so that when each AnchorPane of
	 * each post is created, the LandingController can be passed to the PostController of each post being displayed. This is done so that the PostController
	 * of each post and LandingController can communicate to each other and pass data between each other.
	 * 		
	 * This can be useful for example when the user clicks on a post, a new scene will be loaded with the clicked post and replies. However to load
	 * this new scene, we need to get the "contentPane" of the LandingController to load this new scene into. So since the LandingController is passed
	 * into each PostController, the PostController can get the contentPane of the LandingController passed into it and display the new scene in the content
	 * pane.
	 */
	public static void displayPosts(PostCenter postList, TilePane tilePane, LandingController landingController) {
		Iterator<Post> itr = postList.getValues().iterator();
		while(itr.hasNext()) {
			Post currPost = itr.next();
			try {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(GUIBackend.class.getResource("/frontend/fxmls/Post.fxml"));
				AnchorPane ap = fxmlLoader.load();
				PostController postController = fxmlLoader.getController();
				postController.setPostData(currPost);
				postController.setLandingController(landingController);
				tilePane.getChildren().add(ap);	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void displayPosts(LinkedHashMap<UUID, Post> posts, TilePane tilePane, LandingController landingController) {
		for(Map.Entry<UUID, Post> entry: posts.entrySet()) {
			try {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(GUIBackend.class.getResource("/frontend/fxmls/Post.fxml"));
				AnchorPane ap = fxmlLoader.load();
				PostController postController = fxmlLoader.getController();
				postController.setPostData(entry.getValue());
				postController.setLandingController(landingController);
				tilePane.getChildren().add(ap);	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * This method works almost exactly the same as displayPosts method. However, instead of taking in a PostList of posts, it takes in a singular post.
	 * Then, creates an AnchorPane of this post object. Instead of adding the AnchorPane to bottom of TilePane like displayPosts, the AnchorPane is added
	 * to the top of the TilePane. Then, the instance of the LandingController being passed into this method is passed into the
	 * PostController of this post
	 */
	public static void displayPost(Post post, TilePane tilePane, LandingController landingController) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(GUIBackend.class.getResource("/frontend/fxmls/Post.fxml"));
			AnchorPane ap = fxmlLoader.load();
			PostController postController = fxmlLoader.getController();
			postController.setPostData(post);
			postController.setLandingController(landingController);
			tilePane.getChildren().add(0,ap);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
