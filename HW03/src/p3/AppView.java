package p3;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AppView {
	private VBox pane;
	
	public AppView() {
		ArrayBlockingQueue<Student> waitList = new ArrayBlockingQueue<Student>(7); 
		
		Text title = new Text("Student Registration (First Come First Serve)");
		title.setFill(Paint.valueOf("#ffffff"));
		title.setFont(Font.font("Baskerville Old Face",FontWeight.BOLD, 60));
		
		TextField nameField = new TextField();
		nameField.setPrefSize(200, 40);
		nameField.setPromptText("NAME");
		
		TextField numberField = new TextField();
		numberField.setPrefSize(200, 40);
		numberField.setPromptText("PHONE NUMBER");
		
		HBox inputBox = new HBox(30);
		inputBox.setAlignment(Pos.CENTER);
		inputBox.getChildren().addAll(nameField, numberField);
		
		Button addBtn = new Button("Register Student!");
		addBtn.setPrefSize(300, 60);
		addBtn.setFont(Font.font("Calibri",FontWeight.BOLD, 30));
		addBtn.setTextFill(Color.INDIGO);
		
		Button viewAcceptedBtn = new Button("View Accpeted Students");
		viewAcceptedBtn.setPrefSize(300, 60);
		viewAcceptedBtn.setFont(Font.font("Calibri",FontWeight.BOLD, 25));
		viewAcceptedBtn.setTextFill(Color.HOTPINK);
		
		HBox btnBox = new HBox(50);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().addAll(addBtn, viewAcceptedBtn);
		
		Label lbl1 = new Label("Accepted Students:");
		lbl1.setMaxSize(400, 80);
		lbl1.setFont(Font.font("Arial",FontWeight.BOLD, 25));
		lbl1.setTextFill(Color.WHITE);
		
		Label lbl2 = new Label("WaitListed Students:");
		lbl2.setMaxSize(400, 80);
		lbl2.setFont(Font.font("Arial",FontWeight.BOLD, 25));
		lbl2.setTextFill(Color.WHITE);
		
		HBox lblBox = new HBox(200);
		lblBox.setAlignment(Pos.CENTER);
		lblBox.getChildren().addAll(lbl1, lbl2);
		
		ListView<Student> listView1 = new ListView<>();
		listView1.setMinSize(400, 300);
		
		ListView<Student> listView2 = new ListView<>();
		listView2.setMinSize(400, 300);
		
		HBox txtAreaBox = new HBox(30);
		txtAreaBox.setAlignment(Pos.CENTER);
		txtAreaBox.getChildren().addAll(listView1, listView2);
		
		addBtn.setOnAction(e -> {
			waitList.add(new Student(nameField.getText(), numberField.getText()));
			nameField.clear();
			numberField.clear();
		});
		
		viewAcceptedBtn.setOnAction(e -> {
			for(int i = 0 ; i < 5; i++) {
				if(waitList.isEmpty()) {
					break;
				}
				listView1.getItems().add(waitList.remove()); //Adds First 5 students to "Accepted List" ListView
			}
			while(!waitList.isEmpty()) {
				listView2.getItems().add(waitList.remove()); //Adds rest of students to "Wait List" ListView
			}
		});

		pane = new VBox(30);
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(30));
		pane.getChildren().addAll(title, inputBox, btnBox, lblBox, txtAreaBox);
		pane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #1d67a3, #232526 )");	
	}
	
	public VBox getPane() {
		return pane;
	}
}
