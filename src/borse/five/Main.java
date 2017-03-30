/*
Borse, Akanksha
APCS-A
Period 5
PSET5-0
Spring Project
 */

package borse.five;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import borse.five.model.Event;
import borse.five.model.EventListWrapper;
import borse.five.Main;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import java.io.File;
import java.util.prefs.Preferences;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import borse.five.view.RootLayoutController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application {

	/* placing the events in an observable list */  
	private ObservableList<Event> weddingList = FXCollections.observableArrayList();

	/* private fields of a stage that will be the primary stage, 
	and the root layout of the main window */
	private Stage primaryStage;
	private BorderPane rootLayout;

	/* default Constructor that we are using just to put in some initial data to view */  
	public Main() {
		weddingList.add(new Event("Bob & Sally", 1400,35));
		weddingList.add(new Event("John & Mary", 1200,20));
		weddingList.add(new Event("Sue & Jeremy", 1550,45));	
	}
	@Override
	public void start(Stage primaryStage) {
		try {

			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Luxe Events");	/* setting the title of the window */

			/* sets the application icon */
			this.primaryStage.getIcons().add(new Image("file:resources/images/AppIcon.png"));

			/* call to method to set up the Root Layout */
			initRootLayout();


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void initRootLayout() {

		try {
			/* main initialization routines for layout and app commencement */

			/* creates an FXML Loader object */
			FXMLLoader loader = new FXMLLoader();

			/* pointing the loader at the FXML file to load */
			loader.setLocation(Main.class.getResource("/borse/five/view/RootLayoutController.FXML"));

			/* sets the rootlayout field to the BorderPane FXM file injected via @FXML */
			rootLayout = (BorderPane) loader.load();


			/* sets a Scene object to this rootLayout */
			Scene scene = new Scene(rootLayout);

			/* giving the controller access to the main */
			RootLayoutController controller = loader.getController();
			controller.setMain(this);

			/* sets the primaryScene Scene object to scene */
			primaryStage.setScene(scene);

			/* shows the primaryStage object */
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* provides the memory address (handle) of the primaryStage */	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/* provides the handle to the weddingList object */	
	public ObservableList<Event> getWeddingList(){
		return weddingList;
	}

	/* method for saving data returns file preferences/file last opened */
	public File getWeddingListFilePath(){
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	/* method that sets the wedding list file path */
	public void setWeddingListFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(Main.class);
		if (file != null) {
			prefs.put("filePath",  file.getPath());			
			/* update stage title */
			primaryStage.setTitle("Luxe Events - " +  file.getName());			
		} else {
			prefs.remove("filePath");			
			/* update stage title */
			primaryStage.setTitle("Luxe Events");
		}				
	}

	/* reads in the wedding list */
	public void loadWeddingListFromFile(File file) {
		try{
			JAXBContext context = JAXBContext.newInstance(Event.class);
			Unmarshaller um = context.createUnmarshaller();

			/* reading XML from the unmarshaller */
			Event wrapper = (Event) um.unmarshal(file);

			weddingList.clear();
			weddingList.addAll(wrapper.getEvents());

			/* saving the file path to the registry */
			setWeddingListFilePath(file);
		} catch (Exception e) { /* catches any exception */
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Could NOT load data");
			alert.setContentText("Could NOT load data from file:\n" + file.getPath()+ ": " + e.toString());

			alert.showAndWait();
		}

	}



	/* writes the wedding list to disk */
	public void saveWeddingListToFile(File file) {
		try{
			JAXBContext context = JAXBContext.newInstance(Event.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			/* wrapping the person data */
			EventListWrapper wrapper = new EventListWrapper();
			wrapper.setEvents(weddingList);

			/* marshaling and saving the XML to file */
			m.marshal(wrapper, file);

			/* saves the file path to the registry */
			setWeddingListFilePath(file);


		} catch (Exception e) {  /* catches every exception */
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("SORRY! could not save data");
			alert.setContentText("SORRY could not save data to file:\n" + file.getPath());

			alert.showAndWait();
		}

	}

}
