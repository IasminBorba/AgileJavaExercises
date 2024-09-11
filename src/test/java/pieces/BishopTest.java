package pieces;

import chess.Board;

import java.util.ArrayList;
import java.util.Arrays;

import static pieces.Bishop.*;
import static pieces.Queen.Class;

public class BishopTest extends PieceTest {
    private Board board;
    private ArrayList<String> possibleMoves;

    @Override
    public void setUp() {
        super.setUp();
        board = new Board();
        possibleMoves = new ArrayList<>();
    }

    public void testCreate() {
        Piece bishopBlack = createPiece(Color.BLACK, Class);
        assertEquals(Bishop.Class, bishopBlack.getType());
        assertEquals('B', bishopBlack.getRepresentation());

        Piece bishopWhite = createPiece(Color.WHITE, Class);
        assertEquals(Bishop.Class, bishopWhite.getType());
        assertEquals('b', bishopWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return Bishop.create(color);
    }

    public void testMove() {
        Bishop bishopWhite = Bishop.create(Piece.Color.WHITE);
        board.addPiece(bishopWhite, "g2");

        possibleMoves = bishopWhite.getPossibleMoves();

        assertTrue(possibleMoves.containsAll(Arrays.asList("f1", "f3", "e4", "d5", "c6", "b7", "a8", "h3", "h1")));
    }

    public void testLotsMovements() {
        Bishop bishopBlack = Bishop.create(Color.BLACK);
        board.addPiece(bishopBlack, "d7");

        board.movePiece("f5", bishopBlack);
        board.movePiece("c2", bishopBlack);
        board.movePiece("h7", bishopBlack);

        possibleMoves = bishopBlack.getPossibleMoves();

        assertTrue(possibleMoves.containsAll(Arrays.asList("g8", "g6", "f5", "e4", "d3", "c2", "b1")));
    }

    public void testNotMove() {
        Bishop bishopBlack = Bishop.create(Color.BLACK);
        board.addPiece(bishopBlack, "b1");

        possibleMoves = bishopBlack.getPossibleMoves();

        assertFalse(possibleMoves.contains("a1"));
    }
}