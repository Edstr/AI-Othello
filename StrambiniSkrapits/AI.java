package Participants.StrambiniSkrapits;

import java.util.ArrayList;
import Othello.Move;

public class AI
{
	private static int MIN = -1;
	private static int MAX = 1;
	private static GameBoard _gameBoard;
	private static int _player;

	static class AlphaBeta
	{
		public int value = 0;
		public Move move = null;

		public AlphaBeta(int value, Move move)
		{
			this.value = value;
			this.move = move;
		}
	}

	public static Move getBestMove(GameBoard gameBoard, int depth, int playerID)
	{
		_gameBoard = gameBoard;
		_player = playerID;

		Node root = new Node(gameBoard, null);
		AlphaBeta ab = alphabeta(root, depth, MAX, 0);
		return ab.move;
	}

	/**
	 * Alpha beta pruning algorithm
	 * @param root Root node
	 * @param depth How deep the alpha beta has to search.
	 * @param minOrMax Define if the function 
	 */
	private static AlphaBeta alphabeta(Node root, int depth, int minOrMax, int parentValue)
	{
		AlphaBeta ab;
		Move optOp = null;
		Node newNode;
		int val;
		int optVal;

		int player = minOrMax == MAX ? _player : 1 - _player;


		if ((depth == 0 || root.isFinal(_player)) && root.getMove() != null)
			return new AlphaBeta(root.eval(root.getMove(), player), null);

		optVal = minOrMax * -1000000; //Integer.MAX_VALUE;
		for (Move op : root.ops(player))
		{
			newNode = root.apply(op, player);

			ab = alphabeta(newNode, depth - 1, -minOrMax, optVal);
			val = ab.value;

			if (val * minOrMax > optVal * minOrMax)
			{
				optVal = val;
				optOp = op;
				if (optVal * minOrMax > parentValue * minOrMax)
					break;
			}
		}

		return new AlphaBeta(optVal, optOp);
	}
}
