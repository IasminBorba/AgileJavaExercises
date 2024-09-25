package pieces;

import chess.Board;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import static pieces.Queen.*;

public class QueenTest extends PieceTest {
    private Board board;
    private ArrayList<String> possibleMoves;

    @Override
    public void setUp() {
        super.setUp();
        board = new Board();
        possibleMoves = new ArrayList<>();
    }

    public void testCreatePiece() {
        Piece queenBlack = createPiece(Color.BLACK, Class);
        assertEquals(Queen.Class, queenBlack.getType());
        assertEquals('Q', queenBlack.getRepresentation());

        Piece queenWhite = createPiece(Color.WHITE, Class);
        assertEquals(Queen.Class, queenWhite.getType());
        assertEquals('q', queenWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return Queen.createPiece(color);
    }

    public void testIconPiece() throws IOException {
        Piece queen = createPiece(Color.WHITE, Class);
        BufferedImage queenImage = (BufferedImage) queen.getImage();

        BufferedImage imageExpected = ImageIO.read(new File("src/exercises/resources/queenWhite.png"));

        for (int x = 0; x < imageExpected.getWidth(); x++)
            for (int y = 0; y < imageExpected.getHeight(); y++)
                assertEquals(imageExpected.getRGB(x, y), queenImage.getRGB(x, y));
    }

    public void testNotIconPiece() {
        String pathOriginal = PATH_QUEEN_BLACK;
        try {
            PATH_QUEEN_BLACK = "a";
            Piece queen = createPiece(Color.BLACK, Class);
        } catch (Exception e) {
            assertEquals("Can't read input file!", e.getMessage());
        } finally {
            PATH_QUEEN_BLACK = pathOriginal;
        }
    }

    public void testMove() {
        Queen queenWhite = Queen.createPiece(Piece.Color.WHITE);
        board.addPiece(queenWhite, "d5");

        possibleMoves = queenWhite.getPossibleMoves();

        assertTrue(possibleMoves.containsAll(
                Arrays.asList(
                        "e6", "e4", "f7", "f3", "g8", "g2", "h1",
                        "c6", "c4", "b7", "b3", "a8", "a2",
                        "a5", "b5", "c5", "e5", "f5", "g5", "h5",
                        "d1", "d2", "d3", "d4", "d6", "d7", "d8"
                )
        ));
    }

    public void testLotsMovements() {
        Queen queenBlack = Queen.createPiece(Color.BLACK);
        board.addPiece(queenBlack, "c7");

        board.movePieceToPosition("h7", queenBlack);
        board.movePieceToPosition("b1", queenBlack);
        board.movePieceToPosition("a1", queenBlack);

        possibleMoves = queenBlack.getPossibleMoves();

        assertTrue(possibleMoves.containsAll(
                Arrays.asList(
                        "a2", "a3", "a4", "a5", "a6", "a7", "a8",
                        "b1", "c1", "d1", "e1", "f1", "g1", "h1",
                        "b2", "c3", "d4", "e5", "f6", "g7", "h8"
                )
        ));
    }

    public void testNotMove() {
        Queen queenBlack = Queen.createPiece(Color.WHITE);
        board.addPiece(queenBlack, "g2");

        possibleMoves = queenBlack.getPossibleMoves();

        assertFalse(possibleMoves.contains("e1"));
    }
}

