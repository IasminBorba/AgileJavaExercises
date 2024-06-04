package pieces;

import java.util.Objects;

public class Piece {
    public enum Color {WHITE, BLACK}
    public enum Type {
        PAWN(1,'P'),
        KNIGHT(2.5,'N'),
        ROOK(5,'R'),
        BISHOP(3,'B'),
        QUEEN(9,'Q'),
        KING(0,'K'),
        NO_PIECE(0,'.');
        public final double points;
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
    public double points;
    public char representation;

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
        this.points = type.points;

        if (color == Color.WHITE) {
            this.representation = Character.toLowerCase(type.representation);
        }
    }

    private Piece() {
        this.type = Type.NO_PIECE;
        this.representation = '.';
    }

    public static Piece createWhitePiece(Type type) {
        return switch (type) {
            case PAWN -> new Piece(Color.WHITE, Type.PAWN);
            case ROOK -> new Piece(Color.WHITE, Type.ROOK);
            case KNIGHT -> new Piece(Color.WHITE, Type.KNIGHT);
            case BISHOP -> new Piece(Color.WHITE, Type.BISHOP);
            case QUEEN -> new Piece(Color.WHITE, Type.QUEEN);
            case KING -> new Piece(Color.WHITE, Type.KING);
            case NO_PIECE -> null;
        };
    }

    public static Piece createBlackPiece(Type type) {
        return switch (type) {
            case PAWN -> new Piece(Color.BLACK, Type.PAWN);
            case ROOK -> new Piece(Color.BLACK, Type.ROOK);
            case KNIGHT -> new Piece(Color.BLACK, Type.KNIGHT);
            case BISHOP -> new Piece(Color.BLACK, Type.BISHOP);
            case QUEEN -> new Piece(Color.BLACK, Type.QUEEN);
            case KING -> new Piece(Color.BLACK, Type.KING);
            case NO_PIECE -> null;
        };
    }

    public static Piece noPiece() {
        return new Piece();
    }

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
