package p4;
import java.util.PriorityQueue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdmissionPageController {
	// Admission Page
	@FXML private ListView<Student> listView1;
	@FXML private ListView<Student> listView2;
	@FXML private Button admitStudentBtn;
    @FXML private Button newStudentBtn;
    
    // Add New Student Page
    @FXML private Button addBtn;
    @FXML private Button backBtn;
    @FXML private TextField gpaField;
    @FXML private TextField nameField;
    
    PriorityQueue<Student> admissionQueue = new PriorityQueue<Student>(10,(s1, s2) -> {
    	return Double.compare(s2.getGpa(), s1.getGpa());	
	});;
 
    public void newStudentBtnOnAction(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/p4/fxmls/NewStudentPage.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Add New Student");
			stage.setResizable(false);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    public void addBtnOnAction(ActionEvent event) {
    	Student s = new Student(nameField.getText(), Double.parseDouble(gpaField.getText()));
    	admissionQueue.add(s);
    	listView1.getItems().add(s);
    	
    }
    
    public void backBtnOnAction(ActionEvent event) {
    	Stage stage = (Stage) backBtn.getScene().getWindow();
    	stage.close();
    }
}

