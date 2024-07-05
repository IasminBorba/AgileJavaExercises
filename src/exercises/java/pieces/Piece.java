package pieces;

//import chess.Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

abstract public class Piece implements Comparable<Piece>, Serializable {
    public enum Color {WHITE, BLACK}
    public enum Type {
        PAWN(1,'P'),
        KNIGHT(2.5,'N'),
        ROOK(5,'R'),
        BISHOP(3,'B'),
        QUEEN(9,'Q'),
        KING(0,'K');
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
    private final Color color;
    private final Type type;
    private double points;
    private final char representation;
    protected int column;
    protected int rank;

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
    public int compareTo(Piece that) {
        int compare = this.getType().compareTo(that.getType());
        if(compare != 0)
            return compare;
        return this.getColor().compareTo(that.getColor());
    }

    public abstract ArrayList<String> getPossibleMoves(String position);

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

    public Color getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }

    public void setPosition(int column, int rank){
        this.column = column;
        this.rank = rank;
    }

    public ArrayList<Integer> getPiecePosition(){
        ArrayList<Integer> piecePosition = new ArrayList<>();
        piecePosition.add(this.column);
        piecePosition.add(this.rank);

        return piecePosition;
    }
}
