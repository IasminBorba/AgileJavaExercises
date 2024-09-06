package util;

import chess.Position;

public class TransformCoordenate {
    public static Position aplly(String coordenate) {
        int column = convertColumnToIndex(coordenate.charAt(0));

        String rank = String.valueOf(coordenate.charAt(1));
        return new Position((Integer.parseInt(rank)) - 1, column);
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
            default -> 9;
        };
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
            default -> "error";
        };
    }

    public static String rankToLetter(int rank) {
        rank++;
        if (rank >= 0 && rank <= 8) {
            return String.valueOf(rank);
        } else
            return "error";
    }
}
