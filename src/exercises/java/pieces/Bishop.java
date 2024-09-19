package pieces;

import chess.Moves;
import chess.MovesFactoryImpl;

import java.util.ArrayList;

public class Bishop extends Piece {
    public static Type Class = Type.BISHOP;

    protected Bishop(Color color) {
        super(color, Class);
    }

    public static Bishop createPiece(Color color) {
        return new Bishop(color);
    }

    @Override
    public ArrayList<String> getPossibleMoves() {
        Moves movesFactory = new MovesFactoryImpl(this);
        return movesFactory.possibleMoves();
    }
}