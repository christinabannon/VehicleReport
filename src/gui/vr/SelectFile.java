/**
 * In SelectFile a user is prompted to select a file for input, and they can only 
 * select a file with the input file extension they chose in the previous window. 
 * Once a file is selected the absolute path of the file is displayed to the user. 
 * 
 * From here a user can go on to CreateTxtReport to actually create their report, 
 * or they can go back to SelectFileType. 
 */

package gui.vr;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class SelectFile extends Application {
	String inputFileType = null;
	String outputFileType = null;
	
	public SelectFile(String inputFileType, String outputFileType) {
		this.inputFileType = inputFileType.toLowerCase();
		this.outputFileType = outputFileType.toLowerCase();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Label inputFileLabel = new Label("Select " + inputFileType + " Input File: ");
		Label fileTextLabel = new Label("");
		Button browseButton = new Button("Browse");
		Button createButton = new Button("Create");
		Button backButton = new Button("Back");
		
		browseButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters()
					.add(new ExtensionFilter(inputFileType, "*."+inputFileType));           
				File inputFile = fileChooser.showOpenDialog(stage);
				if (inputFile != null) {
					fileTextLabel.setText(inputFile.getAbsolutePath().toString());
				}
			}
		});
		
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SelectFileType specifyFilesWindow = new SelectFileType();
				specifyFilesWindow.start(new Stage());
				stage.close();
			}
		});
		
		createButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String fileString = fileTextLabel.getText();
				
				if (fileString.length() > 0) {
					try {
						CreateTxtReport createReport = new CreateTxtReport(fileString, outputFileType);
						createReport.start(new Stage());
						stage.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					Alert errorAlert = new Alert(AlertType.ERROR);
					errorAlert.setHeaderText("Select a " + inputFileType + " file to make a report.");
					errorAlert.showAndWait();
				}
			}
		});

		fileTextLabel.setStyle("-fx-border-style: solid;");
		fileTextLabel.setStyle("-fx-background-color: white;");
		fileTextLabel.setMaxWidth(Double.MAX_VALUE);
		browseButton.setMaxWidth(Double.MAX_VALUE);
		backButton.setMaxWidth(Double.MAX_VALUE);
		createButton.setMaxWidth(Double.MAX_VALUE);
		
		GridPane gridPane = new GridPane();
		ColumnConstraints fifthCol = new ColumnConstraints();
		fifthCol.setPercentWidth(20);
		double standardSpacing = 15;
		
    	gridPane.setHgap(standardSpacing);
    	gridPane.setVgap(standardSpacing);
    	gridPane.setPadding(new Insets(standardSpacing));
		gridPane.getColumnConstraints().addAll(fifthCol, 
				fifthCol, fifthCol, fifthCol, fifthCol);
		gridPane.add(inputFileLabel, 0, 0, 4, 1);
		gridPane.add(fileTextLabel,  0, 1, 4, 1);
		gridPane.add(browseButton,   4, 1, 1, 1);
		gridPane.add(backButton,     3, 2, 1, 1);
		gridPane.add(createButton,   4, 2, 1, 1);
	  	gridPane.setAlignment(Pos.CENTER);
	  	
		Scene scene = new Scene(gridPane, 500, 150);
		stage.setTitle("Create New Report: Select File");
		stage.setScene(scene);
		stage.show();
	}
	
	

}
