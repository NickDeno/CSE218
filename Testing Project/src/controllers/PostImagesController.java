package controllers;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;

import model.FXImage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PostImagesController {
	 @FXML private Button closeBtn;
	 @FXML private ImageView currentImg;
	 @FXML private Button nextBtn;
	 @FXML private Button previousBtn;
	 
	 private LandingController landingController;
	 private LinkedList<FXImage> postImages;
	 private int imgIdx = 0;
	 
	 public void initialize() {
		 Platform.runLater(() -> {
			 currentImg.setImage(new Image(new ByteArrayInputStream(postImages.get(imgIdx).returnBytes())));
			 if(postImages.size() == 1) {
				 previousBtn.setVisible(false);
				 nextBtn.setVisible(false);
			 } else {
				 previousBtn.setVisible(false);
			 }
			 
			 ((Stage)nextBtn.getScene().getWindow()).setOnCloseRequest(e -> {
	    			landingController.getPane().setEffect(null);
	    		});
		 });
	 }
	 
	 @FXML public void closeBtnOnAction(ActionEvent event) {
		 landingController.getPane().setEffect(null);
		 ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
	 }

	 @FXML public void nextBtnOnAction(ActionEvent event) {
		 imgIdx++;
		 if(imgIdx == postImages.size()) {
			 imgIdx--;
		 } else {
			 previousBtn.setVisible(true);
			 currentImg.setImage(new Image(new ByteArrayInputStream(postImages.get(imgIdx).returnBytes())));
			 if(imgIdx + 1 == postImages.size()) {
				 nextBtn.setVisible(false);
			 }
		 }
	 }

	 @FXML public void previousBtnOnAction(ActionEvent event) {
		 imgIdx--;
		 if(imgIdx < 0) {
			 imgIdx = 0; 
		 } else {
			 nextBtn.setVisible(true);
			 currentImg.setImage(new Image(new ByteArrayInputStream(postImages.get(imgIdx).returnBytes())));
			 if(imgIdx - 1 < 0) {
				 previousBtn.setVisible(false);
			 }
		 }
	 }
	 
	 public void setPostImages(LinkedList<FXImage> postImages) {
		 this.postImages = postImages;
	 }
	 
	 public void setLandingController(LandingController landingController) {
		 this.landingController = landingController;
	 }
	 
	 
}
