package pieces;

import chess.MovesHelper;
import chess.Position;
import util.CoordinateTransformer;

import java.util.ArrayList;

public class King extends Piece {
    public static Type Class = Type.KING;

    protected King(Color color) {
        super(color, Class);
    }

    public static King createPiece(Color color) {
        return new King(color);
    }

    @Override
    public ArrayList<String> getPossibleMoves() {
        ArrayList<String> moves = new ArrayList<>();
        Position position = this.getPosition();

        for (int initialRow = position.getRow() - 1; initialRow <= position.getRow() + 1; initialRow++)
            for (int initialFile = position.getFile() - 1; initialFile <= position.getFile() + 1; initialFile++)
                moves.add(CoordinateTransformer.positionToCoordinateString(initialFile, initialRow));

        MovesHelper.removeInvalidOrDuplicateMoves(moves, position);
        return moves;
    }
}