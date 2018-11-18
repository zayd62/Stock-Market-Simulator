import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.io.*;

/**
 * the portfolio class creates a portfolio which stores stock subclasses and deals with buying and selling stocks
 * also deals with simulating a day where stock prices changejjdgsg
 */

public class Portfolio implements Serializable {

    /**
     *arraylist of stocks the user has bought. 
     */
    private ArrayList<Stock> stockArrayList;

    /**
     * how many days this portfolio has been active for
     */
    private int day;

    /**the amount of cash the portfolio has.  */
    private double balance;

    /**
     * constructor for portfolio. creates an array list of type stock and a balance
     * @param balance the starting balance to buy stocks with.
     */
    public Portfolio(double balance) {

        stockArrayList = new ArrayList<Stock>();
        this.balance = balance;
        day = 0;

    }

    /**
     * @return the stockArrayList arraylist
     */
    public ArrayList<Stock> getStockArrayList() {
        return stockArrayList;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
    * @return the day
    */
    public double getDay() {
        return day;
    }

    /**
     * @param balance the balance to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * method to create a share stock and add to portfolio
     * @param i1 how many shares available at the inital public offering
     * @param d2 the price of the share in pounds
     * @param s3 the name of the share
     * @param s4 the country of origin of the share
     */
    public void createShare(int i1, double d2, String s3, String s4) {

        Stock stock1 = new Share(i1, d2, s3, s4);
        stockArrayList.add(stock1);

    }

    /**
     * method to create a forex and add to portfolio
     * @param i1 how many forex available at the inital public offering
     * @param d2 the price of the currency in pounds
     * @param s3 the name of the currency
     * @param d4 the multiplier that converts pound to currency
     */

    public void createForex(int i1, double d2, String s3, double d4) {

        Stock forex1 = new Forex(i1, d2, s3, d4);
        stockArrayList.add(forex1);

    }

    /**
     * method to create a crypto and add to portfolio
     * @param i1 how many coins available at the inital public offering
     * @param d2 the price of the crypto in pounds
     * @param s3 the name of the crypto
     * @param d4 the difficulty
     */
    public void createCrypto(int i1, double d2, String s3, double d4) {

        Stock crypto1 = new Crypto(i1, d2, s3, d4);
        stockArrayList.add(crypto1);

    }

    /**
     * method to change price for a single stock. used by change all price to simulate a day of stocks changing. 
     * 
     * @param index starts at what index position in the ArrayList to change the price on. combined with arraylist.get will return a stock object
     */
    public void changePrice(int index) {
        int pos = index;
        Stock s1 = stockArrayList.get(pos);
        OUTER_LOOP: while (true) {
            double genNewPrice = Stock.newPrice(s1);
            if (((s1.getPrice().getWeekPrice(6) + genNewPrice) > 0)) {
                genNewPrice = Double.parseDouble(String.format("%.02f", (s1.getPrice().getWeekPrice(6) + genNewPrice)));
                s1.getPrice().setPrice(genNewPrice);
                break OUTER_LOOP;

            } else {
                genNewPrice = Stock.newPrice(s1);

            }

        }
    }

    /**
     * takes the arraylist and changes all the stock prices. iterates over arraylist and uses {@link #changePrice}.
     * this is used to simulate a day
     */
    public void changePriceAll() {
        for (int i = 0; i < stockArrayList.size(); i++) {
            changePrice(i);

        }
        day = day + 1;

    }

    /**
     * method that allows you to buy shares. returns boolean if purchase was successful
     * @param index the index position in the array list that the stock is in. starts at 0
     * @param num number of shares 
     * @return a boolean, true if purchase is successful false if purchased failed, usually not enough funds or not enough stock to purchase
     */
    public boolean buy(int index, int num) {
        Stock s1 = stockArrayList.get(index);
        if (num + s1.getAvailable() > s1.getInitialAvailable()) {
            //check if there is enough shares to acutally buy. 
            //if 10 + 5 > 10, there is more stock being bought than available 
            System.out.println("buying more stock than available");
            return false;

        } else if (balance - (s1.getPrice().getWeekPrice(6) * num) > 0) {
            //checks if you have enough money
            //2000 - (25 * 10) > 0, means that after buying, you have a positive balance meaning purchase was successful
            balance = balance - (s1.getPrice().getWeekPrice(6) * num);
            s1.setAvailable((s1.getAvailable() + num));
            System.out.println("you have purchased " + num + " stock of " + s1.getName());
            return true;

        } else {
            System.out.println("not enough money");
            return false;

        }

    }

    /**
     * method that allows you to sell shares
     * @param index the index position in the array list that the stock is in
     * @param num the number that you want to sell
     * @return a boolean to determine if the sale was successfull
     */
    public boolean sell(int index, int num) {
        Stock s1 = stockArrayList.get(index);
        if ((s1.getAvailable() >= num)) {
            //checks that you have enough to sell
            //5 > 4
            s1.setAvailable((s1.getAvailable() - num));
            balance = balance + (s1.getPrice().getWeekPrice(6) * num);
            return true;

        } else {
            System.out.println("you are selling stock you dont have");
            return false;

        }

    }

    /**
     * (COMMAND LINE)method to print all shares, forex and crypto in a neat table.
     * 
     */
    public void printAllStocks_command() {
        System.out.println("");
        System.out.println(String.format("%-25s", "Index") + String.format("%-25s", "Name")
                + String.format("%-25s", "Price") + String.format("%-25s", "Available")
                + String.format("%-25s", "Available to purchase") + String.format("%-25s", "Country"));
        int shareCount = 0;
        for (Stock i : stockArrayList) {
            if (i instanceof Share) {
                System.out.println(
                        String.format("%-25s", Integer.toString(shareCount)) + String.format("%-25s", i.getName())
                                + String.format("%-25s", Double.toString(i.getPrice().getWeekPrice(6)))
                                + String.format("%-25s", Integer.toString(i.getAvailable()))
                                + String.format("%-25s", Integer.toString(i.getInitialAvailable()))
                                + String.format("%-25s", ((Share) i).getCountry()));

            }
            shareCount = shareCount + 1;

        }

        System.out.println("");
        System.out.println(String.format("%-25s", "Index") + String.format("%-25s", "Name")
                + String.format("%-25s", "Price") + String.format("%-25s", "Available")
                + String.format("%-25s", "Available to purchase") + String.format("%-25s", "Multiplier"));
        int forexCount = 0;
        for (Stock i : stockArrayList) {
            if (i instanceof Forex) {
                System.out.println(
                        String.format("%-25s", Integer.toString(forexCount)) + String.format("%-25s", i.getName())
                                + String.format("%-25s", Double.toString(i.getPrice().getWeekPrice(6)))
                                + String.format("%-25s", Integer.toString(i.getAvailable()))
                                + String.format("%-25s", Integer.toString(i.getInitialAvailable()))
                                + String.format("%-25s", Double.toString(((Forex) i).getPoundMultiplier())));

            }
            forexCount = forexCount + 1;

        }

        System.out.println("");
        System.out.println(String.format("%-25s", "Index") + String.format("%-25s", "Name")
                + String.format("%-25s", "Price") + String.format("%-25s", "Available")
                + String.format("%-25s", "Available to purchase") + String.format("%-25s", "Difficulty"));
        int cryptoCount = 0;
        for (Stock i : stockArrayList) {
            if (i instanceof Crypto) {
                System.out.println(
                        String.format("%-25s", Integer.toString(cryptoCount)) + String.format("%-25s", i.getName())
                                + String.format("%-25s", Double.toString(i.getPrice().getWeekPrice(6)))
                                + String.format("%-25s", Integer.toString(i.getAvailable()))
                                + String.format("%-25s", Integer.toString(i.getInitialAvailable()))
                                + String.format("%-25s", Double.toString(((Crypto) i).getdificulty())));

            }
            cryptoCount = cryptoCount + 1;

        }

    }

    /**
    * (text area)method to print all shares, forex and crypto in a neat table.
    * @param ta textarea that will be printed to
    */
    public void printAllStocks_textArea(JTextArea ta) {
        String newln = "\n";
        ta.append("" + newln);
        ta.append(String.format("%-25s", "Index") + String.format("%-25s", "Name") + String.format("%-25s", "Price")
                + String.format("%-25s", "Available") + String.format("%-25s", "Available to purchase")
                + String.format("%-25s", "Country") + newln);
        int shareCount = 0;
        for (Stock i : stockArrayList) {
            if (i instanceof Share) {
                ta.append(String.format("%-25s", Integer.toString(shareCount)) + String.format("%-25s", i.getName())
                        + String.format("%-25s", Double.toString(i.getPrice().getWeekPrice(6)))
                        + String.format("%-25s", Integer.toString(i.getAvailable()))
                        + String.format("%-25s", Integer.toString(i.getInitialAvailable()))
                        + String.format("%-25s", ((Share) i).getCountry()) + newln);

            }
            shareCount = shareCount + 1;

        }

        ta.append("" + newln);
        ta.append(String.format("%-25s", "Index") + String.format("%-25s", "Name") + String.format("%-25s", "Price")
                + String.format("%-25s", "Available") + String.format("%-25s", "Available to purchase")
                + String.format("%-25s", "Multiplier") + newln);
        int forexCount = 0;
        for (Stock i : stockArrayList) {
            if (i instanceof Forex) {
                ta.append(String.format("%-25s", Integer.toString(forexCount)) + String.format("%-25s", i.getName())
                        + String.format("%-25s", Double.toString(i.getPrice().getWeekPrice(6)))
                        + String.format("%-25s", Integer.toString(i.getAvailable()))
                        + String.format("%-25s", Integer.toString(i.getInitialAvailable()))
                        + String.format("%-25s", Double.toString(((Forex) i).getPoundMultiplier())) + newln);

            }
            forexCount = forexCount + 1;

        }

        ta.append("" + newln);
        ta.append(String.format("%-25s", "Index") + String.format("%-25s", "Name") + String.format("%-25s", "Price")
                + String.format("%-25s", "Available") + String.format("%-25s", "Available to purchase")
                + String.format("%-25s", "Difficulty") + newln);
        int cryptoCount = 0;
        for (Stock i : stockArrayList) {
            if (i instanceof Crypto) {
                ta.append(String.format("%-25s", Integer.toString(cryptoCount)) + String.format("%-25s", i.getName())
                        + String.format("%-25s", Double.toString(i.getPrice().getWeekPrice(6)))
                        + String.format("%-25s", Integer.toString(i.getAvailable()))
                        + String.format("%-25s", Integer.toString(i.getInitialAvailable()))
                        + String.format("%-25s", Double.toString(((Crypto) i).getdificulty())) + newln);

            }
            cryptoCount = cryptoCount + 1;

        }
        ta.append(newln);

    }

    /**
     * method that prints the historical prices stored of a stock at a certain index
     * @param index the index position of the stock you want to print
     *  
     */
    public void historicalPricePrint(int index) {
        Stock s1 = stockArrayList.get(index);
        String priceConcatenated = "";
        for (int i = 0; i < 7; i++) {
            priceConcatenated = priceConcatenated
                    + String.format("%-7s", Double.toString(s1.getPrice().getWeekPrice(i)));
        }
        System.out.println(s1.getName() + priceConcatenated);

    }

    public void historicalPricePrint_jtextarea(int index, JTextArea ta) {
        Stock s1 = stockArrayList.get(index);
        String priceConcatenated = "";
        for (int i = 0; i < 7; i++) {
            priceConcatenated = priceConcatenated
                    + String.format("%-7s", Double.toString(s1.getPrice().getWeekPrice(i)));
        }
        JOptionPane.showMessageDialog((Component) null, s1.getName() + " " + priceConcatenated,
                "Historical price of " + s1.getName(), JOptionPane.PLAIN_MESSAGE);

    }

    /**
     * method to calculate a predicted price using linear regression
     * @param index the index position in the arraylist for the stock you want
     * @param day how many days into the future you want to predict the price
     * @return the predicted price
     */

    public double predictPrice(int index, int day) {
        //see linear_"regression formulas.png" on how it works 
        //x is days, y is price n is 7
        double[] pricelist = stockArrayList.get(index).getPrice().getWeekPriceArray();
        int[] days = { 6, 5, 4, 3, 2, 1, 0 };
        int n = 7;

        //calculating sumx
        double sumx = 0;
        for (int i : days) {
            sumx = sumx + i;
        }

        //calculating sumy
        double sumy = 0;
        for (double j : pricelist) {
            sumy = sumy + j;
        }

        //calculating sumxsquared
        double sumxsquared = 0;
        for (int k : days) {
            sumx = sumx + (Math.pow(k, 2));
        }

        //calculating sumxy
        double sumxy = 0;
        for (int l = 0; l < 7; l++) {
            sumxy = sumxy + (days[l] * pricelist[l]);
        }

        //calculating xbar
        double xbar = sumx / n;

        //calculating ybar
        double ybar = sumy / n;

        //calculating sxy
        double sxy = sumxy - ((sumx * sumy) / n);

        //calculating sxx
        double sxx = sumxsquared - (Math.pow(sumx, 2) / n);

        //calculating b
        double b = sxy / sxx;

        //calculating a
        double a = ybar - (b * xbar);

        //return predicted value

        double predictedPrice = a + (b * (day * 1.0));

        predictedPrice = Double.parseDouble(String.format("%.02f", predictedPrice));

        return predictedPrice;
    }

    /**
     * method for converting name into an index positon. returns -1 if name not found.
     * @param name the name of the share. this is the value returned by {@link Stock#getName()}
     * @return an int with the index position of the Stock ni stockArrayList
     */
    public int nameToIndex(String name) {
        for (int i = 0; i < stockArrayList.size(); i++) {
            if ((stockArrayList.get(i).getName()).equals(name)) {
                return i;

            }

        }
        return -1;
    }

}