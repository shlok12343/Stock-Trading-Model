package model;

import java.io.File;
import java.util.Map;

import model.Client;
import model.IRetrivableClient;

/**
 * Represents a client that is retrivble
 * and save in json files.
 */
public class RetrivableClient extends Client implements IRetrivableClient {
  /**
   * Constructs a new model.Client with the specified name.
   *
   * @param name the name of the client
   */
  public RetrivableClient(String name) {
    super(name);
  }

  @Override
  public void savePortfolios(String directory) {
    for (Map.Entry<String, SmartPortfolio> entry : portfolios.entrySet()) {
      String filename = directory + "/" + entry.getKey().replace(" ", "_")
              + ".json";
      entry.getValue().savePortfolio(filename);
    }

  }

  @Override
  public void loadPortfolios(String directory) {
    File dir = new File(directory);
    File[] files = dir.listFiles((dir1, name) -> name.endsWith(".json"));
    if (files != null) {
      for (File file : files) {
        String filename = file.getName();
        String portfolioName = filename.substring(0, filename.length() - 5).replace("_",
                " ");
        SmartPortfolio portfolio = new SmartPortfolio(portfolioName, this);
        portfolio.loadPortfolio(file);
        portfolios.put(portfolioName, portfolio);
      }
    }


  }


}
