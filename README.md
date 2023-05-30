# PixelBattle

This project is a game that uses sockets and the JavaFX library. It involves the interaction of 2 or more players over a network. The game uses its own protocol for transmitting data over sockets, with 4 types of messages

## Project Structure
- ExtremePixelBattle
    - .mvn
        - wrapper
    - src/main
        - java/en/itis/extreme_pixel_battle
            - controllers
            - models
            - sockets
            - PixelBattleApplication.java
            - Server.java
        - resources/en/itis/extreme_pixel_battle
            - view.fxml
    - .gitignore
    - mvnw
    - mvnw.cmd
    - pom.xml
    - 
The project has the following features:

- Networking architecture
- Its own protocol for transmitting data over sockets
- 4 types of messages for the protocol

It also includes the following components:

- Controllers
- Models
- Sockets
- Server.java
- view.fxml

## Getting Started

To run the ExtremePixelBattle project, follow these steps:

    Ensure that you have Java Development Kit (JDK) installed on your system.
    Clone the project repository.
    Open a terminal or command prompt and navigate to the project directory.
    Run the following command to compile and run the application:
    ./mvnw javafx:run
