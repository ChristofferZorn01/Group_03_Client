
public class BoardGameClient 
{

	public static void main(String[] args) 
	{
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

	}

}
