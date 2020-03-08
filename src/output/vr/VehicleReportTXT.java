/**
 * VehicleReportTXT uses an ArrayList of vehicles to write a formatted .txt report
 * that is saved in a given folder. 
 *
 */

package output.vr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import storage.vr.Vehicle;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class VehicleReportTXT {

	File txtReport        = null;     // File we are writing to
	String report         = null;     // will hold String to be written
	FileWriter fileWriter = null;     // how we will write report to txtReport


	/**
	 * 
	 * Constructor accepts ArrayList of vehicles that are being reported on
	 * and the pathname of the directory the report should be saved to. 
	 * From there it 
	 * calls createFile to create a new File with the given path,
	 * calls createReport to have the String of the formatted report written
	 * and  calls writeReport to write that String to a file. 
	 */
	public VehicleReportTXT(ArrayList<Vehicle> vehicles, String pathName) {
		txtReport = createFile(pathName);
		try {
			fileWriter = new FileWriter(txtReport);
		} catch (IOException e) {
			e.printStackTrace();
		}
		report = createReport(vehicles);
		writeReport(report);	
	}
	
	/**
	 * getReport returns the finished File to caller (gui/vr/ShowReport)
	 */
	public File getReport() {
		return txtReport;
	}
	
	/**
	 * getFormattedDate returns the date in the way required for 
	 * the final report
	 */
	private String getFormattedDate() {
		Date date = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter.format(date);

	}
	
	/**
	 * createFile creates a unique file name for the report consisting of 
	 * VehicleReport_<date>_(number-of-reports-made-so-far-on-this-date).txt
	 * located at the specified path. 
	 */
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
	

	/**
	 * 
	 * cashFormatBigDecimal takes a BigDecimal and changes it
	 * to a cash format. This is used to format msrp and list price
	 * for the report
	 */
	private String cashFormatBigDecimal(BigDecimal bigDecimal) {
		DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
		return decimalFormat.format(bigDecimal);
	}

	/**
	 * msrpToListPrice does the quick calculation to turn an msrp
	 * into a list price
	 */
	private BigDecimal msrpToListPrice (BigDecimal msrp) {
		return msrp.multiply(new BigDecimal(1.07));
	}

	/**
	 * createReport uses vehicles ArrayList to write 
	 * & return a formatted String
	 * 
	 */
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
	
	/**
	 * writeReport uses fileWriter to write the report String to a file. 
	 */
	private void writeReport(String report) {
		try {
			fileWriter.write(report);
			fileWriter.close();
		} catch (Exception E) {
			E.printStackTrace();
		}
	}
}
