/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_project;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author alexis cruz
 */
public class MyClassDashBoard extends Application{
     public String ssn;
	public String pwd;
	public DBActivity db;

	private TableView<stClass> table = new TableView<stClass>();
	private ObservableList<stClass> data = FXCollections.observableArrayList();
	
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) throws SQLException {
        Scene scene = new Scene(new Group());
        stage.setTitle("My Class Dashboard / CUNYFIRST");
        stage.setWidth(600);
        stage.setHeight(650);
        // class number, class name, and grade
        
        table.setEditable(true);
        TableColumn<stClass, String> classNbrCol = new TableColumn<stClass, String>("class number");
        TableColumn<stClass, String> classNameCol = new TableColumn<stClass, String>("class name");
        TableColumn<stClass, String> gradeCol = new TableColumn<stClass, String>("grade");
        
        classNbrCol.setCellValueFactory(new PropertyValueFactory<stClass, String>("classNbr"));
        classNameCol.setCellValueFactory(new PropertyValueFactory<stClass, String>("className"));
        gradeCol.setCellValueFactory(new PropertyValueFactory<stClass, String>("gradeCol"));
        table.resize(700, 500);
        
        gradeCol.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
        
        db = new DBActivity();
        db.connectDB(pwd);
        data = db.getMyClasses(ssn);
        db.disConnectDB();
        table.getColumns().addAll(classNbrCol, classNameCol, gradeCol);

        table.setItems(data);
        Button btExit = new Button("Exit");
    	btExit.setOnAction(actionEvent ->  {
    		stage.close();
    	});
    	Button btDrop = new Button("Drop Class");
    	btDrop.setOnAction(actionEvent ->  {
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    		alert.setTitle("Confirmation Dialog");
    		alert.setHeaderText("Are you sure you want to drop class?");
    		alert.setContentText("Are you ok with this?");
    		alert.showAndWait();
    		if (alert.getResult() == ButtonType.OK) {
        		try {
        			// get class
        			System.out.println("delete class");
        			stClass delClass = table.getSelectionModel().getSelectedItem();
        			
    				db.connectDB(pwd);
    				db.dropClass(ssn, delClass.getClassNbr());
    				db.disConnectDB();
                                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                                alert2.setTitle("Confirmation Dialog");
                                alert2.setHeaderText("Class Successfully Dropped");
                                alert2.setContentText("The Class(es) selected have been Dropped, Exit and reEnter to see changes!");
                                alert2.showAndWait();
                                
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	});
    	Button btReg = new Button("Register");
    	btReg.setOnAction(actionEvent ->  {
    		ArrayList<String> tmpArr = new ArrayList<String>( );
    		ListView<String> list = new ListView<String>();
    		try {
    			db.connectDB(pwd);
    			tmpArr = db.getCourses(ssn);
				db.disConnectDB();
    		} catch (SQLException e) {
				e.printStackTrace();
			}
    		ObservableList<String> items =FXCollections.observableArrayList (tmpArr);
    		list.setItems(items);
    		
    		BorderPane  root2 = new BorderPane();
    		root2.setCenter(list);
    		Stage secondStage = new Stage();
    		Button btnExit = new Button("Exit");
    		btnExit.setOnAction(actionEvent2 ->  {
    			secondStage.close();
    		});
    		root2.setLeft(btnExit);
    		
    		Button btnReg = new Button("Register");
    		root2.setRight(btnReg);
    		btnReg.setOnAction(actionEvent2 ->  {
    			try {
    				String row = list.getSelectionModel().getSelectedItem();
    				String [] arrOfStr = row.split(",");
    				String currentcourseId = arrOfStr[0];
    				db.connectDB(pwd);
    				db.addClass(ssn, currentcourseId);
    				db.disConnectDB();
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation Dialog");
                                alert.setHeaderText("Class Successfully Added");
                                alert.setContentText("The Class(es) selected have been added! Exit and reEnter to see tha changes");
                                alert.showAndWait();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		});
    		
    	    secondStage.setTitle("New Classes");
    	    Scene secondScene = new Scene(root2, 250, 350);
    	    secondStage.setScene(secondScene);
    	    secondStage.show();
    	});
    	
    	
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table,btDrop, btReg,btExit);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();
    }

    /* @Override*/
    public static class stClass {
        private final SimpleStringProperty classNbr;
        private final SimpleStringProperty className;
        private final SimpleStringProperty gradeCol;
        
        public stClass(String cNbr, String cName, String cGrade) {
            this.classNbr = new SimpleStringProperty(cNbr);
            this.className = new SimpleStringProperty(cName);
            this.gradeCol = new SimpleStringProperty(cGrade);
        }
        
        public String getClassNbr() {
        	return classNbr.get();
        }
        public String getClassName() {
        	return className.get();
        }
        public String getGradeCol() {
        	return gradeCol.get();
        }
        public void setClassNbr(String cNbr) {
        	classNbr.set(cNbr);
        }
        public void setClassName(String cName) {
        	className.set(cName);
        }
        public void setGradeCol(String cGrade) {
        	gradeCol.set(cGrade);
        }        
    }
}
