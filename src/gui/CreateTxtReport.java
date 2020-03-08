package gui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  
import java.util.ArrayList;

import input.csv.ReadVehicleCSV;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import output.txt.VehicleReportTXT;
import storage.Vehicle;

public class CreateTxtReport extends Application {
	File inputFile; 
	File outputFile; 
	String outputFileType;
	
	public CreateTxtReport (String inputFileString, String outputFileType) {
		this.inputFile =  new File(inputFileString);
		this.outputFileType = outputFileType;
	}

	@Override
	public void start(Stage stage) {
		VBox vBox = new VBox(20);
		Label creatingReport = new Label("Creating Report...");
		ProgressIndicator progressIndicator = new ProgressIndicator();
		vBox.getChildren().addAll(creatingReport, progressIndicator);
		vBox.setAlignment(Pos.BASELINE_CENTER);	
		vBox.setPadding(new Insets(10));
		Scene scene = new Scene(vBox, 200, 120);
		stage.setTitle("Creating Report");
		stage.setScene(scene);
		stage.show();
		boolean successfulCreation = createReport(inputFile);
		if (successfulCreation) {
			ShowReport showReport = new ShowReport(outputFile);
			showReport.start(new Stage());
			stage.close();
		}
		else {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText("Input not valid");
			errorAlert.setContentText("Please check that your input file has 4 columns.");
			errorAlert.showAndWait();
		}
	}
	
	private boolean createReport(File inputFile) {
		try {
			ReadVehicleCSV readVehicleCSV = new ReadVehicleCSV(inputFile);
			ArrayList<Vehicle> vehicles = readVehicleCSV.getVehicles();
			String pathName = readVehicleCSV.getFilePath();
			VehicleReportTXT vehicleReportTxt = 
					new VehicleReportTXT(vehicles, pathName);
			outputFile = vehicleReportTxt.getReport();
			return true;
		} catch (IOException e) {
			return false;
		}
		


	}
	
}
