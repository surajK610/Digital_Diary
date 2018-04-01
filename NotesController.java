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

public class NotesController{

	@FXML
	Button back;
	@FXML
	Button edit;
	@FXML
	Button remove;
	@FXML
	Button save;
	@FXML
	Button newNote;
	@FXML
	TableView<Note> table;
	@FXML
	TableColumn<Note, String> notesCol = new TableColumn<Note, String>();
	
	@FXML
	TextArea noteText;
	@FXML
	Button backNote;
	
	static private boolean list, n;
	static private int index;
	
	static ArrayList <Note> notes= new ArrayList<Note>();

    public void initialize() // initialization methods for each window
	{
    	System.out.println(list + "  " + n);
    	if (list && n)
    	{
    		if (notes.size() > 0)
    			noteText.setText(notes.get(index).toString());
    	}
    	else if (!list)
    	{
    		notesCol.setCellValueFactory(new PropertyValueFactory<Note,String>("text"));
    		table.setItems(getNotesListObservable());

    	}

	}    
    
	public static String getNotes()
	{
		String noteList = ""; 
		for (Note note: notes)
			noteList += note.toString() + "\n\n";
		return noteList;
	}


	public void paneSet(ActionEvent event) throws java.io.IOException
	{
		Stage stage = null; 
		Parent root = null;
		if(event.getSource()==newNote){
			list = true;
			//get reference to the button's stage         
			stage =(Stage) newNote.getScene().getWindow();
			//load up OTHER FXML document
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/SingleNote.fxml"));
		}
		if(event.getSource()==backNote){
			//get reference to the button's stage         
			stage =(Stage) backNote.getScene().getWindow();
			//load up OTHER FXML document
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/Notes.fxml"));
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
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/SingleNote.fxml"));
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
        	Note NoteSel = table.getSelectionModel().getSelectedItem();
        	
            index = notes.indexOf(NoteSel);
            System.out.println(notes + "" + index);
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
        	Note NoteSel = table.getSelectionModel().getSelectedItem();
            int index = notes.indexOf(NoteSel);
            notes.remove(index);
    		table.setItems(getNotesListObservable());

        }
    }
   
    @FXML
    public void add(ActionEvent e) throws java.io.IOException
    {
    	list = true;
    	n = false;
		paneSet(e);
		notesCol.setCellValueFactory(new PropertyValueFactory<Note,String>("text"));
		table.setItems(getNotesListObservable());

    }
    
    @FXML
    public void backNote(ActionEvent e) throws java.io.IOException
    {
    	list = false;
    	if (!n)
    		notes.add(new Note(noteText.getText()));
    	else
    		notes.set(index, new Note(noteText.getText()));
    	n = false;
    	paneSet(e);
    }
    
    @FXML
    public void back(ActionEvent e) throws java.io.IOException
    {
    	paneSet(e);
    }
    

    
 // method to get notes list into an observable list
 	public ObservableList<Note> getNotesListObservable(){

 		ObservableList<Note> noteObservableList = FXCollections.observableArrayList();
 	
 		for(Note note: notes)
 			noteObservableList.add(note);
 		
 		return noteObservableList;
 	}
 	

    

}
