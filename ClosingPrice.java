package controller.text;

import java.util.Scanner;

import controller.StockInformationCommands;
import model.IStock;
import view.text.IView;

/**
 * The controller.text.ClosingPrice class implements the controller.StockInformationCommands
 * interface to provide
 * functionality for fetching and displaying the closing price of a stock on a specified date.
 */
public class ClosingPrice implements StockInformationCommands {

  IView vd;
  private Scanner in;

  /**
   * Constructs a controller.text.ClosingPrice instance with the specified view and scanner.
   *
   * @param vd the view interface for user interactions
   * @param in the scanner for user input
   */
  public ClosingPrice(IView vd, Scanner in) {
    this.vd = vd;
    this.in = in;
  }

  /**
   * Executes the command to fetch and display the closing price of a stock on a user-specified
   * date.
   * It prompts the user to enter a date and retrieves the closing price of the stock for that
   * date.
   *
   * @param s the stock for which the closing price is to be fetched
   */
  @Override
  public void goController(IStock s) {

    boolean quit = false;
    while (!quit) {
      vd.writeMessage("Give us the date that you would like "
              + "to know the stock " + s.getTicker() + " closing price\n");
      try {
        vd.writeMessage("\nthe closing price of the give date is  --- " +
                String.format("$%.2f", s.getClosingPrice(vd.provideDate(in))) + "\n\n");
      } catch (IllegalArgumentException e) {
        vd.writeMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
      vd.goBack();
      vd.writeMessage("\ninput any character other that b if you would " +
              "like to find the closing price of another date\n\n");
      String j = in.next();
      if (j.equals("b")) {
        quit = true;
      }
    }

  }

}
