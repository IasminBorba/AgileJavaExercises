package chess;

import junit.framework.TestCase;

import java.util.*;

public class MovesHelperTest extends TestCase {
    ArrayList<String> moves;

    public void setUp() {
        moves = new ArrayList<>();
    }

    public void testAddIfValidMove() {
        MovesHelper.addIfValidMove(1, 1, moves);
        assertEquals("b2", moves.getFirst());
    }

    public void testAddNotValidMovement() {
        MovesHelper.addIfValidMove(10, 99, moves);
        assertTrue(moves.isEmpty());
    }

    public void testAddDiagonalNeighborMoves() {
        Position position = new Position(0, 0);
        int distanceDiagonal = 1;
        int rightColumn = position.getFile() + distanceDiagonal;
        MovesHelper.addDiagonalNeighborMoves(distanceDiagonal, rightColumn, position, moves);

        assertTrue(moves.size() == 1);
        assertTrue(moves.contains("b2"));
    }

    public void testAddDiagonalMovesInvalidNeighbor() {
        Position position = new Position(0, 0);
        int distanceDiagonal = 1;
        int leftColumn = position.getFile() - distanceDiagonal;
        MovesHelper.addDiagonalNeighborMoves(distanceDiagonal, leftColumn, position, moves);

        assertTrue(moves.isEmpty());
    }

    public void testAddDiagonalsMovementsLeftUp() {
        Position position = new Position(0, 7);
        MovesHelper.calculateDiagonalMoves(-1, moves, position);

        assertTrue(moves.size() == 7);
        assertTrue(moves.containsAll(Arrays.asList("g2", "f3", "e4", "d5", "c6", "b7", "a8")));
    }

    public void testAddDiagonalsMovementsRightUp() {
        Position position = new Position(0, 0);
        MovesHelper.calculateDiagonalMoves(1, moves, position);

        assertTrue(moves.size() == 7);
        assertTrue(moves.containsAll(Arrays.asList("b2", "c3", "d4", "e5", "f6", "g7", "h8")));
    }

    public void testAddDiagonalsMovementsLeftDown() {
        Position position = new Position(7, 7);
        MovesHelper.calculateDiagonalMoves(-1, moves, position);

        assertTrue(moves.size() == 7);
        assertTrue(moves.containsAll(Arrays.asList("g7", "f6", "e5", "d4", "c3", "b2", "a1")));
    }

    public void testAddDiagonalsMovementsRightDown() {
        Position position = new Position(7, 0);
        MovesHelper.calculateDiagonalMoves(1, moves, position);

        assertTrue(moves.size() == 7);
        assertTrue(moves.containsAll(Arrays.asList("b7", "c6", "d5", "e4", "f3", "g2", "h1")));
    }

    public void testAddDiagonalsMovementsLeft() {
        Position position = new Position(4, 4);
        MovesHelper.calculateDiagonalMoves(-1, moves, position);

        assertTrue(moves.size() == 7);
        assertTrue(moves.containsAll(Arrays.asList("d6", "d4", "c7", "c3", "b8", "b2", "a1")));
    }

    public void testAddDiagonalsMovementsRight() {
        Position position = new Position(4, 4);
        MovesHelper.calculateDiagonalMoves(1, moves, position);

        assertTrue(moves.size() == 6);
        assertTrue(moves.containsAll(Arrays.asList("f6", "f4", "g7", "g3", "h8", "h2")));
    }

    public void testAddAllDiagonalsMovements() {
        Position position = new Position(4, 3);
        MovesHelper.addDiagonalMoves(moves, position);

        assertTrue(moves.size() == 13);
        assertTrue(moves.containsAll(Arrays.asList("e6", "e4", "f7", "f3", "g8", "g2", "h1", "c6", "c4", "b7", "b3", "a8", "a2")));
    }

    public void testAddStraightLineMovements() {
        Position position = new Position(4, 3);
        MovesHelper.addStraightLineMoves(moves, position);

        assertTrue(moves.size() == 14);
        assertTrue(moves.containsAll(Arrays.asList("a5", "d1", "b5", "d2", "c5", "d3", "e5", "d4", "f5", "d6", "g5", "d7", "h5", "d8")));
    }

    public void testRemoveDuplicateMoves() {
        Position position = new Position(4, 3);
        List<String> duplicateMoves = new ArrayList<>(Arrays.asList("a5", "a5", "b5", "f3", "g4", "d5"));

        MovesHelper.removeInvalidOrDuplicateMoves(duplicateMoves, position);

        assertTrue(duplicateMoves.size() == 4);
        assertTrue(duplicateMoves.containsAll(Arrays.asList("a5", "b5", "f3", "g4")));
    }

    public void testRemoveInvalidMoves() {
        Position position = new Position(5, 5);
        List<String> duplicateMoves = new ArrayList<>(Arrays.asList("", "a1", "a2", "f99", "bb", "g9", "b5", "??", "ç3", "t1", "44", "e 1", "5g", "h", "1"));

        MovesHelper.removeInvalidOrDuplicateMoves(duplicateMoves, position);

        assertTrue(duplicateMoves.size() == 3);
        assertTrue(duplicateMoves.containsAll(Arrays.asList("a1", "a2", "b5")));
    }
}
