package model;

import java.time.LocalDate;
import java.util.HashMap;

import model.Client;

/**
 * Interface representing a portfolio of stocks.
 */
public interface IPortfolio {
  /**
   * Adds a specified quantity of a stock to the portfolio.
   *
   * @param stock       the stock to be added
   * @param quantity    the quantity of the stock to be added
   * @param currentDate the current date
   */
  void addStock(IStock stock, double quantity, LocalDate currentDate);

  /**
   * Removes a specified quantity of a stock from the portfolio.
   *
   * @param stock       the stock to be removed
   * @param quantity    the quantity of the stock to be removed
   * @param currentDate the current date
   */
  void removeStock(IStock stock, double quantity, LocalDate currentDate);

  /**
   * Calculates the total value of the portfolio on a certain date.
   *
   * @param currentDate the certain date
   * @return the total value of the portfolio
   */
  Double calculatePortfolioValue(LocalDate currentDate);

  /**
   * Returns the portfolio's name.
   *
   * @return the name of the portfolio
   */
  String getName();

  /**
   * Returns the portfolio's stocks.
   *
   * @return the HashMap of stocks in the portfolio
   */
  HashMap<IStock, Double> getStocks(LocalDate l);


  /**
   * Returns the portfolio's owner.
   *
   * @return the model.Client that owns the portfolio
   */
  Client getOwner();

  /**
   * Calculates the value of a specific stock on a given date within the portfolio.
   *
   * @param s           the stock for which the value is calculated
   * @param currentDate the date on which the stock value is calculated
   * @return the calculated value of the stock
   */
  Double calculateStockValue(IStock s, LocalDate currentDate);


}