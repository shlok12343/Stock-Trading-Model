
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import java.util.Scanner;
import java.util.TreeMap;

import controller.text.ClosingPrice;
import model.IStock;
import mock.MockStock;
import view.text.IView;
import view.text.TextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the functionality of retrieving the closing price for a specified stock on a given date
 * using the controller.text.ClosingPrice class. This test suite aims to verify that the user
 * interface correctly
 * captures user input and accurately displays the closing price, thereby ensuring the integrity
 * of the interface interaction and the correctness of the data retrieval mechanism.
 */
public class ClosingPriceTest {
  private IStock stock;
  private IView view;


  /**
   * Sets up the necessary components for the tests. Initializes
   * a mock stock with the symbol "AAPL"
   * using a TreeMap to simulate historical
   * price data storage. Also, prepares a ByteArrayOutputStream
   * to capture the output of the view,
   * allowing for validation of what is printed to the user during
   * tests. This setup ensures each test is performed with
   * a clean and isolated environment, promoting
   * accurate and reliable results.
   */
  @Before
  public void setUp() {
    stock = new MockStock("AAPL", new TreeMap<>());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    view = new TextView(printStream);
  }

  /**
   * Tests the interaction of the controller.text.ClosingPrice interface
   * by simulating user input to specify a date
   * and verifying the correct output is displayed.
   * This test checks that the system correctly prompts
   * the user for a date, retrieves the corresponding
   * closing price, and appropriately communicates the
   * result back to the user. The test simulates
   * the process of a user entering a specific date,
   * checks that the interface displays the correct
   * prompts, and ensures the expected closing price is
   * returned and displayed accurately.
   */
  @Test
  public void testGo() {
    ClosingPrice closingPrice;
    StringBuilder input = new StringBuilder();
    StringBuilder expected = new StringBuilder();
    StringBuilder expected1 = new StringBuilder();
    ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();


    input.append("10\n5\n2023\n");
    expected.append("Give us the date that you would like to know the stock AAPL closing price\n");
    expected.append("Please provide the date in the form of day, month, year.\n");
    expected.append("Day: Month: Year: ");
    expected.append("\nthe closing price of the give date is  --- 100.0\n\n");


    expected.append("Enter 'b' to come back to the most recent menu.\nTo quit the program, "
            + "navigate back to main menu.\n");
    expected.append("\ninput any character other than b if you would like to find the "
            + "closing price of another date\n\n");
    input.append("b\n");


    InputStream inputStream = new ByteArrayInputStream(input.toString().getBytes());
    Scanner scanner = new Scanner(inputStream);
    closingPrice = new ClosingPrice(view, scanner);

    closingPrice.goController(stock);

    assertEquals(expected1.toString(), outputStream1.toString());
  }
}


