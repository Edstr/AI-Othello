package Participants.StrambiniSkrapits;

import Othello.Move;

/**
 * Class containing strategics methods.
 * All the methods above were taken from http://mkorman.org/othello.pdf.
 * @author Cyriaque Skrapits
 * @author Eddy Strambini
 */
public class Strategies
{
	/*
	 * Matrixes
	 */
	public final static int[][] POSITIONAL = {
		{99,  -8,  8,  6},
		{-8, -24, -4, -3},
		{ 8,  -4,  7,  4},
		{ 6,  -3,  4,  0}
	};

	// http://www.cs.columbia.edu/~evs/ml/hw4EDGAR.html
	public final static int[][] EDGAR = {
		{ 64, -8, 8, 8},
		{ -8, -8, 0, 0},
		{  8,  0, 0, 0},
		{  8,  0, 0, 0}
	};

	public final static int[][] GOCORNERS = {
		{ 32, -99, -8,  8},
		{-99, -64,  8,  8},
		{ -8,   8,  0,  0},
		{  8,   8,  0,  0}
	};

	public final static int[][] GOEDGES = {
		{  32, -64, 16,  16},
		{ -64, -32, 16,  16},
		{  16,  16,  0,   0},
		{  16,  16,  0,   0}
	};

	// Taken from http://mkorman.org/othello.pdf
	public final static int[][] DISCSQUARES = {
		{20, -3, 11,  8},
		{-3, -7, -4,  1},
		{11, -4,  2,  2},
		{ 8,  1,  2, -3}
	};


	/*
	 * Matrix access methods
	 */

	/**
	 * Return the value from a 4x4 matrix.
	 * @param move Position in the matrix.
	 * @return value Value in the matrix.
	 */
	public static int getValue4(Move move, int[][] matrix)
	{
		int i = move.i;
		int j = move.j;

		if(i >= 4)
			i = 7 % i;

		if(j >= 4)
			j = 7 % j;

		return matrix[i][j];
	}

	/**
	 * Return the value from a 4x4 matrix.
	 * @param i Line
	 * @param j Column
	 * @return value Value in the matrix.
	 */
	public static int getValue4(int i, int j, int[][] matrix)
	{
		if(i >= 4)
			i = 7 % i;

		if(j >= 4)
			j = 7 % j;

		return matrix[i][j];
	}

	/**
	 * Return the value from a 8x8 matrix.
	 * @param move Position in the matrix.
	 * @return value Value in the matrix.
	 */
	public static int getValue8(Move move, int[][] matrix)
	{
		int i = move.i;
		int j = move.j;

		return matrix[i][j];
	}


	/*
	 * Strategies from http://mkorman.org/
	 */

	public static int pieceDifference(GameBoard gameBoard, int player)
	{
		int opponent = 1 - player;

		int piecesPlayer = gameBoard.getCoinCount(player);
		int piecesOpponent = gameBoard.getCoinCount(opponent);

		if (piecesPlayer > piecesOpponent)
			return 100 * piecesPlayer / (piecesPlayer + piecesOpponent);

		if (piecesPlayer < piecesOpponent)
			return -100 * piecesOpponent / (piecesPlayer + piecesOpponent);

		return 0;
	}

	public static int cornerOccupancy(GameBoard gameBoard, int player)
	{
		int opponent = 1 - player;

		int piecesPlayer = gameBoard.getCornerCoinCount(player);
		int piecesOpponent = gameBoard.getCornerCoinCount(opponent);

		return 25 * piecesPlayer - 25 * piecesOpponent;
	}

	public static int cornerCloseness(GameBoard gameBoard, int player)
	{
		int opponent = 1 - player;

		int piecesPlayer = gameBoard.getEdgeCoinCount(player);
		int piecesOpponent = gameBoard.getEdgeCoinCount(opponent);

		return -13 * piecesPlayer + 13 * piecesOpponent;
	}

	public static int mobility(GameBoard gameBoard, int player)
	{
		int opponent = 1 - player;

		int piecesPlayer = gameBoard.getPossibleMoves(player).size();
		int piecesOpponent = gameBoard.getPossibleMoves(opponent).size();

		if (piecesPlayer > piecesOpponent)
			return 100 * piecesPlayer / (piecesPlayer + piecesOpponent);

		if (piecesPlayer < piecesOpponent)
			return -100 * piecesOpponent / (piecesPlayer + piecesOpponent);

		return 0;
	}

	public static int frontierDiscs(GameBoard gameBoard, int player)
	{
		int opponent = 1 - player;

		int piecesPlayer = gameBoard.getAdjacentsEmpty(player);
		int piecesOpponent = gameBoard.getAdjacentsEmpty(opponent);

		if (piecesPlayer > piecesOpponent)
			return -100 * piecesPlayer / (piecesPlayer + piecesOpponent);

		if (piecesPlayer < piecesOpponent)
			return 100 * piecesOpponent / (piecesPlayer + piecesOpponent);

		return 0;
	}

	/*public static int frontierDiscs(GameBoard gameBoard, int player)
	{
		int opponent = 1 - player;
		int score = 0;

		int piecesPlayer;
		int piecesOpponent;

		for (int i = 1; i <= 8; i++)
		{
			piecesPlayer = gameBoard.getAdjacentsEmpty(player, i);
			piecesOpponent = gameBoard.getAdjacentsEmpty(opponent, i);

			if (piecesPlayer > piecesOpponent)
				score += -100 * piecesPlayer / (piecesPlayer + piecesOpponent);
			if (piecesPlayer < piecesOpponent)
				score += 100 * piecesOpponent / (piecesPlayer + piecesOpponent);
		}

		return score;
	}*/

	public static int discSquares(GameBoard gameBoard, int player)
	{
		int scorePlayer = 0;
		int scoreOpponent = 0;

		for (int i = 0; i < GameBoard.BOARD_SIZE; i++)
		{
			for (int j = 0; j < GameBoard.BOARD_SIZE; j++)
			{
				scorePlayer += getValue4(i, j, DISCSQUARES);
			}
		}

		for (int i = 0; i < GameBoard.BOARD_SIZE; i++)
		{
			for (int j = 0; j < GameBoard.BOARD_SIZE; j++)
			{
				scoreOpponent += -getValue4(i, j, DISCSQUARES);
			}
		}

		return scorePlayer - scoreOpponent;
	}
}
