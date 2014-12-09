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
		int evaluation = 0;
		evaluation = 10 * Strategies.pieceDifference(gameBoard, player);
		evaluation += 802 * Strategies.cornerOccupancy(gameBoard, player);
		evaluation += 382 * Strategies.cornerCloseness(gameBoard, player);
		evaluation += 78 * Strategies.mobility(gameBoard, player);
		evaluation += 74 * Strategies.frontierDiscs(gameBoard, player) * 7;
		evaluation += 10 * Strategies.discSquares(gameBoard, player);
		return -evaluation;

		/*int piecesPlayer = gameBoard.getCoinCount(player);
		int piecesOpponent = gameBoard.getCoinCount(opponent);

		if (piecesPlayer > piecesOpponent)
			return 100 * piecesPlayer / (piecesPlayer + piecesOpponent);

		if (piecesPlayer < piecesOpponent)
			return -100 * piecesOpponent / (piecesPlayer + piecesOpponent);

		return 0;*/


		/*
		ok niveau 1

		if (gameBoard.getEdgeCoinCount(opponent) == 0)
			return Strategies.getValue4(move, Strategies.POSITIONAL);

		if (gameBoard.getEdgeCoinCount(opponent) > gameBoard.getEdgeCoinCount(player))
			return Strategies.getValue4(move, Strategies.GOEDGES);

		return Strategies.getValue4(move, Strategies.EDGAR);
		*/


		/*
		meilleur général

		if (gameBoard.getEdgeCoinCount(opponent) > gameBoard.getEdgeCoinCount(player))
			return Strategies.getValue4(move, Strategies.EDGAR);

		return Strategies.getValue4(move, Strategies.GOEDGES);
		*/


		/*if (gameBoard.getEdgeCoinCount(opponent) > gameBoard.getEdgeCoinCount(player))
			return Strategies.getValue4(move, Strategies.POSITIONAL);

		return Strategies.getValue4(move, Strategies.GOEDGES);*/
	}

	/**
	 * Check if game is over.
	 * @param player Player's game to check.
	 */
	public boolean isFinal(int player)
	{
		//return (gameBoard.getCoinCount(player) + gameBoard.getCoinCount(1 - player) == 64 ||
			//this.ops(player).size() == 0);
		return this.ops(player).size() == 0;
	}

	/**
	 * Return possible moves.
	 * @param player Available moves for this player.
	 */
	public ArrayList<Move> ops(int player)
	{
		return this.gameBoard.getPossibleMoves(player);
	}


	/**
	 * Move getter/setter
	 */
	public Move getMove()
	{
		return move;
	}
	
	public void setMove(Move move)
	{
		this.move = move;
	}
}
