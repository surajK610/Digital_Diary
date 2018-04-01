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

public class ContactsController{

	@FXML
	Button quit;
	@FXML
	Button edit;
	@FXML
	Button remove;
	@FXML
	Button view;
	@FXML
	Button enterLast;
	@FXML
	Button enterFirst;
	@FXML
	Button addContact;
	@FXML
	Button back;
	@FXML
	Button print;
	@FXML
	TextField lastName, firstName, phoneNum, address, emAddress;
	@FXML
	TableView<Contact> table;
	@FXML
	TableColumn<Contact, String> lastNameCol = new TableColumn<Contact, String>(), firstNameCol = new TableColumn<Contact, String>(), phoneNumCol = new TableColumn<Contact, String>(), emAddressCol = new TableColumn<Contact, String>(), addressCol = new TableColumn<Contact, String>();

	
	//Person.fxml class
	@FXML
	TextField name;
	@FXML
	TextField personPhoneNum, personAdd, personEmail;
	@FXML
	Button backPerson;
	
	static private boolean person;
	static private int index;
	
	static ArrayList <Contact> contacts= new ArrayList<Contact>();

    public void initialize() // initialization methods for each window
	{
    	if (person)
    	{
    		name.setText(contacts.get(index).getLastName() + ", " + contacts.get(index).getFirstName());
    		personPhoneNum.setText(contacts.get(index).getPhoneNumber());
    		personAdd.setText(contacts.get(index).getAddress());
    		personEmail.setText(contacts.get(index).getEmailAddress());	
    	}
    	else
    	{
    		firstNameCol.setCellValueFactory(new PropertyValueFactory<Contact,String>("firstName"));
        	lastNameCol.setCellValueFactory(new PropertyValueFactory<Contact,String>("lastName"));
        	phoneNumCol.setCellValueFactory(new PropertyValueFactory<Contact,String>("phoneNumber"));
        	emAddressCol.setCellValueFactory(new PropertyValueFactory<Contact,String>("emailAddress"));
        	addressCol.setCellValueFactory(new PropertyValueFactory<Contact,String>("address"));
        	
    		table.setItems(getContactListObservable());
    	}

	}    
    
	public static String getContacts()
	{
		String contactList = ""; 
		for (Contact contact: contacts)
			contactList += contact.toString() + "\n\n";
		return contactList;
	}


	public void paneSet(ActionEvent event) throws java.io.IOException
	{
		Stage stage = null; 
		Parent root = null;
		if(event.getSource()==backPerson){
			person = false;
			//get reference to the button's stage         
			stage =(Stage) backPerson.getScene().getWindow();
			//load up OTHER FXML document
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/Contacts.fxml"));
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
			root = FXMLLoader.load(getClass().getClassLoader().getResource("application/Person.fxml"));
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
        	Contact ContactSel = table.getSelectionModel().getSelectedItem();
            index = contacts.indexOf(ContactSel);
            person = true;
            paneSet(e);
        }
    }
    
    @FXML
    public void backPerson(ActionEvent e) throws java.io.IOException
    {
    	Contact con = contacts.get(index);
    	con.setFirstName(name.getText().substring(name.getText().indexOf(", ") + 2));
    	con.setLastName(name.getText().substring(0, name.getText().indexOf(", ")));
    	con.setAddress(personAdd.getText());
    	con.setPhoneNumber(personPhoneNum.getText());
    	con.setEmailAddress(personEmail.getText());
    	paneSet(e);

    }
    
    @FXML
    public void remove(ActionEvent e) throws java.io.IOException
    {
        //if something is selected opens pop up to remove selected item
        if (table.getSelectionModel().getSelectedIndex() >= 0)
        {
        	Contact ContactSel = table.getSelectionModel().getSelectedItem();
            int index = contacts.indexOf(ContactSel);
            contacts.remove(index);
    		table.setItems(getContactListObservable());

        }
    }
   
    @FXML
    public void add(ActionEvent e) throws java.io.IOException
    {
    	String lastName, firstName, phoneNum, address, emAddress;
    	lastName = this.lastName.getText();
    	firstName = this.firstName.getText();
    	phoneNum = this.phoneNum.getText();
    	address = this.address.getText();
    	emAddress = this.emAddress.getText();
    	
    	Contact newCon = new Contact(lastName, firstName, phoneNum, address, emAddress);
    	contacts.add(newCon);
    	
    	System.out.println(newCon);
    	
    	firstNameCol.setCellValueFactory(new PropertyValueFactory<Contact,String>("firstName"));
    	lastNameCol.setCellValueFactory(new PropertyValueFactory<Contact,String>("lastName"));
    	phoneNumCol.setCellValueFactory(new PropertyValueFactory<Contact,String>("phoneNumber"));
    	emAddressCol.setCellValueFactory(new PropertyValueFactory<Contact,String>("emailAddress"));
    	addressCol.setCellValueFactory(new PropertyValueFactory<Contact,String>("address"));
    	
		table.setItems(getContactListObservable());
		//paneSet(e);
		this.lastName.setText("");
		this.firstName.setText("");
		this.phoneNum.setText("");
		this.address.setText("");
		this.emAddress.setText("");
		
    }
    

    
 // method to get contact list into an observable list
 	public ObservableList<Contact> getContactListObservable(){

 		ObservableList<Contact> contactObservableList = FXCollections.observableArrayList();

 		for(Contact contact: contacts)
 			contactObservableList.add(contact);
 		return contactObservableList;
 	}
 	
 	public void back(ActionEvent e) throws java.io.IOException
 	{
 		paneSet(e);
 	}

    


}
