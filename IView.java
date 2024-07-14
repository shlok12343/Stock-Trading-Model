package view.text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import model.Client;
import model.SmartPortfolio;

/**
 * Interface defining the methods for user interaction within a stock management application.
 * This interface facilitates the display of user interfaces, handling of user inputs, and
 * presentation of relevant data back to the user.
 */
public interface IView {
  /**
   * Displays the welcome screen to the user at the start of the application.
   */
  void welcomeScreen();

  /**
   * Allows the user to navigate back to the previous menu or screen.
   */
  void goBack();

  /**
   * Displays the main menu options to the user.
   */
  void showMainMenu();

  /**
   * Displays the stock information menu where users can choose to view different
   * stock-related operations.
   */
  void showStockInfoMenu();

  /**
   * Displays the portfolio management menu, allowing users to manage their stock portfolios.
   */
  void showManagePortfolioMenu();

  /**
   * Displays detailed statistics for a specific stock identified by its ticker symbol.
   *
   * @param ticker The ticker symbol of the stock whose statistics are to be displayed.
   */
  void showStockStats(String ticker);

  /**
   * Provides an interface for creating a new stock portfolio.
   */
  void showCreatePortfolio();

  /**
   * Displays all existing portfolios for a specific client.
   *
   * @param c The client whose portfolios are to be viewed.
   */
  void viewExistingPortfolios(Client c);

  /**
   * Shows detailed information about a specific portfolio on a given date.
   *
   * @param p The portfolio to view.
   * @param l The date for which portfolio information is required.
   */
  void showSpecificPortfolio(SmartPortfolio p, LocalDate l);

  /**
   * Displays a general message to the user.
   *
   * @param s The message to display.
   */
  void writeMessage(String s);

  /**
   * Converts a double value to its String representation, typically for display.
   *
   * @param d The double value to convert.
   * @return The string representation of the double value.
   */
  String doubleToString(Double d);

  /**
   * Displays an error message when the user selects an invalid option.
   */
  void showOptionError();

  /**
   * Provides an interface for adding stocks to a portfolio.
   */
  void showAddStocks();

  /**
   * Prompts the user to provide a date using a scanner.
   *
   * @param in The scanner object used for input.
   * @return The date entered by the user.
   */
  LocalDate provideDate(Scanner in);

  /**
   * Retrieves a valid integer input from the user using a scanner.
   *
   * @param in The scanner object used for input.
   * @return The valid integer entered by the user.
   */
  int getValidInteger(Scanner in);

  /**
   * Displays a list of dates where crossovers in stock indicators (like moving averages)
   * have occurred.
   *
   * @param a The list of LocalDate objects representing the crossover dates.
   */
  void getListCrossovers(ArrayList<LocalDate> a);

  /**
   * Displays options related to portfolio management, such as viewing or editing portfolios.
   */
  void showPortfolioOptions();



}