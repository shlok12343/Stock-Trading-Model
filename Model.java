package model;

import java.time.LocalDate;
import java.util.TreeMap;

import model.IClient;

/**
 * The model.Model class provides an implementation of the model.IModel interface.
 * It is responsible for creating real instances of stocks and clients.
 */
public class Model implements IModel {

  /**
   * Creates a stock instance with the given historical data and ticker symbol.
   *
   * @param stockHistory a TreeMap containing the historical prices of the stock,
   *                     with LocalDate as the key and Double as the value representing the price
   * @param ticker       the stock ticker symbol
   * @return a stock instance with the provided historical data and ticker
   */
  @Override
  public IStock makeStock(TreeMap<LocalDate, Double> stockHistory, String ticker) {
    return new Stock(ticker, stockHistory);
  }

  /**
   * Creates a client instance with a default name.
   *
   * @return a client instance with the name "user1"
   */
  @Override
  public IClient makeClient() {
    return new Client("user1");
  }

}
