package mock;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import model.Client;
import model.SmartPortfolio;
import view.text.IView;

/**
 * Mock for IView.
 */
public class MockView implements IView {

  private Appendable out;
  int field;

  /**
   * Constructor to initialize the output stream.
   *
   * @param out The output stream to which messages will be printed.
   */
  public MockView(Appendable out) {
    this.out = out;
  }

  private void messageAppend(String message) throws IllegalStateException {
    try {
      out.append(message);

    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  @Override
  public void welcomeScreen() {
    messageAppend("");
  }

  @Override
  public void goBack() {
    messageAppend("");
  }

  @Override
  public void showMainMenu() {
    messageAppend("");
  }

  @Override
  public void showStockInfoMenu() {
    messageAppend("");
  }

  @Override
  public void showManagePortfolioMenu() {
    messageAppend("");
  }

  @Override
  public void showStockStats(String ticker) {
    messageAppend("");
  }

  @Override
  public void showCreatePortfolio() {
    messageAppend("");
  }

  @Override
  public void viewExistingPortfolios(Client c) {
    messageAppend("");
  }

  @Override
  public void showSpecificPortfolio(SmartPortfolio p, LocalDate l) {
    messageAppend("");
  }

  @Override
  public void writeMessage(String s) {
    messageAppend("");
  }

  @Override
  public String doubleToString(Double d) {
    return "";
  }

  @Override
  public void showOptionError() {
    messageAppend("");
  }

  @Override
  public void showAddStocks() {
    messageAppend("");
  }

  @Override
  public LocalDate provideDate(Scanner in) {
    return LocalDate.of(2023, 1, 2);
  }

  @Override
  public int getValidInteger(Scanner in) {
    return 0;
  }

  @Override
  public void getListCrossovers(ArrayList<LocalDate> a) {
    messageAppend("");
  }

  @Override
  public void showPortfolioOptions() {
    field = 1;

  }
}
