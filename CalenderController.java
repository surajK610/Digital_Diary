package application;

/**
 * Write a description of class Perform_FunctionsGUI here.
 * 
 * @author Suraj Anand
 * @version 1
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.scene.layout.Pane;
import javafx.stage.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;

import application.Main.MyBackgroudMethod;

public class CalenderController{

	@FXML
	Button back;
	@FXML
	Button edit;
	@FXML
	Button remove;
	@FXML
	Button save;
	@FXML
	Button newEvent;
	@FXML
	TableView<Event> table;
	@FXML
	TableColumn<Event, String> eventCol = new TableColumn<Event, String>();
	@FXML
	TableColumn<Event, Date> timeCol = new TableColumn<Event, Date>();

	@FXML
	TextField name;
	@FXML
	TextArea description;
	@FXML
	TextField day, month, year, hour, minute;
	@FXML
	CheckBox pm;
	@FXML
	Button backEvent;

	static boolean alert = false;
	static Event alertE;
	
	static private boolean list, n;
	static private int index;

	static ArrayList <Event> events= new ArrayList<Event>();

	@SuppressWarnings("deprecation")
	public void initialize() // initialization methods for each window
	{
		if (alert)
		{
			AlertBox(alertE.getEventName());
			alert = false;
		}
		MyBackgroudMethod thread = new MyBackgroudMethod();
        thread.setDaemon(true);
        thread.start();
		if (list && n)
		{
			if (events.size() > 0)
			{
				int sub = 0;
				if (events.get(index).getTime().getHours() > 12)
				{
					sub = 12;
					pm.setSelected(true);
				}
				name.setText(events.get(index).getEventName());
				description.setText(events.get(index).getEvent());
				day.setText(Integer.toString(events.get(index).getTime().getDate()));
				hour.setText(Integer.toString(events.get(index).getTime().getHours()));
				month.setText(Integer.toString(events.get(index).getTime().getMonth()));
				year.setText(Integer.toString(events.get(index).getTime().getYear()));
				minute.setText(Integer.toString(events.get(index).getTime().getMinutes()));


			}
		}
		else if (!list)
		{
			eventCol.setCellValueFactory(new PropertyValueFactory<Event,String>("eventName"));
			timeCol.setCellValueFactory(new PropertyValueFactory<Event,Date>("time"));
			table.setItems(getEventsListObservable());

		}

	}    

	public static void AlertBox(String alertText)
    {
        //makes a new Alert with customizable text
        Stage stage = new Stage();
        Alert alert = new Alert(AlertType.INFORMATION, alertText);
        alert.showAndWait();
    }

	public static String getEvents()
	{
		String eventList = ""; 
		for (Event event: events)
			eventList += event.toString() + "\n\n";
		return eventList;
	}


	public void paneSet(ActionEvent event) throws java.io.IOException
	{
		if (alert)
		{
			AlertBox(alertE.getEventName());
		}
		Stage stage = null; 
		Parent root = null;
		
		if(event.getSource()==newEvent){
			//get reference to the button's stage         
			stage =(Stage) newEvent.getScene().getWindow();
			//load up OTHER FXML document
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/SingleEvent.fxml"));
		}
		if(event.getSource()==backEvent){
			//get reference to the button's stage         
			stage =(Stage) backEvent.getScene().getWindow();
			//load up OTHER FXML document
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/Calender.fxml"));
		}
		if(event.getSource()==back){
			//get reference to the button's stage         
			stage =(Stage) back.getScene().getWindow();
			//load up OTHER FXML document
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/Start.fxml"));
		}
		if(event.getSource()==edit){
			//get reference to the button's stage         
			stage =(Stage) edit.getScene().getWindow();
			//load up OTHER FXML document
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/SingleEvent.fxml"));
		}

		//create a new scene with root and set the stage
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void edit(ActionEvent e) throws java.io.IOException
	{
		//if something is selected opens pop up to edit selected item
		if (table.getSelectionModel().getSelectedIndex() >= 0)
		{
			Event eventsel = table.getSelectionModel().getSelectedItem();

			index = events.indexOf(eventsel);
			System.out.println(events + "" + index);
			list = true;
			n = true;
			paneSet(e);
		}
	}


	@FXML
	public void remove(ActionEvent e) throws java.io.IOException
	{
		//if something is selected opens pop up to remove selected item
		if (table.getSelectionModel().getSelectedIndex() >= 0)
		{
			Event eventsel = table.getSelectionModel().getSelectedItem();
			int index = events.indexOf(eventsel);
			events.remove(index);
			table.setItems(getEventsListObservable());

		}
	}

	@FXML
	public void add(ActionEvent e) throws java.io.IOException
	{
		list = true;
		n = false;
		paneSet(e);
		eventCol.setCellValueFactory(new PropertyValueFactory<Event,String>("eventName"));
		timeCol.setCellValueFactory(new PropertyValueFactory<Event,Date>("time"));
		table.setItems(getEventsListObservable());

	}

	@SuppressWarnings("deprecation")
	@FXML
	public void backEvent(ActionEvent e) throws java.io.IOException
	{
		System.out.println(new Date(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()), Integer.parseInt(hour.getText()), Integer.parseInt(minute.getText())));
		list = false;
		
		int add = 0;
		if (pm.isSelected())
			add = 12;
		if (!n)
			events.add(new Event(name.getText(), description.getText(), new Date(Integer.parseInt(year.getText()), Integer.parseInt(month.getText()), Integer.parseInt(day.getText()), Integer.parseInt(hour.getText()), Integer.parseInt(minute.getText()))));
		else
			events.set(index, new Event(name.getText(), description.getText(), new Date((Integer.parseInt(year.getText())), (Integer.parseInt(month.getText())), Integer.parseInt(day.getText()), Integer.parseInt(hour.getText()), Integer.parseInt(minute.getText()))));
		n = false;
		paneSet(e);
	}

	@FXML
	public void back(ActionEvent e) throws java.io.IOException
	{
		paneSet(e);
	}



	// method to get events list into an observable list
	public ObservableList<Event> getEventsListObservable(){

		ObservableList<Event> eventObservableList = FXCollections.observableArrayList();

		for(Event event: events)
			eventObservableList.add(event);

		return eventObservableList;
	}
	
	 

}


