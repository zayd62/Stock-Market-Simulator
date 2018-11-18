import java.io.*;

public class Forex extends Stock implements Serializable{

    /** a value where when multiplied by the price, will get the value of the stock in the currency of the stock name
     * formula: price*poundMultiplier=value in stock name country
     */
    private double poundMultiplier; 

    	/**
	 * Constructor for object ofl type Forex. Forex is subclass of Stock
	 * @param valueAvailable Describes the number of that stock available
	 * @param valuePrice The price of the stock in GBP
	 * @param valueName The name of the stock
	 * @param valuePoundMultiplier; a value where when multiplied by the price, will get the value of the stock in the currency of the stock name
	 */
   
    public Forex(int valueAvailable, double valuePrice, String valueName, double valuePoundMultiplier) {
        super(valueAvailable, valuePrice, valueName);
        poundMultiplier = valuePoundMultiplier;
    }

		/**
		 * @return returns the pound multiplier
		 */
		public double getPoundMultiplier() {
			return poundMultiplier;
		}

		/**
		 * @param poundMultiplier sets the pound multiplier 
		 */
		public void setPoundMultiplier(double valuePoundMultiplier) {
			poundMultiplier = valuePoundMultiplier;
		}

}
