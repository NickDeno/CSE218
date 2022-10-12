package p2_lyricsLinkedListGUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class GUIPageController {
	@FXML private Menu exit;
    @FXML private MenuItem choseFile;
    @FXML private MenuItem saveOutput;
    @FXML private TextField keywordTextField;
    @FXML private TextField numberTextField;
    @FXML private Button produceBtn;
    @FXML private Button saveBtn;
    @FXML private Button clearBtn;
    @FXML private TextArea textArea;
    @FXML private Label lyricsLabel;
    
    private static File selectedFile;
    private static LinkedList<ParentLink> lyricsList;
    
    @FXML public void choseFileOnAction(ActionEvent event) {
    	FileChooser fc= new FileChooser();
//    	fc.setInitialDirectory(new File("F:\\Users\\Nick DeNobrega\\Desktop\\CSE Workspaces\\CSE218 Workspace\\HW05\\data"));
    	fc.setInitialDirectory(new File("C:\\Users\\Nickd\\Desktop\\CSE Workspaces\\CSE218 Workspace\\HW05\\data"));
    	fc.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
    	selectedFile = fc.showOpenDialog(null);
    	if(selectedFile == null) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid File");
			alert.setHeaderText(null);
			alert.setContentText("Either chosen lyrics file was invalid, or no file was chosen. Please try again.");
			alert.showAndWait();
    	} else {
    		lyricsList = Utilities.lyricsToList(selectedFile);
    		lyricsLabel.setText(selectedFile.getName());
    		for(ParentLink p: lyricsList) {
    			System.out.println(p.toString());
    		}
    	}
    }
    
    @FXML public void produceOnAction(ActionEvent event) {
    	textArea.clear();
    	if(selectedFile == null) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("No Lyrics File Present");
			alert.setHeaderText(null);
			alert.setContentText("Please import a lyrics file before triyng to produce paragraph.");
			alert.showAndWait();
			return;
    	}
    	
    	if(keywordTextField.getText().isEmpty() || numberTextField.getText().isEmpty()) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Empty Input(s)");
			alert.setHeaderText(null);
			alert.setContentText("Please make sure a chosen keyword and the length of the paragraph was entered");
			alert.showAndWait();
    	}
    	if(Utilities.keyIsPresent(keywordTextField.getText(), lyricsList)) {
    		textArea.appendText(Utilities.generateParagraph(keywordTextField.getText(), Integer.parseInt(numberTextField.getText()), lyricsList));
    	} else {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Keyword Not Found");
			alert.setHeaderText(null);
			alert.setContentText("The chosen keyword is not present in selected lyrics file.");
			alert.showAndWait();
    	}
	}

    @FXML public void saveOutputOnAction(ActionEvent event) {
    	try {
//    		PrintWriter pw = new PrintWriter("F:\\Users\\Nick DeNobrega\\Desktop\\CSE Workspaces\\CSE218 Workspace\\HW05\\output\\output.txt");
    		PrintWriter pw = new PrintWriter("C:\\Users\\Nickd\\Desktop\\CSE Workspaces\\CSE218 Workspace\\HW05\\output\\output.txt");
			pw.println(textArea.getText());
			pw.close();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sucessfully Saved!");
			alert.setHeaderText(null);
			alert.setContentText("The produced paragraph was saved in output\\output.txt");
			alert.showAndWait();
			keywordTextField.clear();
			numberTextField.clear();
			textArea.clear();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    @FXML public void clearOnAction(ActionEvent event) {
    	keywordTextField.clear();
    	numberTextField.clear();
    	textArea.clear();
    }
    
    @FXML public void exitOnAction(ActionEvent event) {
    	Stage stage = (Stage)clearBtn.getScene().getWindow();
    	stage.close();
    }
}
