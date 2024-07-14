package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Interface representing stock.
 */
public interface IStock {

  /**
   * Allow a user to examine the x-day moving average of a stock for a specified date
   * and a specified value of x.
   *
   * @param date the date from when they want the moving average.
   * @param xDay the last x_day they want the stock average.
   * @return the value of the last x day average of the stock.
   */
  double calculateXDayMovingAverage(LocalDate date, int xDay);


  /**
   * Allow a user to examine the gain or loss of a stock over a specified period.
   *
   * @param startDate the start date of calculate gain or loss.
   * @param endDate   the end date of calculate gain or loss.
   * @return the value of gain or loss of stock.
   */
  double calculateGainLoss(LocalDate startDate, LocalDate endDate);

  /**
   * Allow a user to determine which days are x-day crossovers for a specified stock
   * over a specified date range and a specified value of x.
   *
   * @param startDate the start date of the date you would like to search from.
   * @param endDate   the end date of the date you would like to search from.
   * @param xDay      the last x-days moving average you want to use for crossover
   * @return list of dates the there has been an x-day cross`over in.
   */
  ArrayList<LocalDate> findXDayCrossover(LocalDate startDate, LocalDate endDate, int xDay);

  /**
   * adds a new date and price to the stock history it only takes dates
   * after the last date given in the stock history.
   *
   * @param price   the price of the stock on that day.
   * @param endDate the date of that price of the stock.
   * @throws IllegalArgumentException if not given latest data on stock.
   */
  void addClosing(double price, LocalDate endDate);

  /**
   * the closing price at that date.
   *
   * @param endDate the date we want the closing price of.
   * @return the closing price of the given date.
   */
  double getClosingPrice(LocalDate endDate);

  /**
   * getter for the ticker of the stock.
   *
   * @return the ticker of the stock
   */
  String getTicker();


  /**
   * Analyzes performance over time for a specific stock and returns a graph that visualizes
   * the performance.
   *
   * @param start start date
   * @param end   end date
   * @return string of graph
   */
  String performanceOverTime(LocalDate start, LocalDate end);

}
