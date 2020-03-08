/**
 * Vehicle class holds info on a vehicle necessary for a report. 
 * 
 */

package storage.vr;
import java.math.BigDecimal;

public class Vehicle {
	
	private int year;
	private String make;
	private String model;
	private BigDecimal msrp;
	
	/**
	 * Vehicle cannot be made without parameters for the 4 fields that we will use in the report
	 */
	public Vehicle(int year, String make, String model, BigDecimal msrp) {
		this.year = year; 
		this.make = cleanMake(make);
		this.model = cleanModel(model);
		this.msrp = msrp; 
	}

	/**
	 * cleanMake acts on the assumption that everything outside of a letter is an error,
	 * and takes it out, then formats the String to look how it should in the report. 
	 */
	private String cleanMake(String make) {
		String cleanMake = make.trim().replaceAll("[^a-zA-Z]", "");
		return cleanMake.substring(0,1).toUpperCase() + cleanMake.substring(1).toLowerCase();
	}
	
	/**
	 * cleanModel allows certain symbols to be present in the model String, 
	 * but takes out ones that are unlikely to be anything other than an error. 
	 * then formats the String to look how it should in the report. 
	 */
	private String cleanModel(String model) {
		String cleanModel = model.replaceAll("[$|#|@|!|%|^|&|*|(|)|]", "").trim();
		return cleanModel.substring(0,1).toUpperCase() + cleanModel.substring(1).toLowerCase();
	}

	/**
	 * getYear returns the saved year of the vehicle
	 */
	public int getYear() {
		return year; 
	}
	
	/**
	 * getMake returns the saved make of the vehicle
	 */
	public String getMake() {
		return make; 
	}
	
	/**
	 * getModel returns the saved model of the vehicle
	 */
	public String getModel() {
		return model; 
	}
	
	/**
	 * getMSRP returns the saved MSRP
	 */
	public BigDecimal getMSRP() {
		return msrp;
	}
}
