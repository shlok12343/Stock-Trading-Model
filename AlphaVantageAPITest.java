import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import model.AlphaVantageAPI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * This class contains unit tests for the model.AlphaVantageAPI class.
 * It tests the various methods involved in fetching and handling stock data from the
 * Alpha Vantage API.
 */
public class AlphaVantageAPITest {
  private AlphaVantageAPI api;

  /**
   * Sets up the environment for each test.
   * This method is called before each test method to initialize the API instance.
   */
  @Before
  public void setUp() {
    api = new AlphaVantageAPI();
  }

  /**
   * Cleans up the environment after each test.
   * This method is called after each test method to remove any files created during the test.
   */
  @After
  public void tearDown() throws IOException {
    Files.deleteIfExists(Paths.get("AAPL_data.csv"));
    Files.deleteIfExists(Paths.get("allT_data.csv"));
    Files.deleteIfExists(Paths.get("test_stock_data.csv"));
  }

  /**
   * Tests the creation of a CSV file from API data.
   * This method simulates the scenario where the API is used to fetch stock data
   * and then save it to a CSV file, verifying that the file is created successfully.
   */
  @Test
  public void testMakeCSVFile() throws IOException {
    String expectedOutput = "timestamp,open,high,low,close,volume\n" +
            "2024-06-01,170.0,175.0,169.0,173.5,1000000\n" +
            "2024-05-01,165.0,171.0,164.0,170.5,900000";

    String fileName = "AAPL_data.csv";
    Files.write(Paths.get(fileName), expectedOutput.getBytes());

    String resultFileName = api.makeCSVFile("TIME_SERIES_DAILY", "AAPL");

    assertTrue(Files.exists(Paths.get(resultFileName)));
    List<String> lines = Files.readAllLines(Paths.get(resultFileName));
    assertEquals("timestamp,open,high,low,close,volume", lines.get(0));
    assertEquals("2024-06-07,194.6500,196.9400,194.1400,196.8900,52762886", lines.get(1));

    Files.deleteIfExists(Paths.get(fileName));
  }

  /**
   * Tests the functionality to fetch stock history from a CSV file.
   * This method verifies that the stock history is correctly parsed and loaded into a TreeMap.
   */
  @Test
  public void testFetchStockHistory() throws IOException {
    String fileName = "test_stock_data.csv";
    String csvContent = "timestamp,open,high,low,close,volume\n" +
            "2024-06-01,170.0,175.0,169.0,173.5,1000000\n" +
            "2024-05-01,165.0,171.0,164.0,170.5,900000";
    Files.write(Paths.get(fileName), csvContent.getBytes());

    TreeMap<LocalDate, Double> stockHistory = api.fetchStockHistory(fileName);

    assertEquals(2, stockHistory.size());
    assertEquals((Double) 173.5, stockHistory.get(LocalDate.of(2024,
            6, 1)));
    assertEquals((Double) 170.5, stockHistory.get(LocalDate.of(2024,
            5, 1)));
  }

  /**
   * Tests the retrieval of a list of ticker symbols from a CSV file.
   * This method verifies that ticker symbols are correctly read from a file and stored in a list.
   */
  @Test
  public void testTickerCSVToList() throws IOException {
    String fileName = "allT_data.csv";
    String csvContent = "symbol\nGOOG\nAMZN";
    Files.write(Paths.get(fileName), csvContent.getBytes());

    List<String> tickers = api.tickerCSVToList();

    assertEquals(2, tickers.size());
    assertTrue(tickers.contains("GOOG"));
    assertTrue(tickers.contains("AMZN"));
  }


  /**
   * Tests the creation of a CSV file containing all ticker symbols.
   * This method simulates the scenario where the API is used to fetch all available ticker symbols
   * and then save them to a CSV file, verifying that the file is created successfully.
   */
  @Test
  public void testMakeAllTickerCSVFile() throws IOException {
    String expectedOutput = "symbol\nA\nAA";

    String fileName = "allT_data.csv";
    Files.write(Paths.get(fileName), expectedOutput.getBytes());

    String resultFileName = api.makeAllTickerCSVFile();

    assertTrue(Files.exists(Paths.get(resultFileName)));
    List<String> lines = Files.readAllLines(Paths.get(resultFileName));
    assertEquals("symbol", lines.get(0));
    assertEquals("A", lines.get(1));
    assertEquals("AA", lines.get(2));
  }
}
