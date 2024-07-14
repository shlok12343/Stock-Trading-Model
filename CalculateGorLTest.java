
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.TreeMap;

import controller.text.CalculateGorL;
import model.IStock;
import mock.MockStock;
import view.text.IView;
import view.text.TextView;

import static org.junit.Assert.assertEquals;

/**
 * class to test to  calculate the gain or loss of a stock.
 */
public class CalculateGorLTest {
  private IStock stock;
  private IView view;


  /**
   * Sets up the test environment before each test method.
   * Initializes a mock stock instance and a view that directs
   * its output to a ByteArrayOutputStream
   * to capture the interactions for verification.
   */
  @Before
  public void setUp() {
    stock = new MockStock("AAPL", new TreeMap<>());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    view = new TextView(printStream);
  }

  /**
   * Tests the interaction flow of calculating the gain or loss of a stock.
   * Simulates user input via a ByteArrayInputStream to provide start and end dates
   * for the calculation, and checks that the output from the view matches the expected
   * sequence of interactions.
   */
  @Test
  public void testGo() {
    CalculateGorL calculateGorL;
    StringBuilder input = new StringBuilder();
    StringBuilder expected = new StringBuilder();
    StringBuilder expected1 = new StringBuilder();
    ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
    // Simulate user inputs for start date and end date
    input.append("1\n1\n2023\n");
    input.append("31\n12\n2023\n");
    expected.append("\nprovide us with the time period you would like to calculate "
            + "the stock AAPL gain or loss .\nStart Date:\n");
    expected.append("Please provide the date in the form of day, month, year.\n");
    expected.append("\nDay: ");
    expected.append("Month: ");
    expected.append("Year: ");
    expected.append("\nEnd Date: ");
    expected.append("Please provide the date in the form of day, month, year.\n");
    expected.append("Day: ");
    expected.append("Month: ");
    expected.append("Year: ");
    expected.append("\nthe gain or loss of the stock is  --- 50.0");
    expected.append("\n(negative numbers are loss of that number and positive numbers "
            + "are gain of that number )\n");


    expected.append("Enter 'b' to come back to the most recent menu.\nTo quit the program, "
            + "navigate back to main menu.\n");
    expected.append("\ninput any character other that b if you would like to find the "
            + "gain or loss of another time period \n\n");
    input.append("b\n");

    InputStream inputStream = new ByteArrayInputStream(input.toString().getBytes());
    Scanner scanner = new Scanner(inputStream);
    calculateGorL = new CalculateGorL(view, scanner);

    calculateGorL.goController(stock);

    assertEquals(expected1.toString(), outputStream1.toString());
  }
}
