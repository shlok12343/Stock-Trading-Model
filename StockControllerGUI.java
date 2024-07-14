package controller.gui;

import controller.IStockController;
import model.IModel;
import model.RetrivableClient;
import view.gui.IViewGUI;

/**
 * The StockController class implements the IStockController interface to handle
 * the main control flow for user interactions in a stock management application.
 * It takes user input through a Readable source and outputs responses through an
 * Appendable target.
 */
public class StockControllerGUI implements IStockController {
  IModel md;
  IViewGUI vd;
  private RetrivableClient user;


  /**
   * Constructs a StockController that takes in a model and a view,It runs the gui and gets inputs
   * .
   *
   * @param md The input stream for reading user input.
   * @param vd The output stream for writing messages to the user.
   */
  public StockControllerGUI(IModel md, IViewGUI vd) {
    this.md = md;
    this.vd = vd;
    this.user = new RetrivableClient("user");
  }

  /**
   * Starts the main control loop of the application, displaying the main menu
   * and processing user input to navigate through different functionalities
   * such as viewing stock information or managing portfolios.
   */
  @Override
  public void goController() {
    vd.setText(this);
    vd.setVisible();
  }


  public void setClient(RetrivableClient c) {
    user = c;
  }


  public RetrivableClient getUser() {
    return user;
  }

}

