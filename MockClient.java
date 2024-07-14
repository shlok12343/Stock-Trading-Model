package mock;

import java.util.HashMap;

import model.Client;
import model.IClient;
import model.IRetrivableClient;
import model.Portfolio;
import model.SmartPortfolio;

/**
 * Mock for client.
 */
public class MockClient implements IClient, IRetrivableClient {
  private String name;
  private HashMap<String, SmartPortfolio> portfolios;

  public MockClient() {
    this.name = "user1";
    this.portfolios = new HashMap<>();
  }

  @Override
  public HashMap<String, SmartPortfolio> getPortfolios() {
    return portfolios;
  }

  @Override
  public Portfolio createPortfolio(String portfolioName) {
    SmartPortfolio portfolio = new SmartPortfolio(portfolioName, new Client(name));
    portfolios.put(portfolioName, portfolio);
    return portfolio;
  }

  @Override
  public void savePortfolios(String directory) {
    this.name = "user1";
  }

  @Override
  public void loadPortfolios(String directory) {
    this.name = "user1";
  }
}
