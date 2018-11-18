import java.io.*;

public class Crypto extends Stock implements Serializable{
    /** 
     * the dificulty
     */
    private double dificulty;

    /**
     * Constructor for object of class share. Share is subclass of Stock
     * @param valueAvailable Describes the number of that stock available
     * @param valuePrice The price of the stock in GBP
     * @param valueName The name of the stock
     * @param valuedificulty the difficulty of the network. 
     */
    public Crypto(int valueAvailable, double valuePrice, String valueName, double valuedificulty) {
        super(valueAvailable, valuePrice, valueName); //calls superclass constructor
        dificulty = valuedificulty;
    }

    /**
     * @return returns the maximum number of coins left
     */
    public double getdificulty() {
        return dificulty;
    }

    /**
     * @param dificulty
      sets the market cap
     */
    public void setdificulty(double valuedificulty) {
        dificulty = valuedificulty;
    }


    

}
