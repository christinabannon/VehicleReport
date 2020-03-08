/**
 * ReadVehicleInput is accessed by gui/vr/CreateTxtReport to convert the lines
 * of a .csv or .tsv file into an ArrayList of Vehicle objects. 
 */

package input.vr;

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
import storage.vr.Vehicle;

public class ReadVehicleInput {
	private BufferedReader bufferedReader = null;
	private File inputFile = null; 
	private ArrayList<Vehicle> vehicles;

	/**
	 * Since the functions of this class ultimately add up to one task, 
	 * it made sense to put the series of methods to be performed within 
	 * the constructor.
	 * 
	 * @param csvFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ReadVehicleInput(File csvFile) throws FileNotFoundException, IOException {
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
		sort();
	}
	
	/**
	 * getFilePath takes the file path from the input file and returns it
	 */
	public String getFilePath() {
		String absolutePath = inputFile.getAbsolutePath();
		String fileName = inputFile.getName();
		String pathName = 
				absolutePath.substring(0, (absolutePath.length() - fileName.length()));
		return pathName; 
	}
	
	/**
	 * getVehicles returns an ArrayList containing all of the vehicle objects 
	 * found in the input file .
	 */
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	/**
	 * processInputFile takes argument "splitAround" as it's 
	 * String to split the line around, depending on if the input
	 * is a .csv or a .tsv file. 
	 * From there it passes each line
	 * to stringToVehicle, gets back a Vehicle, 
	 * and adds the Vehicle object to the vehicles ArrayList.
	 * 
	 */
	private void processInputFile(String splitAround) throws IOException {
		String currentLine;
		vehicles = new ArrayList<Vehicle>(); 
		while ((currentLine = bufferedReader.readLine()) != null) {
			Vehicle vehicle = stringToVehicle(currentLine, splitAround);
			if ( vehicle != null ) {
				vehicles.add(vehicle);
			}
		}	
		if (bufferedReader != null) {
			bufferedReader.close();
		}
	}

	/**
	 * stringToVehicle takes a string, splits it into an array of words
	 * and creates a new Vehicle object from the ordered list of words
	 * which is then returned to the calling method (processInputFile).
	 * 
	 * If anything is amiss with the list of words, 
	 * the vehicle object is not created and the method returns a null, 
	 * which will be discarded instead of added to the vehicles ArrayList. 
	 * 
	 */
	private Vehicle stringToVehicle(String line, String splitAlong) {
		String [] fileLine = line.split(splitAlong);
		Vehicle vehicle = null; 
		try {
			if(fileLine.length == 4) {
			int year = Integer.parseInt(fileLine[0]);
			String make = fileLine[1];
			String model = fileLine[2];
			BigDecimal msrp = new BigDecimal(fileLine[3]);
			vehicle = new Vehicle(year, make, model, msrp);
			}
		} catch (NumberFormatException e) {
			vehicle = null; 
		}
		return vehicle;		
	}
	
	/**
	 * sort() sorts the stored vehicles ArrayList first by year 
	 * and second by make. 
	 */
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
	}
}
