/**
 * This class makes a user specify the type of input file they will
 * be using for their report. 
 * 
 * It also makes a user specify their output type, but that is less important
 * as I have only given them one choice. 
 * 
 * From here a user can go on to the SelectFile class or can go back to the 
 * Start class. 
 */

package gui.vr;

import gui.Start;
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
    public void start(Stage stage) {
    	
    	Label instructions = new Label();
    	instructions.setWrapText(true);
    	instructions.setText("To start a Vehicle Report: \n"
    			+ "\t1. Make the following selections \n"
    			+ "\t2. Click the Next button.");
		
		Label inputFileTypeLabel = new Label("Select Input File Type: ");
		ObservableList<String> inputFileTypeOptions = 
				FXCollections.observableArrayList(
						".csv",
						".tsv");
		final ComboBox<String> inputFileTypeComboBox = 
				new ComboBox<String>(inputFileTypeOptions);
		inputFileTypeComboBox.setValue(".csv");
		
		Label outputFileTypeLabel = new Label("Select Output File Type: ");
		ObservableList<String> outputFileTypeOptions = 
				FXCollections.observableArrayList(
						".txt");
		final ComboBox<String> outputFileTypeComboBox = 
				new ComboBox<String>(outputFileTypeOptions);
		outputFileTypeComboBox.setValue(".txt");

		Button nextButton = new Button("Next");
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					SelectFile selectFileWindow = 
							new SelectFile(inputFileTypeComboBox.getValue(),
									outputFileTypeComboBox.getValue());
					selectFileWindow.start(new Stage());
					stage.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
		Button backButton = new Button("Back");
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Start startStage = new Start();
					try {
						startStage.start(new Stage());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					stage.close();
			}
		});

		inputFileTypeComboBox.setMaxWidth(Double.MAX_VALUE);
		outputFileTypeComboBox.setMaxWidth(Double.MAX_VALUE);
		nextButton.setMaxWidth(Double.MAX_VALUE);
		backButton.setMaxWidth(Double.MAX_VALUE);
		
    	double standardSpacing = 15;	
    	
    	GridPane gridPane = new GridPane();

    	gridPane.setPrefSize(400,300);
    	gridPane.setHgap(standardSpacing);
    	gridPane.setVgap(standardSpacing);
    	gridPane.setPadding(new Insets(standardSpacing));
    	
    	ColumnConstraints quarterCol = new ColumnConstraints();
    	quarterCol.setPercentWidth(25);	
    	
    	gridPane.getColumnConstraints().addAll(quarterCol, quarterCol, quarterCol, quarterCol);
    	
    				// startCol, startRow, colsWide, rowsTall
    	gridPane.add(instructions,           0, 0, 3, 1); 
    	gridPane.add(inputFileTypeLabel,     0, 1, 2, 1);
    	gridPane.add(inputFileTypeComboBox,  2, 1, 2, 1);
    	gridPane.add(outputFileTypeLabel,    0, 2, 2, 1);
    	gridPane.add(outputFileTypeComboBox, 2, 2, 2, 1);
    	gridPane.add(backButton,             2, 3);
    	gridPane.add(nextButton,             3, 3);
    	gridPane.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(gridPane, 350, 225);

		stage.setTitle("Create New Report: Specify File Types");
		stage.setScene(scene);
		stage.show();
    }
}
