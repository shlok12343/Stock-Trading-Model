# Simulated-Trading-Model
The application helps users simulate stock market investments by allowing them to create and manage virtual stock portfolios. It aims to provide a risk-free environment for beginners to learn about stock investments and test various trading strategies.

Key Features
My project involves developing an investment simulation application that educates new investors on managing stock portfolios without using real money. This application uses a model-view-controller (MVC) architecture and is designed to be an educational tool for simulating investment strategies. Here's a detailed description of your project:

### Project Overview
The application helps users simulate stock market investments by allowing them to create and manage virtual stock portfolios. It aims to provide a risk-free environment for beginners to learn about stock investments and test various trading strategies.

### Key Features
1. **Portfolio Management**: Users can create multiple portfolios, adding or removing stocks, and observing how their decisions affect the portfolio's value over time.
2. **Stock Market Simulation**: Utilizes historical stock data from the Alpha Vantage API to provide realistic stock market conditions.
3. **Investment Strategies Simulation**: Users can experiment with different investment strategies to see how they would perform in real market conditions.

### Technical Components
- **Alpha Vantage API**: This API provides historical stock data that the application uses to simulate stock prices and market conditions.
- **JSON Files**: Portfolios are saved in human-readable JSON files, allowing users to save and retrieve their investment data conveniently.
- **JUnit Testing**: Ensures that all parts of the application function correctly through comprehensive unit testing, focusing on reliability and performance.
- **GUI Interface**: Developed using Java Swing, the GUI showcases the application’s functionality in a user-friendly manner. It includes features like buying and selling stocks, viewing portfolio composition and value, and displaying investment outcomes through various graphical representations.

### Specific Functionalities
- **X-Day Moving Average Calculation**: The application allows users to calculate and visualize the moving average of a stock over a specified number of days, helping in trend analysis.
- **Portfolio Performance Charts**: Users can generate charts showing the progression and performance of their portfolios over time, aiding in visual comparison and strategy assessment.
- **Portfolio Rebalancing**: Features tools to redistribute investments within a portfolio to maintain desired asset allocations, reflecting common real-world investment strategies.

### User Interface
The GUI is designed to be intuitive and accessible, with clear visual cues and straightforward navigation to minimize user errors and enhance user experience. It supports both detailed interactions via forms and quick overviews via dashboard-style displays, ensuring that users can effectively manage and analyze their portfolios.

### Development Environment and Tools
- **Java Swing for GUI development**: Utilized for crafting the graphical user interface, providing a robust platform for building desktop applications.
- **MVC Architecture**: Ensures a clean separation of concerns, making the application maintainable and scalable.
- **Design Principles**: Adheres to SOLID design principles to enhance modularity and flexibility of the code.

