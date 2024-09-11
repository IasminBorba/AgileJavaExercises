package pieces;

import java.util.ArrayList;

public class Queen extends Piece {
    public static Type Class = Type.QUEEN;

    protected Queen(Color color) {
        super(color, Class);
    }

    public static Queen create(Color color) {
        return new Queen(color);
    }

    @Override
    public ArrayList<String> getPossibleMoves() {
        Moves movesFactory = new MovesFactoryImpl(this);
        return movesFactory.possibleMoves();
    }
}
