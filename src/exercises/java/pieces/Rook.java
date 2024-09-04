package pieces;

import chess.Board;

import java.util.ArrayList;

public class Rook extends Piece {
    public static Type Class = Type.ROOK;
    private final Board board;

    protected Rook(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static Rook create(Color color, Board board) {
        return new Rook(color, board);
    }

    @Override
    public ArrayList<String> getPossibleMoves(String position) {
        Moves movesFactory = new MovesFactoryImpl(this, board);
        return movesFactory.possibleMoves();
    }
}