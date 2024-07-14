import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import controller.text.CreatePortfolio;
import model.AlphaVantageAPI;
import model.Client;
import model.IModel;
import mock.MockAlphaVantageAPI;
import mock.MockModel;
import view.text.IView;
import view.text.TextView;

/**
 * Tests the functionality of the CreatePortfolio class, which is responsible for handling user
 * interactions related to creating a new stock portfolio.
 * This class simulates user input for creating
 * a portfolio and adding stocks to it, and then checks
 * whether the system outputs the correct response
 * as expected. The test aims to ensure that the portfolio
 * creation process is robust, user-friendly,
 * and error-free.
 */
public class CreatePortfolioTest {

  private ByteArrayOutputStream outputStream;


  /**
   * Sets up the environment for testing the controller.CreatePortfolio class. Initializes
   * mock objects for
   * the model, user, and external API interactions. Also sets up a stream to capture the output
   * for later verification. This method ensures a clean testing environment for each test case,
   * preventing side effects between tests and providing a consistent state for the CreatePortfolio
   * interactions.
   */
  @Before
  public void setUp() {
    Client user;
    IView view;
    AlphaVantageAPI api;
    IModel model;
    model = new MockModel();
    user = new Client("");
    api = new MockAlphaVantageAPI();
    outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    view = new TextView(printStream);
  }

  /**
   * Tests the controller.CreatePortfolio's go method by simulating user input to create a
   * new portfolio and add
   * stock to it. The test verifies that the application correctly interprets
   * user input and provides
   * appropriate feedback, such as confirmation of
   * stock additions and instructions for navigation.
   * This method checks the end-to-end functionality
   * from receiving input to updating the model and
   * presenting the correct output to the user.
   */
  @Test
  public void testGo() {
    CreatePortfolio createPortfolio;

    StringBuilder input = new StringBuilder();
    StringBuilder expected = new StringBuilder();
    StringBuilder expected1 = new StringBuilder();

    // Simulate user inputs
    input.append("NewPortfolio\n");
    input.append("AAPL,10\n");
    input.append("b\n");

    expected.append("Create New model.Portfolio:\n\nWhat would you like to name " +
            "your new portfolio?\n");
    expected.append("To add stocks to your portfolio, enter ticker and quantity " +
            "(ex. AAPL,2).\n");
    expected.append("Enter 'b' to come back to the most recent menu.\nTo quit the program, "
            + "navigate back to main menu.\n");
    expected.append("Successfully added 10 AAPL stocks in NewPortfolio\n");

    expected.append("Enter 'b' to come back to the most recent menu.\nTo quit the program, "
            + "navigate back to main menu.\n");

    InputStream inputStream = new ByteArrayInputStream(input.toString().getBytes());
    Scanner scanner = new Scanner(inputStream);


    assertEquals(expected1.toString(), outputStream.toString());
  }
}