package controller.text;

import java.util.Scanner;

import controller.IStockControllerCommands;
import controller.StockInformationCommands;
import model.APIInterface;
import model.IModel;
import model.Stock;
import view.text.IView;

/**
 * The controller.text.StockInformation class implements the controller.IStockControllerCommands
 * interface to provide
 * functionality for interacting with stock information. It allows the user to view and interact
 * with various stock metrics and statistics through a command interface.
 */
public class StockInformation implements IStockControllerCommands {


  private IView vd;
  private Scanner in;

  /**
   * Constructs a controller.text.StockInformation instance with the specified view and scanner.
   *
   * @param vd the view interface for user interactions
   * @param in the scanner for user input
   */
  public StockInformation(IView vd, Scanner in) {
    this.vd = vd;
    this.in = in;
  }

  /**
   * Starts the stock information controller, allowing the user to view stock information
   * and interact with various commands related to stock statistics.
   *
   * @param m the model interface for interacting with the stock data
   */
  @Override
  public void goController(IModel m) {
    APIInterface api = new model.AlphaVantageAPI();
    boolean quit = false;
    while (!quit) {
      vd.goBack();
      vd.showStockInfoMenu();
      String s = in.next();
      try {
        if (s.equals("b")) {
          quit = true;
        } else {
          if (api.tickerCSVToList().contains(s)) {
            try {
              stockCommands(api.makeStock(s));
            } catch (IllegalArgumentException e) {
              vd.writeMessage("Error: " + e.getMessage() + System.lineSeparator());
            }
          } else {
            vd.showOptionError();
            in.next();
          }
        }
      } catch (IllegalArgumentException e) {
        vd.writeMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    }
  }


  /**
   * Handles the execution of various stock commands based on user input.
   * It allows the user to interact with commands like viewing the closing price,
   * calculating gain/loss, finding the x-day moving average, and identifying x-day crossovers.
   *
   * @param s the stock for which the commands are to be executed
   */
  private void stockCommands(Stock s) {
    boolean quit = false;
    StockInformationCommands sid = null;
    while (!quit) {

      vd.writeMessage("\n");
      vd.goBack();
      vd.writeMessage("\n");
      vd.showStockStats(s.getTicker());
      String g = in.next();
      try {
        switch (g) {
          case "1":
            sid = new controller.text.ClosingPrice(vd, in);
            break;
          case "2":
            sid = new controller.text.CalculateGorL(vd, in);
            break;
          case "3":
            sid = new controller.text.Moving(vd, in);
            break;
          case "4":
            sid = new controller.text.Crossover(vd, in);
            break;
          case "5":
            sid = new controller.text.PerformaceOverTime(vd, in);
            break;
          case "b":
            quit = true;
            break;
          default:
            vd.showOptionError();
            vd.writeMessage("Error: Invalid stock symbol: " + s);
            in.next();
            break;
        }
        if (sid != null && !quit) {
          sid.goController(s);
        }
      } catch (IllegalArgumentException e) {
        vd.writeMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    }

  }
}