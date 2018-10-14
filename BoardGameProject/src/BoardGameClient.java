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
  
	public static void main(String[] args) 
	{
		
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
			  
			// Create an output stream to send data to the server
			  DataOutputStream osToServer = new DataOutputStream(connectToServer.getOutputStream());
			  
			  while (connect) {
				  
				  //Ask the client if it is ready
				  System.out.print("Welcome to this awesome board game - type: 'yes' to start and 'no' to exit");
				  if (input.next().equals("no")) {	
					  System.out.print("You have exited the game");	
					  break;	
				  }
				  
				  //The client rolls the dice by using the dice class and receives a random number
				  System.out.print("Please roll the dice (until now just enter 2)");
				  int diceRoll = input.nextInt(); 
				  
				  //Send the diceroll to the server
				  osToServer.writeInt(diceRoll);
				  
				 //flushes all the streams of data and executes them completely 
				  //and gives a new space to new streams 
				  osToServer.flush();
				  
				  //Get updated score from server
				  double clientScore = isFromServer.readInt();
				  
				  //print out score
				  System.out.println("Your new score is: " + clientScore);
			 
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
		 * 
		 * 
		 * 
		 * */

