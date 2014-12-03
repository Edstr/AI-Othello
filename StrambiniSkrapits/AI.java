package Participants.StrambiniSkrapits;

import java.util.ArrayList;
import Othello.Move;

public class AI
{
	private static int MIN = -1;
	private static int MAX = 1;
	private static GameBoard _gameBoard;
	private static int _playerID;

	private static int[][] force ={
		{99, -8, 8, 6},
		{-8, -24, -4, -3},
		{8, -4, 7, 4},
		{6, -3, 4, 0}
	};

	public static Move getBestMove(GameBoard gameBoard, int depth, int playerID)
	{
		_gameBoard = gameBoard;
		_playerID = playerID;

		Node node = new Node(null);
		node.setEvaluation(0);
		alphabeta(node, depth, MAX, 0);

		//ArrayList<Move> moves = gameBoard.getPossibleMoves(playerID);
		return node.getMove();
	}

	private static void alphabeta(Node root, int depth, int minOrMax, int parentValue)
	{
		int optVal;
		Move optOp = null;
		Node newMove;
		int val = 0;

		GameBoard gameBoard;
		ArrayList<Move> moves = _gameBoard.getPossibleMoves(_playerID);

		boolean isFinal = moves.size() == 0;
		if (depth == 0 || isFinal)
			return;

		optVal = minOrMax * -10000;
		for (Move op : moves)
		{
			gameBoard = _gameBoard.clone();
			gameBoard.addCoin(op, _playerID);

			newMove = new Node(op);
			newMove.setEvaluation(getForce(op));
			root.addChildNode(newMove);

			alphabeta(newMove, depth - 1, -minOrMax, optVal);

			if (val * minOrMax > optVal * minOrMax)
			{
				optVal = val;
				optOp = op;
				if (optVal * minOrMax > parentValue * minOrMax)
					break;
			}
		}

		root.setEvaluation(optVal);
		root.setMove(optOp);
	}

	public static int getForce(Move move)
	{
		int i = move.i;
		int j = move.j;

		if(i >= 4)
			i = 7 % i;

		if(j >= 4)
			j = 7 % j;

		return force[i][j];
	}
}