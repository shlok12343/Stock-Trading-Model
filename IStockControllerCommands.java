package controller;

import model.IModel;

/**
 * Interface for executing specific commands within the stock management system.
 * This interface allows the implementation of a command pattern, facilitating the execution of
 * various commands based on the stock model provided.
 */
public interface IStockControllerCommands {

  /**
   * Executes the main control operations of the stock management program using the provided model.
   * This method serves as the entry point for interacting with the model, allowing the controller
   * to perform actions such as querying or updating stock information based on user input or
   * automated processes.
   *
   * @param m The model used by the controller to interact with stock data. This model should
   *          implement the {@link IModel} interface and provide access to stock and
   *          client data necessary for operations.
   */
  void goController(IModel m);

}
