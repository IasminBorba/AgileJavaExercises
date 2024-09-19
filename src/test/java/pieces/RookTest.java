package pieces;

import chess.Board;

import java.util.ArrayList;
import java.util.Arrays;

import static pieces.Queen.Class;
import static pieces.Rook.*;

public class RookTest extends PieceTest {
    private Board board;
    private ArrayList<String> possibleMoves;

    @Override
    public void setUp() {
        super.setUp();
        board = new Board();
        possibleMoves = new ArrayList<>();
    }

    public void testCreate() {
        Piece rookBlack = createPiece(Color.BLACK, Class);
        assertEquals(Rook.Class, rookBlack.getType());
        assertEquals('R', rookBlack.getRepresentation());

        Piece rookWhite = createPiece(Color.WHITE, Class);
        assertEquals(Rook.Class, rookWhite.getType());
        assertEquals('r', rookWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return Rook.create(color);
    }

    public void testMove() {
        Rook rookWhite = Rook.create(Piece.Color.WHITE);
        board.addPiece(rookWhite, "b7");

        possibleMoves = rookWhite.getPossibleMoves();

        assertTrue(possibleMoves.containsAll(Arrays.asList("b8", "b6", "b5", "b4", "b3", "b2", "b1", "a7", "c7", "d7", "e7", "g7", "h7")));
    }

    public void testLotsMovements() {
        Rook rookBlack = Rook.create(Color.BLACK);
        board.addPiece(rookBlack, "e1");

        board.movePieceToPosition("e8", rookBlack);
        board.movePieceToPosition("a8", rookBlack);
        board.movePieceToPosition("a1", rookBlack);

        possibleMoves = rookBlack.getPossibleMoves();

        assertTrue(possibleMoves.containsAll(Arrays.asList("a2", "a3", "a4", "a5", "a6", "a7", "a8", "b1", "c1", "d1", "e1", "f1", "g1", "h1")));
    }

    public void testNotMove() {
        Bishop bishopBlack = Bishop.create(Color.BLACK);
        board.addPiece(bishopBlack, "f8");

        possibleMoves = bishopBlack.getPossibleMoves();

        assertFalse(possibleMoves.contains("f9"));
    }
}