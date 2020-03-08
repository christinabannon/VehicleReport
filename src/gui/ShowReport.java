package gui;

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
		Label locationLabel = new Label("Location: ");
		Label fileLocationLabel = new Label(reportFile.getAbsolutePath());
		Button openFileButton = new Button("Open Report");
		
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

		fileLocationLabel.setStyle("-fx-border-style: solid;");
		fileLocationLabel.setStyle("-fx-background-color: white;");
		
		openFileButton.setMaxWidth(Double.MAX_VALUE);
    	ColumnConstraints quarterCol = new ColumnConstraints();
    	quarterCol.setPercentWidth(25);	
    	gridPane.getColumnConstraints().addAll(quarterCol, quarterCol, quarterCol, quarterCol);
		gridPane.setGridLinesVisible(true);
		int standardSpacing = 10;
		gridPane.setVgap(standardSpacing);
		gridPane.setPadding(new Insets(standardSpacing));
		gridPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		gridPane.add(reportCreatedLabel, 0, 0, 3, 1);
		gridPane.add(locationLabel,      0, 1, 1, 1);
		gridPane.add(fileLocationLabel,  1, 1, 4, 1);
		gridPane.add(openFileButton,     2, 2, 1, 1);
		gridPane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gridPane, 400, 120);
		stage.setTitle("View Report");
		stage.setScene(scene);
		stage.show();
	}
}
