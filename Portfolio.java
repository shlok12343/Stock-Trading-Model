package model;

import java.time.LocalDate;

import java.util.HashMap;

import java.util.Map;

import java.util.TreeMap;

//import static jdk.internal.org.jline.utils.Colors.s;

/**
 * The model.Portfolio class represents a collection of stocks owned by a client.
 * Each portfolio has a name, an owner, and a collection of stocks with their quantities.
 */
public class Portfolio implements IPortfolio {

  protected Client owner;
  protected String name;
  protected HashMap<IStock, TreeMap<LocalDate, Double>> stocks;

  /**
   * Constructs a new model.Portfolio with the specified name and owner.
   *
   * @param portfolioName the name of the portfolio
   * @param owner         the client who owns this portfolio
   */
  public Portfolio(String portfolioName, Client owner) {
    this.owner = owner;
    this.name = portfolioName;
    this.stocks = new HashMap<IStock, TreeMap<LocalDate, Double>>();
  }

  /**
   * Adds the specified quantity of a stock to the portfolio.
   * If the stock is already in the portfolio, its quantity is increased by the specified amount.
   * Otherwise, the stock is added to the portfolio with the specified quantity.
   *
   * @param stock    the stock to add
   * @param quantity the quantity of the stock to add
   */
  @Override
  public void addStock(IStock stock, double quantity, LocalDate currentDate) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("You cannot add zero or negative stocks.");
    }

    if (this.stocks.containsKey(stock)) {
      if (this.stocks.get(stock).containsKey(currentDate)) {
        double currentQuantity = this.stocks.get(stock).get(currentDate);
        this.stocks.get(stock).put(currentDate, quantity + currentQuantity);
      } else {
        this.stocks.get(stock).put(currentDate, quantity);
      }
    } else {
      TreeMap<LocalDate, Double> newTree = new TreeMap<>();
      newTree.put(currentDate, quantity);
      this.stocks.put(stock, newTree);
    }
  }

  /**
   * Removes the specified quantity of a stock from the portfolio.
   * If the quantity to remove is greater than or equal to the current quantity,
   * the stock is removed from the portfolio.
   * Otherwise, the stock's quantity is decreased by the specified amount.
   *
   * @param stock    the stock to remove
   * @param quantity the quantity of the stock to remove
   */
  @Override
  public void removeStock(IStock stock, double quantity, LocalDate currentDate) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("You cannot remove zero or negative stocks.");
    }
    if (!this.stocks.containsKey(stock)) {
      throw new IllegalArgumentException("model.Stock not found in the portfolio.");
    }
    double totalQuantity = 0;
    for (Map.Entry<LocalDate, Double> entry : this.stocks.get(stock).entrySet()) {
      double i = entry.getValue();
      totalQuantity += i;
    }
    if (totalQuantity == 0) {
      throw new IllegalArgumentException("You have zero " + stock.getTicker() + " stocks.");
    }
    if (totalQuantity - quantity < 0) {
      throw new IllegalArgumentException("Removal quantity cannot exceed existing quantity. " +
              "You " + "currently have " + totalQuantity + " " + stock.getTicker() + " stocks.");
    }
    if (this.stocks.get(stock).containsKey(currentDate)) {
      double currentQuantity = this.stocks.get(stock).get(currentDate);
      this.stocks.get(stock).put(currentDate, currentQuantity - quantity);
    } else {
      this.stocks.get(stock).put(currentDate, -quantity);
    }
  }

  /**
   * Calculates the total value of the portfolio on the specified date.
   *
   * @param currentDate the date on which to calculate the portfolio value
   * @return the total value of the portfolio on the specified date
   */
  @Override
  public Double calculatePortfolioValue(LocalDate currentDate) {
    double totalValue = 0.0;
    for (Map.Entry<IStock, TreeMap<LocalDate, Double>> entry : stocks.entrySet()) {
      IStock stock = entry.getKey();
      Double price = calculateStockValue(stock, currentDate);
      totalValue += price;
    }
    return totalValue;
  }

  /**
   * Calculates the total value of the stock multiplied by the quantity
   * on the specified date.
   *
   * @param s           the stock
   * @param currentDate the date on which to calculate the stock value
   * @return the total value of the stock on the specified date
   */
  @Override
  public Double calculateStockValue(IStock s, LocalDate currentDate) {
    double totalValue = 0.0;
    Double price = s.getClosingPrice(currentDate);
    for (Map.Entry<LocalDate, Double> entry : stocks.get(s).headMap(currentDate, true).entrySet()) {
      double quantity = entry.getValue();
      totalValue += quantity;
    }
    totalValue *= price;
    return Math.round(totalValue * 100.0) / 100.0;
  }


  /**
   * Returns the name of the portfolio.
   *
   * @return the name of the portfolio
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Returns the collection of stocks in the portfolio with their quantities.
   *
   * @return a HashMap containing the stocks and their quantities
   */
  @Override
  public HashMap<IStock, Double> getStocks(LocalDate l) {
    HashMap<IStock, Double> stockList = new HashMap<>();
    for (Map.Entry<IStock, TreeMap<LocalDate, Double>> entry : stocks.entrySet()) {
      IStock stock = entry.getKey();
      double stockNum = 0;
      if (!stocks.get(stock).headMap(l, true).isEmpty()) {
        for (Map.Entry<LocalDate, Double> entry1 : stocks.get(stock).headMap(l, true).entrySet()) {
          double i = entry1.getValue();
          stockNum += i;
        }
      }
      if (stockNum > 0) {
        stockList.put(stock, Math.round(stockNum * 100.0) / 100.0);
      }
    }
    return stockList;
  }

  @Override
  public Client getOwner() {
    return owner;
  }


}