package model;

import java.util.HashMap;

/**
 * The model.IClient interface defines the operations for managing a client's investment portfolios.
 * Implementations of this interface provide functionality to create, retrieve, save,
 * and load portfolios.
 * <p>
 * This interface serves as a contract for client-related operations, including
 * handling multiple portfolios and ensuring that the portfolios can be persistently
 * stored and retrieved from a specified directory.
 * </p>
 */
public interface IClient {
  /**
   * Gets the list of portfolios owned by the client.
   *
   * @return a HashMap of portfolios owned by the client
   */
  HashMap<String, SmartPortfolio> getPortfolios();

  /**
   * Creates a new portfolio with the specified name for the client.
   *
   * @param portfolioName the name of the new portfolio
   * @return the newly created portfolio
   */
  Portfolio createPortfolio(String portfolioName);


}
