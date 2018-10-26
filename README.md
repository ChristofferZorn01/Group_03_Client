# Group_03_Server

Aalborg University Medialogy Group 3 Semester 3 2018 <br /> Dice Racing Digital Board Game Server, Version 1

===============================

Welcome to the 2018 Medialogy 3 Group 3 Dice Racing Digital Board game! <br />
Built in Java for the AAU Medialogy Programming MiniProject.

------

This game will have players roll a die against each other to reach the final goal.

The rules are simple:

1: All players Start with 0 points. It is not possible to get less.

2: All players roll a die each round and the size of the die changes based on the number of players.
	Example: there is 4 players, so the dice the players roll has 5 sides, from 1-5 (#Specify when we're done)
	
3: Whoever rolls the highest number, gets 1 one point.
	Example: Player 3 rolls the only 5, so he gets 1 point.
	
4: If 2 or more players rolls highest, they all lose 1 point.
	Example: Player 2 and Player 4 both roll highest with 4's, so they both loose 1 point.
	
5: The player who reach 10 points first wins the game. 

------

Installation guide:

0: Download (name of file / repository) and save to a directory of your choice.

1: Run "MainServer".

2: Each Player run "BoardGameClient".

3: Players Connect to the Server Lobby.

4: When the Server Lobby is full, the game starts.

5: Players Will be told to roll the die by pressing "R".

6: Follow the rules and play the game.

------
##Below should be in the wiki if anything

6: When Players have rolled, the result is sent to the server and the player can not roll again until next round.

7: When the server has recieved a result from each player, the Server will compare the results and assign scores. Afterwards, the 	players will recieve signal that the next round has begun, and that they can roll again.

8: 5-7 will repeat until a player reaches the max score (10 points) in the game, where the Server will tell all Players that the game is over, and tell the winning player that they won

---

Last updated 26/10/2018
