package frontend.fxmlsControllers;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.UUID;

import backend.Post;
import backend.PostCenter;
import backend.User;
import backend.UserCenter;
import frontend.GUIBackend;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Line;

public class HomeFeedController {
	@FXML private TilePane tilePane;
	@FXML private ComboBox<String> filterBox;
	@FXML private ComboBox<String> topicBox;
	@FXML private TextField searchField;
	@FXML private Line searchFieldLine;
	@FXML private Button applyFilterBtn;
	@FXML private Button removeFilterBtn;
	
	private User currentUser;
	private LandingController landingController;
	private final String[] filterOptions = {"Following", "Followers", "User", "Topic", "Title"};
	private final String[] topicOptions = {"Computer Science", "School", "Gaming", "Gym", "Sports", "Misc.", "Other"};
	private String chosenFilter;
	private String chosenTopic;
	
	//Initializer
	public HomeFeedController() {}
	
	public void initialize() {
		currentUser = UserCenter.getInstance().getCurrentUser();
		filterBox.getItems().addAll(filterOptions);
		topicBox.getItems().addAll(topicOptions);
		Platform.runLater(() ->{
			displayPosts(PostCenter.getInstance().getPosts());
		});
	}
	
	@FXML public void filterBoxOnAction(ActionEvent event) {	
		if(filterBox.getValue().equals("Following")) {
			chosenFilter = filterBox.getValue();
			searchField.setVisible(false);
			searchFieldLine.setVisible(false);	
			topicBox.setVisible(false);
		} else if(filterBox.getValue().equals("Followers")) {
			chosenFilter = filterBox.getValue();
			searchField.setVisible(false);
			searchFieldLine.setVisible(false);	
			topicBox.setVisible(false);
		} else if(filterBox.getValue().equals("User")) {
			chosenFilter = filterBox.getValue();
			searchField.setVisible(true);
			searchFieldLine.setVisible(true);	
			topicBox.setVisible(false);
		} else if(filterBox.getValue().equals("Topic")) {
			chosenFilter = filterBox.getValue();
			searchField.setVisible(false);
			searchFieldLine.setVisible(false);
			topicBox.setVisible(true);
		} else if(filterBox.getValue().equals("Title")) {
			chosenFilter = filterBox.getValue();
			searchField.setVisible(true);
			searchFieldLine.setVisible(true);
			topicBox.setVisible(false);
		}	
	}
	
	@FXML public void topicBoxOnAction(ActionEvent event) {
		if(topicBox.getValue().equals("Other")) {
			chosenTopic = topicBox.getValue();
			searchField.setVisible(true);
			searchFieldLine.setVisible(true);
		} else {
			chosenTopic = topicBox.getValue();
			searchField.setVisible(false);
			searchFieldLine.setVisible(false);
		}
	}
	
	@FXML public void applyFilterBtnOnAction(ActionEvent event) {
		if(chosenFilter.equals("Following")) {
			if(currentUser.getFollowing().values().size() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("No Followed Users.");
				alert.setHeaderText(null);
				alert.setContentText("Unable to filter posts by following since you are not following anyone.");
				alert.showAndWait();
				resetFields();
				return;
			}
			Iterator<User> itr = currentUser.getFollowing().values().iterator();
			while(itr.hasNext()) {
				displayPosts(itr.next().getUserPosts());
			}
		} else if(chosenFilter.equals("Followers")) {
			if(currentUser.getFollowers().values().size() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("No Followed Users.");
				alert.setHeaderText(null);
				alert.setContentText("Unable to filter posts by followers since nobody follows you.");
				alert.showAndWait();
				resetFields();
				return;
			}
			Iterator<User> itr = currentUser.getFollowers().values().iterator();
			while(itr.hasNext()) {
				displayPosts(itr.next().getUserPosts());
			}
		} else if(chosenFilter.equals("User")) {
			User searchedUser = UserCenter.getInstance().getUser(searchField.getText());
			if(searchedUser == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("No User Found.");
				alert.setHeaderText(null);
				alert.setContentText("No user found with specified username. Please try again.");
				alert.showAndWait();
				searchField.clear();
			} else if(currentUser.getBlockedUsers().get(searchedUser.getUsername()) != null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Blocked User.");
				alert.setHeaderText(null);
				alert.setContentText("Sorry. The user you searched for is in you blocked users. If you wish to see their posts, please unblock them.");
				alert.showAndWait();
				searchField.clear();	
			} else {
				displayPosts(searchedUser.getUserPosts());
				resetFields();
			}
		} else if(chosenFilter.equals("Topic")) {
			displayPosts(PostCenter.getInstance().searchByTopic(chosenTopic));
			resetFields();
		} else if(chosenFilter.equals("Title")) {
			displayPosts(PostCenter.getInstance().searchByTitle(searchField.getText()));
			resetFields();
		}
		
	}
	
	@FXML public void removeFilterBtnOnAction(ActionEvent event) {
		tilePane.getChildren().clear();
		filterBox.setValue("No Filter");
		displayPosts(PostCenter.getInstance().getPosts());
		resetFields();
	}
	
	 private void displayPosts(LinkedHashMap<UUID, Post> posts) {
		 tilePane.getChildren().clear();
		 GUIBackend.displayPostsNewToOld(posts, tilePane, landingController, true);
	   }
	
	
	private void resetFields() {
		searchField.clear();
		searchField.setVisible(false);
		searchFieldLine.setVisible(false);
//		filterBox.setValue("No Filter");
//		topicBox.setValue(null);
		topicBox.setVisible(false);
	}
	
	public void setLandingController(LandingController landingController) {
		this.landingController = landingController;
	}
	
}
