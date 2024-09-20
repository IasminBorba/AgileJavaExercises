package chess;

import util.CoordinateTransformer;

import java.util.*;

public class MovesHelper {
    public static void addDiagonalMoves(List<String> moves, Position position) {
        for (int direction : new int[]{1, -1})
            calculateDiagonalMoves(direction, moves, position);
    }

    protected static void calculateDiagonalMoves(int fileDirection, List<String> moves, Position position) {
        for (int offset = 1; isValidFile(position.getFile() + fileDirection * offset); offset++) {
            int newFile = position.getFile() + (fileDirection * offset);
            addDiagonalNeighborMoves(offset, newFile, position, moves);
        }
    }

    private static boolean isValidFile(int file) {
        return file >= 0 && file <= 8;
    }

    public static void addDiagonalNeighborMoves(int rowOffset, int file, Position position, List<String> moves) {
        for (int direction : new int[]{1, -1}) {
            int newRow = position.getRow() + (rowOffset * direction);
            addIfValidMove(newRow, file, moves);
        }
    }

    protected static void addIfValidMove(int row, int file, List<String> moves) {
        String move = CoordinateTransformer.positionToCoordinateString(file, row);
        if (!move.isEmpty())
            moves.add(move);
    }

    public static void addStraightLineMoves(ArrayList<String> moves, Position position) {
        for (int coordinate = 0; coordinate < 8; coordinate++) {
            moves.add(CoordinateTransformer.positionToCoordinateString(coordinate, position.getRow()));
            moves.add(CoordinateTransformer.positionToCoordinateString(position.getFile(), coordinate));
        }

        removeDuplicateOrInvalidMoves(moves, position);
    }

    public static void removeDuplicateOrInvalidMoves(List<String> moves, Position position) {
        moves.removeIf(String::isEmpty);
        moves.removeIf(move -> move.equals(position.toAlgebraicNotation()));

        Set<String> uniqueMoves = new LinkedHashSet<>(moves);
        moves.clear();
        moves.addAll(uniqueMoves);
    }
}