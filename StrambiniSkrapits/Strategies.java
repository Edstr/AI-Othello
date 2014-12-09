package Participants.StrambiniSkrapits;

import Othello.Move;

public class Strategies
{
	public final static int[][] POSITIONAL = {
		{99,  -8,  8,  6},
		{-8, -24, -4, -3},
		{ 8,  -4,  7,  4},
		{ 6,  -3,  4,  0}
	};

	public final static int[][] RUSHEDGES = {
		{ 120,  88, -50, -90},
		{  88, -99, -40, -10},
		{ -50, -40,   0,   0},
		{ -90, -10,   0,   0}
	};

	// http://www.cs.columbia.edu/~evs/ml/hw4EDGAR.html
	public final static int[][] EDGAR = {
		{ 64, -8, 8, 8},
		{ -8, -8, 0, 0},
		{  8,  0, 0, 0},
		{  8,  0, 0, 0}
	};


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

	public static int getValue8(Move move, int[][] matrix)
	{
		int i = move.i;
		int j = move.j;

		return matrix[i][j];
	}
}
