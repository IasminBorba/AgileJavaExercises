package chess;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;


public class MovesHelperTest extends TestCase {
    ArrayList<String> moves;

    public void setUp() {
        moves = new ArrayList<>();
    }

    public void testAddIfValidMove() {
        MovesHelper.addIfValidMove(1,1, moves);
        assertEquals("b1", moves.getFirst());
    }

    public void testAddNotValidMovement() {
        MovesHelper.addIfValidMove(10,99, moves);
        assertTrue(moves.isEmpty());
    }

    public void testAddDiagonalNeighborMoves() {
        Position position = new Position(0,0);
        MovesHelper.addDiagonalNeighborMoves(1,1, position, moves);

        assertTrue(moves.size() == 1);
        assertTrue(moves.contains("b2"));
    }

    public void testAddDiagonalsMovementsLeftUp() {
        Position position = new Position(0,7);
        MovesHelper.calculateDiagonalMoves(-1, moves, position);

        System.out.println(moves);
        assertTrue(moves.size() == 7);
        assertTrue(moves.containsAll(Arrays.asList("g2", "f3", "e4", "d5", "c6", "b7", "a8")));
    }

    public void testAddDiagonalsMovementsRightUp() {
        Position position = new Position(0,0);
        MovesHelper.calculateDiagonalMoves(1, moves, position);

        assertTrue(moves.size() == 7);
        assertTrue(moves.containsAll(Arrays.asList("b2", "c3", "d4", "e5", "f6", "g7", "h8")));
    }
}
