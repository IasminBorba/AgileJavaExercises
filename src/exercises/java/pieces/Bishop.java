package pieces;

import java.util.ArrayList;

public class Bishop extends Piece {
    public static Type Class = Type.BISHOP;

    protected Bishop(Color color) {
        super(color, Class);
    }

    public static Bishop create(Color color) {
        return new Bishop(color);
    }

    @Override
    public ArrayList<String> getPossibleMoves(String position) {
        Moves movesFactory = new MovesFactoryImpl(this);
        return movesFactory.possibleMoves();
    }
}