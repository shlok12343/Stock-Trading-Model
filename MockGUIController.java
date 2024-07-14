package mock;

import controller.IStockController;
import model.IModel;
import model.RetrivableClient;
import view.gui.IViewGUI;

/**
 * A mock implementation of the IStockController interface for testing purposes.
 * This class simulates interactions between the controller, model, and view without
 * performing any real operations, allowing for controlled testing of the GUI components.
 */
public class MockGUIController implements IStockController {
  IModel md;
  IViewGUI vd;
  String text;

  /**
   * Constructs a MockGUIController with the specified model and view.
   *
   * @param md the model to be used by the controller
   * @param vd the view to be used by the controller
   */
  public MockGUIController(IModel md, IViewGUI vd) {
    this.md = md;
    this.vd = vd;
  }


  @Override
  public void goController() {
    text = "go controller";

  }

  @Override
  public void setClient(RetrivableClient c) {
    text = "set client";
  }

  @Override
  public RetrivableClient getUser() {
    return new RetrivableClient("Get User");
  }

  /**
   * Gets the text indicating the last simulated operation.
   *
   * @return the text indicating the last operation
   */
  public String getText() {
    return text;
  }

}
