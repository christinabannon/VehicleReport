package testing;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import input.csv.ReadVehicleCSV;
import output.txt.VehicleReportTXT;
import storage.Vehicle;

public class VehicleDemo {
	public static void main(String [] args) {
		
		File file = new File("/Users/christinabannon/FileFolder/Vehicles.csv");
		
		try {
			ReadVehicleCSV readVehicleCSV = new ReadVehicleCSV(file);
			ArrayList<Vehicle> vehicles = readVehicleCSV.processCSV(); 
			String pathName = readVehicleCSV.getFilePath();
			System.out.println(pathName);
			VehicleReportTXT txtReport = 
					new VehicleReportTXT(vehicles, pathName, "V1Report2.txt");
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
