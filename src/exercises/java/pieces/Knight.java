package pieces;

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
    public ArrayList<String> getPossibleMoves(String position) {
        Moves movesFactory = new MovesFactoryImpl(this);
        return movesFactory.possibleMoves();
    }
}