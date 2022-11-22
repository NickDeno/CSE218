package frontend.fxmlsControllers;

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
	@FXML private Button searchBtn;
	@FXML private Button removeFilterBtn;
	
	private LandingController landingController;
	private final String[] filterOptions = {"User", "Topic", "Title"};
	private final String[] topicOptions = {"Computer Science", "School", "Gaming", "Gym", "Sports", "Misc.", "Other"};
	
	private String chosenFilter;
	private String chosenTopic;
	
	//Initializer
	public HomeFeedController() {}
	
	public void initialize() {
		filterBox.getItems().addAll(filterOptions);
		topicBox.getItems().addAll(topicOptions);
		Platform.runLater(() ->{
			GUIBackend.displayPosts(PostCenter.getInstance(), SignInController.currentUser, tilePane, landingController);
		});
	}
	
	@FXML public void filterBoxOnAction(ActionEvent event) {
		if(filterBox.getValue().equals("User")) {
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
	
	@FXML public void searchBtnOnAction(ActionEvent event) {
		if(chosenFilter.equals("User")) {
			User searchedUser = UserCenter.getInstance().getUser(searchField.getText());
			if(searchedUser == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("No User Found.");
				alert.setHeaderText(null);
				alert.setContentText("No user found with specified username. Please try again.");
				alert.showAndWait();
				searchField.clear();
			} else if(SignInController.currentUser.getBlockedUsers().containsKey(searchedUser.getUsername())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Blocked User.");
				alert.setHeaderText(null);
				alert.setContentText("Sorry. The user you searched for is in you blocked users. If you wish to see their posts, please unblock them.");
				alert.showAndWait();
				searchField.clear();	
			} else {
				tilePane.getChildren().clear();
				GUIBackend.displayPosts(searchedUser.getUserPosts(), SignInController.currentUser, tilePane, landingController);
				resetFields();
			}
		} else if(chosenFilter.equals("Topic")) {
			tilePane.getChildren().clear();
			GUIBackend.displayPosts(PostCenter.getInstance().searchByTopic(chosenTopic), SignInController.currentUser, tilePane, landingController);
			resetFields();
		} else if(chosenFilter.equals("Title")) {
			tilePane.getChildren().clear();
			GUIBackend.displayPosts(PostCenter.getInstance().searchByTitle(searchField.getText()), SignInController.currentUser,  tilePane, landingController);
			resetFields();
		}
		
	}
	
	@FXML public void removeFilterBtnOnAction(ActionEvent event) {
		tilePane.getChildren().clear();
		GUIBackend.displayPosts(PostCenter.getInstance(), SignInController.currentUser, tilePane, landingController);
		resetFields();
	}
	
	public void displayNewPost(Post post) {
		GUIBackend.displayPost(post, tilePane, landingController);
	}
	
	private void resetFields() {
		searchField.clear();
		searchField.setVisible(false);
		searchFieldLine.setVisible(false);
//		filterBox.setValue(null);
//		topicBox.setValue(null);
		topicBox.setVisible(false);
	}
	
	public TilePane getTilePane() {
		return tilePane;
	}
	
	public void setLandingController(LandingController landingController) {
		this.landingController = landingController;
	}
	
}
