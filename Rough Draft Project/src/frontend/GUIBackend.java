package frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;

import backend.Post;
import backend.UserCenter;
import frontend.fxmlsControllers.LandingController;
import frontend.fxmlsControllers.PostController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUIBackend {
	public static final String SignInScene = "/frontend/fxmls/SignIn.fxml";
	public static final String SignUpScene = "/frontend/fxmls/SignUp.fxml";
	public static final String LandingScene = "/frontend/fxmls/Landing.fxml";
	public static final String HomeFeedScene = "/frontend/fxmls/HomeFeed.fxml";
	public static final String CurrentUserProfileScene = "/frontend/fxmls/CurrentUserProfile.fxml";
	public static final String SettingsScene = "/frontend/fxmls/Settings.fxml";
	public static final String CreatePostScene = "/frontend/fxmls/CreatePost.fxml";
	public static final String PostRepliesScene = "/frontend/fxmls/PostReplies.fxml";
	public static final String PostImagesScene = "/frontend/fxmls/PostImages.fxml";
	public static final String ReplyScene = "/frontend/fxmls/Reply.fxml";
	public static final String BlockUserScene = "/frontend/fxmls/BlockUser.fxml";
	public static final String UserProfileScene = "/frontend/fxmls/UserProfile.fxml";
	
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
	
	//Loads new undecorated window with specified fxmlFile and returns FXMLController of the fxmlFile being loaded
	public static <T> T loadNewUndecoratedWindow(String fxmlFileName) {
		try {
			FXMLLoader loader = new FXMLLoader(GUIBackend.class.getResource(fxmlFileName));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			return loader.getController();
		} catch (IOException e) {
			System.out.println("Unable to load new undecorated window with " + fxmlFileName);
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
	
	//Loads specified AnchorPane with specified fxmlFile. Returns controller of the loaded fxmlFile.
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
	
	/*
	 * Functions virtually the same as other displayPosts method. However in this method, a specific users' "userPosts" will be passed through instead
	 * of PostCenter which contains all posts. This method will be called when a user wants to only view a specific user's posts instead of all posts. One 
	 * difference is that in the other displayPosts method, it will check if each posts' poster is in the current users blocked list. If they are, it will
	 * skip over displaying that post. In this case, the userPosts being displayed all come from one specific user. So all we have to do in this method is check
	 * if the first posts' poster is in the current users blocked list and if they are, we can break out of method since all of the posts in this case will be from
	 * that user.
	 */
	public static void displayPostsNewToOld(LinkedHashMap<UUID, Post> posts, TilePane tilePane, LandingController landingController, boolean isClickable) {
		ArrayList<Post> postList = new ArrayList<Post>(posts.values());
		for(int i = postList.size()-1; i >= 0; i--) {
			//If this posts' poster is in the current users' "blockedUsers", it will not display post
			if(UserCenter.getInstance().getCurrentUser().getBlockedUsers().get(postList.get(i).getPoster().getUsername()) != null) {
				continue;
			}
			try {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(GUIBackend.class.getResource("/frontend/fxmls/Post.fxml"));
				AnchorPane ap = fxmlLoader.load();
				PostController postController = fxmlLoader.getController();
				postController.setPostData(postList.get(i));
				postController.setLandingController(landingController);
				tilePane.getChildren().add(ap);	
				if(isClickable == false) {
					//Removes "Hover" and "Clicked" effect from post when post is not clickable
					ap.getStyleClass().clear();
					ap.setStyle("-fx-background-color: linear-gradient(from 0.0% 0.0% to 100.0% 100.0%, #e84d4d 0.0%, #a8a8a8 100.0%); -fx-border-color: black; -fx-border-width: 3;");
				}
				postController.setClickable(isClickable);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void displayPostsOldToNew(LinkedHashMap<UUID, Post> posts, TilePane tilePane, LandingController landingController, boolean isClickable) {
		ArrayList<Post> postList = new ArrayList<Post>(posts.values());
		for(int i = 0; i < postList.size(); i++) {
			//If this posts' poster is in the current users' "blockedUsers", it will not display post
			if(UserCenter.getInstance().getCurrentUser().getBlockedUsers().get(postList.get(i).getPoster().getUsername()) != null) {
				continue;
			}
			try {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(GUIBackend.class.getResource("/frontend/fxmls/Post.fxml"));
				AnchorPane ap = fxmlLoader.load();
				PostController postController = fxmlLoader.getController();
				postController.setPostData(postList.get(i));
				postController.setLandingController(landingController);
				tilePane.getChildren().add(ap);	
				if(isClickable == false) {
					//Removes "Hover" and "Clicked" effect from post when post is not clickable
					ap.getStyleClass().clear();
					ap.setStyle("-fx-background-color: linear-gradient(from 0.0% 0.0% to 100.0% 100.0%, #e84d4d 0.0%, #a8a8a8 100.0%); -fx-border-color: black; -fx-border-width: 3;");
				}
				postController.setClickable(isClickable);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * This method works almost exactly the same as displayPosts method. However, instead of taking in a PostCenter of posts or a specific
	 * users posts, it takes in a singular post.Then, creates an AnchorPane of this post object. Instead of adding the AnchorPane to bottom of TilePane 
	 * like displayPosts, the AnchorPane is added to the top of the TilePane.
	 */
	public static void displayPostOnTop(Post post, TilePane tilePane, LandingController landingController, boolean isClickable) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(GUIBackend.class.getResource("/frontend/fxmls/Post.fxml"));
			AnchorPane ap = fxmlLoader.load();
			PostController postController = fxmlLoader.getController();
			postController.setPostData(post);
			postController.setLandingController(landingController);
			tilePane.getChildren().add(0,ap);	
			if(isClickable == false) {
				//Removes "Hover" and "Clicked" effect from post when post is not clickable
				ap.getStyleClass().clear();
				ap.setStyle("-fx-background-color: linear-gradient(from 0.0% 0.0% to 100.0% 100.0%, #e84d4d 0.0%, #a8a8a8 100.0%); -fx-border-color: black; -fx-border-width: 3;");
			}
			postController.setClickable(isClickable);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public static void displayPostOnBottom(Post post, TilePane tilePane, LandingController landingController, boolean isClickable) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(GUIBackend.class.getResource("/frontend/fxmls/Post.fxml"));
			AnchorPane ap = fxmlLoader.load();
			PostController postController = fxmlLoader.getController();
			postController.setPostData(post);
			postController.setLandingController(landingController);
			tilePane.getChildren().add(ap);	
			if(isClickable == false) {
				//Removes "Hover" and "Clicked" effect from post when post is not clickable
				ap.getStyleClass().clear();
				ap.setStyle("-fx-background-color: linear-gradient(from 0.0% 0.0% to 100.0% 100.0%, #e84d4d 0.0%, #a8a8a8 100.0%); -fx-border-color: black; -fx-border-width: 3;");
			}
			postController.setClickable(isClickable);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
