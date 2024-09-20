package pieces;

import chess.MovesHelper;
import chess.Position;

import java.util.ArrayList;

public class Knight extends Piece {
    public static Type Class = Type.KNIGHT;

    protected Knight(Color color) {
        super(color, Class);
    }

    public static Knight createPiece(Color color) {
        return new Knight(color);
    }

    @Override
    public ArrayList<String> getPossibleMoves() {
        ArrayList<String> moves = new ArrayList<>();
        Position position = this.getPosition();

        for (int movimentFile = -2; movimentFile <= 2; movimentFile++) {
            int absoluteNumber = Math.abs(movimentFile);
            int movimentRow = 3 - absoluteNumber;

            int newFile = position.getFile() + movimentFile;

            MovesHelper.addDiagonalNeighborMoves(movimentRow, newFile, position, moves);
        }

        return moves;
    }
}