/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final_project;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author alexis cruz
 */
public class Final_Project extends Application {
     public void start(Stage primaryStage) {
		primaryStage.setTitle("Login / CUNYFIRST");
		
		GridPane paneLg = new GridPane();
		paneLg.setAlignment(Pos.CENTER);
		paneLg.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		paneLg.setHgap(5.5);
		paneLg.setVgap(5.5);
		
		Label ssnLabel = new Label("SSN: ");
		TextField ssnTF = new TextField();
		Button exitBTNButton = new Button("Exit");
		Button loginButton = new Button("Login");
		Button newLoginButton = new Button("Create User");
		
		exitBTNButton.setOnAction(actionEvent ->  {
			Platform.exit();
		});
		
		loginButton.setOnAction(actionEvent ->  {
			Boolean isFound = false;
			DBActivity db = new DBActivity();
			try {
				db.connectDB("password1  ");
				isFound = db.validateUser(ssnTF.getText());
				db.disConnectDB();
			} catch (SQLException e) {

				e.printStackTrace();
			}
						
			if (isFound) {
					//load new window
					Stage stage3 = new Stage();
					MyClassDashBoard cdb = new MyClassDashBoard();
					cdb.pwd = "password1";
					cdb.ssn = ssnTF.getText();
					try {
						cdb.start(stage3);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
                        else if (ssnTF.getText().trim().isEmpty()){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error: Field Empty");
					alert.setHeaderText("Error: Field Empty");
					alert.setContentText("You left the field empty");
					alert.showAndWait();
                        }
			else
				{
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Error Dialog");
					alert.setContentText("User not found");
					alert.showAndWait();
				}
		});
		
		newLoginButton.setOnAction(actionEvent ->  {
			newUserWnd();
		});
		
		paneLg.add(ssnLabel, 0, 0);
		paneLg.add(ssnTF, 1, 0);
		paneLg.add(exitBTNButton, 1, 4);
		paneLg.add(loginButton, 1, 2);
		paneLg.add(newLoginButton, 1, 3);
		
		Scene scene1 = new Scene(paneLg, 250, 200);
		primaryStage.setScene(scene1);
		primaryStage.show();
	}

public static void main(String[] args) {
	Application.launch(args);
	}

public static void newUserWnd() {
	GridPane paneNu = new GridPane();
	paneNu.setAlignment(Pos.CENTER);
	
	Label lbSSN = new Label("SSN:       ");
	TextField tfSSN = new TextField();
	Label lbF = new Label("First: ");
	TextField tfF = new TextField();
	Label lbM = new Label("Middle: ");
	TextField tfM = new TextField();
	Label lbL = new Label("Last: ");
	TextField tfL = new TextField();
	Button btExit = new Button("exit");
	Button btAdd = new Button("Add");
	
	paneNu.add(lbSSN, 0, 0);
	paneNu.add(tfSSN, 1, 0);
	paneNu.add(lbF, 0, 1);
	paneNu.add(tfF, 1, 1);
	paneNu.add(lbM, 0, 2);
	paneNu.add(tfM, 1, 2);
	paneNu.add(lbL, 0, 3);
	paneNu.add(tfL, 1, 3);
	paneNu.add(btExit, 0, 5);
	paneNu.add(btAdd, 1, 5);
	
	Scene secondScene = new Scene(paneNu, 250, 300);
	Stage secondStage = new Stage();
    secondStage.setTitle("New User");
    secondStage.setScene(secondScene);
    
	btExit.setOnAction(actionEvent ->  {
		secondStage.close();
	});
	
	btAdd.setOnAction(actionEvent ->  {
		DBActivity db = new DBActivity();
		String apwd = "password1";
		try {
			db.connectDB(apwd);
			db.addUser(tfSSN.getText(), tfF.getText(), tfM.getText(), tfL.getText());
			db.disConnectDB();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
			});
    secondStage.show();
	
}
}
