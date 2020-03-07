package testing;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import input.csv.ReadVehicleCSV;
import output.txt.VehicleReportTXT;
import storage.Vehicle;

public class VehicleDemo {
	public static void main(String [] args) throws IOException {
		
		File reportFile = new File("/Users/christinabannon/FileFolder/V1Report3.txt");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(reportFile));
		String currentLine;
		StringBuilder stringBuilder = new StringBuilder();

		while ((currentLine = bufferedReader.readLine()) != null) {
			if ((currentLine.length() < 3) ||
				(currentLine.charAt(2) != ' ') ||
				currentLine.substring(12, 16).contentEquals("MSRP") ||
				currentLine.substring(12, 22).contentEquals("List Price")) {
				stringBuilder.append(currentLine);
			}
			else{
				String [] lineArray = currentLine.split("\\s{2,}");
				String make = lineArray[0];
				String model = lineArray[1];
				String msrp = lineArray[2];
				String listPrice = lineArray[3];
				stringBuilder.append(String.format("%10s %-15s %-15s %-30s %-15s %n", 
						"", make, model, msrp, listPrice));
			}
			stringBuilder.append("\n");
		}
		if (bufferedReader != null) {
			bufferedReader.close();
		}
		
		System.out.print(stringBuilder.toString());
		
		
		/*
		File file = new File("/Users/christinabannon/FileFolder/Vehicles.csv");
		
		try {
			ReadVehicleCSV readVehicleCSV = new ReadVehicleCSV(file);
			ArrayList<Vehicle> vehicles = readVehicleCSV.getVehicles(); 
			String pathName = readVehicleCSV.getFilePath();
			System.out.println(pathName);
			VehicleReportTXT txtReport = 
					new VehicleReportTXT(vehicles, pathName, "V1Report2.txt");
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
*/
	}
}
