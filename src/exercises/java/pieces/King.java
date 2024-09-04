package pieces;

import chess.Board;

import java.util.ArrayList;

public class King extends Piece {
    public static Type Class = Type.KING;
    private Board board;

    public King() {
    }

    protected King(Color color, Board board) {
        super(color, Class);
        this.board = board;
    }

    public static King create(Color color, Board board) {
        return new King(color, board);
    }

    @Override
    public ArrayList<String> getPossibleMoves(String position) {
        Moves movesFactory = new MovesFactoryImpl(this, board);
        return movesFactory.possibleMoves();
    }
}
