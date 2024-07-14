package mock;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import model.AlphaVantageAPI;
import model.IStock;

/**
 * The mock.MockAlphaVantageAPI class provides a mock implementation of the AlphaVantageAPI for
 * testing purposes.
 * It overrides methods to return predefined data and simulate behavior without making actual
 * API calls.
 */
public class MockAlphaVantageAPI extends AlphaVantageAPI {

  /**
   * Provides a list of mock stock tickers for testing.
   *
   * @return a list of predefined stock tickers.
   */
  public List<String> tickerCSVToList() {
    return Arrays.asList("AAPL", "GOOG", "AMZN");
  }

  /**
   * Creates a mock stock with the given ticker symbol.
   * This method simulates the creation of a stock with empty historical data.
   *
   * @param ticker the stock ticker symbol.
   * @return an instance of mock.MockStock with the provided ticker and an empty TreeMap for
   *         historical data.
   * @throws IllegalArgumentException if the ticker symbol is invalid.
   */
  public IStock makeMockStock(String ticker) throws IllegalArgumentException {
    return new MockStock(ticker, new TreeMap<LocalDate, Double>());
  }
}