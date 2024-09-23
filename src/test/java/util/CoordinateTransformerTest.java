package util;

import chess.Position;
import junit.framework.TestCase;

public class CoordinateTransformerTest extends TestCase {
    public void testStringToPosition() {
        Position position = new Position(5, 5);
        assertEquals(position.toAlgebraicNotation(), CoordinateTransformer.stringToPosition("f6").toAlgebraicNotation());
    }

    public void testConvertFileToIndex() {
        char fileLetter = 'h';
        assertEquals(7, CoordinateTransformer.convertFileToIndex(fileLetter));
    }

    public void testInvalidFileException() {
        char fileLetter = 'z';
        try {
            int fileIndex = CoordinateTransformer.convertFileToIndex(fileLetter);
        } catch (Exception e) {
            assertEquals("Invalid file: z", e.getMessage());
        }
    }

    public void testInvalidRowException() {
        char row = '3';
        assertEquals(2, CoordinateTransformer.convertRowToIndex(row));
    }

    public void testConvertRowToIndex() {
        char row = '9';
        try {
            int rowIndex = CoordinateTransformer.convertRowToIndex(row);
        } catch (Exception e) {
            assertEquals("Invalid row: 9", e.getMessage());
        }
    }

    public void testPositionToCoordinateString() {
        int file = 5;
        int row = 5;

        assertEquals("f6", CoordinateTransformer.positionToCoordinateString(file, row));
    }

    public void testNotPositionToCoordinateString() {
        int file = 5;
        int row = 9;

        assertEquals("", CoordinateTransformer.positionToCoordinateString(file, row));
    }

    public void testFileIndexToLetter() {
        int file = 0;
        assertEquals("a", CoordinateTransformer.fileIndexToLetter(file));
    }

    public void testRowIndexToNumber() {
        int row = 4;
        assertEquals("5", CoordinateTransformer.rowIndexToNumber(row));
    }

    public void testIsCoordinateValid() {
        String coordinate = "a5";
        assertTrue(CoordinateTransformer.isCoordinateValid(coordinate));
    }

    public void testIsNotCoordinateValid() {
        String coordinate = "??";
        assertFalse(CoordinateTransformer.isCoordinateValid(coordinate));
    }

    public void testIsValidIndex() {
        int index = 7;
        assertTrue(CoordinateTransformer.isValidIndex(index));
    }

    public void testIsNotValidIndex() {
        int index = -1;
        assertFalse(CoordinateTransformer.isValidIndex(index));
    }
}