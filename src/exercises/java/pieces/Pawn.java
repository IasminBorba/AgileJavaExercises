package pieces;

import chess.Board;

import java.util.ArrayList;

public class Pawn extends Piece {
    public static Type Class = Type.PAWN;
    private final Board board;

    protected Pawn(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static Pawn create(Color color, Board board) {
        return new Pawn(color, board);
    }

    @Override
    public ArrayList<String> getPossibleMoves(String position) {
        Moves movesFactory = new MovesFactoryImpl(this, board);
        return movesFactory.possibleMoves();
    }
}