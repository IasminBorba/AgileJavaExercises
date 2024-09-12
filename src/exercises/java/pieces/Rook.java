package pieces;

import java.util.ArrayList;

public class Rook extends Piece {
    public static Type Class = Type.ROOK;

    protected Rook(Color color) {
        super(color, Class);
    }

    public static Rook create(Color color) {
        return new Rook(color);
    }

    @Override
    public ArrayList<String> getPossibleMoves() {
        Moves movesFactory = new MovesFactoryImpl(this);
        return movesFactory.possibleMoves();
    }
}