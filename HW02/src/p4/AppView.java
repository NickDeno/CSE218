package p4;

import java.util.Stack;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AppView {
	private VBox appPane;
	private Stack<Button> undoStack;
	private final String RED_BTN_COLOR = "-fx-background-color: #ff0000; ";
	private	final String BLACK_BTN_COLOR = "-fx-background-color: #000000; ";
	private final int COL_SIZE = 3;
	private final int ROW_SIZE = 3;
	
	public AppView() {
		undoStack = new Stack<>();
		
		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setMaxSize(600, 600);
		
		Button[][] btnArr = new Button[COL_SIZE][ROW_SIZE]; // Adds buttons to grid
		for(int i = 0; i < ROW_SIZE; i++) {
			for(int j = 0; j < COL_SIZE; j++) {
				Button btn = new Button();
				btnArr[i][j] = btn;
				btn.setStyle(BLACK_BTN_COLOR);
				btn.setTextFill(Color.WHITE);
				btn.setMinSize(200,200);
				grid.add(btn, j, i);
				btn.setOnAction(e -> {
					if(btn.getStyle().equals(BLACK_BTN_COLOR)) {
						btn.setStyle(RED_BTN_COLOR);
						undoStack.push(btn);
					}
				});
			}
		}
		
		Button undo = new Button("Undo!");
		undo.setMinSize(200, 50);
		undo.setFont(Font.font("Arial",FontWeight.BOLD, 30));
		undo.setOnAction(e -> {
			if(!undoStack.isEmpty()) {
				undoStack.pop().setStyle(BLACK_BTN_COLOR);
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Sorry. All buttons are black.");
				alert.showAndWait();
			}
		});
		
		appPane = new VBox(15);
		appPane.getChildren().addAll(undo, grid);
		appPane.setAlignment(Pos.CENTER);
		appPane.setPadding(new Insets(25));
		appPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #555352  , #E0E0E0 )");
	}
	
	public VBox getPane() {
		return appPane;
	}
}
