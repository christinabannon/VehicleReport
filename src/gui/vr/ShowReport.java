package gui.vr;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ShowReport extends Application {

	File reportFile;

	/*
	public static void main(String [] args) {
		reportFile = new File("/Users/christinabannon/FileFolder/VehicleReport_03-08-2020_\\(3\\)_.txt");
		launch(args);
	}
*/
	public ShowReport(File reportFile) {
		this.reportFile = reportFile;
	}
	@Override
	public void start(Stage stage) {
		
		GridPane gridPane = new GridPane();
		Label reportCreatedLabel = new Label("Report successfully created!");
		Label locationLabel = new Label("Report Location: ");
		Label fileLocationLabel = new Label(reportFile.getAbsolutePath());
		Button openFileButton = new Button("Open Report");
		Button doneButton = new Button("Done");
		
		openFileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				/* if you wanted to open the file */
				try {
					final Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
					if (desktop != null && desktop.isSupported(Desktop.Action.OPEN)) {
						desktop.open(reportFile);
					} else {
						throw new UnsupportedOperationException("Open action not supported");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		doneButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});

		fileLocationLabel.setStyle("-fx-border-style: solid;");
		fileLocationLabel.setStyle("-fx-background-color: white;");
		fileLocationLabel.setMaxWidth(Double.MAX_VALUE);
		openFileButton.setMaxWidth(Double.MAX_VALUE);
		doneButton.setMaxWidth(Double.MAX_VALUE);
		
    	ColumnConstraints col = new ColumnConstraints();
    	col.setPercentWidth(17);	
    	gridPane.getColumnConstraints().addAll(col, col, col, col, col, col);
		int standardSpacing = 10;
		gridPane.setVgap(standardSpacing);
		gridPane.setHgap(standardSpacing);
		gridPane.setPadding(new Insets(standardSpacing));

		gridPane.add(reportCreatedLabel, 0, 0, 3, 1);
		gridPane.add(locationLabel,      0, 1, 1, 1);
		gridPane.add(fileLocationLabel,  1, 1, 5, 1);
		gridPane.add(openFileButton,     4, 2, 1, 1);
		gridPane.add(doneButton,         5, 2, 1, 1);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setMaxWidth(Double.MAX_VALUE);
		Scene scene = new Scene(gridPane, 710, 120);
		
		stage.setTitle("View Report");
		stage.setScene(scene);
		stage.show();
	}
}
