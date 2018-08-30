package xyz.nexost.deeplearning.tictactoe;

import java.util.ArrayList;
import java.util.Random;

import xyz.nexost.deeplearning.tictactoe.models.Move;
import xyz.nexost.deeplearning.tictactoe.models.TrainingExperience;
import xyz.nexost.deeplearning.tictactoe.models.Type;

/***
 * TODO 
 * 1. Finish TrainingExperience.calculateScore().
 * 
 * 
 * 
 * @author exeet
 *
 */

public class BotCore {
	
	public ArrayList<TrainingExperience> trainingSet = new ArrayList<TrainingExperience>();

	public static void main(String[] args) {
		BotCore bc = new BotCore();
		bc.startTrainingSet(10000);
	}
	
	public void startTrainingSet(int iterations) {
		Random r = new Random();

		long lStartTime = System.nanoTime();
		
		//trainingSet.
		
		for (int a = 0; a < iterations; a++) {
			TrainingExperience te = new TrainingExperience(Type.X);
			int ticTacToeSize = TrainingExperience.WIDTH * TrainingExperience.HEIGHT;

			for (int i = 0; i < ticTacToeSize; i++) {
				if(te.winner != null)
					break;
				
				Move m;
				boolean appended = false;
				while (!appended) {
					m = te.possibleMoves.get(r.nextInt(te.possibleMoves.size()));
					te.possibleMoves.remove(m);
					if ((i % 2) == 0)
						m.type = Type.X;// m = new Move(Type.X, r.nextInt(4), r.nextInt(4));
					else
						m.type = Type.O;// m = new Move(Type.O, r.nextInt(4), r.nextInt(4));

					try {
						te.appendMove(m);
						/*if(te.checkWin()) {
							System.out.println("WON THE GAME!!!!!!!!");
							break;
						}*/
						//System.out.println(m.toString());
						//System.out.println(te.prettyPrint());
						//te.checkWin();
						
						appended = true;
					} catch (Exception e) {
						System.out.println("Append Move Exception: " + e.getMessage() + " || Trying again...");
					}
				}
			}
			
			trainingSet.add(te);
			//System.out.println(te.toString());
			System.out.println(te.prettyPrint());
		}

		long lEndTime = System.nanoTime();
		long output = lEndTime - lStartTime;
		System.out.println("Elapsed time in milliseconds: " + output / 1000000);
	}
}
