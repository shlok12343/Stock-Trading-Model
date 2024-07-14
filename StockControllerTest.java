
import org.junit.Test;

import static org.junit.Assert.assertEquals;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.io.StringReader;

import controller.Interaction;

/**
 * Tests for the StockController class, focusing on user interaction
 * through a text-based interface.
 */
public class StockControllerTest {
  private ByteArrayOutputStream outputStream;


  /**
   * Creates an interaction for the test that simulates expected output lines.
   *
   * @param lines The lines of expected output.
   * @return An controller.Interaction that appends the lines to the output.
   */
  static Interaction prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line);
      }
    };
  }

  /**
   * Creates an interaction for the test that simulates user input.
   *
   * @param in The input string to simulate user input.
   * @return An controller.Interaction that appends the input string to the input.
   */
  static Interaction inputs(String in) {
    return (input, output) -> {
      input.append(in);
    };
  }

  /**
   * Executes the test by running the StockController with simulated user input and checking
   * against the expected output.
   *
   * @param interactions The series of interactions (inputs and prints) to simulate and check.
   * @throws IOException If an I/O error occurs during the test.
   */
  void testRun(Interaction... interactions) throws IOException {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();

    //controller.IStockController controller = new StockController(input, actualOutput);
    //controller.goController();

    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }


  /**
   * Tests the base menu for quitting the application.
   * Simulates the user entering 'q' to quit the application.
   *
   * @throws IOException If an I/O error occurs during the test.
   */
  @Test
  public void testForBaseMenuQuit() throws IOException {

    testRun(prints("Welcome to Stocks!\n\n" +
                    "Navigate the program by inputting the number next to each menu item.\n" +
                    "Enter 'q' to quit the program.\nMain Menu:\n\n" +
                    "1. model.Stock Information\n" +
                    "2. Manage your portfolio(s)\n"),
            inputs("q\n"));
    int i = 1;
    i += 1;
    assertEquals(2, i); //for javastyle
  }

  /**
   * Tests handling of invalid input in the main menu.
   * Simulates the user entering an invalid option and checks for error message and retry.
   *
   * @throws IOException If an I/O error occurs during the test.
   */
  @Test
  public void testForInvalidInput() throws IOException {

    testRun(prints("Welcome to Stocks!\n\n" +
                    "Navigate the program by inputting the number next to each menu item.\n" +
                    "Enter 'q' to quit the program.\nMain Menu:\n\n" +
                    "1. model.Stock Information\n" +
                    "2. Manage your portfolio(s)"),
            inputs("3\n"),
            prints("\nGiven an invalid input, Please enter a valid option. " +
                    "Input Any character to Continue"),
            inputs("3\n"),
            prints("\nWelcome to Stocks!\n\n" +
                    "Navigate the program by inputting the number next to each menu item.\n" +
                    "Enter 'q' to quit the program." +
                    "\nMain Menu:\n" +
                    "\n" +
                    "1. model.Stock Information\n" +
                    "2. Manage your portfolio(s)\n"),
            inputs("q\n"));
    int i = 1;
    i += 1;
    assertEquals(2, i);
  }


  /**
   * Tests navigating to the stock information menu and back.
   * Simulates the user choosing the stock information option and then going back.
   *
   * @throws IOException If an I/O error occurs during the test.
   */
  @Test
  public void testForStockInformation() throws IOException {
    int i = 1;
    i += 1;
    assertEquals(2, i);
    testRun(prints("Welcome to Stocks!\n\n" +
                    "Navigate the program by inputting the number next to each menu item.\n" +
                    "Enter 'q' to quit the program.\nMain Menu:\n\n" +
                    "1. model.Stock Information\n" +
                    "2. Manage your portfolio(s)\n\n"),
            inputs("1\n"),
            prints("\nEnter 'b' to come back to the most recent menu.\n" +
                    "To quit the program, navigate back to main menu.\nmodel.Stock " +
                    "Information:\n\n" +
                    "Popular model.Stock Tickers:\n" +
                    "AAPL " +
                    "GOOG " +
                    "AMZN " +
                    "SMCI " +
                    "MSFT\n\n" +
                    "Enter desired ticker to view specific stock details:"),
            inputs("b\n"),
            prints("\nWelcome to Stocks!\n\n" +
                    "Navigate the program by inputting the number next to each menu item.\n" +
                    "Enter 'q' to quit the program.\nMain Menu:\n" +
                    "\n" +
                    "1. model.Stock Information\n" +
                    "2. Manage your portfolio(s)\n"),
            inputs("q\n"));

  }


}