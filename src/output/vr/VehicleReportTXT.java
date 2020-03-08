package output.vr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.Date;

import storage.vr.Vehicle;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/*
 * 
 * 
 * 
 */
public class VehicleReportTXT {

	File txtReport = null;
	FileWriter fileWriter = null;
	BufferedWriter bufferedWriter = null; 
	String report = null; 

	public VehicleReportTXT(ArrayList<Vehicle> vehicles, String pathName) {
		txtReport = createFile(pathName);
		try {
			fileWriter = new FileWriter(txtReport);
		} catch (IOException E) {
			E.printStackTrace();
		}
		report = createReport(vehicles);
		writeReport(report);
	}

	public String getReportString() {
		return report; 
	}
	
	private String getFormattedDate() {
		Date date = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter.format(date);

	}
	
	private File createFile(String pathName) {
		StringBuilder vehicleReportName = new StringBuilder(pathName + "VehicleReport_");
		Date date = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		vehicleReportName.append(formatter.format(date));
		File file = new File(vehicleReportName.toString() + ".txt");	
		int version = 1;
		while (file.exists()) {
			file = new File(vehicleReportName.toString() +"_(" + version++ + ")_" + ".txt");
		}
		return file;
	}
	

	private String cashFormatBigDecimal(BigDecimal bigDecimal) {
		DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
		return decimalFormat.format(bigDecimal);
	}

	private BigDecimal msrpToListPrice (BigDecimal msrp) {
		return msrp.multiply(new BigDecimal(1.07));
	}

	// methods for what to write to file
	private String createReport(ArrayList<Vehicle> vehicles) {

		String make = null;
		String model = null; 
		String msrp = null;
		String listPrice = null;
		int vehicleIndex = 0; 
		int year = 0;
		BigDecimal msrpSum = new BigDecimal(0);
		Vehicle currentVehicle = null; 
		StringBuilder stringBuilder = new StringBuilder(); 

		stringBuilder.append(" -- Vehicle Report -- ");
		stringBuilder.append(String.format(" %70s", "Date: " + getFormattedDate()));

		while (vehicleIndex < vehicles.size()) {
			currentVehicle = vehicles.get(vehicleIndex);

			if (year != currentVehicle.getYear()) {
				stringBuilder.append(String.format("%n"));
				year = currentVehicle.getYear();
				stringBuilder.append(String.format(" %d %n", year ));
			}
			make = currentVehicle.getMake();
			model = currentVehicle.getModel();
			msrp = "MSRP: $" + cashFormatBigDecimal(currentVehicle.getMSRP());
			listPrice = "List Price: $" + 
					cashFormatBigDecimal(msrpToListPrice(currentVehicle.getMSRP()));
			stringBuilder.append(String.format("%10s %-15s %-15s %-30s %-15s %n", 
					"", make, model, msrp, listPrice));

			msrpSum = msrpSum.add(currentVehicle.getMSRP());
			vehicleIndex++;
		}

		stringBuilder.append(String.format("%n %n %s %n", " -- Grand Total -- "));
		stringBuilder.append(String.format(" %10s %s %n", "", "MSRP: $" + cashFormatBigDecimal(msrpSum)));
		stringBuilder.append(String.format(" %10s %s %n", "", 
				"List Price: $" + cashFormatBigDecimal(msrpToListPrice(msrpSum))));
		
		make = null;
		model = null; 
		msrp = null; 
		listPrice = null; 
		msrpSum = null; 
		currentVehicle = null; 
		
		return stringBuilder.toString();
	}
	
	private void writeReport(String report) {
		try {
			fileWriter.write(report);
			fileWriter.close();
		} catch (Exception E) {
			E.printStackTrace();
		}
	}
	
	public File getReport() {
		return txtReport;
	}
}














