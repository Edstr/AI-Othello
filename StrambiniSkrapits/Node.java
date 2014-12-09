package Participants.StrambiniSkrapits;

import java.util.ArrayList;
import Othello.Move;

/**
 * Tree node class
 * @author Cyriaque Skrapits
 * @author Eddy Strambini
 */
public class Node {
	private GameBoard gameBoard;
	private Move move;

	/**
	 * Constructor
	 * @param gameBoard GameBoard to work on.
	 * @param move Move to play.
	 */
	public Node(GameBoard gameBoard, Move move)
	{
		this.gameBoard = gameBoard;
		this.move = move;
	}
	
	/**
	 * Apply a move and return the result as a new child node.
	 * @param move Move to play.
	 * @param player Player who plays the move.
	 */
	public Node apply(Move move, int player) {
		this.move = move;
		GameBoard newBoard = this.gameBoard.clone();
		newBoard.addCoin(move, player);
		return new Node(newBoard, move);
	}

	/**
	 * Evaluation function
	 * @param move Move to play.
	 * @param player Player who plays the move.
	 */
	public int eval(Move move, int player)
	{
		int opponent = 1 - player;

		if (gameBoard.getEdgeCoinCount(opponent) > gameBoard.getEdgeCoinCount(player))
			return Strategies.getValue4(move, Strategies.POSITIONAL);

		return Strategies.getValue4(move, Strategies.EDGAR);
	}

	public boolean isFinal(int player)
	{
		return (gameBoard.getCoinCount(player) + gameBoard.getCoinCount(1 - player) == 64 ||
			this.ops(player).size() == 0);
	}

	public ArrayList<Move> ops(int player)
	{
		return this.gameBoard.getPossibleMoves(player);
	}
	
	public Move getMove()
	{
		return move;
	}
	
	public void setMove(Move move)
	{
		this.move = move;
	}
}
