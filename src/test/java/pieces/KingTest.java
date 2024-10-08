package pieces;

import chess.Board;

import static pieces.King.*;

import java.util.ArrayList;
import java.util.Arrays;


public class KingTest extends PieceTest {
    private Board board;
    private ArrayList<String> possibleMoves;

    @Override
    public void setUp() {
        super.setUp();
        board = new Board();
        possibleMoves = new ArrayList<>();
    }

    public void testCreatePiece() {
        Piece kingBlack = createPiece(Color.BLACK, Class);
        assertEquals(King.Class, kingBlack.getType());
        assertEquals('K', kingBlack.getRepresentation());

        Piece kingWhite = createPiece(Color.WHITE, Class);
        assertEquals(King.Class, kingWhite.getType());
        assertEquals('k', kingWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return King.createPiece(color);
    }

    public void testMove() {
        King kingWhite = King.createPiece(Color.WHITE);
        board.addPiece(kingWhite, "d4");
        possibleMoves = kingWhite.getPossibleMoves();

        assertTrue(possibleMoves.containsAll(Arrays.asList("c3", "c4", "c5", "d3", "d5", "e3", "e4", "e5")));
    }

    public void testLotsMovements() {
        King kingBlack = King.createPiece(Color.BLACK);
        board.addPiece(kingBlack, "a1");
        board.movePieceToPosition("b1", kingBlack);
        board.movePieceToPosition("c2", kingBlack);
        board.movePieceToPosition("e1", kingBlack);

        possibleMoves = kingBlack.getPossibleMoves();

        assertTrue(possibleMoves.containsAll(Arrays.asList("d1", "d2", "e2", "f1", "f2")));
    }

    public void testNotMove() {
        King kingBlack = King.createPiece(Color.BLACK);
        board.addPiece(kingBlack, "h7");

        possibleMoves = kingBlack.getPossibleMoves();

        assertFalse(possibleMoves.contains("h5"));
    }
}

