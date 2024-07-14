package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * The AlphaVantageAPI class provides methods for fetching stock data from the Alpha Vantage API.
 * It includes functionality for creating CSV files of stock data, converting tickers to lists,
 * and retrieving historical stock prices.
 */
public class AlphaVantageAPI implements APIInterface {
  public final String API_KEY = "WMDORK6BEVBQ7K7S";

  /**
   * Creates a URL for accessing Alpha Vantage API data.
   *
   * @param function    the function type for the API call (e.g., "TIME_SERIES_DAILY")
   * @param stockSymbol the stock ticker symbol
   * @return a URL object pointing to the Alpha Vantage API endpoint for the given function and
   *         stock symbol
   */
  public URL urlMaker(String function, String stockSymbol) {
    URL url = null;
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function"
              + "=" + function
              + "&outputsize=full"
              + "&symbol=" + stockSymbol
              + "&apikey=" + API_KEY + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("Invalid URL");
    }
    return url;
  }

  /**
   * used to return the dat in the provided url and tells if there id no data.
   *
   * @param url the url to extract information from.
   * @return the string form of the data.
   */
  public String returnData(URL url) {
    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found.");
    }

    return output.toString();
  }

  /**
   * Creates a URL for accessing Alpha Vantage API data.
   *
   * @param function the function type for the API call (e.g., "TIME_SERIES_DAILY")
   * @param ticker   the stock ticker symbol
   * @return a URL object pointing to the Alpha Vantage API endpoint for the given function
   *         and stock symbol
   */
  public String makeCSVFile(String function, String ticker) {
    String output = returnData(urlMaker(function, ticker));
    try {
      Files.write(Paths.get(ticker + "_data.csv"), output.getBytes());
      System.out.println("Data saved to " + ticker + "_data.csv");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ticker + "_data.csv";
  }

  /**
   * Fetches historical stock prices from a CSV file and returns them as a TreeMap.
   *
   * @param fileName the name of the CSV file containing stock data
   * @return a TreeMap with dates as keys and closing prices as values
   */
  public TreeMap<LocalDate, Double> fetchStockHistory(String fileName) {
    TreeMap<LocalDate, Double> treeMap = new TreeMap<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      br.readLine(); // Skip first line
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        String[] d = values[0].split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(d[0]), Integer.parseInt(d[1]),
                Integer.parseInt(d[2]));
        Double price = Double.parseDouble(values[4]);
        treeMap.put(date, price);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return treeMap;
  }

  /**
   * Converts a CSV file of tickers into a list of strings.
   *
   * @return a list of tickers from the CSV file
   */
  public List<String> tickerCSVToList() {
    List<String> finalTickers = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("allT_data.csv"))) {
      String line;
      br.readLine(); // Skip first line
      while ((line = br.readLine()) != null) {
        finalTickers.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return finalTickers;
  }

  /**
   * Creates a model.Stock object by fetching historical data for the given ticker.
   *
   * @param ticker the stock ticker symbol
   * @return a model.Stock object containing the ticker and its historical prices
   * @throws IllegalArgumentException if there is an issue with the API or no data is found
   */
  public Stock makeStock(String ticker) throws IllegalArgumentException {
    AlphaVantageAPI a = new AlphaVantageAPI();
    //For pulling data about specific ticker
    String function = "TIME_SERIES_DAILY";
    TreeMap<LocalDate, Double> history = fetchStockHistory(a.makeCSVFile(function, ticker));
    if (history.isEmpty()) {
      throw new IllegalArgumentException("Having technical issues with the API could you try " +
              "again later");
    }
    return new Stock(ticker, history);
  }

  /**
   * Creates a CSV file containing all American tickers from Alpha Vantage and returns the
   * filename.
   *
   * @return the filename of the CSV file created
   */
  public String makeAllTickerCSVFile() {
    InputStream in = null;
    StringBuilder output = new StringBuilder();
    URL url = null;
    try {
      url = new URL("https://www.alphavantage.co/query?function=LISTING_STATUS" +
              "&apikey=" + API_KEY);
    } catch (MalformedURLException e) {
      throw new RuntimeException("Invalid URL");
    }
    try {
      in = url.openStream();
      int b;
      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found.");
    }
    String[] t = output.toString().split("\n");
    output = new StringBuilder();
    for (String ticker : t) {
      output.append(ticker.split(",")[0] + "\n");
    }

    try {
      Files.write(Paths.get("allT_data.csv"), output.toString().getBytes());
      System.out.println("Data saved to allT_data.csv");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "allT_data.csv";
  }


  /**
   * Main method for testing the model.AlphaVantageAPI class.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    AlphaVantageAPI a = new AlphaVantageAPI();

    a.makeAllTickerCSVFile();
  }
}

