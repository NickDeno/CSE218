package controllers;

import java.util.LinkedList;

import model.AppState;
import model.Post;
import model.User;
import util.GUIBackend;
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
	private String chosenFilter;
	private String chosenTopic;
	private final String[] filterOptions = {"All Posts", "Following", "User", "Topic", "Title"};
	private final String[] topicOptions = {"Computer Science", "School", "Gaming", "Gym", "Sports", "Misc.", "Other"};
	private LandingController landingController;
	
	//Initializer
	public HomeFeedController() {}
	
	public void initialize() {
		currentUser = AppState.getInstance().getUserCenter().getCurrentUser();
		filterBox.getItems().addAll(filterOptions);
		topicBox.getItems().addAll(topicOptions);
		filterBox.getSelectionModel().selectFirst();
		Platform.runLater(() ->{
			displayPosts(AppState.getInstance().getPostCenter().getPosts());
		});
	}
	
	@FXML public void filterBoxOnAction(ActionEvent event) {	
		if(filterBox.getValue().equals("All Posts")) {
			chosenFilter = filterBox.getValue();
			searchField.setVisible(false);
			searchFieldLine.setVisible(false);	
			topicBox.setVisible(false);
		} else if(filterBox.getValue().equals("Following")) {
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
		if(chosenFilter.equals("All Posts")) {
			displayPosts(AppState.getInstance().getPostCenter().getPosts());		
		} else if(chosenFilter.equals("Following")) {
			if(currentUser.getFollowing().values().size() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("No Followed Users.");
				alert.setHeaderText(null);
				alert.setContentText("Unable to filter posts by following since you are not following anyone.");
				alert.showAndWait();
				filterBox.getSelectionModel().selectFirst();
				tilePane.getChildren().clear();
				displayPosts(AppState.getInstance().getPostCenter().getPosts());
				resetFields();
			} else {
				tilePane.getChildren().clear();
				displayFollowingPosts(AppState.getInstance().getPostCenter().getPosts());
			}
		} else if(chosenFilter.equals("User")) {
			User searchedUser = AppState.getInstance().getUserCenter().getUser(searchField.getText());
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
				tilePane.getChildren().clear();
				displayPosts(searchedUser.getUserPosts());
				resetFields();
			}
		} else if(chosenFilter.equals("Topic")) {
			tilePane.getChildren().clear();
			displayPosts(AppState.getInstance().getPostCenter().searchByTopic(chosenTopic));
			resetFields();
		} else if(chosenFilter.equals("Title")) {
			tilePane.getChildren().clear();
			displayPosts(AppState.getInstance().getPostCenter().searchByTitle(searchField.getText()));
			resetFields();
		}
		
	}
	
	@FXML public void removeFilterBtnOnAction(ActionEvent event) {
		tilePane.getChildren().clear();
		filterBox.getSelectionModel().selectFirst();
		displayPosts(AppState.getInstance().getPostCenter().getPosts());
		resetFields();
	}
	
	private void displayPosts(LinkedList<Post> posts) {
		 GUIBackend.displayPostsNewToOld(posts, tilePane, landingController);
	}
	
	private void displayFollowingPosts(LinkedList<Post> posts) {
		 GUIBackend.displayFollowingPosts(posts, tilePane, landingController);
	}
	
	private void resetFields() {
		searchField.clear();
		searchField.setVisible(false);
		searchFieldLine.setVisible(false);
		topicBox.setValue("");
		topicBox.setVisible(false);
	}
	
	public void setLandingController(LandingController landingController) {
		this.landingController = landingController;
	}
	
}
