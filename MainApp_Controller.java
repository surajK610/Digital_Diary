package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainApp_Controller {

	@FXML
	Button contactsButton;
	@FXML
	Button calenderButton;
	@FXML
	Button notesButton;
	
	@FXML
	Label time;
	
	@FXML
	public void initilize()
	{
		Task dynamicTimeTask = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				while (true) {
					updateMessage(sdf.format(new Date()));
					try {
						Thread.sleep(100);
					} catch (InterruptedException ex) {
						break;
					}
				}
				return null;
			}
		};
		time.textProperty().bind(dynamicTimeTask.messageProperty());
		Thread t2 = new Thread(dynamicTimeTask);
		t2.setName("Tesk Time Updater");
		t2.setDaemon(true);
		t2.start();
	}
	
	@FXML
	public void paneSet(ActionEvent event) throws java.io.IOException
	{
		Stage stage = null; 
		Parent root = null;
		
		if(event.getSource()==contactsButton){
			Button source = (Button) event.getSource();
			//get reference to the button's stage         
			stage =(Stage) source.getScene().getWindow();
			//load up OTHER FXML document
			
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/Contacts.fxml"));
		}

		if(event.getSource()==notesButton){
			Button source = (Button) event.getSource();
			//get reference to the button's stage         
			stage =(Stage) source.getScene().getWindow();
			//load up OTHER FXML document
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/Notes.fxml"));

		}

		if(event.getSource()==calenderButton){
			Button source = (Button) event.getSource();
			//get reference to the button's stage         
			stage =(Stage) source.getScene().getWindow();
			//load up OTHER FXML document
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/Calender.fxml"));
		}
		
		//create a new scene with root and set the stage

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	
}
