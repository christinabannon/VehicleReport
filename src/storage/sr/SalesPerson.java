package storage.sr;

import java.util.ArrayList;

public class SalesPerson {
	private String name;
	private ArrayList<Double> sales; 
	private double commissionRate; 
	private double totalSales;
	
	public SalesPerson(String name, ArrayList<Double>sales) {
		this.name = name; 
		this.sales = sales;
		totalSales = 0; 
		
		for(int i = 0; i < sales.size(); i++) {
			totalSales += sales.get(i);
		}
		
		if (totalSales > 100000) {
			commissionRate = .5;
		} else if (totalSales > 70000) {
			commissionRate = .3;
		} else if (totalSales > 50000) {
			commissionRate = .2;
		} else {
			commissionRate = .1;
		}
	}
	
	public double getTotalSales() {
		double totalSales = 0; 
		return totalSales;
	}
	
	public ArrayList getSales() {
		return sales; 
	}
	
	private double getCommission() {
		return commissionRate * totalSales; 
	}
	
	
}
