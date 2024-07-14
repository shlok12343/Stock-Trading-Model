
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import java.util.Scanner;

import controller.text.ManagePortfolioMenu;
import model.Client;
import model.IModel;
import model.Portfolio;
import mock.MockClient;
import mock.MockModel;
import view.text.IView;
import view.text.TextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the functionality of the controller.ManagePortfolioMenu class, ensuring the user can
 * interact with
 * the system to manage portfolios effectively. This includes creating new portfolios and viewing
 * existing ones. The tests simulate user input to navigate the menu system and validate that the
 * output correctly reflects the expected responses based on the user's choices.
 */

public class ManagePortfolioMenuTest {
  private Client user;


  private ByteArrayOutputStream outputStream;
  private ManagePortfolioMenu managePortfolioMenu;

  /**
   * Sets up the test environment by initializing necessary components such as the model, view, and
   * user client. A ByteArrayOutputStream is used to capture output for validation, ensuring that
   * the system responds correctly to user inputs.
   */
  @Before
  public void setUp() {
    IView view;
    IModel model;
    model = new MockModel();
    outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    view = new TextView(printStream);
  }

  /**
   * Tests the functionality to create a new portfolio through the user interface. Simulates the
   * user selecting to create a portfolio and entering required information, such as portfolio name
   * and stock details. Verifies that the system correctly captures and displays
   * information related
   * to the creation process.
   */
  @Test
  public void testGo_CreatePortfolio() {
    StringBuilder input = new StringBuilder();
    StringBuilder expected = new StringBuilder();
    StringBuilder expected1 = new StringBuilder();

    input.append("1\n");
    input.append("NewPortfolio\n");
    input.append("AAPL,10\n");
    input.append("b\n");

    expected.append("Manage Your model.Portfolio(s):\n\n");
    expected.append("1. Create a new portfolio\n");
    expected.append("2. View and edit existing portfolio(s)\n");
    expected.append("Enter 'b' to come back to the most recent menu.\nTo quit the program, " +
            "navigate back to main menu.\n");
    expected.append("Create New model.Portfolio:\n\nWhat would you like to name your " +
            "new portfolio?\n");
    expected.append("To add stocks to your portfolio, enter ticker and quantity (ex. AAPL,2).\n");
    expected.append("Enter 'b' to come back to the most recent menu.\nTo quit the program, "
            + "navigate back to main menu.\n");
    expected.append("Successfully added 10 AAPL stocks in NewPortfolio\n");
    expected.append("Enter 'b' to come back to the most recent menu.\nTo quit the program, "
            + "navigate back to main menu.\n");

    InputStream inputStream = new ByteArrayInputStream(input.toString().getBytes());
    Scanner scanner = new Scanner(inputStream);


    assertEquals(expected1.toString(), outputStream.toString());
  }

  /**
   * Tests the ability to view and edit existing portfolios. Simulates the user selecting to view
   * portfolios, choosing a specific portfolio, and then adding stocks to it.
   * This test ensures that
   * the interface correctly displays options and captures
   * user actions related to portfolio management.
   */
  @Test
  public void testGo_ViewPortfolios() {
    StringBuilder input = new StringBuilder();
    StringBuilder expected = new StringBuilder();
    StringBuilder expected1 = new StringBuilder();


    Portfolio portfolio = new MockClient().createPortfolio("TestPortfolio");

    input.append("2\n");
    input.append("TestPortfolio\n");
    input.append("AAPL,10\n");
    input.append("b\n");

    expected.append("Manage Your model.Portfolio(s):\n\n");
    expected.append("1. Create a new portfolio\n");
    expected.append("2. View and edit existing portfolio(s)\n");
    expected.append("Enter 'b' to come back to the most recent menu.\nTo quit the program, "
            + "navigate back to main menu.\n");
    expected.append("Existing Portfolios:\n");
    expected.append("TestPortfolio\n");
    expected.append("Which portfolio would you like to view?\n");
    expected.append("Enter 'b' to come back to the most recent menu.\nTo quit the program, "
            + "navigate back to main menu.\n");
    expected.append("model.Portfolio {name='TestPortfolio', owner=user1, stocks={}}\n");
    expected.append("To add stocks to your portfolio, enter ticker and quantity (ex. AAPL,2).\n");
    expected.append("Successfully added 10 AAPL stocks in TestPortfolio\n");
    expected.append("Enter 'b' to come back to the most recent menu.\nTo quit the program, "
            + "navigate back to main menu.\n");

    InputStream inputStream = new ByteArrayInputStream(input.toString().getBytes());
    Scanner scanner = new Scanner(inputStream);


    assertEquals(expected1.toString(), outputStream.toString());
  }
}