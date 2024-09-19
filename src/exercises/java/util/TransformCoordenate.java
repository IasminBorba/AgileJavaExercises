package util;

import chess.Position;

public class TransformCoordenate {
    public static Position aplly(String coordenate) {
        int column = convertColumnToIndex(coordenate.charAt(0));
        int rank = convertRankToIndex(coordenate.charAt(1));

        return new Position(rank, column);
    }

    public static String convertCoordinate(int column, int rank) {
        String colunmStr = indexToColumnLetter(column);
        String rankStr = rankToLetter(rank);

        return colunmStr+rankStr;
    }

    public static String teste(Position position) {
        return convertCoordinate(position.getColumn(), position.getRank());
    }

    public static int convertColumnToIndex(char column) {
        return switch (column) {
            case 'a' -> 0;
            case 'b' -> 1;
            case 'c' -> 2;
            case 'd' -> 3;
            case 'e' -> 4;
            case 'f' -> 5;
            case 'g' -> 6;
            case 'h' -> 7;
            default -> throw new InvalidColumnException(column);
        };
    }

    private static class InvalidColumnException extends IllegalArgumentException {
        public InvalidColumnException(char column) {
            super("Invalid column: " + column);
        }
    }

    public static int convertRankToIndex(char rankChar) {
        return validRank(rankChar - '1');
    }

    public static int validRank(int rank) {
        if (rank < 0 || rank > 7)
            throw new InvalidRankException(rank);

        return rank;
    }

    private static class InvalidRankException extends IllegalArgumentException {
        public InvalidRankException(int rank) {
            super("Invalid rank: " + (rank + 1));
        }

    }

    public static String indexToColumnLetter(int column) {
        return switch (column) {
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

    public static String rankToLetter(int rank) {
        rank++;
        if (rank >= 1 && rank <= 8)
            return String.valueOf(rank);
        else
            return "";
    }
}
