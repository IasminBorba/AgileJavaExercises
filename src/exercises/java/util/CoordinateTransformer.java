package util;

import chess.Position;

public class CoordinateTransformer {
    public static Position stringToPosition(String coordenate) {
        int file = convertFileToIndex(coordenate.charAt(0));
        int row = convertRowToIndex(coordenate.charAt(1));

        return new Position(row, file);
    }

    public static String positionToCoordinateString(int file, int row) {
        String fileLetter = fileIndexToLetter(file);
        String rowNumber = rowIndexToNumber(row);

        if (isCoordinateValid(fileLetter, rowNumber))
            return fileLetter + rowNumber;

        return "";
    }

    private static boolean isCoordinateValid(String column, String rank) {
        return !column.isEmpty() && !rank.isEmpty();
    }


    public static String positionToString(Position position) {
        return positionToCoordinateString(position.getFile(), position.getRow());
    }

    public static int convertFileToIndex(char file) {
        return switch (file) {
            case 'a' -> 0;
            case 'b' -> 1;
            case 'c' -> 2;
            case 'd' -> 3;
            case 'e' -> 4;
            case 'f' -> 5;
            case 'g' -> 6;
            case 'h' -> 7;
            default -> throw new InvalidException(file);
        };
    }

    private static class InvalidException extends IllegalArgumentException {
        public InvalidException(char file) {
            super("Invalid file: " + file);
        }
    }

    public static int convertRowToIndex(char rowChar) {
        int row = rowChar - '1';
        if (!isValidRow(row))
            throw new InvalidRowException(row);

        return row;
    }

    public static boolean isValidRow(int row) {
        return row >= 0 && row <= 7;
    }

    private static class InvalidRowException extends IllegalArgumentException {
        public InvalidRowException(int row) {
            super("Invalid row: " + (row + 1));
        }

    }

    public static String fileIndexToLetter(int file) {
        return switch (file) {
            case 0 -> "a";
            case 1 -> "b";
            case 2 -> "c";
            case 3 -> "d";
            case 4 -> "e";
            case 5 -> "f";
            case 6 -> "g";
            case 7 -> "h";
            default -> "";
        };
    }

    public static String rowIndexToNumber(int row) {
        row++;
        if (row >= 1 && row <= 8)
            return String.valueOf(row);
        else
            return "";
    }
}
