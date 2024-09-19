package pieces;

import chess.Moves;
import chess.MovesFactoryImpl;

import java.util.ArrayList;

public class Knight extends Piece {
    public static Type Class = Type.KNIGHT;

    protected Knight(Color color) {
        super(color, Class);
    }

    public static Knight create(Color color) {
        return new Knight(color);
    }

    @Override
    public ArrayList<String> getPossibleMoves() {
        Moves movesFactory = new MovesFactoryImpl(this);
        return movesFactory.possibleMoves();
    }
}