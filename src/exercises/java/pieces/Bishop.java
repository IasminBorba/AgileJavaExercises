package pieces;

import chess.MovesHelper;
import chess.Position;

import java.util.ArrayList;

public class Bishop extends Piece {
    public static Type Class = Type.BISHOP;


    protected Bishop(Color color) {
        super(color, Class);
    }

    public static Bishop createPiece(Color color) {
        return new Bishop(color);
    }

    @Override
    public ArrayList<String> getPossibleMoves() {
        ArrayList<String> moves = new ArrayList<>();
        Position position = this.getPosition();

        MovesHelper.addDiagonalMoves(moves, position);

        return moves;
    }
}