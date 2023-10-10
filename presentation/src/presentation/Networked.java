package presentation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Networked {
	Scanner sc=new Scanner(System.in);
	ServerSocket ss=new ServerSocket(6666);  
	 Socket s=ss.accept();  
	 DataInputStream din=new DataInputStream(s.getInputStream());  
	 DataOutputStream dout=new DataOutputStream(s.getOutputStream()); 
	char mBoard[] = {'1','2','3','4','5','6','7','8','9'};
	int BOARD_SIZE = 9;
	
	 static char HUMAN_PLAYER = 'X';
	 static char COMPUTER_PLAYER = 'O';
	
	    Networked() {	
	    
		
		char turn = HUMAN_PLAYER;    // Human starts first
		
		
	    	int  win = 0;                // Set to 100,-100, or0 when game is over
		
		// Keep looping until someone wins or a tie
		while (win == 0)
		{	
			displayBoard();

			if (turn == HUMAN_PLAYER)
			{   
				getUserMove(null);
				turn = COMPUTER_PLAYER;
			}
			else
			{   
				getComputerMove();
				turn = HUMAN_PLAYER;
			}

			win = checkForWinner();
		}

		displayBoard();

		// Report the winner
		System.out.println();
		if (win == 2){
			System.out.println("It's a tie.");
			din.close();  
			 s.close();  
			 ss.close(); 
		             }
		              
		else if (win == -100) {
			System.out.println(HUMAN_PLAYER + " wins!");
			din.close();  
			 s.close();  
			 ss.close(); 
		                       }
		else if (win == 100) {
			System.out.println(COMPUTER_PLAYER + " wins!");
			din.close();  
			 s.close();  
			 ss.close(); 
		                     }
		else
			System.out.println("There is a logic problem!");
	}
	
	void displayBoard()	{
		System.out.println();
		System.out.println(mBoard[0] + " | " + mBoard[1] + " | " + mBoard[2]);
		System.out.println("-----------");
		System.out.println(mBoard[3] + " | " + mBoard[4] + " | " + mBoard[5]);
		System.out.println("-----------");
		System.out.println(mBoard[6] + " | " + mBoard[7] + " | " + mBoard[8]);
		System.out.println();
	}
	
	
	 int checkForWinner() {
		
		// Check horizontal wins
		for (int i = 0; i <= 6; i += 3)	{
			if (mBoard[i] == HUMAN_PLAYER && 
				mBoard[i+1] == HUMAN_PLAYER &&
				mBoard[i+2]== HUMAN_PLAYER)
				return -100;
			if (mBoard[i] == COMPUTER_PLAYER && 
				mBoard[i+1]== COMPUTER_PLAYER && 
				mBoard[i+2] == COMPUTER_PLAYER)
				return 100;
		}
	
		// Check vertical wins
		for (int i = 0; i <= 2; i++) {
			if (mBoard[i] == HUMAN_PLAYER && 
				mBoard[i+3] == HUMAN_PLAYER && 
				mBoard[i+6]== HUMAN_PLAYER)
				return -100;
			if (mBoard[i] == COMPUTER_PLAYER && 
				mBoard[i+3] == COMPUTER_PLAYER && 
				mBoard[i+6]== COMPUTER_PLAYER)
				return 100;
		}
	
		// Check for diagonal wins
		if ((mBoard[0] == HUMAN_PLAYER &&
			 mBoard[4] == HUMAN_PLAYER && 
			 mBoard[8] == HUMAN_PLAYER) ||
			(mBoard[2] == HUMAN_PLAYER && 
			 mBoard[4] == HUMAN_PLAYER &&
			 mBoard[6] == HUMAN_PLAYER))
			return -100;
		if ((mBoard[0] == COMPUTER_PLAYER &&
			 mBoard[4] == COMPUTER_PLAYER && 
			 mBoard[8] == COMPUTER_PLAYER) ||
			(mBoard[2] == COMPUTER_PLAYER && 
			 mBoard[4] == COMPUTER_PLAYER &&
			 mBoard[6] == COMPUTER_PLAYER))
			return 100;
	
		// Check for tie
		for (int i = 0; i < BOARD_SIZE; i++) {
			// If we find a number, then no one has won yet
			if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER)
				return 0;
		}
	
		// If we make it through the previous loop, all places are taken, so it's a tie
		  return 2;
	 }
	 void getUserMove(String args[])throws Exception
		{
			// Eclipse throws a NullPointerException with Console.readLine
			// Known bug: https://bugs.eclipse.org/bugs/show_bug.cgi?id=122429
			//Console console = System.console();
		  
			// public String gh(String args[])throws Exception{
			 	System.out.println("my turn");
			 Scanner sc= new Scanner(System.in);	
			 
		   
			
              //return str;
			 //System.out.println("client says: "+str);  
			 //str2=br.readLine(); 	 
			 
			int move = -1;
			
			while (move == -1) {			
				//try {
					String str="",str2="";  
					 while(!str.equals("stop")){  
					 str=din.readUTF();
					 //move=Integer.parseInt(str);
					System.out.print("Enter your move: ");
					 move=Integer.parseInt(str);
				
				/*	while (move < 1 || move > BOARD_SIZE || 
						   mBoard[move-1] == HUMAN_PLAYER || mBoard[move-1] == COMPUTER_PLAYER) {
						
						if (move < 1 || move > BOARD_SIZE)
							System.out.println("Please enter a move between 1 and " + BOARD_SIZE + ".");
						else
							System.out.println("That space is occupied.  Please choose another space.");
			
						System.out.print("Enter your move: ");
						 while(!str.equals("stop")){  
						 str=din.readUTF();
						 move=Integer.parseInt(str);
					}
				} }}
				catch (InputMismatchException ex) {
					System.out.println("Please enter a number between 1 and " + BOARD_SIZE + ".");
					String str="",str2="";  
					 while(!str.equals("stop")){  
					 str=din.readUTF();
					 move=Integer.parseInt(str);  // Get next line so we start fresh
					move = -1;
				}*/

		mBoard[move-1] = HUMAN_PLAYER;
	}}}
	 public  void getComputerMove()throws Exception{
			{
	 String str2;;
	 str2=sc.nextLine();
	 dout.writeUTF(str2);  
	 dout.flush();
	  int j = Integer.parseInt(str2);    
    	mBoard[j-1]=COMPUTER_PLAYER;
				}
		// See if there's a move O can make to block X from winning

			//public static void main(String[] args) {		
			//	new TicTacToeConsole();		
			//}
	
		}
}
   
	
