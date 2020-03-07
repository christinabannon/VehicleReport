package input.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import storage.Vehicle;

/*
 * accept a file
 * save the lines as Vehicles
 * --- sort some how by 2 fields
 * return the Vehicles[]
 * 
 *  
 */

public class ReadVehicleCSV {
	BufferedReader bufferedReader = null;
	File inputFile = null; 

	public ReadVehicleCSV(File csvFile) throws FileNotFoundException {
		inputFile = csvFile; 
		bufferedReader = new BufferedReader(new FileReader(inputFile));
	}
	
	public ArrayList<Vehicle> processCSV() throws IOException {
		String currentLine;
		ArrayList<Vehicle> vehicles = new ArrayList<>(); 
		while ((currentLine = bufferedReader.readLine()) != null) {
			Vehicle vehicle = stringToVehicle(currentLine);
			if ( vehicle != null ) {
				vehicles.add(vehicle);
			}
		}	
		if (bufferedReader != null) {
			bufferedReader.close();
		}
		vehicles = sort(vehicles);
		return vehicles; 
	}

	private Vehicle stringToVehicle(String line) {
		String [] csvLine = line.split(",");
		Vehicle vehicle = null; 
		try {
			// this will need to change with the gui
			if (csvLine.length == 4) {
			int year = Integer.parseInt(csvLine[0]);
			String make = csvLine[1];
			String model = csvLine[2];
			BigDecimal msrp = new BigDecimal(csvLine[3]);
			vehicle = new Vehicle(year, make, model, msrp);
			}
		} catch (NumberFormatException e) {
			vehicle = null; 
		}
		return vehicle;		
	}
	
	public String getFilePath() {
		String absolutePath = inputFile.getAbsolutePath();
		String fileName = inputFile.getName();
		String pathName = 
				absolutePath.substring(0, (absolutePath.length() - fileName.length()));
		return pathName; 
	}
	
	private ArrayList<Vehicle> sort(ArrayList<Vehicle> vehicles) {
		Collections.sort(vehicles, new Comparator<Vehicle>() {
			public int compare(Vehicle vehicle1, Vehicle vehicle2) {
				Integer year1 = vehicle1.getYear();
				Integer year2 = vehicle2.getYear();		
				int winner = year1.compareTo(year2);
				if (winner == 0) {
					String make1 = vehicle1.getMake();
					String make2 = vehicle2.getMake();
					winner = make1.compareTo(make2);
				}
				return winner; 
			}});
		return vehicles;
	}
}
