package application;
	
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Main extends Application {
	
	int gridPaneWidth = 9, gridPaneHeight = 9;
	@Override
	public void start(Stage stage) throws IOException {
		
		Pane pane = FXMLLoader.load(getClass().getClassLoader().getResource("application/Start.fxml"));
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("KoolAid");
				
		stage.show();
		MyBackgroudMethod thread = new MyBackgroudMethod();
        thread.setDaemon(true);
        thread.start();
			
	}
	 
	public static void main(String[] args) {
		launch(args);
	}
	

	  public static class MyBackgroudMethod extends Thread {

	        @Override
	        public void run() {
	            while (true) {
	            	Date now = new Date();
	        
	            	
					for (Event event: CalenderController.events)
					{
						System.out.println(event.getTime().toString() + "    " + now.toString() );
						System.out.println(event.getTime().toString().equalsIgnoreCase(now.toString()));
						if (event.getTime().toString().equals(now.toString()))
						{
							CalenderController.alert = true;
							CalenderController.alertE = event;
						}
							
					}
	                try {
	                    Thread.sleep(100);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        
	        public void AlertBox(String alertText)
	        {
	            //makes a new Alert with customizable text
	            Stage stage = new Stage();
	            Alert alert = new Alert(AlertType.INFORMATION, alertText);
	            alert.showAndWait();
	        }
	    }
}
	        

	 