package view.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import controller.IPortfolioOptions;
import controller.IStockController;
import model.RetrivableClient;
import model.SmartPortfolio;


/**
 * The ViewPortfolios class handles the user interactions for viewing and
 * managing existing portfolios.
 * It allows the user to view details of a specific portfolio and add stocks to it.
 */
public class GUIViewForPortfoliosGUI extends JFrame implements ViewCommands, ActionListener {
  IStockController user;
  IViewGUI v;
  private JTextField input;



  /**
   * Constructs a new ViewPortfolios instance with the specified view, input scanner, and client.
   *
   * @param v    the view to interact with the user
   * @param user the client whose portfolios are being viewed
   */
  public GUIViewForPortfoliosGUI(IViewGUI v, IStockController user) {

    this.v = v;
    this.user = user;
    setSize(700, 700);
    setLocation(100, 200);
    this.setLayout(new FlowLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    input = new JTextField(10);
    v.viewExistingPortfolios(user.getUser(), this);
    this.add(input);
    this.add(v.createButton("Enter text", "entertext", this));


    v.goBack(this);
    this.add(v.createButton("Back", "back", this));

    pack();


  }

  /**
   * Executes the process for viewing and managing existing portfolios.
   * Displays the user's existing portfolios, allows the user to select a portfolio,
   * view its details, and add stocks to the selected portfolio.
   * It validates stock ticker symbols and fetches historical stock data using the AlphaVantageAPI.
   */
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
    }

    if (e.getActionCommand().equals("entertext")) {
      String s = input.getText();
      if (!user.getUser().getPortfolios().containsKey(s)) {
        JOptionPane.showMessageDialog(null, "Error: No portfolio with " +
                "the name " + s + " exists", "Error", JOptionPane.ERROR_MESSAGE);
        pack();
      } else {
        this.setVisible(false);
        RetrivableClient c = user.getUser();
        SmartPortfolio p = c.getPortfolios().get(s);
        IPortfolioOptions ipo = new GUIViewForPortfolioOp(v, user);
        ipo.portfolioOperation(p);
        user.setClient(c);
      }


    }
  }


}
