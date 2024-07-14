package controller.text;

import java.time.LocalDate;
import java.util.Scanner;

import controller.StockInformationCommands;
import model.IStock;
import view.text.IView;

/**
 * The controller.text.CalculateGorL class implements the controller.StockInformationCommands
 * interface to provide
 * functionality for calculating the gain or loss of a stock over a specified time period.
 */
public class CalculateGorL implements StockInformationCommands {
  IView vd;
  private Scanner in;

  /**
   * Constructs a controller.text.CalculateGorL instance with the specified view and scanner.
   *
   * @param vd the view interface for user interactions
   * @param in the scanner for user input
   */
  public CalculateGorL(IView vd, Scanner in) {
    this.vd = vd;
    this.in = in;
  }

  /**
   * Executes the command to calculate the gain or loss of a stock over a user-specified
   * time period.
   * It prompts the user to enter the start and end dates for the period and calculates the gain
   * or loss
   * based on the stock's performance during that period.
   *
   * @param s the stock for which the gain or loss is to be calculated
   */
  @Override
  public void goController(IStock s) {

    boolean quit = false;
    while (!quit) {
      vd.writeMessage("\nprovide us with the time period you would like to calculate the stock "
              + s.getTicker() + "gain or loss .\nStart Date:\n");
      LocalDate sd = vd.provideDate(in);
      vd.writeMessage("\nEnd Date: ");
      LocalDate ed = vd.provideDate(in);
      try {
        vd.writeMessage("\nthe gain or loss of the stock is  --- " +
                String.format("$%.2f", s.calculateGainLoss(sd, ed)));
        vd.writeMessage("\n(negative numbers are loss of that number "
                + "and positive numbers are gain of that number )\n");
      } catch (IllegalArgumentException e) {
        vd.writeMessage("Error: " + e.getMessage() + System.lineSeparator());
      }

      vd.goBack();
      vd.writeMessage("\ninput any character other that b if you would " +
              "like to find the gain or loss of another time period \n\n");
      String j = in.next();
      if (j.equals("b")) {
        quit = true;
      }
    }

  }
}
