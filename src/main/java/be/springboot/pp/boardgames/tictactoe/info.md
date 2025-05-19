## Step 1: Requirements
- There is a 3x3 board
- two players can play the game
- the game is played in turns, each player has 1 move per turn
- 'X' and 'O' are the players
- in each turn a player puts their symbol on an empty cell
- the game ends when the board is full and it will be a draw
- a player wins when they have 3 of their symbols in a row or column or diagonal
- the game ends when one player has won
- 'X' always plays first

## Step 2: Identify entities
> trick: consider all nouns as entities, clarify if each noun needs a class progressively
- Board
- board cell
- Player: X and O
- Game
- symbol
- turn/move
- Game state: new, won, draw

## Step 3: Create a rough code structure
```
Game -> Board
    -> Player -> X and O
```
