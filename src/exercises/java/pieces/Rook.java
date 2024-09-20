package pieces;

import chess.MovesHelper;
import chess.Position;

import java.util.ArrayList;

public class Rook extends Piece {
    public static Type Class = Type.ROOK;

    protected Rook(Color color) {
        super(color, Class);
    }

    public static Rook createPiece(Color color) {
        return new Rook(color);
    }

    @Override
    public ArrayList<String> getPossibleMoves() {
        ArrayList<String> moves = new ArrayList<>();
        Position position = this.getPosition();

        MovesHelper.addStraightLineMoves(moves, position);

        return moves;
    }
}