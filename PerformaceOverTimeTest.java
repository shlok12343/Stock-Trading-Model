import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.TreeMap;

import controller.text.PerformaceOverTime;
import model.IStock;
import mock.MockStock;
import view.text.IView;
import view.text.TextView;

import static org.junit.Assert.assertEquals;


/**
 * PerformanceOverTimeTest verifies the correctness of performance over time calculations
 * and user interactions, ensuring that the system accurately presents historical stock data
 * and interprets user input correctly.
 */
public class PerformaceOverTimeTest {
  private IStock stock;
  private IView view;

  /**
   * Sets up the testing environment with mock stock data and a view configured to capture output.
   * The mock data represents the stock
   * prices over a period of time, and the output stream captures
   * the text displayed to the user, allowing for verification against expected output.
   */
  @Before
  public void setUp() {
    // Mock stock with sample historical data
    stock = new MockStock("AAPL", new TreeMap<>() {
      {
        put(LocalDate.of(2023, 1, 1), 150.0);
        put(LocalDate.of(2023, 6, 1), 160.0);
        put(LocalDate.of(2023, 12, 31), 170.0);
      }
    }
    );
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    view = new TextView(printStream);
  }

  /**
   * Tests the functionality of calculating and displaying the stock's performance
   * over a specified time period.
   * This test simulates user input to specify the
   * start and end dates and checks if the output matches the expected
   * historical price data, confirming the correct behavior of the goController method.
   */
  @Test
  public void testGo() {
    PerformaceOverTime performanceOverTime;
    StringBuilder input = new StringBuilder();
    StringBuilder expected = new StringBuilder();
    String expected1 = "";
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    input.append("1\n");
    input.append("1\n"); // Start Month input
    input.append("2023\n"); // Start Year input
    input.append("31\n"); // End Day input
    input.append("12\n"); // End Month input
    input.append("2023\n"); // End Year input

    expected.append("\nprovide us with the time period " +
            "you would see Performace Over Time of the stock "
            + "\nStart Date:\n");
    expected.append("Please provide the date in the form of day, month, year.\n");
    expected.append("Day: 1\n");
    expected.append("Month: 1\n");
    expected.append("Year: 2023\n");
    expected.append("End Date: ");
    expected.append("Please provide the date in the form of day, month, year.\n");
    expected.append("Day: 31\n");
    expected.append("Month: 12\n");
    expected.append("Year: 2023\n");

    expected.append("Performance over time for the period from 2023-01-01 to 2023-12-31 is:\n");
    expected.append("[2023-01-01=150.0, 2023-06-01=160.0, 2023-12-31=170.0]\n");
    InputStream inputStream = new ByteArrayInputStream(input.toString().getBytes());
    Scanner scanner = new Scanner(inputStream);
    performanceOverTime = new PerformaceOverTime(view, scanner);
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    performanceOverTime.goController(stock);

    assertEquals(expected1, outputStream.toString());


  }
}
