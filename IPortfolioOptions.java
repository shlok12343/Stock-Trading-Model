package controller;

import model.SmartPortfolio;

/**
 * The controller.IPortfolioOptions interface defines the contract for portfolio operation
 * functionalities
 * within a stock management application. It requires implementing classes to provide a method
 * for managing various portfolio operations.
 */
public interface IPortfolioOptions {
  /**
   * Executes various operations on a given portfolio. This method serves as the central
   * interaction point for performing different actions on a portfolio, such as viewing,
   * adding, or removing stocks, rebalancing, or analyzing the portfolio's performance.
   *
   * @param p The portfolio on which operations will be performed.
   */
  void portfolioOperation(SmartPortfolio p);
}
