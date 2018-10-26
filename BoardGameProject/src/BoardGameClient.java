	
	/*import java.io.DataInputStream;
	import java.io.DataOutputStream;
	import java.io.IOException;
	import java.net.Socket;
	import java.util.Scanner;*/
	import java.io.*;
	import java.net.Socket;
	import java.util.Scanner;
	
	public class BoardGameClient {
		
		static int playerName; // Initialize variable to assign to joining players

		// Game message strings to inform the players
		public static String GAME_OVER_WON = "Game Over! Congratulations, you won! "; 
		public static String GAME_OVER_LOSE = "Game Over! You lost! :( ";
		public static String WELCOME_MESSAGE = "Welcome to this awesome board game - type: 'yes' to start and 'no' to exit ";
		public static String EXIT_GAME = "You have exited the game ";
		public static String ROLL_DICE = "\"Please roll the dice (until now just enter 2)\"";
		
		public static void main(String[] args) {
			
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
					  
					// Ask the client if it is ready
					System.out.print(WELCOME_MESSAGE);
					
					if (input.next().equals("no")) {	
						System.out.print(EXIT_GAME);	
						connect = false;	
					}
				
					// The client rolls the dice by using the dice class and receives a random number
					// NOTE: This should be inside an 'if' Statement controlled by the 'diceReady' boolean, to ensure that the dice can only be rolled once per turn. The boolean is reactivated by a signal from the server after each turn is finshed.
					System.out.print(ROLL_DICE);
					int diceRoll = input.nextInt(); 
					  
					// Tell the user what the roll was
					System.out.print("You rolled: " + diceRoll);
					  
					// Send the diceroll to the server
					osToServer.writeInt(diceRoll);
					  
					// Lock the dice until next round
					diceReady = false;//NOTE: This boolean must be accessed by the Server to turn true, and start the next round.
					// And here the 'if' statement with the rolling dice part of the game should be closed off
					  
					// flushes all the streams of data and executes them completely 
					// and gives a new space to new streams 
					osToServer.flush();
					  
					// Get updated score from server
					double clientScore = isFromServer.readInt();//Jens: I think this should be an int, not a double. Also, I think the variable should be made in the top, not here. I have it as playerScore. 
					// int clientScore = isFromServer.readInt();//Like this.
					  
					// print out score
					System.out.println("Your new score is: " + clientScore);
					  
					// GAME END CODE START
					// This part of the code is for when any player wins a game. NOTE: The 2 booleans (gameEnd & winCondition) is supposed to change from the server. gameEnd affects all players, and winCondition affects only the winner
					// NOTE: When the 2 booleans are sent from the Server, send the "winCondition" first, to ensure that the messages don't get mixed up.
					// If the game ends, and you are not the winner
					if (gameEnd == true && winCondition == true) {
						
						System.out.println(GAME_OVER_WON);//we tell that the game is over, and the player won
						//gameResetQuestion();//We run the part that ask if player wants a new game.
					} else if (gameEnd == true && winCondition == false) { //If the game ends, and you are not the winner
						
						System.out.println(GAME_OVER_LOSE);//we tell that the game is over, and the player did not win
						//gameResetQuestion();//We run the part that ask if player wants a new game.
					}
					//GAME END CODE END
				 
				} input.close();
				connectToServer.close();
				  
			} catch (IOException ex) {
				System.out.println(ex.toString() + '\n');
			}
		}
	}
			
