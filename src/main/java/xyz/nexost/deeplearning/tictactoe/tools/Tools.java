package xyz.nexost.deeplearning.tictactoe.tools;

import xyz.nexost.deeplearning.tictactoe.models.Type;

public class Tools {
	
	public static String typeString(Type pType) {
		return pType == Type.O ? "O" : "X";
	}
	
}
