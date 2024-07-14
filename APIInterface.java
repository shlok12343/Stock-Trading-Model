package model;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

/**
 * Interface for interacting with financial data APIs such as Alpha Vantage.
 * This interface provides methods to construct URLs for API calls, fetch and process stock data,
 * and manage CSV files containing stock ticker information.
 */
public interface APIInterface {
  /**
   * Creates a URL for accessing Alpha Vantage API data.
   *
   * @param function    the function type for the API call (e.g., "TIME_SERIES_DAILY")
   * @param stockSymbol the stock ticker symbol
   * @return a URL object pointing to the Alpha Vantage API endpoint for the given function and
   *         stock symbol
   */
  URL urlMaker(String function, String stockSymbol);

  /**
   * Retrieves data from the Alpha Vantage API or any similar financial data API using
   * the provided URL.
   *
   * @param url The URL pointing to the API endpoint from which data is to be fetched.
   * @return A string representation of the data retrieved from the API.
   */
  String returnData(URL url);

  /**
   * Creates a URL for accessing Alpha Vantage API data.
   *
   * @param function the function type for the API call (e.g., "TIME_SERIES_DAILY")
   * @param ticker   the stock ticker symbol
   * @return a URL object pointing to the Alpha Vantage API endpoint for the given function
   *         and stock symbol
   */
  String makeCSVFile(String function, String ticker);

  /**
   * Fetches historical stock prices from a CSV file and returns them as a TreeMap.
   *
   * @param fileName the name of the CSV file containing stock data
   * @return a TreeMap with dates as keys and closing prices as values
   */
  TreeMap<LocalDate, Double> fetchStockHistory(String fileName);

  /**
   * Converts a CSV file of tickers into a list of strings.
   *
   * @return a list of tickers from the CSV file
   */
  List<String> tickerCSVToList();

  /**
   * Creates a model.Stock object by fetching historical data for the given ticker.
   *
   * @param ticker the stock ticker symbol
   * @return a model.Stock object containing the ticker and its historical prices
   * @throws IllegalArgumentException if there is an issue with the API or no data is found
   */
  Stock makeStock(String ticker);

  /**
   * Creates a CSV file containing all American tickers from Alpha Vantage and returns the
   * filename.
   *
   * @return the filename of the CSV file created
   */
  String makeAllTickerCSVFile();
}

