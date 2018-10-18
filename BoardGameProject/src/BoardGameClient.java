/*import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;*/
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BoardGameClient 
{
 static int playerName; 
	public static void main(String[] args) 
	{
		
		boolean diceReady = false;//this boolean is to ensure that the dice can only be rolled in a game, and only once per round
		//NOTE: This boolean must be updated by the Server after each round.
		
		boolean winCondition = false;//THIS is updated by Server *Only* if this client is the winner
		
		boolean gameEnd = false;//The is updated by the Server when the game ends, in all clients
		
		int playerScore = 0;//this will be updated through messages from the server after each round
		
		//public int playerNumber = [Recieve from Server];//When we connect to the server, we will receive a message telling this variable what it is.
		
		//In case the user needs to input, the scanner is declared 
		Scanner input = new Scanner(System.in);	
		//This boolean makes sure the communication between the client and server only
		//takes place when actually connected
		boolean connect = true;

		try {
			 
			// Create a socket to connect to the server
			  Socket connectToServer = new Socket("localhost", 7845);
			  
			  // Create an input stream to receive data from the server
			  DataInputStream isFromServer = new DataInputStream(connectToServer.getInputStream());
			  
			  playerName = isFromServer.readInt();
			  System.out.println("You are player number " + playerName);
			  
			// Create an output stream to send data to the server
			  DataOutputStream osToServer = new DataOutputStream(connectToServer.getOutputStream());
			  
			  while (connect) {
				  
				  //Ask the client if it is ready
				  System.out.print("Welcome to this awesome board game - type: 'yes' to start and 'no' to exit");
				  if (input.next().equals("no")) {	
					  System.out.print("You have exited the game");	
					  connect = false;	
				  }
				  
				 
				  //The client rolls the dice by using the dice class and receives a random number
				  //NOTE: This should be inside an 'if' Statement controlled by the 'diceReady' boolean, to ensure that the dice can only be rolled once per turn. The boolean is reactivated by a signal from the server after each turn is finshed.
				  System.out.print("Please roll the dice (until now just enter 2)");
				  int diceRoll = input.nextInt(); 
				  
				  //Tell the user what the roll was
				  System.out.print("You rolled: " + diceRoll);
				  
				  //Send the diceroll to the server
				  osToServer.writeInt(diceRoll);
				  
				  //Lock the dice until next round
				  diceReady = false;//NOTE: This boolean must be accessed by the Server to turn true, and start the next round.
				  //And here the 'if' statement with the rolling dice part of the game should be closed off
				  
				 //flushes all the streams of data and executes them completely 
				  //and gives a new space to new streams 
				  osToServer.flush();
				  
				  //Get updated score from server
				  double clientScore = isFromServer.readInt();//Jens: I think this should be an int, not a double. Also, I think the variable should be made in the top, not here. I have it as playerScore. 
				  //int clientScore = isFromServer.readInt();//Like this.
				  
				  //print out score
				  System.out.println("Your new score is: " + clientScore);
				  
				  //GAME END CODE START
				  //This part of the code is for when any player wins a game. NOTE: The 2 booleans (gameEnd & winCondition) is supposed to change from the server. gameEnd affects all players, and winCondition affects only the winner
				  //NOTE: When the 2 booleans are sent from the Server, send the "winCondition" first, to esure that the messages don't get mixed up.
				  if (gameEnd == true && winCondition == true)//If the game ends, and you are not the winner
					{
					  System.out.println("Game Over. Congratulations, you won!");//we tell that the game is over, and the player won
						//gameResetQuestion();//We run the part that ask if player wants a new game.
					}
					else if (gameEnd == true && winCondition == false)//If the game ends, and you are not the winner
					{
						System.out.println("Game Over. Sorry, you did not win.");//we tell that the game is over, and the player did not win
						//gameResetQuestion();//We run the part that ask if player wants a new game.
					}
				  //GAME END CODE END
			 
			  }  input.close();
				connectToServer.close();
			  
		} catch (IOException ex) {
			System.out.println(ex.toString() + '\n');
		}
		
	}
}
				
			  // TODO Auto-generated method stub
		
		/*THE CLIENT HAS TO:
		 * -CONNECT TO THE SERVER
		 * -WHEN CONNECTING TO THE SERVER, The Client Recieves a player number (The sever only needs to send an int, the rest can be done in the client-code)
		 * -WHEN TOLD BY THE SERVER, The Client has to roll a die, either in the client or in the server, and then send the result to the server. Aferwards, the roll function has to be locked with a boolean until the server sends a message that next round has started (out.writeBoolean(arg0);)
		 * -WHEN THE ROUND IS OVER IN THE SERVER, the client will recieve a message that the round is over, possibly the current scores, and a unlock of the booolean, so that the roll for the next round can be done.
		 * -WHEN A CLIENT WINS:All clients recieve the same message: 
		 * 		System.out.println("GAME IS OVER. WINNER IS: PLAYER " + [INT OF VARIABLE SENT FROM SEVER OF WINNER])
		 * 		System.out.println("NEW GAME? Y/N")
		 * IF Y, RECONNECT TO SERVER?
		 * IF N, end game.
		 * 
		 * 
		 * What else?
		 * 
		 * 
		 * */

//<<<<<<< HEAD
/*
//theorectical 
		public boolean diceReady = false;//this boolean is to ensure that the dice can only be rolled in a game, and only once per round
		
		public boolean winCondition = false;//THIS is updated by Server *Only* if this client is the winner
		
		public boolean gameEnd = false;//The is updated by the Server when the game ends, in all clients
		
		public int diceSize = numberOfPlayers + 1;//If we go with the decision that the size of the dice is based on the number of  players, we need to get 'numberOfPlayers' as an integer from the server when the game starts
		
		public int roll = 0;//since we have yet to start the game, we don't have the roll be game breaking
		
		public int playerScore = 0;//this will be updated through messages from the server after each round
		
		
		public int playerNumber = [Recieve from Server];//When we connect to the server, we will receive a message telling this variable what it is.
		
		//connecting to the server (Run only when starting a game)
		public void serverConnectingThingy()
		{
			[All the server connecting fiddely stuff];
			[The server updates here playerNumber];
			System.out.println("Connected to Server. Waiting for players. You are Player Number " + playerNumber);
			

		}
		
		//When the game Starts
		{
			System.out.println("Game Start!");
			[Message from Server that diceReady = true];
		}
		
		if (diceReady == true)//NOTE: the 'diceReady' boolean should be accessed by the Server, when a new roll starts
		{
			System.out.println("Dice is ready to roll! Push 'Roll' to roll");
			//[WHATEVER THE ROLL DIE BUTTON IS, IT SHOULD BE HERE]
			diceRoll();
			//SEND RESULT (roll) AFTER ROLLING TO SERVER HERE. NOTE: should the 'send' Command be here, or in diceRoll()?
			//When we send the result, we must also change a counter in the server, that ensures that the comparison of the rolls does not happen before all players have rolled
		}
		
		if (playerScore < 0)//if the playerScore goes below the minimum score possible
		{
			playerScore = 0;//It will be reset to 0
		}
		
		if (gameEnd == true && winCondition == false)//If the game ends, and you are not the winner
		{
			System.out.println("Game Over. Sorry, you did not win.");//we tell that the game is over, and the player did not win
			gameResetQuestion();//We run the part that ask if player wants a new game.
		}
		else if (gameEnd == true && winCondition == true)//If the game ends, and you are not the winner
		{
			System.out.println("Game Over. Congratulations, you won!");//we tell that the game is over, and the player won
			gameResetQuestion();//We run the part that ask if player wants a new game.
		}
		
		NOT IMPORTANT; USE DICEROLLING CLASS MADE ELSEWHERE
		public void diceRoll()//All the dice rolling action
		{
			roll = int(((Random(1, diceSize*100))%diceSize)+1);//We roll the die here
			System.out.println("Dice rolled. The result is: " + roll);//the tell the result to the user
			diceReady = false;//and this ensures that the die cannot be rolled until next round.
		}
		
		public void gameResetQuestion()//this runs when the game is over, and determines if a new game is played, or end the session
		{
			System.out.print("Do you want to play again? Y/N: ");//we ask the player
			[PLAYer WRITES Y or N];
			if Y
			{
				serverConnectingThingy();
			}
			else
			{
				[shutting down thighy]
			}
		}

*/

//AAHAHAWRGOERHJGPFDG11
//=======
//>>>>>>> branch 'master' of https://github.com/ChristofferZorn01/Group_03_Client.git
