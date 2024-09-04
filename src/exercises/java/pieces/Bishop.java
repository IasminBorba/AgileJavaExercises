package pieces;

import chess.Board;

import java.util.ArrayList;

public class Bishop extends Piece {
    public static Type Class = Type.BISHOP;
    private final Board board;

    protected Bishop(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static Bishop create(Color color, Board board) {
        return new Bishop(color, board);
    }

    @Override
    public ArrayList<String> getPossibleMoves(String position) {
        Moves movesFactory = new MovesFactoryImpl(this, board);
        return movesFactory.possibleMoves();
    }
}