package pieces;

import chess.MovesHelper;
import chess.Position;

import java.util.ArrayList;

public class Queen extends Piece {
    public static Type Class = Type.QUEEN;

    protected Queen(Color color) {
        super(color, Class);
    }

    public static Queen createPiece(Color color) {
        return new Queen(color);
    }

    @Override
    public ArrayList<String> getPossibleMoves() {
        ArrayList<String> moves = new ArrayList<>();
        Position position = this.getPosition();

        MovesHelper.addDiagonalMoves(moves, position);
        MovesHelper.addStraightLineMoves(moves, position);

        return moves;
    }
}
