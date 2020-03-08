/**
 * This very simple class is the entry point to begin a VehicleReport. 
 * From here you can either continue on to the SelectFileType window, or close. 
 */

package gui;

import gui.vr.SelectFileType;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Start extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		Button makeVehicleReportButton = new Button("Start Vehicle Report");
		Button closeButton = new Button("Close");
		
		makeVehicleReportButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SelectFileType selectFileTypeWindow = new SelectFileType();
				selectFileTypeWindow.start(new Stage());
			}
		});
		
		closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.close();
				System.exit(0);
			}
		});
		
		makeVehicleReportButton.setMaxWidth(Double.MAX_VALUE);
		closeButton.setMaxWidth(Double.MAX_VALUE);
		
		VBox vBox = new VBox(10, makeVehicleReportButton, closeButton);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(10));

		Scene scene = new Scene(vBox, 200, 100);
		stage.setTitle("Start Report");
		stage.setScene(scene);
		stage.show();
	}

}
