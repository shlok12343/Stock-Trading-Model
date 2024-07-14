package model;

import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

import view.text.IView;

/**
 * model.ISmartPortfolio provides methods to manage and analyze a smart stock portfolio.
 */
public interface ISmartPortfolio {

  /**
   * Prints the stocks in the portfolio as of the specified date.
   *
   * @param date the date to view the stocks in the portfolio
   * @return a string representation of the stocks in the portfolio on the given date
   */
  String printStocks(LocalDate date);

  /**
   * Returns the distribution of the portfolio's value on a specified date.
   *
   * @param date the date to calculate the distribution of value
   * @return a string showing the distribution of value on the specified date
   */
  String distributionOfValueOnDate(LocalDate date);

  /**
   * Rebalances the portfolio to match the specified target allocations as of a given date.
   *
   * @param in   the scanner for user input
   * @param date the date to perform the rebalance
   * @param view the view interface for user interactions
   */
  void rebalance(Scanner in, LocalDate date, IView view);

  /**
   * Provides the performance of the portfolio over a specified time period.
   *
   * @param start the start date of the time period
   * @param end   the end date of the time period
   * @return a string representation of the portfolio's performance over the given time period
   */
  String performanceOverTime(LocalDate start, LocalDate end);



  /**
   * Saves the current state of the portfolio to a file.
   *
   * @param filename the name of the file to save the portfolio to
   */
  void savePortfolio(String filename);

  /**
   * Loads the portfolio state from a file.
   *
   * @param file the file to load the portfolio from
   */
  void loadPortfolio(File file);

  /**
   * it gets the api for the use of loading stocks with the use of only the ticker.
   * @return APIInterface in the code.
   */
  APIInterface getApi();
}
