

// -----------------------------------------------------
// Assignment 2
// ©Samuel Fauteux
// Written by:Samuel Fauteux 202033158
// -----------------------------------------------------
// This is the computer class, every computer has a brand, 
// a model, a serial number and a price. All of these 
// attributes are gettable and settable. A computer can 
// return a string made to display those attributes and 
// can also be compared to another computer.
//
// This class can also return the number of computer 
// created so far. It is the only use of the computer 
// counter
// -----------------------------------------------------

class Computer {
	
	static int computerCounter = 0; //counts the number of computers created
	
	//Computer attribute
	private String brand;
	private String model;
	private long sn; //SN = Serial Number
	private double price;
	
	//this is Computer sole constructor, it requires all attribute to be specified
	//it will also increment the computer counter
	Computer(String brand, String model, long sn, double price) {
		++computerCounter;
		
		this.brand = brand;
		this.model = model;
		this.sn = sn;
		this.price = price;
	}
	
	//Accessors
	public String getBrand() {
		return brand;
	}
	
	public String getModel() {
		return model;
	}
	
	public long getSN() {
		return sn;
	}
	
	public double getPrice() {
		return price;
	}
	
	//Mutators
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public void setSN(long sn) {
		this.sn = sn;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	//Methods
	
	//What to print when asked
	public String toString() {
		return "\nBrand:         " + brand
				+ "\nModel:         " + model
				+ "\nSerial Number: " + sn
				+ "\nPrice:         $" + String.format("%.2f", price) 
				+ "\n// -----------------------------------------------------\n";
	}
	
	//This method take an other computer object and compare it with this one
	//All their attributes except their serial numbers are compared
	//If all those attributes are the same, return true, else return false
	public boolean equals(Computer otherComputer) {
		if(brand == otherComputer.brand && model == otherComputer.model && price == otherComputer.price)
		{
			return true;
		}
		return false;
	}
	
	//Return the number of computer that where created
	public static int findNumberOfCreatedComputers() {
		return computerCounter;
	}
}