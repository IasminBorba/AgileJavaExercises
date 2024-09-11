package pieces;

import java.util.ArrayList;

public class Pawn extends Piece {
    public static Type Class = Type.PAWN;

    protected Pawn(Color color) {
        super(color, Class);
    }

    public static Pawn create(Color color) {
        return new Pawn(color);
    }

    @Override
    public ArrayList<String> getPossibleMoves() {
        Moves movesFactory = new MovesFactoryImpl(this);
        return movesFactory.possibleMoves();
    }
}