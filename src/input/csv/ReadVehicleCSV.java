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
 */

public class ReadVehicleCSV {
	private BufferedReader bufferedReader = null;
	private File inputFile = null; 
	private ArrayList<Vehicle> vehicles;

	public ReadVehicleCSV(File csvFile) throws FileNotFoundException, IOException {
		inputFile = csvFile; 
		bufferedReader = new BufferedReader(new FileReader(inputFile));

		String extension = inputFile.getName().substring(inputFile.getName().lastIndexOf("."));
		String splitAround = "";
		
		switch(extension) {
		case ".csv":
			splitAround = ",";
			break;
		case ".tsv":
			splitAround = "\t";
			break;
		}
		processInputFile(splitAround);
	}
	
	public String getFilePath() {
		String absolutePath = inputFile.getAbsolutePath();
		String fileName = inputFile.getName();
		String pathName = 
				absolutePath.substring(0, (absolutePath.length() - fileName.length()));
		return pathName; 
	}
	
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	private void processInputFile(String splitAlong) throws IOException {
		String currentLine;
		vehicles = new ArrayList<Vehicle>(); 
		while ((currentLine = bufferedReader.readLine()) != null) {
			Vehicle vehicle = stringToVehicle(currentLine, splitAlong);
			if ( vehicle != null ) {
				vehicles.add(vehicle);
			}
		}	
		if (bufferedReader != null) {
			bufferedReader.close();
		}
	}

	private Vehicle stringToVehicle(String line, String splitAlong) {
		String [] csvLine = line.split(splitAlong);
		Vehicle vehicle = null; 
		try {
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
	
	private void sort() {
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
		//return vehicles;
	}
}
