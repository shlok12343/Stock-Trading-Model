package model;

import java.time.LocalDate;
import java.util.TreeMap;

import model.IClient;

/**
 * Interface for creating and managing stock market models.
 * This interface provides methods to create stock objects with historical data
 * and to instantiate client profiles for portfolio management.
 */
public interface IModel {

  /**
   * make a stock with its information.
   *
   * @param stockHistory the history of the stocks closing prices
   * @param ticker       the ticker to identify the stock
   * @return The model.IStock
   */
  public IStock makeStock(TreeMap<LocalDate, Double> stockHistory, String ticker);


  /**
   * make a default client.
   *
   * @return the client
   */
  public IClient makeClient();


}
