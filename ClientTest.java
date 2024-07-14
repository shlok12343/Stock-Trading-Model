import org.junit.Before;
import org.junit.Test;

import model.Client;
import model.SmartPortfolio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the functionality of the Client class to ensure it properly handles operations related to
 * managing portfolios. This includes testing the creation of portfolios and the retrieval of
 * existing portfolios. The tests aim to validate that the model.Client class meets the business
 * requirements for accurately tracking and managing multiple portfolio entries
 * associated with a single client.
 */
public class ClientTest {
  private Client client;

  /**
   * Initializes a new model.Client object before each test execution to ensure test isolation.
   * This setup method is crucial as it prepares a consistent state for the tests by creating a
   * model.Client named "Janet Lim". This approach ensures that each test starts with a clean slate,
   * avoiding dependencies between tests and potential side effects from previous tests.
   */
  @Before
  public void setUp() {
    client = new Client("Janet Lim");
  }

  /**
   * Tests the ability of the model.Client class to correctly create and retrieve portfolios.
   * This method first creates a portfolio named "Tech model.Portfolio" and then checks whether this
   * portfolio can be successfully retrieved from the client's portfolio list. The test verifies
   * the functionality of both creating a new portfolio and retrieving it by name, ensuring that
   * the create operation adds the portfolio correctly
   * and the get operation retrieves it accurately.
   */
  @Test
  public void testGetPortfolios() {
    client.createPortfolio("Tech model.Portfolio");
    assertTrue("The portfolio list should contain the 'Tech model.Portfolio'",
            client.getPortfolios().containsKey("Tech model.Portfolio"));
  }

  /**
   * Tests the portfolio creation logic within the model.Client class to ensure that a
   * new portfolio can be created and is returned correctly. This test specifically
   * checks if the newly created portfolio object matches an expected portfolio object
   * created with the same parameters. It tests the creation process
   * by comparing the result of the createPortfolio
   * method against a new instance of a model.Portfolio, verifying that the properties and linkage
   * to the client are correctly established.
   */
  @Test
  public void testCreatePortfolio() {
    assertEquals("Newly created portfolio should match the expected portfolio object",
            new SmartPortfolio("Tech model.Portfolio", client),
            client.createPortfolio("Tech model.Portfolio"));
  }
}
