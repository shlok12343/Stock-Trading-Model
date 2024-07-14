package controller.text;

import java.time.LocalDate;
import java.util.Scanner;

import controller.IPortfolioOptions;
import model.SmartPortfolio;
import view.text.IView;

/**
 * The PortfolioOptions class implements the IPortfolioOptions interface to manage
 * portfolio operations within the stock management application. It provides various
 * functionalities allowing the user to view, add, remove, rebalance stocks, and
 * analyze portfolio performance.
 */
public class PortfolioOptions implements IPortfolioOptions {
  IView v;
  private Scanner in;

  /**
   * Constructs a PortfolioOptions instance with dependencies for input and output.
   *
   * @param in The scanner to read user input.
   * @param v  The view interface to display outputs to the user.
   */
  public PortfolioOptions(Scanner in, IView v) {
    this.v = v;
    this.in = in;
  }

  /**
   * Starts the portfolio operation interface, allowing the user to perform various
   * actions on a portfolio such as viewing its value, adding or removing stocks,
   * rebalancing, and viewing performance over time.
   *
   * @param p The portfolio on which operations are to be performed.
   */
  @Override
  public void portfolioOperation(SmartPortfolio p) {
    boolean quit = false;
    while (!quit) {


      v.goBack();
      v.showPortfolioOptions();
      String s = in.next();
      LocalDate l;
      try {
        switch (s) {
          case "b":
            quit = true;
            break;
          case "1":
            l = v.provideDate(in);
            v.writeMessage("The value of your portfolio is "
                    + String.format("$%.2f", p.calculatePortfolioValue(l)));
            break;
          case "2":
            l = v.provideDate(in);
            String t = "";
            while (t.isEmpty()) {
              v.writeMessage("Stock Ticker: ");
              s = in.next();
              if (p.getApi().tickerCSVToList().contains(s)) {
                try {
                  t = s;
                  v.writeMessage("quantity:");
                  int i = v.getValidInteger(in);
                  p.addStock(p.getApi().makeStock(s), i, l);
                  v.writeMessage("the stock " + t + " is ADDED for shares " + i);
                } catch (IllegalArgumentException e) {
                  v.writeMessage("Error: " + e.getMessage() + System.lineSeparator());
                }
              } else {
                v.writeMessage("Invalid Ticker,provide a valid stock ticker");
              }
            }

            break;
          case "3":
            l = v.provideDate(in);
            String t1 = "";
            while (t1.isEmpty()) {
              v.writeMessage("Stock Ticker: ");
              s = in.next();
              if (p.getApi().tickerCSVToList().contains(s)) {
                try {
                  t1 = s;
                  v.writeMessage("quantity:");
                  int i = v.getValidInteger(in);
                  p.removeStock(p.getApi().makeStock(s), i, l);
                  v.writeMessage("the stock " + t1 + " is removed for shares " + i);
                } catch (IllegalArgumentException e) {
                  v.writeMessage("Error: " + e.getMessage() + System.lineSeparator());
                }
              } else {
                v.writeMessage("Invalid Ticker,provide a valid stock ticker");
              }
            }
            break;
          case "4":
            l = v.provideDate(in);
            p.rebalance(in, l, v);
            break;
          case "5":
            l = v.provideDate(in);
            v.writeMessage(p.printStocks(l));
            break;
          case "6":
            v.writeMessage("\nprovide us with the time period you would like to "
                    + "see performance overtime the stock. \nStart Date:\n");
            LocalDate sd = v.provideDate(in);
            v.writeMessage("\nEnd Date:\n ");
            LocalDate ed = v.provideDate(in);
            v.writeMessage(p.performanceOverTime(sd, ed));
            break;
          case "7":
            l = v.provideDate(in);
            v.writeMessage(p.distributionOfValueOnDate(l));
            break;
          default:
            v.showOptionError();
            in.next();
            break;
        }
      } catch (IllegalArgumentException e) {
        v.writeMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    }
  }
}
