import java.util.Random;
import java.io.*;
public abstract class Stock implements Serializable  {

	/** 
	 * number of stocks available at the inital offering  
	*/
	private final int initialAvailable;

	/** 
	 * number of stocks in portfolio */
	private int available;

	/** 
	 * the price object */
	private Price price = new Price();

	/** 
	 * the name of the stock */
	private String name; //

	/**
	 * Constructor for the stock object
	 * 
	 * @param valueAvailable Describes the number of that stock available
	 * @param valuePrice The price of the stock in GBP
	 * @param valueName The name of the stock
	 */
	public Stock(int valueAvailable, double valuePrice, String valueName) {

		initialAvailable = valueAvailable;
		available = 0;
		price.setPrice(valuePrice);
		name = valueName;
	}

	/**
	 * @return the number of shares at IPO 
	 */
	public int getInitialAvailable() {
		return initialAvailable;
	}

	/**
	 * @return the available shares to sell
	 */
	public int getAvailable() {
		return available;
	}

	/**
	 * @param available the available shares to sell
	 */
	public void setAvailable(int available) {
		this.available = available;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price object
	 */
	public Price getPrice() {
		return price;
	}

	/**
	 * method for calculating the price.   
	 * @return a double which is the new price
	 * @param s1 a stock object
	 */
	public static double newPrice(Stock s1) {
		double newPrice;
		double temp;
		String sTemp;

		newPrice = new Random().nextDouble();
		//above outputs double in range of 0-1
		temp = ((s1.getInitialAvailable() - s1.getAvailable()) / 2 * s1.getInitialAvailable());

		//next three lines gets the double, seperates the whole number from decimal and  
		//converts decimal to double
		sTemp = Double.toString(temp);
		sTemp = sTemp.split("\\.")[1];
		temp = Double.parseDouble(sTemp);

		newPrice = newPrice * (1 + temp);
		// //above line changes price based on available share, lower available means more valuble


		if (new Random().nextDouble() < 0.2) {
			//20% chance of a price change of 2-5x
			newPrice = newPrice * (new Random().nextInt((5 - 2) + 1) + 2) + 0.75;
			
		}

		if (new Random().nextDouble() < 0.09) {
			//9% chance of a price change of 5-10x
			newPrice = newPrice * (new Random().nextInt((10 - 5) + 1) + 5) + 1.75;
			
		}

		if (new Random().nextBoolean()) {
			//if true, price change positive
			return newPrice;

		} else {
			//if false, price change negative
			return newPrice * -1.0;

		}

	}



}
