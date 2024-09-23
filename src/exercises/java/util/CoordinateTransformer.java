package util;

import chess.Position;

public class CoordinateTransformer {
    public static Position stringToPosition(String coordenate) {
        int file = convertFileToIndex(coordenate.charAt(0));
        int row = convertRowToIndex(coordenate.charAt(1));

        return new Position(row, file);
    }

    public static int convertFileToIndex(char file) {
        int fileIndex = file - 'a';

        if (!isValidIndex(fileIndex))
            throw new InvalidFileException(file);

        return fileIndex;
    }

    private static class InvalidFileException extends IllegalArgumentException {
        public InvalidFileException(char file) {
            super("Invalid file: " + file);
        }
    }

    public static int convertRowToIndex(char row) {
        int rowIndex = row - '1';
        if (!isValidIndex(rowIndex))
            throw new InvalidRowException(rowIndex);

        return rowIndex;
    }

    private static class InvalidRowException extends IllegalArgumentException {
        public InvalidRowException(int row) {
            super("Invalid row: " + (row + 1));
        }
    }

    public static String positionToCoordinateString(int file, int row) {
        String fileLetter = fileIndexToLetter(file);
        String rowNumber = rowIndexToNumber(row);
        String coordinate = fileLetter + rowNumber;

        if (isCoordinateValid(coordinate))
            return coordinate;

        return "";
    }

    public static String fileIndexToLetter(int file) {
        char letterFile = (char) ('a' + file);

        return String.valueOf(letterFile);
    }

    public static String rowIndexToNumber(int row) {
        return String.valueOf(++row);
    }

    public static boolean isCoordinateValid(String coordinate) {
        if(coordinate.length() != 2)
            return false;

        int fileIndex = coordinate.charAt(0) - 'a';
        int rowIndex = coordinate.charAt(1) - '1';

        return isValidIndex(rowIndex) && isValidIndex(fileIndex);
    }

    public static boolean isValidIndex(int index) {
        return index >= 0 && index <= 7;
    }
}
