package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeMap;

import view.text.IView;

/**
 * The SmartPortfolio class extends the Portfolio class and implements
 * the ISmartPortfolio interface.
 * It represents an advanced version of a portfolio with more sophisticated
 * functionalities for managing investments.
 * This class inherits basic portfolio operations and adds capabilities
 * for advanced portfolio management.
 */
public class SmartPortfolio extends Portfolio implements ISmartPortfolio {

  /**
   * Constructs a new SmartPortfolio instance with the specified name and owner.
   * Initializes the portfolio with a name and assigns it to a client.
   *
   * @param portfolioName the name of the portfolio
   * @param owner         the client who owns this portfolio
   */
  public SmartPortfolio(String portfolioName, Client owner) {
    super(portfolioName, owner);
  }


  @Override
  public String distributionOfValueOnDate(LocalDate date) {
    double totalPortfolioValue = calculatePortfolioValue(date);
    if (totalPortfolioValue == 0) {
      return "The portfolio has no value on " + date;
    }

    StringBuilder distribution = new StringBuilder();
    distribution.append("Distribution of value on ").append(date).append(":\n");

    for (Map.Entry<IStock, TreeMap<LocalDate, Double>> entry : stocks.entrySet()) {
      IStock stock = entry.getKey();
      double stockValue = calculateStockValue(stock, date);
      if (stockValue > 0) {
        distribution.append(stock.getTicker()).append(": $").append(stockValue).append("\n");
      }
    }

    distribution.append("Total model.Portfolio Value: $").append(totalPortfolioValue);
    return distribution.toString();
  }

  @Override
  public String performanceOverTime(LocalDate start, LocalDate end) {
    catchErrors(start, end);
    StringBuilder chart = new StringBuilder();
    chart.append("Performance of portfolio '").append(this.getName()).append("' from ")
            .append(start).append(" to ").append(end).append("\n");

    long totalDays = ChronoUnit.DAYS.between(start, end);
    List<LocalDate> dates = new ArrayList<>();

    TemporalUnit interval;
    DateTimeFormatter formatter;
    if (totalDays <= 5) {
      throw new IllegalArgumentException("Time span too short");
    } else if (totalDays <= 30) {
      interval = ChronoUnit.DAYS;
      formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    } else if (totalDays <= 730) {
      interval = ChronoUnit.MONTHS;
      formatter = DateTimeFormatter.ofPattern("MMM yyyy");
    } else {
      interval = ChronoUnit.YEARS;
      formatter = DateTimeFormatter.ofPattern("yyyy");
    }

    LocalDate current = start;
    while (!current.isAfter(end)) {
      if (dateContainsPrice(current)) {
        dates.add(current);
      }
      if (interval == ChronoUnit.DAYS) {
        current = current.plusDays(1);
      } else if (interval == ChronoUnit.MONTHS) {
        current = current.plusMonths(1);
      } else if (interval == ChronoUnit.YEARS) {
        current = current.plusYears(1);
      }
    }

    if (dates.isEmpty()) {
      return "No data available for the given date range.";
    }

    if (dates.size() > 30) {
      return "Entered time span too long";
    }


    Map<LocalDate, Double> values = new HashMap<>();
    double maxValue = Double.MIN_VALUE;
    for (LocalDate date : dates) {
      double value = calculatePortfolioValue(date);
      values.put(date, value);
      if (value > maxValue) {
        maxValue = value;
      }
    }

    int maxAsterisks = 50;
    double scale = maxValue / maxAsterisks;
    if (scale == 0) {
      scale = 1;
    }

    for (LocalDate date : dates) {
      double value = values.get(date);
      int numAsterisks = (int) (value / scale);
      chart.append(date.format(formatter)).append(": ").append(
              "*".repeat(numAsterisks)).append("\n");
    }

    chart.append("Scale: * = ").append(String.format("$%.2f", scale)).append(" units\n");
    return chart.toString();
  }

  private void catchErrors(LocalDate start, LocalDate end) {
    if (!end.isAfter(start)) {
      throw new IllegalArgumentException("Your end date must come after your start date.");
    }
    if (!dateContainsPrice(start)) {
      throw new IllegalArgumentException("No data available for the start date: " + start);
    }

    if (!dateContainsPrice(end)) {
      throw new IllegalArgumentException("No data available for the end date: " + end);
    }

  }


  private boolean dateContainsPrice(LocalDate date) {
    for (Map.Entry<IStock, TreeMap<LocalDate, Double>> entry : stocks.entrySet()) {
      IStock stock = entry.getKey();
      try {
        Double price = stock.getClosingPrice(date);
        if (price != null) {
          return true;
        }
      } catch (Exception e) {
        return false;
      }
    }
    return false;
  }


  @Override
  public void rebalance(Scanner in, LocalDate date, IView v) {
    if (stocks.isEmpty()) {
      throw new IllegalArgumentException("No stock available.");
    }

    Map<model.IStock, Double> targetDistribution = new HashMap<>();

    double totalPercentage = 0.0;
    for (model.IStock stock : stocks.keySet()) {
      v.writeMessage("Enter the target percentage for %s: " + stock.getTicker());
      double percentage = in.nextDouble() / 100.0;
      targetDistribution.put(stock, percentage);
      totalPercentage += percentage;
    }

    in.nextLine();

    if (Math.abs(totalPercentage - 1.0) > 0.001) {
      v.writeMessage("The target percentages do not add up to 100%. Please try again.");
      return;
    }

    v.writeMessage("The new distribution will be:");
    for (Map.Entry<model.IStock, Double> entry : targetDistribution.entrySet()) {
      model.IStock stock = entry.getKey();
      double targetValue = calculatePortfolioValue(date) * entry.getValue();
      double currentValue = calculateStockValue(stock, date);
      v.writeMessage(stock.getTicker() + " : Current Value = " +
              String.format("$%.2f", currentValue) + ", Target Value =" +
              String.format("$%.2f", targetValue));
    }

    v.writeMessage("Do you want to proceed with rebalancing? (enter yes to proceed, "
            + "anything else to cancel)");
    String response = in.nextLine().trim().toLowerCase();

    if (!response.equals("yes")) {
      v.writeMessage("Rebalancing cancelled.");
      return;

    }


    double totalPortfolioValue = calculatePortfolioValue(date);
    for (Map.Entry<model.IStock, Double> entry : targetDistribution.entrySet()) {
      model.IStock stock = entry.getKey();
      double currentStockValue = calculateStockValue(stock, date);
      double targetStockValue = totalPortfolioValue * entry.getValue();
      double price = stock.getClosingPrice(date);

      if (currentStockValue < targetStockValue) {
        double sharesToBuy = ((targetStockValue - currentStockValue) / price);
        addStock(stock, sharesToBuy, date);
      } else if (currentStockValue > targetStockValue) {
        double sharesToSell = ((currentStockValue - targetStockValue) / price);
        removeStock(stock, sharesToSell, date);
      }
    }

    v.writeMessage("model.Portfolio rebalanced successfully.");

  }


  /**
   * Compares this portfolio to the specified object. The result is true if and only if
   * the argument is not null and is a model.Portfolio object that has the same name and owner
   * as this portfolio.
   *
   * @param o the object to compare this portfolio against
   * @return true if the given object represents a model.Portfolio equivalent to this portfolio,
   *                    false otherwise
   */
  @Override
  public boolean equals(Object o) {
    Portfolio other = (Portfolio) o;
    return Objects.equals(name, other.name) &&
            Objects.equals(owner, other.owner);
  }

  /**
   * Returns a hash code for this portfolio.
   *
   * @return a hash code value for this portfolio
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, owner);
  }

  /**
   * Returns a string representation of this portfolio.
   *
   * @return a string representation of this portfolio
   */
  @Override
  public String printStocks(LocalDate l) {
    String s = "";
    for (Map.Entry<IStock, Double> e : getStocks(l).entrySet()) {
      s += e.getKey().getTicker() + ", " + Math.round(e.getValue() * 100.0) / 100.0 + "; ";
    }
    return "'" + name + "'" +
            "\n{" + s +
            '}';
  }


  @Override
  public void savePortfolio(String filename) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      writer.write("name:" + this.name + "\n");
      writer.write("stocks:\n");

      for (Map.Entry<IStock, TreeMap<LocalDate, Double>> entry : stocks.entrySet()) {
        IStock s = entry.getKey();
        for (Map.Entry<LocalDate, Double> entry2 : stocks.get(s).entrySet()) {
          writer.write("ticker:" + s.getTicker() +
                  ":quantity:" + entry2.getValue() +
                  ":dateAdded:" + entry2.getKey().format(formatter) + "\n");
        }
      }
    } catch (IOException e) {
      e.fillInStackTrace();
    }
  }

  @Override
  public void loadPortfolio(File filename) {
    AlphaVantageAPI api = new AlphaVantageAPI();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.startsWith("name:")) {
          this.name = line.split(":")[1];
        } else if (line.startsWith("ticker:")) {
          String[] parts = line.split(":");
          String ticker = parts[1];
          double quantity = Double.parseDouble(parts[3]);
          LocalDate dateAdded = LocalDate.parse(parts[5], formatter);
          if (quantity > 0) {
            this.addStock(api.makeStock(ticker), quantity, dateAdded);
          }

          if (quantity < 0) {
            this.removeStock(api.makeStock(ticker), -quantity, dateAdded);
          }
        }

      }
    } catch (IOException e) {
      e.fillInStackTrace();
    }
  }

  public APIInterface getApi() {
    APIInterface api = new AlphaVantageAPI();
    return api;
  }
}
