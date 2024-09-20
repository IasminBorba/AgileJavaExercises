package pieces;

import chess.MovesHelper;
import chess.Position;
import util.CoordinateTransformer;

import java.util.ArrayList;

public class Pawn extends Piece {
    public static Type Class = Type.PAWN;

    protected Pawn(Color color) {
        super(color, Class);
    }

    public static Pawn createPiece(Color color) {
        return new Pawn(color);
    }

    @Override
    public ArrayList<String> getPossibleMoves() {
        ArrayList<String> moves = new ArrayList<>();
        Position position = this.getPosition();

        if (this.isWhite())
            moves.add(CoordinateTransformer.positionToCoordinateString(position.getFile(), position.getRow() + 1));
        else
            moves.add(CoordinateTransformer.positionToCoordinateString(position.getFile(), position.getRow() - 1));

        return moves;
    }
}