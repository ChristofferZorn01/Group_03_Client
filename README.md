# Group_03_Server

Aalborg University Medialogy Group 3 Semester 3 2018 <br /> Dice Racing Digital Board Game Server, Version 1

===============================

Welcome to the 2018 Medialogy 3 Group 3 Dice Racing Digital Board game! <br />
Built in Java for the AAU Medialogy Programming MiniProject.
This game is run in the consol of the environment of which the files are opened.

------

This game will have players roll a die against each other to reach the final goal.

The rules are simple:

1: All players Start with 0 points. It is not possible to get less.

2: All players roll a die each round and the size of the die changes based on the number of players.
	Example: There are 4 players, so the dice the players roll has numberOfPlayers*2 sides = 8.
	
3: Whoever rolls the highest number, gets 1 one point.
	Example: Player 3 rolls the only 5, so he gets 1 point.
	
4: If 2 or more players rolls highest, they all lose 1 point.
	Example: Player 2 and Player 4 both roll highest with 4's, so they both loose 1 point.
	
5: The player who reaches the score to win first, wins the game. (That score can be changed in the VariableClass)

------

Installation guide:

0: Download (name of file / repository) from botht the Client and Server repository, and save to a directory of your choice.

1: Run the file "MainServer".

2: Run the file "Lobby".

3: Each Player have to run the file "BoardGameClient".

4: Players Connect to the Server Lobby.

5: When the Server Lobby is full ( 4 players ), the game starts.

6: Players will be told to roll the die by pressing 'r'.

7: The game will then roll the dice and tell the players the outcome of the roll and which player gets a point.

8: 6-7 will repeat until a player reaches the max score in the game, where the Server will tell all Players that the game is over, and tell the winning player that they won.

---

Last updated 28/10/2018
