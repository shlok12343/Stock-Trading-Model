package controller.text;

import java.time.LocalDate;
import java.util.Scanner;

import controller.StockInformationCommands;
import model.IStock;
import view.text.IView;

/**
 * The controller.text.Crossover class implements the controller.StockInformationCommands
 * interface to provide
 * functionality for calculating and displaying the x-day crossover dates of a stock
 * over a specified time period.
 */
public class Crossover implements StockInformationCommands {

  private IView vd;
  private Scanner in;

  /**
   * Constructs a controller.text.Crossover instance with the specified view and scanner.
   *
   * @param vd the view interface for user interactions
   * @param in the scanner for user input
   */
  public Crossover(IView vd, Scanner in) {
    this.vd = vd;
    this.in = in;
  }


  /**
   * Executes the command to calculate and display the x-day crossover dates for a stock
   * over a user-specified time period. It prompts the user to enter the number of days (x),
   * the start date, and the end date for the period, then calculates and displays the crossover
   * dates.
   *
   * @param s the stock for which the x-day crossover dates are to be calculated
   */
  @Override
  public void goController(IStock s) {
    boolean quit = false;
    while (!quit) {
      vd.writeMessage("\nprovide us with the how many days or x-day "
              + "would like your x-day crossover to look back at of stock "
              + s.getTicker() + " history\n");
      int x = vd.getValidInteger(in);
      vd.writeMessage("\nprovide us with the time period you would like the stock "
              + s.getTicker() + " x-day crossover.\nStart Date:\n");
      LocalDate sd = vd.provideDate(in);
      vd.writeMessage("\nEnd Date: ");
      LocalDate ed = vd.provideDate(in);


      try {
        vd.writeMessage("\nthe dates that there is an x-day crossovers of the give time " +
                "period and x-days are  ---\n");
        vd.getListCrossovers(s.findXDayCrossover(sd, ed, x));
      } catch (IllegalArgumentException e) {
        vd.writeMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
      vd.writeMessage("\n");
      vd.goBack();
      vd.writeMessage("\ninput any character other that b if you would " +
              "like to find the x-day crossover average of another time period and X-day\n\n");
      String j = in.next();
      if (j.equals("b")) {
        quit = true;
      }
    }



  }
}
