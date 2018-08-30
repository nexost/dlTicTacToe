package xyz.nexost.deeplearning.tictactoe.models;

import java.util.ArrayList;
import java.util.Arrays;

import javax.tools.Tool;

import xyz.nexost.deeplearning.tictactoe.tools.Tools;

public class TrainingExperience {

	public static final int WIDTH = 3;
	public static final int HEIGHT = 3;

	public String winner;
	public Type type;
	public String[][] movesMatrix;
	public ArrayList<Move> possibleMoves;
	private ArrayList<Move> moves;
	private double score;

	public double getScore() {
		return score;
	}

	public ArrayList<Move> getMoves() {
		return moves;
	}

	public TrainingExperience(Type pType) {
		type = pType;
		// moves = new Move[WIDTH * HEIGHT];
		moves = new ArrayList<Move>();
		movesMatrix = new String[WIDTH][HEIGHT];

		possibleMoves = new ArrayList<Move>();
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				possibleMoves.add(new Move(type, x, y));
			}
		}

	}

	public ArrayList<Move> appendMove(Move pMove) throws Exception {
		addMoveToMatrix(pMove);
		moves.add(pMove);
		checkWin(pMove);
		/*
		 * for(int i = 0; i < moves.length; i++) { if(moves[i] == null) { moves[i] =
		 * pMove; break; } }
		 */
		return moves;
	}

	/*
	 * public ArrayList<Move> insertMove(Move pMove, int index) throws Exception {
	 * addMoveToMatrix(pMove); moves.set(index, pMove); return moves; }
	 */

	private void addMoveToMatrix(Move pMove) throws Exception {
		if (movesMatrix[pMove.x][pMove.y] == null)
			movesMatrix[pMove.x][pMove.y] = Tools.typeString(pMove.type);
		else
			throw new Exception("Cannot add move there, already used.");
	}

	public boolean checkWin(Move lstMove) {
		int x = lstMove.x, y = lstMove.y;

		if (checkDiagonal(x, y) || checkHorizontal(x, y) || checkVertical(x, y)) {
			winner = movesMatrix[x][y];
			calculateScore();
			return true;
		}

		/*
		 * for (int y = 0; y < HEIGHT; y++) { for (int x = 0; x < WIDTH; x++) { if
		 * (checkDiagonal(x, y) || checkHorizontal(x, y) || checkVertical(x, y)) {
		 * winner = movesMatrix[x][y]; calculateScore(); return true; } } }
		 */
		return false;
	}

	private boolean checkDiagonal(int x, int y) {
		if (movesMatrix[x][y] == null)
			return false;

		boolean w = false;
		switch (x) {
		case 0:
			if (y == 0) {
				if (movesMatrix[x][y] == movesMatrix[x + 1][y + 1] || movesMatrix[x][y] == movesMatrix[x + 2][y + 2]) {
					score += 10;
					if (movesMatrix[x][y] == movesMatrix[x + 1][y + 1]
							&& movesMatrix[x][y] == movesMatrix[x + 2][y + 2])
						w = true;
				}
			} else if (y == 2) {
				if (movesMatrix[x][y] == movesMatrix[x + 1][y - 1] || movesMatrix[x][y] == movesMatrix[x + 2][y - 2]) {
					score += 10;
					if (movesMatrix[x][y] == movesMatrix[x + 1][y - 1]
							&& movesMatrix[x][y] == movesMatrix[x + 2][y - 2])
						w = true;
				}
			}
			break;
		case 1:
			if (y == 1) {
				if ((movesMatrix[x][y] == movesMatrix[x - 1][y - 1] || movesMatrix[x][y] == movesMatrix[x + 1][y + 1])
						|| (movesMatrix[x][y] == movesMatrix[x - 1][y + 1]
								|| movesMatrix[x][y] == movesMatrix[x + 1][y - 1])) {
					score += 10;
					if ((movesMatrix[x][y] == movesMatrix[x - 1][y - 1]
							&& movesMatrix[x][y] == movesMatrix[x + 1][y + 1])
							|| (movesMatrix[x][y] == movesMatrix[x - 1][y + 1]
									&& movesMatrix[x][y] == movesMatrix[x + 1][y - 1]))
						w = true;
				}
			}
			break;
		case 2:
			if (y == 0) {
				if (movesMatrix[x][y] == movesMatrix[x - 1][y + 1] || movesMatrix[x][y] == movesMatrix[x - 2][y + 2]) {
					score += 10;
					if (movesMatrix[x][y] == movesMatrix[x - 1][y + 1]
							&& movesMatrix[x][y] == movesMatrix[x - 2][y + 2])
						w = true;
				}
			} else if (y == 2) {
				if (movesMatrix[x][y] == movesMatrix[x - 1][y - 1] || movesMatrix[x][y] == movesMatrix[x - 2][y - 2]) {
					score += 10;
					if (movesMatrix[x][y] == movesMatrix[x - 1][y - 1]
							&& movesMatrix[x][y] == movesMatrix[x - 2][y - 2])
						w = true;
				}
			}
			break;
		}

		return w;
	}

	private boolean checkHorizontal(int x, int y) {
		if (movesMatrix[x][y] == null)
			return false;

		boolean w = false;
		switch (x) {
		case 0:
			if (movesMatrix[x][y] == movesMatrix[x + 1][y] || movesMatrix[x][y] == movesMatrix[x + 2][y]) {
				score += 10;
				if (movesMatrix[x][y] == movesMatrix[x + 1][y] && movesMatrix[x][y] == movesMatrix[x + 2][y])
					w = true;
			}
			break;
		case 1:
			if (movesMatrix[x][y] == movesMatrix[x - 1][y] || movesMatrix[x][y] == movesMatrix[x + 1][y]) {
				score += 10;
				if (movesMatrix[x][y] == movesMatrix[x - 1][y] && movesMatrix[x][y] == movesMatrix[x + 1][y])
					w = true;
			}
			break;
		case 2:
			if (movesMatrix[x][y] == movesMatrix[x - 1][y] || movesMatrix[x][y] == movesMatrix[x - 2][y]) {
				score += 10;
				if (movesMatrix[x][y] == movesMatrix[x - 1][y] && movesMatrix[x][y] == movesMatrix[x - 2][y])
					w = true;
			}
			break;
		}
		/*
		 * if (w) System.out.println("WON BY HORIZONTAL! " + movesMatrix[x][y] + " -> {"
		 * + x + "," + y + "}");
		 */

		return w;
	}

	private boolean checkVertical(int x, int y) {
		if (movesMatrix[x][y] == null)
			return false;

		boolean w = false;
		switch (y) {
		case 0:
			if (movesMatrix[x][y] == movesMatrix[x][y + 1] || movesMatrix[x][y] == movesMatrix[x][y + 2]) {
				score += 10;
				if (movesMatrix[x][y] == movesMatrix[x][y + 1] && movesMatrix[x][y] == movesMatrix[x][y + 2])
					w = true;
			}
			break;
		case 1:
			if (movesMatrix[x][y] == movesMatrix[x][y - 1] || movesMatrix[x][y] == movesMatrix[x][y + 1]) {
				score += 10;
				if (movesMatrix[x][y] == movesMatrix[x][y - 1] && movesMatrix[x][y] == movesMatrix[x][y + 1])
					w = true;
			}
			break;
		case 2:
			if (movesMatrix[x][y] == movesMatrix[x][y - 1] || movesMatrix[x][y] == movesMatrix[x][y - 2]) {
				score += 10;
				if (movesMatrix[x][y] == movesMatrix[x][y - 1] && movesMatrix[x][y] == movesMatrix[x][y - 2])
					w = true;
			}
			break;
		}
		/*
		 * if (w) System.out.println("WON BY VERTICAL! " + movesMatrix[x][y] + " -> {" +
		 * x + "," + y + "}");
		 */

		return w;
	}

	private void calculateScore() {
		int nbMoves = moves.size();
		score += (100 - (nbMoves * 10));

		if (Tools.typeString(type).equals(winner))
			score += score * 2;
		//score = score / nbMoves;
	}

	@Override
	public String toString() {
		String movesString = "";
		/*
		 * for(int i = 0; i < moves.length; i++) { if(moves[i] != null) movesString +=
		 * moves[i].toString() + ", "; else movesString += "null, "; }
		 */
		for (Move m : moves)
			movesString += m.toString() + ", ";
		movesString = movesString.substring(0, movesString.length() - 2);
		return "{" + Tools.typeString(type) + "|[" + movesString + "]" + score + "}";
	}

	public String prettyPrint() {
		String prettyString = "Player: " + Tools.typeString(type)
				+ (winner != null ? " Winner: " + winner + " Nb of moves: " + moves.size() : "") + "\n";
		for (int y = HEIGHT - 1; y >= 0; y--) {
			for (int x = 0; x < WIDTH; x++) {
				if (movesMatrix[x][y] != null)
					prettyString += movesMatrix[x][y] + " ";
				else
					prettyString += "- ";
			}
			prettyString = prettyString.substring(0, prettyString.length() - 1) + "\n";
		}
		prettyString += "Score: " + score + "\n";
		return prettyString;
	}

}
