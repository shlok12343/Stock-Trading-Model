

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import java.util.Scanner;
import java.util.TreeMap;

import controller.text.Moving;
import model.IStock;
import mock.MockStock;
import view.text.IView;
import view.text.TextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the functionality of the controller.text.Moving class which handles calculating t
 * he moving average of a stock.
 * This test verifies that the controller.text.Moving class accurately receives
 * user inputs and correctly displays
 * the x-day moving average for a given date.
 */
public class MovingTest {
  private IStock stock;
  private IView view;


  /**
   * Sets up the test environment before each test. Initializes
   * a mock stock with an empty price history,
   * sets up a ByteArrayOutputStream to capture the output
   * for verification, ensuring that each test is
   * independent and reproducible.
   */
  @Before
  public void setUp() {
    ByteArrayOutputStream outputStream;
    stock = new MockStock("AAPL", new TreeMap<>());
    outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    view = new TextView(printStream);
  }

  /**
   * Tests the 'goController' method for functionality of calculating the x-day moving average.
   * Simulates user inputs for the number of days and a specific date, and checks if the output
   * matches the expected result that describes the x-day moving
   * average calculation process and result.
   */
  @Test
  public void testGo() {
    Moving moving;
    StringBuilder input = new StringBuilder();
    StringBuilder expected = new StringBuilder();
    StringBuilder expected1 = new StringBuilder();
    ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();

    input.append("5\n");
    expected.append("\nprovide us with the how many days or x-day would like your x-day moving "
            + "average to look back of stock AAPL history\n");
    expected.append("5\n");
    input.append("10\n5\n2023\n");
    expected.append("\n\nGive us the date that you would like to know the stock AAPL "
            + "controller.text.Moving average of\n");
    expected.append("Please provide the date in the form of day, month, year.\n");
    expected.append("Day: ");
    expected.append("Month: ");
    expected.append("Year: ");
    expected.append("\nthe x-day moving average of the given date and x-day is  --- 100.0\n\n");
    expected.append("Enter 'b' to come back to the most recent menu.\nTo quit the program, "
            + "navigate back to main menu.\n");

    input.append("b\n");
    expected.append("\ninput any character other than b if you would like to find the x-day "
            + "moving average of another date and X-day\n");
    input.append("b\n");

    InputStream inputStream = new ByteArrayInputStream(input.toString().getBytes());
    Scanner scanner = new Scanner(inputStream);
    moving = new Moving(view, scanner);

    moving.goController(stock);
    assertEquals(expected1.toString(), outputStream1.toString());
  }
}
