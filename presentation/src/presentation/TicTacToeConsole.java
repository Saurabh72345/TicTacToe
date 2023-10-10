package presentation;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.Locale; 
import javax.speech.Central; 
import javax.speech.synthesis.Synthesizer; 
import javax.speech.synthesis.SynthesizerModeDesc; 
import java.util.Scanner;

public class TicTacToeConsole {
	char turn;
	int flag=0;

    char mBoard[] = {'1','2','3','4','5','6','7','8','9'};
	int BOARD_SIZE = 9;
	
	 static char HUMAN_PLAYER = 'X';
	 static char COMPUTER_PLAYER = 'O';
	
	Random mRand=new Random();
	
	public TicTacToeConsole() {	
		speak("welcome to tictac toe");
		
		turn = HUMAN_PLAYER;    // Human starts first
		int  win = 0;
		
		// Keep looping until someone wins or a tie
		while (win == 0)
		{	
			displayBoard();

			if (turn == HUMAN_PLAYER)
			{
				speak("your move");
				getUserMove();	
				turn = COMPUTER_PLAYER;
			}
			else
			{				
				getComputerMove();
				speak("moved");
				turn = HUMAN_PLAYER;
			}	

			win = checkForWinner();
		}

		displayBoard();

		// Report the winner
		System.out.println();
		if (win == 1)
			{System.out.println("It's a tie.");
			 speak("Its a tie");
			 flag=1;
			 	}
		else if (win == 2) {
			System.out.println(HUMAN_PLAYER + " wins!");
			speak("Congratulations you have won the game");
			//speak("done");
		}
		else if (win == 3)
			{System.out.println(COMPUTER_PLAYER + " wins!");
		   speak("Computer won");
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
	
	// Check for a winner.  Return
	//  0 if no winner or tie yet
	//  1 if it's a tie
	//  2 if X won
	//  3 if O won
	 int checkForWinner() {
		
		// Check horizontal wins
		for (int i = 0; i <= 6; i += 3)	{
			if (mBoard[i] == HUMAN_PLAYER && 
				mBoard[i+1] == HUMAN_PLAYER &&
				mBoard[i+2]== HUMAN_PLAYER)
				return 2;
			if (mBoard[i] == COMPUTER_PLAYER && 
				mBoard[i+1]== COMPUTER_PLAYER && 
				mBoard[i+2] == COMPUTER_PLAYER)
				return 3;
		}
	
		// Check vertical wins
		for (int i = 0; i <= 2; i++) {
			if (mBoard[i] == HUMAN_PLAYER && 
				mBoard[i+3] == HUMAN_PLAYER && 
				mBoard[i+6]== HUMAN_PLAYER)
				return 2;
			if (mBoard[i] == COMPUTER_PLAYER && 
				mBoard[i+3] == COMPUTER_PLAYER && 
				mBoard[i+6]== COMPUTER_PLAYER)
				return 3;
		}
	
		// Check for diagonal wins
		if ((mBoard[0] == HUMAN_PLAYER &&
			 mBoard[4] == HUMAN_PLAYER && 
			 mBoard[8] == HUMAN_PLAYER) ||
			(mBoard[2] == HUMAN_PLAYER && 
			 mBoard[4] == HUMAN_PLAYER &&
			 mBoard[6] == HUMAN_PLAYER))
			return 2;
		if ((mBoard[0] == COMPUTER_PLAYER &&
			 mBoard[4] == COMPUTER_PLAYER && 
			 mBoard[8] == COMPUTER_PLAYER) ||
			(mBoard[2] == COMPUTER_PLAYER && 
			 mBoard[4] == COMPUTER_PLAYER &&
			 mBoard[6] == COMPUTER_PLAYER))
			return 3;
	
		// Check for tie
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER)
				return 0;
		}
	
		// If we make it through the previous loop, all places are taken, so it's a tie
		return 1;
	}
	
	void getUserMove() 
	{ 
		int move = -1;
		while ( move == -1)
		try{ {
		     String response = JOptionPane.showInputDialog(null,"Enter a no. between 1 and 9");
		        move = Integer.parseInt(response);
		 
			while (move < 1 || move > BOARD_SIZE || mBoard[move-1] == HUMAN_PLAYER || mBoard[move-1] == COMPUTER_PLAYER) {
					
					if (move < 1 || move > BOARD_SIZE) 
						System.out.println("Please enter a move between 1 and " + BOARD_SIZE + ".");
					else
						System.out.println("That space is occupied.  Please choose another space.");
		
					System.out.print("Enter your move: ");

			  	     response = JOptionPane.showInputDialog(null,
				         "Enter a no. between 1 and 9");
				      move = Integer.parseInt(response);}
				  
				
			}}catch (NumberFormatException ex) {
				System.out.println("exiting");
				System.exit(0);
			
				
			}


		mBoard[move-1] = HUMAN_PLAYER;
	}
	
	void getComputerMove() 
	{
		int move;

		// First see if there's a move O can make to win
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
				char current = mBoard[i];
				mBoard[i] = COMPUTER_PLAYER;
				if (checkForWinner() == 3) {
					System.out.println("Computer is moving to " + (i + 1));
					return;
				}
				else
					mBoard[i] = current;
			}
		}

		// See if there's a move O can make to block X from winning
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) {
				char curr = mBoard[i];
				mBoard[i] = HUMAN_PLAYER;
				if (checkForWinner() == 2) {
					mBoard[i] = COMPUTER_PLAYER;
					System.out.println("Computer is moving to " + (i + 1));
					return;
				}
				else
					mBoard[i] = curr;
			}
		}

		// Generate random move
		do
		{
			move = mRand.nextInt(BOARD_SIZE);
		} while (mBoard[move] == HUMAN_PLAYER || mBoard[move] == COMPUTER_PLAYER);
			
		System.out.println("Computer is moving to " + (move + 1));

		mBoard[move] = COMPUTER_PLAYER;}
	void speak(String k){ 
     try{
		// Set property as Kevin Dictionary 
		System.setProperty( 
			"freetts.voices", "com.sun.speech.freetts.en.us"+ ".cmu_us_kal.KevinVoiceDirectory"); 

		// Register Engine 
	    Central.registerEngineCentral( "com.sun.speech.freetts"+".jsapi.FreeTTSEngineCentral"); 

		// Create a Synthesizer 
		Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US)); 

		// Allocate synthesizer 
		 synthesizer.allocate(); 

		// Resume Synthesizer 
		 synthesizer.resume(); 

		// Speaks the given text 
		// until the queue is empty. 
		synthesizer.speak(k, null);
		// Deallocate the Synthesizer.
       }
       catch(Exception e) { 
			e.printStackTrace();  
			}
	
    }
	
public static void main(String[] args){	
	  System.out.println("xxxxxxxxxxxTIC TAC TOE CONSOLExxxxxxxxxxxxxx ");
	  System.out.println("Enter the mode in which you want to play the game");
	  System.out.println("Enter 1 for Medium difficulty Mode");
	  System.out.println("Enter 2 for Expert Mode against Artificial intelligence opponent");
	  System.out.println("Enter 3 to play with a friend");
	  System.out.println("Enter any other no. to exit");
	   Scanner sc= new Scanner(System.in);
	   int n;
	   n= sc.nextInt();
	   sc.close();
       switch (n) {
       case 1:
    	   new TicTacToeConsole();
           break;
       case 2:
    	   new Tic();
           break;
       case 3:
    	   new Turnbyturn();
    	   break;
      // case 4:
    	//   new Networked();
    	  // break;
       default:
           System.exit(0);
                  }
		  
	   }
	}

