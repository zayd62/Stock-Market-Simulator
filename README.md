# Welcome to stock market simulator!

Stock Market Simulator written in Java with a GUI. Feature include:

 - Create three different types of stocks:
	 - Share
	 - Currency (Forex)
	 - Crypto-currency (eg Bitcoin)
 - Randomly changing prices
 - Ability to save state
 - Predict future Prices

## How to run

>tested on Oracle JDK 8 on Ubuntu 18.04.1 LTS
><ul>
>  <li>java version "1.8.0_181"</li>
>  <li>Java(TM) SE Runtime Environment (build 1.8.0_181-b13)</li>
>  <li>Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)</li>
>  <li>javac 1.8.0_181</li>
></ul>

1. Download repository
2. navigate to src using terminal
3. run the following command in terminal (note, the following command is guaranteed to work on tested configuration as mentioned above, no guarantee on other configurations.) ```find -name "*.java" > sources.txt && javac -verbose @sources.txt && java Work```
4. if the command above does not work, then compile all the ```.java``` files and run ```java Work```

## How to use

After running ```java Work```, maximise the window that opens, create stocks of type:
 - Share
 - Currency (Forex)
 - Crypto-currency (eg Bitcoin)
 
 once you have created all of your stocks, you press start simulation
 
you then have options on top to buy and sell stocks, view historical pricing of a stock, predict pricing of stock, save portfolio (simulator state) and start next day. staring next day will change the price of the stocks. 
