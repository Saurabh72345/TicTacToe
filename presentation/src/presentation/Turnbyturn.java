package presentation;
import javax.swing.JOptionPane;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
public class Turnbyturn{		
	char mBoard[] = {'1','2','3','4','5','6','7','8','9'};
	int BOARD_SIZE = 9;
	
	 static char HUMAN_PLAYER = 'X';
	 static char COMPUTER_PLAYER = 'O';
	
	    Turnbyturn() {	
	    speak("Welcome to tictac toe");
		
		char turn = HUMAN_PLAYER;    // Human starts first
		
		
	    	int  win = 0;                // Set to 100,-100, or0 when game is over
		
		// Keep looping until someone wins or a tie
		while (win == 0)
		{	
			displayBoard();

			if (turn == HUMAN_PLAYER)
			{   speak("player1 move");
				getUserMove();
				turn = COMPUTER_PLAYER;
			}
			else
			{    speak("player 2 move");
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
		    speak("Its a tie");
		             }
		              
		else if (win == -100) {
			System.out.println(HUMAN_PLAYER + " wins!");
		      speak("Congratulations player 1 won the game");
		                       }
		else if (win == 100) {
			System.out.println(COMPUTER_PLAYER + " wins!");
		       speak("player 2 won the game");
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
	void getUserMove() 
	{ int move = -1;
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

	void getComputerMove(){
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
         	mBoard[move-1] = COMPUTER_PLAYER;
	
	}	
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
		//if((k=="done"))
		//synthesizer.deallocate();
		 }
      catch(Exception e) { 
			e.printStackTrace();  
			}
	
   }
	}
