package view.gui;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.IPortfolioOptions;
import controller.IStockController;
import model.SmartPortfolio;

/**
 * The PortfolioOptions class implements the IPortfolioOptions interface to manage
 * portfolio operations within the stock management application. It provides various
 * functionalities allowing the user to view, add, remove, rebalance stocks, and
 * analyze portfolio performance.
 */
public class GUIViewForPortfolioOp extends JFrame implements IPortfolioOptions, ActionListener {
  IViewGUI v;
  JPanel panel;
  JPanel panel1;
  JTextField inputTicker;
  JTextField inputQuantity;
  SmartPortfolio p;
  String t;
  String addorsell;
  IStockController user;

  /**
   * Constructs a PortfolioOptions instance with dependencies for input and output.
   *
   * @param v The view interface to display outputs to the user.
   */
  public GUIViewForPortfolioOp(IViewGUI v, IStockController user) {
    this.user = user;
    this.v = v;
    setSize(700, 700);
    setLocation(100, 200);
    this.setLayout(new FlowLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(v.createButton("Buy Stock", "BuyStock", this));
    this.add(v.createButton("Sell Stock", "SellStock", this));
    this.add(v.createButton("Value of Portfolio", "Value", this));
    this.add(v.createButton("Composition of Portfolio", "Composition",
            this));
    this.add(v.createButton("Back", "Back", this));


    pack();
  }

  /**
   * Starts the portfolio operation interface, allowing the user to perform various
   * actions on a portfolio such as viewing its value, adding or removing stocks,
   * rebalancing, and viewing performance over time.
   *
   * @param p The portfolio on which operations are to be performed.
   */
  @Override
  public void portfolioOperation(SmartPortfolio p) {
    this.p = p;
    this.setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent e) {


    switch (e.getActionCommand()) {
      case "BuyStock":
        addorsell = "add";
        panel = new JPanel(new GridLayout(10, 5, 12, 5));
        inputTicker = new JTextField(20);
        inputQuantity = new JTextField(20);

        panel.add(new JLabel("Enter Ticker:"));
        panel.add(inputTicker);
        panel.add(v.createButton("Enter Ticker", "EnterTicker", this));
        panel.add(new JLabel("Enter Quantity:"));
        panel.add(inputQuantity);
        panel.add(v.createButton("Enter Quantity", "EnterQuantity",this));
        panel.add(new JLabel("enter the quantity and ticker button before okaying"));
        int result = JOptionPane.showConfirmDialog(null, panel, "Portfolio Stock Edit",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
          panel.setVisible(true);
        }
        break;
      case "SellStock":
        addorsell = "sell";
        panel1 = new JPanel(new GridLayout(10, 5, 12, 5));
        inputTicker = new JTextField(20);
        inputQuantity = new JTextField(20);

        panel1.add(new JLabel("Enter Ticker:"));
        panel1.add(inputTicker);
        panel1.add(v.createButton("Enter Ticker", "EnterTicker", this));
        panel1.add(new JLabel("Enter Quantity:"));
        panel1.add(inputQuantity);
        panel1.add(v.createButton("Enter Quantity", "EnterQuantity",this));
        panel1.add(new JLabel("enter the quantity and ticker button before okaying"));
        int result1 = JOptionPane.showConfirmDialog(null, panel1, "Portfolio Stock Edit",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result1 == JOptionPane.OK_OPTION) {
          panel1.setVisible(true);
        }
        break;
      case "Value":
        LocalDate l = v.provideDate(this);
        JOptionPane.showMessageDialog(null, "The value of your " +
                        "portfolio is "
                        + String.format("$%.2f", p.calculatePortfolioValue(l)), "Success",
                JOptionPane.INFORMATION_MESSAGE);
        break;
      case "Composition":
        LocalDate b = v.provideDate(this);
        JOptionPane.showMessageDialog(null, p.printStocks(b), "Success",
                JOptionPane.INFORMATION_MESSAGE);
        break;
      case "EnterTicker":
        String st = inputTicker.getText();
        if (!p.getApi().tickerCSVToList().contains(st)) {
          JOptionPane.showMessageDialog(null, "Error: Invalid stock " +
                  "symbol: " + st, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
          t = st;
        }

        break;
      case "EnterQuantity":
        String q = inputQuantity.getText();
        try {
          int quantity = Integer.parseInt(q);
          if (quantity < 0) {
            throw new NumberFormatException("Quantity cannot be negative.");
          }

          LocalDate date = v.provideDate(this);
          if (t == null || t.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter the " +
                    "ticker first.", "Error", JOptionPane.ERROR_MESSAGE);
          } else {

            try {
              if (addorsell.equals("add")) {
                p.addStock(p.getApi().makeStock(t), quantity, date);
              } else if (addorsell.equals("sell")) {
                p.removeStock(p.getApi().makeStock(t), quantity, date);
              }
            } catch (IllegalArgumentException g) {
              JOptionPane.showMessageDialog(null, g.getMessage(), "Error",
                      JOptionPane.ERROR_MESSAGE);
            }

            JOptionPane.showMessageDialog(null, "Successfully " +
                            "added " + quantity + " of " + t + " to " + p.getName(),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            panel.setVisible(false);
          }
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "Error: Invalid " +
                  "quantity: " + q, "Error", JOptionPane.ERROR_MESSAGE);
        }

        break;


      case "Back":
        this.setVisible(false);
        GUIViewForPortfoliosGUI vp = new GUIViewForPortfoliosGUI(v, user);
        vp.setVisable();
        break;

      default:
        break;
    }
    pack();
  }
}

