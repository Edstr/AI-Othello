package Participants.StrambiniSkrapits;

import java.util.ArrayList;
import Othello.Move;

public class AI
{
	private static int MIN = -1;
	private static int MAX = 1;
	private static GameBoard _gameBoard;
	private static int _playerID;

	static class AlphaBeta
	{
		public int value = 0;
		public Move move = null;

		public AlphaBeta(int value, Move move)
		{
			this.value = value;
			this.move = move;

			//System.out.println("AlphaBeta " + value);
		}
	}

	public static Move getBestMove(GameBoard gameBoard, int depth, int playerID)
	{
		_gameBoard = gameBoard;
		_playerID = playerID;

		Node root = new Node(gameBoard, null);
		AlphaBeta ab = alphabeta(root, depth, MAX, 0);
		//System.out.print("BESTMOVE [" + ab.move.i + "," + ab.move.j + "]");
		return ab.move;

		/*System.out.print("DISPO : ");
		ArrayList<Move> moves = gameBoard.getPossibleMoves(playerID);
		for (Move op : moves)
		{
			System.out.print(" [" + op.i + "," + op.j + "]");
		}
		System.out.print("\n");
		if (moves.size() > 0)
			return moves.get(0);
		else
			return null;*/
	}

	private static AlphaBeta alphabeta(Node root, int depth, int minOrMax, int parentValue)
	{
		AlphaBeta ab;
		Move optOp = null;
		Node newNode;
		int val = 0;
		int optVal;

		int player = minOrMax == MAX ? _playerID : 1 - _playerID;

		if ((depth == 0 || root.isFinal(_playerID)) && root.getMove() != null)
			return new AlphaBeta(root.eval(root.getMove(), player), null);

		optVal = minOrMax * -1000000;
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
