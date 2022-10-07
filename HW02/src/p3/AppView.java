package p3;

import java.util.Stack;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AppView {
	private VBox appPane;
	
	public AppView() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText(null);
		alert.setContentText("Paste code into TextArea then press start button. If code is balanced, label will display true. If not, label will display false.");
		alert.showAndWait();
		
		Label title = new Label();
		title.setMinSize(300, 75);
		title.setFont(Font.font("Baskerville Old Face",FontWeight.BOLD, 40));
		title.setTextFill(Color.WHITE);
		title.setText("Press start to check if code is balanced.");
		title.setAlignment(Pos.CENTER);
		
		Button startBtn = new Button("Start");
		startBtn.setMinSize(200, 50);
		
		Label status = new Label();
		status.setMinSize(300, 75);
		status.setText("Output:");
		status.setFont(new Font("Arial", 30));
		status.setTextFill(Color.WHITE);
		status.setAlignment(Pos.CENTER);
		
		TextArea txtArea = new TextArea();
		txtArea.setMinSize(300, 400);
		
		appPane = new VBox(5);
		appPane.setAlignment(Pos.CENTER);
		appPane.getChildren().addAll(title, startBtn, status, txtArea);
		appPane.setPadding(new Insets(30));
		appPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #1d67a3, #232526 )");
		
		startBtn.setOnAction(e -> {
			if(checkIfBalanced(txtArea.getText())) {
				status.setText("TRUE");
			} else {
				status.setText("FALSE");
			}
			
		});
	}
	
	public boolean checkIfBalanced(String str) {
		str = str.replaceAll("[^(){}\\[\\]]", "");
		System.out.println(str);
		if(str.length() % 2 == 1) {
			return false;
		}
		
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if(ch == '(' ||ch == '{' || ch == '[' ) {
				stack.push(ch);
			} else if(ch == ')') {
				if(stack.isEmpty() || stack.pop() != '(') {
					return false;
				}
			} else if(ch == '}') {
				if(stack.isEmpty() ||stack.pop() != '{' ) {
					return false;
				}
			} else if(ch == ']') {
				if(stack.isEmpty() ||stack.pop() != '[') {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}
	
	public VBox getPane() {
		return appPane;
	}

}
