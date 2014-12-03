package Participants.Strambini-Skrapits;

import Othello.Move;

public class State
{
	private GameBoard gameBoard;
	private State parent;
	private int playerID;
	private int enemyID;
	
	private static int[][] force ={
		{99,-8,8,6},
		{-8,-24,-4,-3},
		{8,-4,7,4},
		{6,-3,4,0}
	};

	public State(State parent, int playerID, GameBoard gameBoard) {
		this.parent = parent;
		this.playerID = playerID;
		this.gameBoard = gameBoard.clone();
		this.enemyID = 1 - playerID;
	}

	public void eval() {

	}

	public boolean final() {
		Move[] parentMoves = this.parent.ops(this.enemyID);
		Move[] currentMoves = ops(this.playerID);

		return parentMoves.size() + currentMoves.size() == 0;
	}

	public Move[] ops(int playerID) {
		return gameBoard.getPossibleMoves(playerID);
	}

	public void apply(Move move) {
		this.gameBoard.addCoin(move, this.playerID);
	}
	
	// Méthode retournant la force d'une case.
	public static int[] getForce(int posI, int posJ)
	{
		if(posI >= 4)
		{
			posI = 7 % posI;
		}
		if(posJ >= 4)
		{
			posJ = 7 % posJ;
		}		
		int []pos = {posI,posJ};	
		
		return pos;
	}
}