package model;

/**
 * an interface to hold a client that is retrivble
 * and save in json files.
 */
public interface IRetrivableClient {
  /**
   * Saves all portfolios owned by the client to the specified directory.
   * <p>
   * This method serializes the current state of each portfolio and writes them to the given
   * directory.The saved files can be used to restore the portfolios later, maintaining the
   * client's investment records.
   * </p>
   *
   * @param directory the directory path where the portfolios will be saved.
   *                  The directory must be accessible and writable.
   */
  void savePortfolios(String directory);

  /**
   * Loads portfolios from the specified directory into the client's account.
   * <p>
   * This method reads the portfolio files from the given directory and restores them into the
   * client's collection of portfolios. It is useful for reloading the investment state after
   * application restarts or moving data between different systems.
   * </p>
   *
   * @param directory the directory path from where the portfolios will be loaded.
   *                  The directory must contain valid portfolio files.
   */
  void loadPortfolios(String directory);
}
