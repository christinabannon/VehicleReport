package gui;

import java.io.File;
import java.util.ArrayList;

import input.csv.ReadVehicleCSV;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import output.txt.VehicleReportTXT;
import storage.Vehicle;
import input.csv.ReadVehicleCSV;

public class CreateTxtReport extends Application {
	File inputFile; 
	File outputFile; 
	

	
	public CreateTxtReport (String inputFileString, String outputFileType) {
		this.inputFile =  new File(inputFileString);
		//this.outputFile = new File(getFilePath(inputFile));
	}

	@Override
	public void start(Stage stage) throws Exception {
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
		
		boolean success = createReport(inputFile);
	}
/*
	private void openFile(File inputFile2) {
		try {
			ReadVehicleCSV vehicleCSV = new ReadVehicleCSV(inputFile);
			ArrayList<Vehicle> vehicles = vehicleCSV.processCSV(); 
			for (Vehicle vehicle : vehicles) {
				System.out.println("Year : " + vehicle.getYear());
				System.out.println("Make : " + vehicle.getMake());
				System.out.println("Model : " + vehicle.getModel());
				System.out.println("MSRP : " + vehicle.getMSRP());
			}
		} catch (Exception e){
			e.printStackTrace();
		}	
	}
	
	private String getFilePath(File file) {
		String absolutePath = file.getAbsolutePath();
		String fileName = file.getName();
		String pathName = 
				absolutePath.substring(0, (absolutePath.length() - fileName.length()));
		return pathName; 
	}
	*/
	
	private boolean createReport(File inputFile) {
		
		try {
			ReadVehicleCSV readVehicleCSV = new ReadVehicleCSV(inputFile);
			ArrayList<Vehicle> vehicles = readVehicleCSV.processCSV(); 
			String pathName = readVehicleCSV.getFilePath();
			System.out.println(pathName);
			VehicleReportTXT vehicleReportTxt = 
					new VehicleReportTXT(vehicles, pathName, "V1Report3.txt");
			return true;
			
		} catch (Exception e){
			e.printStackTrace();
			return false;
		} 

	}
	
}
