package Participants.Strambini-Skrapits;

import Othello.Move;

public class State
{
	private GameBoard gameBoard;
	private State parent;
	private int playerID;
	private int enemyID;

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
}