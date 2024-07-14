package mock;

import java.time.LocalDate;
import java.util.TreeMap;

import mock.MockClient;
import model.IClient;
import model.IModel;
import model.IStock;

/**
 * Mock for model.Model.
 */
public class MockModel implements IModel {

  @Override
  public IStock makeStock(TreeMap<LocalDate, Double> stockHistory, String ticker) {
    return new MockStock(ticker, stockHistory);
  }

  @Override
  public IClient makeClient() {
    return new MockClient();
  }

}
