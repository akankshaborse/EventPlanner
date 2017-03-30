/*
Borse, Akanksha
APCS-A
Period 5
PSET5-0
Spring Project
 */

package borse.five.view;

import java.io.File;
import java.time.LocalDate;
import borse.five.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

/* fields */
public class RootLayoutController {
	@FXML
	private ImageView imagePic;						/* field for picking wedding themes, lets user choose images */  					
	@FXML
	private ChoiceBox<String> cmbImagePicker;		/* field for picking wedding themes */ 
	@FXML
	private WebView webView;						/* field for search engines */
	@FXML
	private Button buttonOne;						/* field for search engine button */
	@FXML
	private Button buttonTwo;						/* field for search engine button */
	@FXML
	private Button buttonThree;						/* field for search engine button */
	@FXML
	private ToggleButton toggleButton;				/* field for calendar button */
	@FXML
	private DatePicker datePicker;					/* field for date picker in calendar */
	@FXML
	private TextField textDateSelected;				/* field for date selected */
	@FXML
	private Label labelDateSelected;				/* field for label of date selected */



	/* reference to the Main */
	/* used by the Main to be able to pass a reference to itself to the controllers */
	private Main Main;

	/* mandatory constructor that is empty */
	/* this is REQUIRED even if it has no commands. since the LOADER looks for this first, 
then will call initialize ONLY if initialize is decorated with @FXML */
	public RootLayoutController() {

		/* method for choosing wedding themes using images */
		cmbImagePicker.setItems(FXCollections.observableArrayList(
				"Beach", "Eclectic", "Elegant", "Garden", "Minimal", 
				"Gold", "White", "Vintage", "Country", "Countryside"));

		cmbImagePicker.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, 
					Number number, Number number2) {

				switch (cmbImagePicker.getItems().get((Integer) number2)) {

				case "Beach" :  {
					Image image = new Image("file:resources/image/beach.png");
					imagePic.setImage(image);
					break;
				}
				case "Eclectic" :  {
					Image image = new Image("file:resources/image/eclectic.png");
					imagePic.setImage(image);
					break;
				}
				case "Elegance" :  {
					Image image = new Image("file:resources/image/elegant.png");
					imagePic.setImage(image);
					break;
				}
				case "Garden" :  {
					Image image = new Image("file:resources/image/garden.png");
					imagePic.setImage(image);
					break;
				}

				case "Minimal" :  {
					Image image = new Image("file:resources/image/minimal.png");
					imagePic.setImage(image);
					break;
				}
				case "Vintage" :  {
					Image image = new Image("file:resources/image/vintage.png");
					imagePic.setImage(image);
					break;
				}

				}

			}
		});


		/* methods for calendar, allows user to open an existing calendar, 
	save a calendar, and create a new calendar */
		ToggleGroup group2 = new ToggleGroup();
		toggleButton.setToggleGroup(group2);
		group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			public void changed(ObservableValue<? extends Toggle> ov,
					Toggle toggle, Toggle new_toggle) {
				if (new_toggle == null){
					handleCalendar();
				}

				else {
					handleCalendar();
				}
			}});

		datePicker.setVisible(false);
		textDateSelected.setVisible(false);
		labelDateSelected.setVisible(false);

		/* handles the changes made to an event for a datePicker */
		datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> arg0, LocalDate arg1, LocalDate arg2) {
				textDateSelected.textProperty().setValue(arg2.toString());						
			}
		});


	}
	/* creating a new calendar */
	@FXML
	private void handleNew() {
		Main.getWeddingList().clear();     /* clears information from the previous calendar */
		Main.setWeddingListFilePath(null);
	}

	/* opens a file-chooser allows the user to select an existing calendar to use */
	@FXML
	private void handleOpen() {
		FileChooser fileChooser = new FileChooser();

		/* setting extension filter */
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml, arg1)", "*.xml");

		/* showing the save file */
		File file = fileChooser.showOpenDialog(Main.getPrimaryStage());

		if (file != null) {
			Main.loadWeddingListFromFile(file);
		}
		else {
			System.out.println("yikes");
		}
	}

	/* this method saves the file to current user's file */
	/* if no file is currently open, the "save as" dialog opens */
	@FXML
	private void handleSave() {
		File personFile = Main.getWeddingListFilePath();
		if (personFile != null) {
			Main.saveWeddingListToFile(personFile);
		} else {
			handleSaveAs();
		}
	}

	/* opens the fileChooser to let the user select the file to save to */
	@FXML
	private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();

		/* setting the extension filter */
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		/* shows the save file dialog */
		File file = fileChooser.showSaveDialog(Main.getPrimaryStage());

		if (file != null) {
			/* use the correct extension!! */
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			Main.saveWeddingListToFile(file);
		}
	}

	/* shows the about dialog */
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Calendar");
		alert.setHeaderText("About");
		alert.setContentText("By: Akanksha Borse");

		alert.showAndWait();
	}

	/* exits the application */	
	@FXML
	private void handleExit() {
		System.exit(0);
	}

	/* shows the pinterest login page */
	@FXML
	private void handlePinterest(){
		WebEngine webEngine = webView.getEngine();
		webEngine.load("http://www.pinterest.com");			
	}

	/* shows the google search engine, opens with the search box */
	@FXML
	private void handleGoogle() {
		WebEngine webEngine = webView.getEngine();
		webEngine.load("http://www.google.com");			
	}
	/* handles the calendar and allows the user to pick a date, which is then put into a text box */
	@FXML
	private void handleCalendar() {
		if (!toggleButton.isSelected()) {
			datePicker.setVisible(false);
			textDateSelected.setVisible(false);
			labelDateSelected.setVisible(false);

		}
		else {
			datePicker.setVisible(true);
			textDateSelected.setVisible(true);
			labelDateSelected.setVisible(true);				
		}
		LocalDate date = datePicker.getValue();
		System.out.println(date);
		if (date!=null){
			textDateSelected.textProperty().setValue(date.toString());
		}
		else {
			textDateSelected.textProperty().setValue(" ");
		}
	}

	/* @param Main */
	public void setMain(Main Main) {

		/* sets the field for this controller to the application passed in */  
		/* this allows for the Main to have a pointer to itself by setting its own pointer into the controller's field */
		this.Main = Main;
	}
}
