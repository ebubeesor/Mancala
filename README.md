# Mancala
## MancalaBoard Class
The MancalaBoard class is a graphical representation of the Mancala game using Swing components. It supports different game variations, such as Kalah and Ayo, by dynamically loading the appropriate game rules.

## Key Features:
-*Dynamic Game Rules:* The board supports multiple variations of Mancala, such as Kalah and Ayo. The game rules are selected based on the gameType parameter during initialization.
-*Interactive UI:* The board consists of 14 pits, including two stores (one for each player). Each pit is represented as a JButton, and their layout is set to resemble the traditional Mancala board configuration.
-*Player and CPU Interaction*: The game alternates turns between a human player and an AI opponent. The human player interacts with the board by clicking on the pits, while the AI makes random valid moves.
-*Game Status Updates:* The UI updates automatically to reflect changes in the number of stones in each pit. After each move, the board is refreshed to show the current state.
-*Error Handling:* Invalid moves are handled gracefully with error messages, ensuring a smooth gaming experience.
-*Initialization:*
The board is initialized with the following steps:

Set Layout: The board layout is configured using absolute positioning to arrange the buttons.
Load Game Rules: Based on the gameType ("Kalah" or "Ayo"), the appropriate rules are loaded.
Create Buttons: Buttons for each pit are created and added to the board, with positions and sizes adjusted to match the traditional Mancala layout.
Display Board: A JFrame is used to display the board, with the background color and title adjusted based on the game type.
