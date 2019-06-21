# ConnectFour

### Setup

* Install Gradle `brew install gradle`

* Build and run the application `./gradlew run`

* Run tests `./gradlew test`

### Architecture

##### MVC pattern
* Model
  
  `Board` - holds all the information about the game, it is a 2 dimensional array as a grid
  
  `Player` - represents a game player, its symbol and play strategy

* View

  `BoardView` - graphical ascii presentation of `Board` model

* Controller
  
  `GameController` - controls how the game runs, takes and validates input, maintains the loop, and executes commands
  
`Domain` package contains classes for business logic used by the controller and models

* `BoardManager` - responsible for updating the board and getting information about the board

* `InputValidator` - validates user input

* `Judge` - decides if a winning condition is met

* `Strategy` - defines how the player plays the game

* `ConnectFour` - main class and entry point of the game
  
  board size can be specified this way
  `val board = Board(row = 6, column = 7)`

  players can have various strategies
  ```
          val HUMAN_PLAYER_1 = Player("*", HumanPlayerStrategy())
          val HUMAN_PLAYER_2 = Player("#", HumanPlayerStrategy())
          val BOT_PLAYER_1 = Player("_", EasyBotPlayerStrategy(boardManager = boardManager))
          val BOT_PLAYER_2 = Player("x", HardBotPlayerStrategy(boardManager = boardManager))
  ```

  there could be as many players as possible
  ```
          val playersForNormalMode = listOf(HUMAN_PLAYER_1, BOT_PLAYER_1)
          val playersForBothHumans = listOf(HUMAN_PLAYER_1, HUMAN_PLAYER_2)
          val playersForBothBots = listOf(BOT_PLAYER_1, BOT_PLAYER_2)
          val playersFoursome = listOf(HUMAN_PLAYER_1, HUMAN_PLAYER_2, BOT_PLAYER_1, BOT_PLAYER_2)
  ```
  
##### Notes

* DI not implemented for the sake of simplicity and minimum dependencies

* Options for various players and strategies could be implemented as program parameters, not implemented for now due to timeframe consideration, hopefully the points listed above explain well

* Just cover unit testing, no tests for `GameController` as it could potentially go to higher level such as functional testing 