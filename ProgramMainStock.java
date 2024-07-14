

import java.io.InputStreamReader;

import controller.IStockController;
import controller.gui.StockControllerGUI;
import controller.text.StockController;
import model.IModel;
import model.Model;
import view.gui.IViewGUI;
import view.gui.TextViewGUI;

/**
 * The ProgramMainStock class serves as the entry point for the stock management application.
 * It initializes the necessary components and starts the application by invoking the controller.
 */
public class ProgramMainStock {

  /**
   * The main method initializes the application by creating a Readable and Appendable
   * for input and output, respectively, and starts the StockController.
   *
   * @param args Command-line arguments (not used).
   */
  public static void main(String[] args) {

    if (args.length == 1) {
      Readable rd = new InputStreamReader(System.in);
      Appendable ap = System.out;
      IStockController stockController2 = new StockController(rd, ap);
      stockController2.goController();
    } else {
      IModel m = new Model();
      IViewGUI v = new TextViewGUI();
      IStockController stockController = new StockControllerGUI(m, v);
      stockController.goController();
    }

  }

}
