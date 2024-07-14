package controller.text;

import java.util.Scanner;

import controller.StockInformationCommands;
import model.IStock;
import view.text.IView;

/**
 * The controller.text.Moving class implements the controller.StockInformationCommands
 * interface to provide
 * functionality for calculating and displaying the x-day moving average of a stock
 * for a specified date.
 */
public class Moving implements StockInformationCommands {

  private IView vd;
  private Scanner in;

  /**
   * Constructs a controller.text.Moving instance with the specified view and scanner.
   *
   * @param vd the view interface for user interactions
   * @param in the scanner for user input
   */
  public Moving(IView vd, Scanner in) {
    this.vd = vd;
    this.in = in;
  }

  /**
   * Executes the command to calculate and display the x-day moving average for a stock
   * for a user-specified date. It prompts the user to enter the number of days (x)
   * and the date for which to calculate the moving average, then calculates and displays the
   * result.
   *
   * @param s the stock for which the x-day moving average is to be calculated
   */
  @Override
  public void goController(IStock s) {
    boolean quit = false;
    while (!quit) {
      vd.writeMessage("\nprovide us with the how many days or x-day "
              + "would like your x-day moving average to look back of stock "
              + s.getTicker() + " history\n");
      int x = vd.getValidInteger(in);
      vd.writeMessage("\n\nGive us the date that you would like "
              + "to know the stock " + s.getTicker() + " controller.text.Moving average of\n");
      try {
        vd.writeMessage("\nthe x-day moving average of the given date and x-day is  --- "
                + String.format("$%.2f", s.calculateXDayMovingAverage(vd.provideDate(in), x))
                + "\n\n");
      } catch (IllegalArgumentException e) {
        vd.writeMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
      vd.goBack();
      vd.writeMessage("\ninput any character other that b if you would " +
              "like to find the x-day moving average of another date and X-day\n");
      String j = in.next();
      if (j.equals("b")) {
        quit = true;
      }
    }
  }
}
