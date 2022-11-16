package frontend.fxmlsControllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.LinkedList;

import backend.FXImage;
import backend.Post;
import frontend.GUIBackend;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class CreatePostController {
	 @FXML private Button backBtn;
	 @FXML private Button createPostButton;
	 @FXML private Button browseButton;
	 @FXML private TextArea descriptionField;
	 @FXML private TextField titleField;
	 @FXML private ComboBox<String> topicBox;
	 @FXML private Label msgLabel;
	 
	 @FXML private ImageView postImg1;
	 @FXML private ImageView postImg2;
	 @FXML private ImageView postImg3;
	 
	 @FXML private Rectangle postImgBorder1;
	 @FXML private Rectangle postImgBorder2;
	 @FXML private Rectangle postImgBorder3;
	 
	 private LandingController landingController;
	 private LinkedList<FXImage> postImages = new LinkedList<FXImage>();
	 private final String[] defaultTopics = {"Computer Science", "School", "Gaming", "Sports", "Misc", "Add New Topic"};
	 
	 public CreatePostController() {}
	 
	 public void initialize() {
		topicBox.getItems().addAll(defaultTopics);
	 }
	 
	 @FXML public void browseButtonOnAction(ActionEvent event) {
			FileChooser fc = new FileChooser();
			fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
			File selectedFile = fc.showOpenDialog(null);
			if (selectedFile == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Invalid File");
				alert.setHeaderText(null);
				alert.setContentText("Either chosen image was invalid, or no image was chosen. Please try again.");
				alert.showAndWait();
			} else {
				byte[] chosenImageBytes = GUIBackend.fileToByteArr(selectedFile);
				postImages.add(new FXImage(chosenImageBytes));
				displayImage(chosenImageBytes);			
			}
	 }

	@FXML public void createPostButtonOnAction(ActionEvent event) {
		 if(!titleField.getText().isEmpty() && !descriptionField.getText().isEmpty()) {
			 Post newPost = new Post(titleField.getText(), topicBox.getValue(), descriptionField.getText(), SignInController.currentUser, postImages);
			 SignInController.allPosts.add(newPost);
			 SignInController.currentUser.getUserPosts().add(newPost);
			 landingController.getHomeFeedNode().getFxmlController().displayNewPost(newPost);
			 landingController.showPane(landingController.getHomeFeedNode().getPaneId());
			 clearFields();
		 } else {
			 clearFields();
			 msgLabel.setText("Error. Please Try Again.");
			 msgLabel.setVisible(true);
		 }
	 }
	 
	 @FXML public void backBtnOnAction(ActionEvent event) {
		 landingController.showPane(landingController.getHomeFeedNode().getPaneId());
		 clearFields();
	 }
	 
	 private void clearFields() {
		 titleField.clear();
		 descriptionField.clear();
		 topicBox.getSelectionModel().clearSelection();
		 topicBox.setValue(null);
		 msgLabel.setVisible(false);
		 postImg1.setImage(null);
		 postImg2.setImage(null);
		 postImg3.setImage(null);
	 }
	 
	private void displayImage(byte[] chosenImageBytes) {
		if(postImg1.getImage() == null) {
			postImg1.setImage(new Image(new ByteArrayInputStream(chosenImageBytes)));
		} else if(postImg2.getImage() == null) {
			postImg2.setImage(new Image(new ByteArrayInputStream(chosenImageBytes)));
		} else if(postImg3.getImage() == null) {
			postImg3.setImage(new Image(new ByteArrayInputStream(chosenImageBytes)));
		} 
	}
	 
	 public void setLandingController(LandingController landingController) {
		 this.landingController = landingController;
	 }

}
