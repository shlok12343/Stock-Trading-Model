package model;

import java.util.HashMap;

import model.IClient;
import model.Portfolio;


/**
 * The model.Client class represents a client who can manage multiple portfolios.
 * Each client has a name and a collection of portfolios.
 */
public class Client implements IClient {
  protected String name;
  protected HashMap<String, SmartPortfolio> portfolios;

  /**
   * Constructs a new model.Client with the specified name.
   *
   * @param name the name of the client
   */
  public Client(String name) {
    this.name = name;
    this.portfolios = new HashMap<>();
  }

  /**
   * Returns the collection of portfolios managed by this client.
   *
   * @return a HashMap containing the client's portfolios, where the keys are portfolio names
   *         and the values are model.Portfolio objects
   */
  @Override
  public HashMap<String, SmartPortfolio> getPortfolios() {
    return portfolios;
  }

  /**
   * Creates a new portfolio with the specified name and adds it to the client's collection
   * of portfolios.
   *
   * @param portfolioName the name of the new portfolio
   * @return the newly created model.Portfolio object
   */
  @Override
  public Portfolio createPortfolio(String portfolioName) {
    SmartPortfolio newPortfolio = new SmartPortfolio(portfolioName, this);
    portfolios.put(portfolioName, newPortfolio);
    return newPortfolio;
  }

  /**
   * Getter method for name.
   *
   * @return name
   */
  public String getName() {
    return name;
  }


}
