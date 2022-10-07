package p1_stack_api;

import java.util.Stack;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		final int ROW_SIZE = 5;
		final int COL_SIZE = 5;
		Stack<Button> myStack = new Stack<>();

		VBox root = new VBox(30);
		root.setAlignment(Pos.CENTER);

		Label messageLbl = new Label();
		HBox lblBox = new HBox();
		lblBox.setAlignment(Pos.CENTER);
		lblBox.getChildren().add(messageLbl);

		Button undoBtn = new Button("UNDO");

		undoBtn.setOnAction(e -> {
			if (myStack.isEmpty()) {
				messageLbl.setText("The stack is empty!");
			} else {
				Button b = myStack.pop();
				b.setStyle("-fx-background-color: #000");
				messageLbl.setText("");
			}
		});

		HBox undoBox = new HBox();
		undoBox.setAlignment(Pos.CENTER);
		undoBox.getChildren().add(undoBtn);

		GridPane gridPane = new GridPane();

		Button[][] a = new Button[ROW_SIZE][COL_SIZE];

		for (int i = 0; i < ROW_SIZE; i++) {
			for (int j = 0; j < COL_SIZE; j++) {
				Button b = new Button();
				b.setPrefSize(100, 100);
				b.setStyle("-fx-background-color: #000");
				a[i][j] = b;
				gridPane.add(b, j, i);
				b.setOnAction(e -> {
					b.setStyle("-fx-background-color: #f00");
					myStack.push(b);
					messageLbl.setText("");
				});
			}
		}

		root.getChildren().addAll(undoBox, gridPane, lblBox);

		Scene scene = new Scene(root, 500, 650);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
