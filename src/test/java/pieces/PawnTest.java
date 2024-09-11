package pieces;

import chess.Board;

import java.util.ArrayList;

import static pieces.Pawn.*;
import static pieces.Queen.Class;

public class PawnTest extends PieceTest {
    private Board board;
    private ArrayList<String> possibleMoves;

    @Override
    public void setUp() {
        super.setUp();
        board = new Board();
        possibleMoves = new ArrayList<>();
    }

    public void testCreate() {
        Piece pawnBlack = createPiece(Color.BLACK, Class);
        assertEquals(Pawn.Class, pawnBlack.getType());
        assertEquals('P', pawnBlack.getRepresentation());

        Piece pawnWhite = createPiece(Color.WHITE, Class);
        assertEquals(Pawn.Class, pawnWhite.getType());
        assertEquals('p', pawnWhite.getRepresentation());
    }

    protected Piece createPiece(Color color, Type type) {
        return Pawn.create(color);
    }

    public void testMoveWhitePawn() {
        Pawn pawnWhite = Pawn.create(Piece.Color.WHITE);
        board.addPiece(pawnWhite, "d5");

        possibleMoves = pawnWhite.getPossibleMoves();

        assertTrue(possibleMoves.contains("d6"));
    }

    public void testNotMoveWhitePawn() {
        Pawn pawnWhite = Pawn.create(Piece.Color.WHITE);
        board.addPiece(pawnWhite, "a2");

        possibleMoves = pawnWhite.getPossibleMoves();

        assertFalse(possibleMoves.contains("a1"));
    }

    public void testMoveBlackPawn() {
        Pawn pawnBlack = Pawn.create(Color.BLACK);
        board.addPiece(pawnBlack, "g4");

        possibleMoves = pawnBlack.getPossibleMoves();

        assertTrue(possibleMoves.contains("g3"));
    }

    public void testNotMoveBlackPawn() {
        Pawn pawnBlack = Pawn.create(Color.BLACK);
        board.addPiece(pawnBlack, "f5");

        possibleMoves = pawnBlack.getPossibleMoves();

        assertFalse(possibleMoves.contains("f6"));
    }
}