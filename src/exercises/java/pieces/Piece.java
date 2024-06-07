package pieces;

import chess.Board;
//import chess.Game;

import java.util.Objects;

import static chess.Board.transformPosition;

abstract public class Piece implements Comparable<Piece> {
    protected enum Color {WHITE, BLACK}
    protected enum Type {
        PAWN(1,'P'),
        KNIGHT(2.5,'N'),
        ROOK(5,'R'),
        BISHOP(3,'B'),
        QUEEN(9,'Q'),
        KING(0,'K'),
        NO_PIECE(0,'.');
        private final double points;
        private final char representation;

        Type(double points, char representation){
            this.points = points;
            this.representation = representation;
        }

        char getRepresentation(){
            return representation;
        }
    }
    private Color color;
    private final Type type;
    private double points;
    private final char representation;

    protected Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
        this.points = type.points;

        if (color == Color.WHITE) {
            this.representation = Character.toLowerCase(type.representation);
        } else {
            this.representation = type.representation;
        }
    }

    @Override
    public int compareTo(Piece o) {
        return 0;
    }

    private Piece() {
        this.type = Type.NO_PIECE;
        this.representation = '.';
    }

//    public static Piece createWhitePiece(Type type) {
//        return switch (type) {
//            case PAWN -> new Piece(Color.WHITE, Type.PAWN);
//            case ROOK -> new Piece(Color.WHITE, Type.ROOK);
//            case KNIGHT -> new Piece(Color.WHITE, Type.KNIGHT);
//            case BISHOP -> new Piece(Color.WHITE, Type.BISHOP);
//            case QUEEN -> new Piece(Color.WHITE, Type.QUEEN);
//            case KING -> new Piece(Color.WHITE, Type.KING);
//            case NO_PIECE -> null;
//        };
//    }
//
//    public static Piece createBlackPiece(Type type) {
//        return switch (type) {
//            case PAWN -> new Piece(Color.BLACK, Type.PAWN);
//            case ROOK -> new Piece(Color.BLACK, Type.ROOK);
//            case KNIGHT -> new Piece(Color.BLACK, Type.KNIGHT);
//            case BISHOP -> new Piece(Color.BLACK, Type.BISHOP);
//            case QUEEN -> new Piece(Color.BLACK, Type.QUEEN);
//            case KING -> new Piece(Color.BLACK, Type.KING);
//            case NO_PIECE -> null;
//        };
//    }

//    public boolean getPossibleMoves(Piece piece, char files, int rank) {
//        boolean permission = false;
//        if (rank > 8){
//            return false;
//        }
//        int file = Board.transformPosition(files);
//        if (file == 9) {
//            return false;
//        }
//
//        permission = switch (piece.getType()){
//            case KING -> Game.newKingPosition(piece, file, rank);
//            case PAWN -> false;
//            case KNIGHT -> false;
//            case ROOK -> false;
//            case BISHOP -> false;
//            case QUEEN -> Game.newQueenPosition(piece, file, rank);
//            case NO_PIECE -> false;
//        };
//
//
//        return permission;
//    }

//    public static Piece noPiece() {
//        return new Piece();
//    }

    public boolean isBlack() {
        return Objects.equals(color.name().toLowerCase(), "black");
    }

    public boolean isWhite() {
        return Objects.equals(color.name().toLowerCase(), "white");
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public double getPoints() {
        return points;
    }

    public Type getType() {
        return type;
    }

    public char getRepresentation() {
        return representation;
    }
}
