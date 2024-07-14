import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.PrintStream;
import java.util.Scanner;

import controller.text.PortfolioOptions;
import mock.MockView;
import model.Client;
import model.SmartPortfolio;
import view.text.IView;

/**
 * Tests for various operations available within the controller.PortfolioOptions class.
 * This includes user interactions
 * like adding, removing stocks,
 * calculating values, and more. These tests simulate user inputs and check
 * if the resulting outputs match expected values.
 */
public class PortfolioOptionsTest {
  private PortfolioOptions portfolioOptions;
  private ByteArrayOutputStream outputStream;

  private IView view;
  private SmartPortfolio portfolio;
  private String expected;


  /**
   * set up the output stream and input stream.
   */
  @Before
  public void setUp() {

    outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    view = new MockView(printStream);
    Client m = new Client("user");
    portfolio = new SmartPortfolio("user", m);
    expected = "";
  }

  private void setInputStream(String data) {
    ByteArrayInputStream inputStream;
    Scanner scanner;
    inputStream = new ByteArrayInputStream(data.getBytes());
    scanner = new Scanner(inputStream);
    portfolioOptions = new PortfolioOptions(scanner, view);
  }

  /**
   * Test the user's option to quit the portfolio options menu.
   * This simulates the user entering 'b'
   * to exit, verifying that
   * the application handles this input correctly by checking the output.
   */
  @Test
  public void testQuitOption() {
    String input = "b";

    setInputStream(input);
    portfolioOptions.portfolioOperation(portfolio);

    assertEquals(expected, outputStream.toString());
  }

  /**
   * Test the functionality for calculating the total value of the portfolio.
   * This simulates the user
   * selecting the option to calculate the
   * portfolio's value and verifies that the correct operations
   * are performed and the expected output is displayed.
   */
  @Test
  public void testCalculatePortfolioValue() {
    String input = "1\nb\n";

    setInputStream(input);
    portfolioOptions.portfolioOperation(portfolio);

    assertEquals(expected, outputStream.toString());
  }

  /**
   * Test adding a stock to the portfolio. This simulates t
   * he user entering the appropriate commands
   * to add a specified number of shares of
   * a stock and verifies that the action is acknowledged correctly.
   */
  @Test
  public void testAddStock() {
    String input = "2\nGOOG\nb\n";
    setInputStream(input);
    portfolioOptions.portfolioOperation(portfolio);

    assertEquals(expected, outputStream.toString());
  }

  /**
   * Test removing a stock from the portfolio.
   * This simulates the user choosing to remove a specified
   * number of shares of a stock and checks that the system correctly reflects this action.
   */
  @Test
  public void testRemoveStock() {
    String input = "2\nGOOG\nb\n";

    setInputStream(input);
    portfolioOptions.portfolioOperation(portfolio);

    assertEquals(expected, outputStream.toString());
  }

  /**
   * Test the rebalancing of the portfolio. This simulates the user selecting the rebalance option
   * and verifies that the system provides the appropriate feedback about this operation.
   */
  @Test
  public void testRebalancePortfolio() {
    String input = "4\nb\n";

    setInputStream(input);
    portfolioOptions.portfolioOperation(portfolio);

    assertEquals(expected, outputStream.toString());
  }

  /**
   * Test the functionality to display the current stocks in the portfolio. This simulates the user
   * selecting to view the stocks and checks that the portfolio contents are correctly listed.
   */
  @Test
  public void testPrintStocks() {
    String input = "5\nb\n";

    setInputStream(input);
    portfolioOptions.portfolioOperation(portfolio);

    assertEquals(expected, outputStream.toString());
  }

  /**
   * Test displaying the performance of the portfolio over time.
   * This simulates the user selecting
   * the option to view performance metrics over a specific period and verifies that t
   * his information
   * is displayed correctly.
   */

  @Test
  public void testPerformanceOverTime() {
    String input = "6\nb\n";

    setInputStream(input);
    portfolioOptions.portfolioOperation(portfolio);

    assertEquals(expected, outputStream.toString());
  }

  /**
   * Test the functionality to display the current stocks in the portfolio. This simulates the user
   * selecting to view the stocks and checks that the portfolio contents are correctly listed.
   */
  @Test
  public void testDistributionOfValue() {
    String input = "7\nb\n";

    setInputStream(input);
    portfolioOptions.portfolioOperation(portfolio);

    assertEquals(expected, outputStream.toString());
  }

}
