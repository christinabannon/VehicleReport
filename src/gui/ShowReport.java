package gui;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ShowReport extends Application {

	String reportFile;

	public static void main(String [] args) {
		launch(args);
	}

	/*
	public ShowReport(File reportFile) {
		this.reportFile = reportFile;
	}
	*/


	@Override
	public void start(Stage stage) {
		
		File reportFile = new File("/Users/christinabannon/FileFolder/V1Report3.txt");
		GridPane gridPane = new GridPane();
		Label fileTextLabel = new Label(reportFile.getAbsolutePath());
		fileTextLabel.setStyle("-fx-border-style: solid;");

		Button openFileButton = new Button("Open Report");
		openFileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				/*
				final FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters()
					.add(new ExtensionFilter(inputFileType, "*."+inputFileType));           
				File inputFile = fileChooser.showOpenDialog(stage);
				if (inputFile != null) {
					fileTextLabel.setText(inputFile.getAbsolutePath().toString());
					// Which should take priority? text or browse?
				}
				*/
				
				/* if you wanted to open the file */
		        try {
		        	open(reportFile.getAbsolutePath());
		        } catch (Exception ex) {

		        }
			}
		});
		/*
		Text reportText = null; 
		try {
			reportText = new Text(getText());
		} catch (HeadlessException | IOException e) {
			// TODO Auto-generated catch block
			reportText = new Text("Nothing good found");
		}*/

		gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		gridPane.add(fileTextLabel, 0, 0, 1, 1);
		gridPane.add(openFileButton, 0, 1);
	//	gridPane.add(reportText, 0, 1, 9, 9);
		gridPane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gridPane, 700, 700);
		stage.setTitle("View Report");
		stage.setScene(scene);
		stage.show();
	}
	
	/*

	private String getText() throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(reportFile));
		String currentLine;
		StringBuilder stringBuilder = new StringBuilder();

		while ((currentLine = bufferedReader.readLine()) != null) {
			if ((currentLine.length() < 3) ||
				(currentLine.charAt(2) != ' ') ||
				currentLine.substring(12, 16).contentEquals("MSRP") ||
				currentLine.substring(12, 22).contentEquals("List Price")) {
				stringBuilder.append(currentLine);
			}
			else {
				String [] lineArray = currentLine.split("\\s{2,}");
				String make = lineArray[0];
				String model = lineArray[1];
				String msrp = lineArray[2];
				String listPrice = lineArray[3];
				stringBuilder.append(String.format("%10s %15s %15s %30s %15s", 
						"", make, model, msrp, listPrice));
			}
			stringBuilder.append("\n");
		}
		if (bufferedReader != null) {
			bufferedReader.close();
		}
		
		return stringBuilder.toString();
	}*/





}
