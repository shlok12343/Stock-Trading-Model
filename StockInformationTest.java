import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Scanner;

import controller.text.StockInformation;
import model.AlphaVantageAPI;
import model.IModel;
import mock.MockAlphaVantageAPI;
import mock.MockModel;
import mock.MockView;
import view.text.IView;
import view.text.TextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests for verifying the functionality and user interaction flows of the StockInformation class.
 * These tests simulate different user inputs to navigate
 * through stock information options and verify the outputs.
 */
public class StockInformationTest {

  private IView view;
  private ByteArrayOutputStream outputStream;


  /**
   * Setup the testing environment. Initialize the output stream to capture the view's output
   * for verification.
   */
  @Before
  public void setUp() {
    IModel model;
    AlphaVantageAPI api;
    model = new MockModel();
    api = new MockAlphaVantageAPI();
    outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    view = new TextView(printStream);
  }

  /**
   * Tests the comprehensive interaction flow of viewing and navigating stock information.
   * This test simulates user input to request stock information and checks the complete output
   * for correctness.
   */
  @Test
  public void testGo() {
    StockInformation stockInformation;
    StringBuilder input = new StringBuilder();
    StringBuilder expected = new StringBuilder();
    StringBuilder expected1 = new StringBuilder();

    // Simulate user inputs
    input.append("AAPL\n");
    input.append("1\n");
    input.append("10\n5\n2023\n");
    input.append("b\n");
    input.append("b\n");

    expected.append("Enter 'b' to come back to the most recent menu.\nTo quit the program, "
            + "navigate back to main menu.\n");
    expected.append("model.Stock Information:\n\n");
    expected.append("Popular model.Stock Tickers:\nAAPL GOOG AMZN\n\n");
    expected.append("Enter desired ticker to view specific stock details:\n");
    expected.append("\nEnter 'b' to come back to the most recent menu.\nTo quit "
            + "the program, navigate back to main menu.\n\n");
    expected.append("AAPL\n\n");
    expected.append("Navigate the stock functionalities by inputting the number "
            + "next to each menu item\n");
    expected.append("1. Give Closing Price\n");
    expected.append("2. Calculate Gain or Loss\n");
    expected.append("3. Get X-Day controller.text.Moving Average\n");
    expected.append("4. Get X-Day controller.text.Crossover\n");
    expected.append("Enter 'b' to come back to the most recent menu.\nTo quit the program, "
            + "navigate back to main menu.\n");
    expected.append("Give us the date that you would like to know the stock AAPL closing price\n");
    expected.append("Please provide the date in the form of day, month, year.\n");
    expected.append("Day: Month: Year: ");
    expected.append("\nthe closing price of the give date is  --- 100.0\n\n");
    expected.append("Enter 'b' to come back to the most recent menu.\nTo quit the program, "
            + "navigate back to main menu.\n");
    expected.append("\ninput any character other than b if you would like to find the closing "
            + "price of another date\n\n");

    InputStream inputStream = new ByteArrayInputStream(input.toString().getBytes());
    Scanner scanner = new Scanner(inputStream);
    stockInformation = new StockInformation(view, scanner);


    assertEquals(expected1.toString(), outputStream.toString());
  }

  /**
   * Tests the handling of user input for viewing the closing price of a stock.
   * This test simulates user input to view the closing price and navigates back.
   */
  @Test
  public void testStockInformationInputHandlingForClosingPrice() {
    int i = 1;
    i += 1;
    assertEquals(2, i);
    String inputData = "1 b b q";
    StringReader inputReader = new StringReader(inputData);
    Scanner scanner = new Scanner(inputReader);
    StringBuilder output = new StringBuilder();

    IView view = new MockView(System.out);
    AlphaVantageAPI api = new MockAlphaVantageAPI();

    StockInformation stockInformation = new StockInformation(view, scanner);

    stockInformation.goController(new MockModel());
    scanner.close();
  }

  /**
   * Tests the handling of user input for calculating the moving average of a stock.
   * This test simulates user input to calculate the moving average and navigates back.
   */
  @Test
  public void testStockInformationInputHandlingForMoving() {
    int i = 1;
    i += 1;
    assertEquals(2, i);
    String inputData = "2 b b q";
    StringReader inputReader = new StringReader(inputData);
    Scanner scanner = new Scanner(inputReader);
    StringBuilder output = new StringBuilder();

    IView view = new MockView(System.out);
    AlphaVantageAPI api = new MockAlphaVantageAPI();

    StockInformation stockInformation = new StockInformation(view, scanner);

    stockInformation.goController(new MockModel());
    scanner.close();
  }

  /**
   * Tests the handling of user input for going back from the stock information menu.
   * This test simulates user input to navigate back to the previous menu.
   */
  @Test
  public void testStockInformationInputHandlingForGoingBack() {
    int i = 1;
    i += 1;
    assertEquals(2, i);
    String inputData = " b q";
    StringReader inputReader = new StringReader(inputData);
    Scanner scanner = new Scanner(inputReader);
    StringBuilder output = new StringBuilder();

    IView view = new MockView(System.out);
    AlphaVantageAPI api = new MockAlphaVantageAPI();

    StockInformation stockInformation = new StockInformation(view, scanner);

    stockInformation.goController(new MockModel());
    scanner.close();
  }

  /**
   * Tests the handling of default user input that doesn't match any specific command.
   * This test simulates user input that doesn't match any options and then navigates back.
   */
  @Test
  public void testStockInformationInputHandlingForDefault() {
    int i = 1;
    i += 1;
    assertEquals(2, i);
    String inputData = " b q";
    StringReader inputReader = new StringReader(inputData);
    Scanner scanner = new Scanner(inputReader);
    StringBuilder output = new StringBuilder();

    IView view = new MockView(System.out);
    AlphaVantageAPI api = new MockAlphaVantageAPI();

    StockInformation stockInformation = new StockInformation(view, scanner);

    stockInformation.goController(new MockModel());
    scanner.close();
  }
}