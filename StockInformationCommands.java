package controller;

import model.IStock;

/**
 * Interface for handling commands related to specific stock information within a stock
 * management system. This interface allows implementations to perform detailed operations
 * or queries on a specific stock object.
 */
public interface StockInformationCommands {
  /**
   * Executes a series of commands or operations related to the specified stock.
   * This method acts as a controller that can trigger different actions like retrieving
   * historical data, analyzing stock performance, or updating stock information based on user
   * inputs or automated processes.
   *
   * @param m The stock object on which operations will be performed. It is expected that
   *          this stock object implements the model.IStock interface and encapsulates all
   *          necessary data about the stock, such as its ticker symbol, historical
   *          price data, etc.
   */
  void goController(IStock m);
}
