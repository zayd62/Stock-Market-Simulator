import java.io.*;
public class Share extends Stock implements Serializable{

	/** 
	 * the country the shares are issued from 
	 * 
	 */
	private String country;//

	/**
	 * Constructor for object of class share. Share is subclass of Stock
	 * @param valueAvailable  Describes the number of that stock available
	 * @param valuePrice The price of the stock in GBP
	 * @param valueName The name of the stock
	 * @param valueCountry; The country the company issues shares from
	 */

	public Share(int valueAvailable, double valuePrice, String valueName, String valueCountry) {
		super(valueAvailable, valuePrice, valueName); //calls superclass constructor
		country = valueCountry;

	}

	/**
	 * @return returns the country the company issues the share from
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country sets the country the shares are from
	 */
	public void setCountry(String valueCountry) {
		country = valueCountry;
	}

}
