package xyz.nexost.deeplearning.tictactoe.models;

import xyz.nexost.deeplearning.tictactoe.tools.Tools;

public class Move {
	
	public Type type;
	public final int x;
	public final int y;
	
	public Move(Type pType, int pX, int pY) {
		type = pType;
		x = pX;
		y = pY;
	}
	
	@Override
	public String toString() {
		return "{" + Tools.typeString(type) + "|" + x + "," + y + "}";
	}

}
