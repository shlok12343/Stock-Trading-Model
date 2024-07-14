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

import controller.IStockController;
import controller.IStockControllerCommands;
import model.RetrivableClient;
import model.SmartPortfolio;

/**
 * The GUIViewForCreatingPortfolio class provides a graphical user interface (GUI) for creating
 * a new stock portfolio.
 * Users can specify the name of the new portfolio and add stocks to it through this interface.
 */
public class GUIViewForCreatingPortfolio extends JFrame implements ViewCommands, ActionListener {
  IStockController user;
  IViewGUI v;
  IStockControllerCommands cmd = null;

  private JTextField input;
  private JTextField inputTicker;
  private JTextField inputQuantity;
  SmartPortfolio p;
  RetrivableClient c;
  String t;
  JPanel panel;

  /**
   * Constructs a new GUIViewForCreatingPortfolio instance with the specified view and
   * stock controller.
   *
   * @param v    the view to interact with the user
   * @param user the stock controller to handle user interactions
   */
  public GUIViewForCreatingPortfolio(IViewGUI v, IStockController user) {
    this.v = v;
    this.user = user;


    setSize(700, 700);
    setLocation(100, 200);
    this.setLayout(new FlowLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    v.showCreatePortfolio(this);

    input = new JTextField(10);

    this.add(input);
    this.add(v.createButton("Enter text", "entertext", this));

    v.goBack(this);
    this.add(v.createButton("Back", "back", this));


    pack();
  }

  @Override
  public void setVisable() {
    this.setVisible(true);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("back")) {
      this.setVisible(false);
      ViewCommands cmd = new ViewForPortfolioManagement(v, user);
      cmd.setVisable();
    } else if (e.getActionCommand().equals("entertext")) {
      String s = input.getText();
      c = (RetrivableClient) user.getUser();
      p = (SmartPortfolio) c.createPortfolio(s);
      panel = new JPanel(new GridLayout(10, 5, 12, 5));
      inputTicker = new JTextField(20);
      inputQuantity = new JTextField(20);

      panel.add(new JLabel("Enter Ticker:"));
      panel.add(inputTicker);
      panel.add(v.createButton("Enter Ticker", "EnterTicker", this));
      panel.add(new JLabel("Enter Quantity:"));
      panel.add(inputQuantity);
      panel.add(v.createButton("Enter Quantity", "EnterQuantity", this));
      panel.add(new JLabel("enter the quantity and ticker button before okaying"));
      int result = JOptionPane.showConfirmDialog(null, panel, "Portfolio Stock Edit",
              JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

      if (result == JOptionPane.OK_OPTION) {
        panel.setVisible(true);
      }
    } else if (e.getActionCommand().equals("EnterTicker")) {
      String st = inputTicker.getText();
      if (!p.getApi().tickerCSVToList().contains(st)) {
        JOptionPane.showMessageDialog(null, "Error: Invalid stock " +
                "symbol: " + st, "Error", JOptionPane.ERROR_MESSAGE);
      } else {
        t = st;
      }
    } else if (e.getActionCommand().equals("EnterQuantity")) {
      String q = inputQuantity.getText();
      try {
        int quantity = Integer.parseInt(q);
        if (quantity < 0) {
          throw new NumberFormatException("Quantity cannot be negative.");
        }

        LocalDate date = v.provideDate(this);
        if (t == null || t.isEmpty()) {
          JOptionPane.showMessageDialog(null, "Please enter the ticker " +
                  "first.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
          p.addStock(p.getApi().makeStock(t), quantity, date);
          JOptionPane.showMessageDialog(null, "Successfully" +
                          " added " + quantity + " of " + t + " to " + p.getName(), "Success",
                  JOptionPane.INFORMATION_MESSAGE);
          panel.setVisible(false);
        }
      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Error: Invalid " +
                "quantity: " + q, "Error", JOptionPane.ERROR_MESSAGE);
      }
      pack();
    }

  }


}

