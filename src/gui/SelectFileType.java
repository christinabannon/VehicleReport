package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SelectFileType extends Application {
    @Override
    public void start(Stage primaryStage) {
    	
    	Label instructions = new Label();
    	instructions.setWrapText(true);
    	instructions.setText("To start a report: \n"
    			+ "\t1. Make the following selections \n"
    			+ "\t2. Click the Next Step button.");

		Label reportTypeLabel = new Label("Select a Report Type: ");
		ObservableList<String> reportTypeOptions = 
				FXCollections.observableArrayList(
						"Vehicle Cost Report",
						"Vehicle Sales Report");
		final ComboBox<String> reportTypeComboBox = 
				new ComboBox<String>(reportTypeOptions);
		reportTypeComboBox.setValue("Vehicle Cost Report");
		
		Label inputFileTypeLabel = new Label("Select Input File Type: ");
		ObservableList<String> inputFileTypeOptions = 
				FXCollections.observableArrayList(
						".csv",
						".tsv",
						".xlsx");
		final ComboBox<String> inputFileTypeComboBox = 
				new ComboBox<String>(inputFileTypeOptions);
		inputFileTypeComboBox.setValue(".csv");
		
		Label outputFileTypeLabel = new Label("Select Output File Type: ");
		ObservableList<String> outputFileTypeOptions = 
				FXCollections.observableArrayList(
						".txt",
						".docx",
						".pdf");
		final ComboBox<String> outputFileTypeComboBox = 
				new ComboBox<String>(outputFileTypeOptions);
		outputFileTypeComboBox.setValue(".txt");
		
		Button nextButton = new Button("Next");
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					SelectFile selectFileWindow = 
							new SelectFile(reportTypeComboBox.getValue(), 
									inputFileTypeComboBox.getValue(),
									outputFileTypeComboBox.getValue());
					/*
					Alert errorAlert = new Alert(AlertType.ERROR);
					errorAlert.setHeaderText("Input not valid");
					errorAlert.setContentText("The size of First Name must be between 2 and 25 characters");
					errorAlert.showAndWait();
					*/
					selectFileWindow.start(new Stage());
					primaryStage.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
		Button closeButton = new Button("Close");
		closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}
		});
		
		reportTypeComboBox.setMaxWidth(Double.MAX_VALUE);
		inputFileTypeComboBox.setMaxWidth(Double.MAX_VALUE);
		outputFileTypeComboBox.setMaxWidth(Double.MAX_VALUE);
		nextButton.setMaxWidth(Double.MAX_VALUE);
		closeButton.setMaxWidth(Double.MAX_VALUE);
		
    	double standardSpacing = 15;	
    	
    	GridPane gridPane = new GridPane();

//     	gridPane.setGridLinesVisible(true);
    	gridPane.setPrefSize(400,300);
    	gridPane.setHgap(standardSpacing);
    	gridPane.setVgap(standardSpacing);
    	gridPane.setPadding(new Insets(standardSpacing));
    	
    	ColumnConstraints quarterCol = new ColumnConstraints();
    	quarterCol.setPercentWidth(25);	
    	
    	gridPane.getColumnConstraints().addAll(quarterCol, quarterCol, quarterCol, quarterCol);
    	
    				// startCol, startRow, colsWide, rowsTall
    	gridPane.add(instructions,           0, 0, 3, 1); 
    	gridPane.add(reportTypeLabel,        0, 1, 2, 1); 
    	gridPane.add(reportTypeComboBox,     2, 1, 2, 1);
    	gridPane.add(inputFileTypeLabel,     0, 2, 2, 1);
    	gridPane.add(inputFileTypeComboBox,  2, 2, 2, 1);
    	gridPane.add(outputFileTypeLabel,    0, 3, 2, 1);
    	gridPane.add(outputFileTypeComboBox, 2, 3, 2, 1);
    	gridPane.add(closeButton,            2, 4);
    	gridPane.add(nextButton,             3, 4);
    	gridPane.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(gridPane, 400, 250);

		primaryStage.setTitle("Create New Report: Specify File Types");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    


    public static void main(String[] args) {
        launch();
    }
}
