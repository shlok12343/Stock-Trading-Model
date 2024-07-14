package controller.text;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import controller.IStockController;
import controller.IStockControllerCommands;
import model.DirectoryCreator;
import model.IModel;
import model.Model;
import model.RetrivableClient;
import view.text.IView;
import view.text.TextView;

/**
 * The StockController class implements the IStockController interface to handle
 * the main control flow for user interactions in a stock management application.
 * It takes user input through a Readable source and outputs responses through an
 * Appendable target.
 */
public class StockController implements IStockController {
  final Readable in;
  final Appendable out;

  RetrivableClient user;

  /**
   * Constructs a StockController instance with the specified input and output streams.
   *
   * @param in  The input stream for reading user input.
   * @param out The output stream for writing messages to the user.
   */
  public StockController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
    user = new RetrivableClient("user");
  }

  /**
   * Starts the main control loop of the application, displaying the main menu
   * and processing user input to navigate through different functionalities
   * such as viewing stock information or managing portfolios.
   */
  @Override
  public void goController() {

    IModel md = new Model();
    Scanner in = new Scanner(this.in);
    IView vd = new TextView(out);

    boolean quit = false;
    while (!quit) {
      String dirPath = "runtime_data";
      DirectoryCreator.ensureDirectoryExists(dirPath);
      RetrivableClient user = new RetrivableClient("user");
      user.loadPortfolios(dirPath);
      try {
        DirectoryCreator.clearDirectory(Paths.get(dirPath));
      } catch (IOException e) {
        System.err.println("Failed to clear directory: " + e.getMessage());
      }


      IStockControllerCommands cmd = null;
      vd.welcomeScreen();
      vd.showMainMenu();
      String s = in.next();
      try {
        switch (s) {
          case "q":
            quit = true;
            break;
          case "1":
            cmd = new StockInformation(vd, in);
            break;
          case "2":
            cmd = new ManagePortfolioMenu(vd, in, this);
            break;
          default:
            vd.showOptionError();
            in.next();
        }
        if (cmd != null) {
          cmd.goController(md);
        }
      } catch (IllegalArgumentException e) {
        vd.writeMessage("Error: " + e.getMessage() + System.lineSeparator());
      }
    }
    user.savePortfolios("runtime_data");
  }

  /**
   * Sets the Client associated with this controller.
   *
   * @param c the Client object to be set for this controller
   */
  public void setClient(RetrivableClient c) {
    user = c;
  }

  /**
   * Gets the current Client using this controller.
   *
   * @return the current Client object
   */
  public RetrivableClient getUser() {
    return user;
  }


}

