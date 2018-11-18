
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Work
 */
public class Work extends JFrame implements WindowListener, Serializable {

    private JButton loadSave;
    private JButton saveData;

    private JButton createShare;
    private JButton createForex;
    private JButton createCrypto;
    private JButton startSim;

    private JButton buyStock;
    private JButton sellStock;
    private JButton historyPrice;
    private JButton predictedPrice;
    private JButton changeDay;

    private JPanel textareaPanel;
    private JPanel buttonPanel;

    private JTextArea activity;

    private Portfolio port;

    public Work() {

        // set layout
        setLayout(new BorderLayout());
        // panel for startup, one for buttonPanel and one that displays all the shares
        // that will be added
        buttonPanel = new JPanel(new FlowLayout());
        textareaPanel = new JPanel(new FlowLayout());

        port = new Portfolio(0);

        // functionality for close button as well as frame parameters
        addWindowListener(this);
        setTitle("Stock Market simulator");
        setSize(800, 800);
        setVisible(true);

        // "start menu" with option to create shares.\\

        // button to do with initialising portfolio
        loadSave = new JButton("Load Save");
        createShare = new JButton("create share");
        createForex = new JButton("create forex");
        createCrypto = new JButton("create crypto");
        startSim = new JButton("start simulation");

        // buttons to do with buying/selling shares

        buyStock = new JButton("Buy Stock");
        sellStock = new JButton("Sell Stock");
        historyPrice = new JButton("View Historical pricing");
        changeDay = new JButton("Start Next Day");
        saveData = new JButton("Save portfolio");
        predictedPrice = new JButton("Predicted Price");

        // add buttons to do with initialising portfolio to panel
        buttonPanel.add(loadSave);
        buttonPanel.add(createShare);
        buttonPanel.add(createForex);
        buttonPanel.add(createCrypto);
        buttonPanel.add(startSim);

        // text area to see what stock objects have been created
        activity = new JTextArea(40, 150);

        // use a monospaced font so everything lines up
        activity.setFont(new Font("Monospaced", 0, 12));

        // set textarea editable to false
        activity.setEditable(false);

        // add text area to panel
        textareaPanel.add(activity);
        this.

        // actionlistner for initialising portfolio
        loadSave.addActionListener(new loadSaveActionListner());
        createShare.addActionListener(new createShareActionListner());
        createForex.addActionListener(new createForexActionListner());
        createCrypto.addActionListener(new createCryptoActionListner());
        startSim.addActionListener(new startSimActionListner());

        // actionlistner for buyingselling stock
        buyStock.addActionListener(new buyStockActionListner());
        sellStock.addActionListener(new sellStockActionListner());
        historyPrice.addActionListener(new historyPriceActionListner());
        predictedPrice.addActionListener(new predictedPriceActionListner());
        changeDay.addActionListener(new changeDayActionListner());
        saveData.addActionListener(new saveDataActionListner());

        // add panels to frame
        add(buttonPanel, BorderLayout.NORTH);
        add(textareaPanel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
        new Work();
    }

    /**
     * uses swing to create a pop up box which asks for user input
     * 
     * @param message the question that is asked to the user
     * @return the user input as string
     */
    public static String userInput(String message) {
        String in = JOptionPane.showInputDialog(message);
        return in;
    }

    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        return s;
    }

    public static String sharePicked(Portfolio p) {
        ArrayList<String> shareName = new ArrayList<String>();
        for (Stock i : p.getStockArrayList()) {
            shareName.add(i.getName());
        }
        String[] shareNameArray = new String[shareName.size()];

        for (int i = 0; i < shareNameArray.length; i++) {
            shareNameArray[i] = shareName.get(i);
        }
        ImageIcon icon = new ImageIcon();
        String s = (String) JOptionPane.showInputDialog((Component) null, "Pick the share you want", "Pick a share",
                JOptionPane.PLAIN_MESSAGE, icon, shareNameArray, shareName.get(0));

        return s;
    }

    public static void refreshSimScreen(JTextArea ta, Portfolio p) {
        ta.setText(null);
        ta.append("day " + p.getDay() + "\n\n");
        ta.append("Your balance is " + p.getBalance() + "\n\n");
        p.printAllStocks_textArea(ta);
    }

    public class loadSaveActionListner implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            try {
                FileInputStream fileIn = new FileInputStream("portfolio.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                port = (Portfolio) in.readObject();
                in.close();
                fileIn.close();
                JOptionPane.showMessageDialog((Component) null, "Your loading of save was successful",
                        "Load successful", JOptionPane.PLAIN_MESSAGE);
                port.printAllStocks_textArea(activity);
            } catch (IOException i) {
                i.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("Employee class not found");
                c.printStackTrace();
                return;
            }

            Work.refreshSimScreen(activity, port);
        }
    }

    public class createShareActionListner implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            int i1 = Integer.parseInt(userInput("how many shares at the initial public offering (int)"));
            double d2 = Double.parseDouble(userInput("price of share at initial public offering (double)"));
            String s3 = userInput("name of the share (String)");
            String s4 = userInput("which countries stock market does the company trade on (String)");
            port.createShare(i1, d2, s3, s4);

            activity.setText(null);
            port.printAllStocks_textArea(activity);
        }
    }

    public class createForexActionListner implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            int i1 = Integer.parseInt(userInput("how many forex at the initial public offering (int)"));
            double d2 = Double.parseDouble(userInput("price of forex at initial public offering (double)"));
            String s3 = userInput("name of the forex (String)");
            double d4 = Double.parseDouble(userInput("multiplier that converts this forex to pound(£)"));
            port.createForex(i1, d2, s3, d4);

            activity.setText(null);
            port.printAllStocks_textArea(activity);
        }
    }

    public class createCryptoActionListner implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            int i1 = Integer.parseInt(userInput("how many coins at the initial public offering (int)"));
            double d2 = Double.parseDouble(userInput("price of a single coin at initial public offering (double)"));
            String s3 = userInput("name of the coin (String)");
            double d4 = Double.parseDouble(userInput("what is the dificulty of the coin network (double)"));
            port.createCrypto(i1, d2, s3, d4);

            activity.setText(null);
            port.printAllStocks_textArea(activity);
        }

    }

    public class startSimActionListner implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            // remove text from textarea, remove buttons to do with initialising portfolio
            // add buttons to do with buying selling shares and all that stuff
            activity.setText(null);
            buttonPanel.removeAll();

            if (port.getBalance() == 0) {
                double bal = Double.parseDouble(userInput("how muc money do you want to invest in"));
                port.setBalance(bal);
            }

            // add buttons to do with buying/selling shares
            buttonPanel.add(buyStock);
            buttonPanel.add(sellStock);
            buttonPanel.add(historyPrice);
            buttonPanel.add(predictedPrice);
            buttonPanel.add(changeDay);
            buttonPanel.add(saveData);
            buttonPanel.revalidate();

            changeDay.doClick();

        }
    }

    public class buyStockActionListner implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String se;
            if ((se = Work.sharePicked(port)) != null) {
                int indexPos = port.nameToIndex(se);
                int numbuy = Integer.parseInt(userInput("how many shares of " + se + " are you buying?"));

                if (port.buy(indexPos, numbuy)) {
                    JOptionPane.showMessageDialog((Component) null, "Your purchase of  " + se + " was successful",
                            "Purchase successful", JOptionPane.PLAIN_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog((Component) null, "Your purchase of  " + se + " was unsuccessful",
                            "Purchase unsuccessful", JOptionPane.ERROR_MESSAGE);

                }

                Work.refreshSimScreen(activity, port);

            }

        }
    }

    public class sellStockActionListner implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String se;
            if ((se = Work.sharePicked(port)) != null) {
                int indexPos = port.nameToIndex(se);
                int numbuy = Integer.parseInt(userInput("how many shares of " + se + " are you selling?"));

                if (port.sell(indexPos, numbuy)) {
                    JOptionPane.showMessageDialog((Component) null, "Your sale of  " + se + " was successful",
                            "Sale successful", JOptionPane.PLAIN_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog((Component) null, "Your sale of  " + se + " was unsuccessful",
                            "Sale unsuccessful", JOptionPane.ERROR_MESSAGE);

                }

                Work.refreshSimScreen(activity, port);
            }
        }
    }

    public class historyPriceActionListner implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String se;
            if ((se = Work.sharePicked(port)) != null) {
                int indexPos = port.nameToIndex(se);
                port.historicalPricePrint_jtextarea(indexPos, activity);

            }

        }
    }

    public class predictedPriceActionListner implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String se;
            if ((se = Work.sharePicked(port)) != null) {
                int indexPos = port.nameToIndex(se);
                int whatday = Integer.parseInt(userInput("what day do you want a predicted price for?"));
                JOptionPane.showMessageDialog((Component) null,
                        "the predicted price of " + port.getStockArrayList().get(indexPos).getName() + " on day "
                                + whatday + " is " + port.predictPrice(indexPos, whatday),
                        "predicted price of " + port.getStockArrayList().get(indexPos).getName(),
                        JOptionPane.PLAIN_MESSAGE);

            }
        }
    }

    public class changeDayActionListner implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            port.changePriceAll();
            Work.refreshSimScreen(activity, port);

        }
    }

    public class saveDataActionListner implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            try {
                FileOutputStream fileOut = new FileOutputStream("portfolio.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(port);
                out.close();
                fileOut.close();
                JOptionPane.showMessageDialog((Component) null, "Your save was successful", "Save successful",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (IOException i) {
                i.printStackTrace();
            }

        }
    }

    // method to allow window to close
    public void windowClosing(WindowEvent evt) {
        System.exit(0); // Terminate the program
    }

    // Not Used, but need to provide an empty body to compile.
    public void windowOpened(WindowEvent evt) {
    }

    public void windowClosed(WindowEvent evt) {
    }

    public void windowIconified(WindowEvent evt) {
    }

    public void windowDeiconified(WindowEvent evt) {
    }

    public void windowActivated(WindowEvent evt) {
    }

    public void windowDeactivated(WindowEvent evt) {
    }
}