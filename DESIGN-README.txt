=====Design Description====

***Assignment 6 Changes***
1. We expanded our application to include a graphical user interface (GUI) and integrated it with a corresponding controller and view to improve user interaction and experience. Our GUI uses different JFrames. We were able to implement this using the command
design pattern in class. The method of our command interface sets these JFrames to visible. It makes the interface more user friendly. We specifically use JButtons to simplify navigation. We have both a GUI and Text based Controller so the user has the freedom to choose. We use error messages for incorrect user inputs.

OVERVIEW

The Stock and Portfolio Management System is designed to provide users with a comprehensive tool to manage their stock investments. The system allows users to create and manage portfolios, view detailed stock information, and perform various stock-related calculations. The application aims to simplify the process of tracking and analyzing stock investments, making it accessible to both novice and experienced investors.

==COMPONENTS==

MODEL
model.Client: Represents a user of the system, containing user details and their stock portfolios.
model.Portfolio: Represents a collection of stocks owned by the client. Contains methods to add and manage stocks.
model.Stock: Represents an individual stock with attributes like ticker symbol, historical prices, and quantity.
model.AlphaVantageAPI: Handles fetching stock data from the Alpha Vantage API.
model.Model: Implements the model.IModel interface, acting as the primary data handler for the application.
model.SmartPortfolio: Extends Portfolio to include more advanced functionalities for managing investments.
model.RetrivableClient: Manages the retrieval and storage of portfolios.
VIEW
view.gui.TextViewGUI: Provides a graphical interface for user interaction, allowing the display of text-based information within a GUI framework.
CONTROLLER
controller.gui.StockControllerGUI: Handles the overall control flow of the application, delegating tasks to appropriate models and views.
controller.gui.ManagePortfolioMenuGUI: Manages the portfolio-related functionalities such as creating and viewing portfolios.
controller.gui.PortfolioOptionsGUI: Controller for handling edits to a portfolio.
controller.gui.ViewPortfoliosGUI: Manages viewing and editing of existing portfolios.
controller.gui.CreatePortfolioGUI: Manages the creation of new portfolios.
controller.text.StockInformation: Manages the stock-related functionalities and calculations.
controller.StockInformationCommands: Handles commands related to stock information such as getting the closing price, calculating gain or loss, and retrieving moving averages and crossovers.
controller.text.PerformaceOverTime: Handles the performance analysis of a single stock over a specific time period.
controller.IPortfolioOptions: Interface for controllers handling edits to a portfolio.
controller.IStockControllerCommands: Interface for handling various stock controller commands.
Instructions and Example Flow

When the user starts the application (ProgramMainStock), the controller.gui.StockControllerGUI initializes the view.gui.TextViewGUI and presents the main menu. User inputs are captured and processed by the controller, which then updates the model and refreshes the view accordingly.

==FILE DESCRIPTIONS==

MODEL
model.Client.java: Contains details of the user and their stock portfolios.
model.Portfolio.java: Represents a stock portfolio with methods to manage stocks.
model.Stock.java: Represents individual stocks with attributes such as ticker symbol and historical prices.
model.AlphaVantageAPI.java: Handles the interaction with the Alpha Vantage API to fetch stock data.
model.Model.java: Implements the model.IModel interface and handles the primary data operations.
model.IModel.java: Interface defining model operations.
model.SmartPortfolio.java: Extends Portfolio to include more advanced functionalities.
model.RetrivableClient.java: Manages retrieval and storage of user portfolios.
model.IClient.java: Interface defining client operations.
model.IStock.java: Interface defining stock operations.
model.APIInterface.java: Interface for handling API data.

VIEW
view.gui.TextViewGUI.java: Provides a graphical user interface for displaying text-based information.
view.gui.TextViewGUI.java: Provides a graphical user interface for displaying text-based information.
view.gui.GUIViewForPortfolioOp.java: Provides a graphical interface for performing operations on a portfolio, such as buying or selling stocks and viewing portfolio composition.
view.gui.GUIViewForCreatingPortfolio.java: Provides a graphical interface for creating new portfolios, including specifying portfolio details and adding initial stocks.
view.gui.ViewForPortfolioManagement.java: Provides a graphical interface for managing portfolios, allowing creation, viewing, and editing.
view.gui.GUIViewForPortfoliosGUI.java: Provides a graphical interface for viewing and selecting existing portfolios.
CONTROLLER
controller.gui.StockControllerGUI.java: Main controller handling user interactions and delegating tasks to appropriate components in the GUI.
controller.text.StockInformation.java: Handles stock-related functionalities and calculations.
controller.StockInformationCommands.java: Contains commands related to fetching and displaying stock information.
controller.text.PerformaceOverTime.java: Handles the performance analysis of a single stock over a specific time period.
controller.IPortfolioOptions.java: Interface for controllers handling edits to a portfolio.
controller.IStockControllerCommands.java: Interface for handling various stock controller commands.

PROGRAM AND SOME TESTS
ProgramMainStock.java: Main class to run the application.
ViewTest.java: Test class for view components.
PortfolioTest.java: Test class for portfolio-related operations.
ClientTest.java: Test class for client-related operations.
StockTest.java: Test class for stock-related operations.
