package controller.text;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeMap;

import controller.IStockController;
import controller.IStockControllerCommands;

import model.IModel;
import model.RetrivableClient;
import model.SmartPortfolio;
import model.Stock;
import view.text.IView;

/**
 * The CreatePortfolio class handles the user interactions for creating a new portfolio.
 * It allows the user to specify the name of the new portfolio and add stocks to it.
 */
public class CreatePortfolio implements IStockControllerCommands {
  IStockController user;
  IView v;
  private Scanner in;
  IStockControllerCommands cmd = null;


  /**
   * Constructs a new CreatePortfolio instance with the specified view, input scanner, and client.
   *
   * @param v  the view to interact with the user
   * @param in the scanner for user input
   */
  public CreatePortfolio(IView v, Scanner in, IStockController user) {
    this.v = v;
    this.user = user;
    this.in = in;
  }

  /**
   * Executes the portfolio creation process.
   * Displays a menu for creating a new portfolio, prompts the user for the portfolio name, and
   * allows the user to add stocks to the portfolio.
   * Validates stock ticker symbols and fetches historical stock data using the AlphaVantageAPI.
   *
   * @param m the model to operate on
   */
  public void goController(IModel m) {

    boolean quit = false;
    while (!quit) {
      v.goBack();
      v.showCreatePortfolio();

      String s = in.next();
      RetrivableClient c = (RetrivableClient) user.getUser();
      SmartPortfolio p = (SmartPortfolio) c.createPortfolio(s);
      user.setClient(c);
      if (!Objects.equals(s, "b")) {
        v.goBack();
        v.showAddStocks();
        v.writeMessage("Ticker:");
        s = in.next();
      }
      if (s.equals("b")) {
        quit = true;
      } else {
        String t = "";
        while (t.isEmpty()) {
          if (!p.getApi().tickerCSVToList().contains(s)) {
            v.showOptionError();
            v.writeMessage("Error: Invalid stock symbol: " + s);
          } else {
            t = s;
          }
        }
        v.writeMessage("Quantity:");
        int quant = v.getValidInteger(in);
        TreeMap<LocalDate, Double> stockHistory = p.getApi().fetchStockHistory(
                p.getApi().makeCSVFile("TIME_SERIES_DAILY", t));
        v.writeMessage("What's today's date?");
        Scanner sc = new Scanner(System.in);
        LocalDate l = v.provideDate(sc);
        p.addStock(new Stock(t, stockHistory), quant, l);
        v.writeMessage("Successfully added " + s + " " + t + " stocks in " +
                p.getName() + "\n");
      }


    }
  }
}
