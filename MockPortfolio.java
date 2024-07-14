package mock;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

import model.APIInterface;
import model.Client;
import model.IPortfolio;
import model.ISmartPortfolio;
import model.IStock;
import view.text.IView;


/**
 * A mock portfolio used for testing all
 * the different functionality with default values.
 */
public class MockPortfolio implements IPortfolio, ISmartPortfolio {
  int field;

  @Override
  public void addStock(IStock stock, double quantity, LocalDate currentDate) {
    field = 0;

  }

  @Override
  public void removeStock(IStock stock, double quantity, LocalDate currentDate) {
    field = 0;
  }

  @Override
  public Double calculatePortfolioValue(LocalDate currentDate) {
    return null;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public HashMap<IStock, Double> getStocks(LocalDate l) {
    return null;
  }

  @Override
  public Client getOwner() {
    return null;
  }

  @Override
  public Double calculateStockValue(IStock s, LocalDate currentDate) {
    return null;
  }

  @Override
  public String printStocks(LocalDate l) {
    return null;
  }

  @Override
  public String distributionOfValueOnDate(LocalDate date) {
    return null;
  }

  @Override
  public void rebalance(Scanner in, LocalDate date, IView v) {
    field = 0;
  }

  @Override
  public String performanceOverTime(LocalDate start, LocalDate end) {
    return null;
  }

  @Override
  public void savePortfolio(String filename) {
    field = 0;
  }

  @Override
  public void loadPortfolio(File flile) {
    field = 0;
  }

  @Override
  public APIInterface getApi() {
    return null;
  }
}
