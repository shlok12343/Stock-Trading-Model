package mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

import model.IStock;

/**
 * Mock for model.Stock.
 */
public class MockStock implements IStock {
  private String ticker;
  private TreeMap<LocalDate, Double> stockHistory;

  public MockStock(String ticker, TreeMap<LocalDate, Double> stockHistory) {
    this.ticker = ticker;
    this.stockHistory = stockHistory;
  }

  @Override
  public double calculateXDayMovingAverage(LocalDate date, int xDay) {
    // Mock behavior: return a fixed value for simplicity
    return 100.0;
  }

  @Override
  public double calculateGainLoss(LocalDate startDate, LocalDate endDate) {
    // Mock behavior: return a fixed value for simplicity
    return 50.0;
  }

  @Override
  public ArrayList<LocalDate> findXDayCrossover(LocalDate startDate, LocalDate endDate, int xDay) {
    // Mock behavior: return a fixed list of dates
    ArrayList<LocalDate> crossovers = new ArrayList<>();
    crossovers.add(LocalDate.of(2023, 1, 1));
    crossovers.add(LocalDate.of(2023, 2, 1));
    return crossovers;
  }

  @Override
  public void addClosing(double price, LocalDate endDate) {
    // Mock behavior: simply add the closing price to the history
    stockHistory.put(endDate, price);
  }

  @Override
  public double getClosingPrice(LocalDate endDate) {
    // Mock behavior: return a fixed value or value from the history
    return stockHistory.getOrDefault(endDate, 0.0);
  }

  @Override
  public String getTicker() {
    return ticker;
  }

  @Override
  public String performanceOverTime(LocalDate start, LocalDate end) {
    return null;
  }
}


