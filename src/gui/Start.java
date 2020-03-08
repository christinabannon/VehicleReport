package gui;

import java.awt.Desktop;
import java.io.IOException;

import gui.vr.SelectFileType;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Start extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Label instructions = new Label("What type of report would you like to start?");	
		Button makeSalesReportButton = new Button("Start Sales Report");
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
		
		instructions.setAlignment(Pos.BASELINE_CENTER);
		makeSalesReportButton.setMaxWidth(Double.MAX_VALUE);
		makeVehicleReportButton.setMaxWidth(Double.MAX_VALUE);
		closeButton.setMaxWidth(Double.MAX_VALUE);
		
		VBox vBox = new VBox(10, instructions, makeSalesReportButton, makeVehicleReportButton, closeButton);
		vBox.setPadding(new Insets(10));

		Scene scene = new Scene(vBox, 300, 150);
		stage.setTitle("Start Report");
		stage.setScene(scene);
		stage.show();
	}

}
