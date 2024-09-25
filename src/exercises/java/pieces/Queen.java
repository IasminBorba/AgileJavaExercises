package pieces;

import chess.*;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;

public class Queen extends Piece {
    public static Type Class = Type.QUEEN;
    public static String PATH_QUEEN_WHITE = "src/exercises/resources/queenWhite.png";
    public static String PATH_QUEEN_BLACK = "src/exercises/resources/queenBlack.png";

    protected Queen(Color color) {
        super(color, Class);

        defineIconPiece();
    }

    public void defineIconPiece() {
        String pathIcon = getIconPath();

        try {
            icon = ImageIO.read(new File(pathIcon));
        } catch (IOException e) {
            throw new RuntimeException("Can't read input file!");
        }
    }

    protected String getIconPath() {
        if (this.getColor() == Color.WHITE)
            return PATH_QUEEN_WHITE;
        else
            return PATH_QUEEN_BLACK;
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
