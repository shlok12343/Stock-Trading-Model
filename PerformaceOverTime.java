package controller.text;

import java.time.LocalDate;
import java.util.Scanner;

import controller.StockInformationCommands;
import model.IStock;
import view.text.IView;


/**
 * The PerformaceOverTime class implements the StockInformationCommands interface.
 * It handles the user interaction for displaying the performance of a single
 * stock over a specified time period.
 * This class uses a text-based view and scanner for input.
 */
public class PerformaceOverTime implements StockInformationCommands {
  IView vd;
  private Scanner in;

  /**
   * Constructs a PerformaceOverTime instance with the specified view and scanner.
   * Initializes the view and input scanner for user interactions.
   *
   * @param vd the view interface for user interactions
   * @param in the scanner for user input
   */
  public PerformaceOverTime(IView vd, Scanner in) {
    this.vd = vd;
    this.in = in;
  }

  /**
   * Executes the control flow for calculating the performance of a stock over time.
   * Prompts the user for a start date and an end date, and displays the performance
   * of the stock over this period.
   *
   * @param m the stock model interface to operate on
   */
  @Override
  public void goController(IStock m) {
    vd.writeMessage("\nprovide us with the time period you would see Performace " +
            "Over Time of the stock "
            + "\nStart Date:\n");
    LocalDate sd = vd.provideDate(in);
    vd.writeMessage("\nEnd Date: ");
    LocalDate ed = vd.provideDate(in);
    vd.writeMessage(m.performanceOverTime(sd, ed));
  }
}
