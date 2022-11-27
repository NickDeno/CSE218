package frontend.fxmlsControllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.LinkedList;
import java.util.UUID;

import backend.FXImage;
import backend.Post;
import backend.PostCenter;
import backend.UserCenter;
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
import javafx.scene.shape.Line;
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
	 @FXML private TextField newTopicField;
	 @FXML private Line newTopicLine;
	 @FXML private Label msgLabel;
	 
	 @FXML private ImageView postImg1;
	 @FXML private ImageView postImg2;
	 @FXML private ImageView postImg3;
	 
	 @FXML private Rectangle postImgBorder1;
	 @FXML private Rectangle postImgBorder2;
	 @FXML private Rectangle postImgBorder3;
	 
	 private LandingController landingController;
	 private LinkedList<FXImage> postImages = new LinkedList<FXImage>();
	 private final String[] defaultTopics = {"Computer Science", "School", "Gaming", "Gym", "Sports", "Other"};
	 
	 public CreatePostController() {}
	 
	 public void initialize() {
		topicBox.getItems().addAll(defaultTopics);
	 }
	 
	 @FXML public void topicBoxOnAction(ActionEvent event) {
		 //When user selects "Other" option, a text field will pop up for them to input their own topic
		 if(topicBox.getValue().equals("Other")) {
			 newTopicField.setVisible(true);
			 newTopicLine.setVisible(true);
		 } else {
			 newTopicField.setVisible(false);
			 newTopicLine.setVisible(false);
		 }
	 }
	 
	 @FXML public void browseButtonOnAction(ActionEvent event) {
		 System.out.println(postImages.size());	
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
				System.out.println(postImages.size());
				displayImage(chosenImageBytes);			
			}
	 }

	@FXML public void createPostButtonOnAction(ActionEvent event) {
		 if(!titleField.getText().isEmpty() && !descriptionField.getText().isEmpty()) {
			 //If user does not select topic or create their own topic, topic will be set to "Misc."
			 String newPostTopic;
			 if(topicBox.getValue() == null) newPostTopic = "Misc.";
			 else if(topicBox.getValue().equals("Other") && newTopicField.getText().isEmpty()) newPostTopic = "Misc.";			 
			 else newPostTopic = topicBox.getValue(); 
			 
			 Post newPost = new Post(titleField.getText(), newPostTopic, descriptionField.getText(), new LinkedList<FXImage>(postImages), UserCenter.getInstance().getCurrentUser(), UUID.randomUUID()); 
			 PostCenter.getInstance().addPost(newPost);
			 UserCenter.getInstance().getUser(UserCenter.getInstance().getCurrentUser().getUsername()).getUserPosts().put(newPost.getUuid(), newPost);		 
			 landingController.homeFeedNode.getFxmlController().displayNewPost(newPost);
			 landingController.profileNode.getFxmlController().displayNewPost(newPost);
			 landingController.showPane(landingController.homeFeedNode.getPaneId());
			 resetFields();
		 } else {
			 resetFields();
			 msgLabel.setText("Error. Please Try Again.");
			 msgLabel.setVisible(true);
		 }
	 }
	 
	 @FXML public void backBtnOnAction(ActionEvent event) {
		 landingController.showPane(landingController.homeFeedNode.getPaneId());
		 resetFields();
	 }
	 
	 private void resetFields() {
		 titleField.clear();
		 descriptionField.clear();
//		 topicBox.setValue(null);
		 msgLabel.setVisible(false);
		 postImg1.setImage(null);
		 postImg2.setImage(null);
		 postImg3.setImage(null);
		 postImages.clear();
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
