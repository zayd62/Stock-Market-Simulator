import java.io.*;
/**
 * the price class is an array which stores the price for the last seven days
 */
public class Price implements Serializable {

    /**
     * array which stores price for last 7 days
     * index 0 is oldest, index 6 is newest
     * index 6 should NEVER BE EMPTY unless the stock is less than a week old OR a new day has been simulated and you need to add the new days price to the array.
     * old prices should be deleted. 
     *
     */
    private double[] weekPrice;

    /**
     * constructor for objects of class Price
     */

    public Price() {
        weekPrice = new double[7];
    }

    /**
     * @return the weekPrice array
     */
    public double[] getWeekPriceArray() {
        return weekPrice;
    }

    /**
     * method to get price at certain index position. index 0 is oldest where as index 6 is latest.
     * returns -0.1 if errors cant be handled internally
     * @param position the index position to return. 
     */
    public double getWeekPrice(int position) {
        int pos = position;
        double x = -0.1;
        try {

            return weekPrice[pos];

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println(e + " :out of bounds (not a number between 0-6) input another number between 0-6");
            pos = Integer.parseInt(Work.getInput());
            return x;
        } catch (Exception e) {

            System.out.println("Warning: Some Other exception  input another number between 0-6 ");
            pos = Integer.parseInt(Work.getInput());
            return x;

        }
    }

    /**
     * method to set new price.
     * also shuffles elements to the left. eg: index 1 becomes 0 and index 0 is deleted 
     * @param last integer that will be the last element in the array
     */
    public void setPrice(double last) {
        for (int i = 1; i < 7; i++) {
            weekPrice[i - 1] = weekPrice[i];

        }
        weekPrice[6] = last;

    }

}