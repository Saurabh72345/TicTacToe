package presentation;
import javax.swing.JOptionPane;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
public class Tic{		
	char mBoard[] = {'1','2','3','4','5','6','7','8','9'};
	int BOARD_SIZE = 9;
	
	 static char HUMAN_PLAYER = 'X';
	 static char COMPUTER_PLAYER = 'O';
	
	    Tic() {	
	    speak("Welcome to tictac toe");
		
		char turn = HUMAN_PLAYER;    // Human starts first
		
		
	    	int  win = 0;                // Set to 100,-100, or0 when game is over
		
		// Keep looping until someone wins or a tie
		while (win == 0)
		{	
			displayBoard();

			if (turn == HUMAN_PLAYER)
			{   speak("your move");
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
		if (win == 2){
			System.out.println("It's a tie.");
		    speak("Its a tie");
		             }
		              
		else if (win == -100) {
			System.out.println(HUMAN_PLAYER + " wins!");
		      speak("Congratulations you won the game");
		                       }
		else if (win == 100) {
			System.out.println(COMPUTER_PLAYER + " wins!");
		       speak("Computer player won");
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
int minimax(int depth,Boolean isMax) 
{ 
int score = checkForWinner() ; 

// If Maximizer has won the game 
// return his/her evaluated score 

// If Minimizer has won the game 
// return his/her evaluated score 
if (score==100) 
return score; 

if (score==-100)
return score; 
// If there are no more moves and 
if(score==2)
return 0;

// If this maximizer's move 
if (isMax) 
{ 
int best =-10000; 

// Traverse all cells 
	for (int j = 0; j<9; j++) 
	{ 
		// Check if cell is empty 
		if (mBoard[j] != HUMAN_PLAYER && mBoard[j] != COMPUTER_PLAYER)  
		{ 
			// Make the move 
			char curr = mBoard[j];
			mBoard[j] = COMPUTER_PLAYER; 

			// Call minimax recursively and choose 
			// the maximum value 
			best = Math.max(best,minimax(depth+1,false)); 

			// Undo the move 
			mBoard[j]=curr; 
		} 
	} 

return best-depth; 
}

// If this minimizer's move 
else
{ 
int best = 10000; 

// Traverse all cells 
	for (int j = 0; j <9; j++) 
	{ 
		// Check if cell is empty    

			// Call minimax recursively and choose 
			// the minimum value 
		if (mBoard[j] != HUMAN_PLAYER && mBoard[j] != COMPUTER_PLAYER)  
		{ 
			// Make the move 
			char curr = mBoard[j];
			mBoard[j] = HUMAN_PLAYER; 
			best = Math.min(best, minimax(depth+1, 
							    true)); 
			// Undo the move 
			mBoard[j]=curr; 
		} 
	} 
 
return best+depth; }
} 
	void getComputerMove() 
	{
		int bestVal = -10000;
		int j=-20;
		char curr; 
		

		for (int i = 0; i <9; i++) {
			if (mBoard[i] != HUMAN_PLAYER && mBoard[i] != COMPUTER_PLAYER) 
			{
			  curr=mBoard[i];
			  mBoard[i]=COMPUTER_PLAYER;
				int moveVal= minimax(0,false); 
				mBoard[i]=curr;
				if (moveVal > bestVal) 
				{ 
					
					   j=i;  
					   bestVal = moveVal;
				} 
		
			}}System.out.println("Computer is moving to " + (j+ 1));
		mBoard[j]=COMPUTER_PLAYER;

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