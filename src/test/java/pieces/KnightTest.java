package pieces;

import chess.Board;

import java.util.ArrayList;
import java.util.Arrays;

import static pieces.Knight.*;
import static pieces.Queen.Class;

public class KnightTest extends PieceTest {
    private Board board;
    private ArrayList<String> possibleMoves;

    @Override
    public void setUp() {
        super.setUp();
        board = new Board();
        possibleMoves = new ArrayList<>();
    }

    public void testCreate() {
        Piece knightBlack = createPiece(Color.BLACK, Class);
        assertEquals(Knight.Class, knightBlack.getType());
        assertEquals('N', knightBlack.getRepresentation());

        Piece knightWhite = createPiece(Color.WHITE, Class);
        assertEquals(Knight.Class, knightWhite.getType());
        assertEquals('n', knightWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return Knight.create(color);
    }

    public void testMove() {
        Knight knightWhite = Knight.create(Piece.Color.WHITE);
        board.addPiece(knightWhite, "d5");

        possibleMoves = knightWhite.getPossibleMoves();

        assertTrue(possibleMoves.containsAll(Arrays.asList("c3", "c7", "b4", "b6", "e3", "e7", "f4", "f6")));
    }

    public void testLotsMovements() {
        Knight knightBlack = Knight.create(Color.BLACK);
        board.addPiece(knightBlack, "f4");

        board.movePiece("e2", knightBlack);
        board.movePiece("c1", knightBlack);
        board.movePiece("a2", knightBlack);

        possibleMoves = knightBlack.getPossibleMoves();

        assertTrue(possibleMoves.containsAll(Arrays.asList("b4", "c3", "c1")));
    }

    public void testNotMove() {
        Knight knightBlack = Knight.create(Color.BLACK);
        board.addPiece(knightBlack, "h1");

        possibleMoves = knightBlack.getPossibleMoves();

        assertFalse(possibleMoves.contains("f1"));
    }
}