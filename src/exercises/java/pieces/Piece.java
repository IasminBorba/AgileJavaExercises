package pieces;

import chess.Position;

import java.awt.*;
import java.io.Serializable;
import java.util.*;

abstract public class Piece implements Serializable {
    public enum Color {WHITE, BLACK}

    public enum Type {
        PAWN(1, 'P'),
        KNIGHT(2.5, 'N'),
        ROOK(5, 'R'),
        BISHOP(3, 'B'),
        QUEEN(9, 'Q'),
        KING(0, 'K');
        private final double points;
        private final char representation;

        Type(double points, char representation) {
            this.points = points;
            this.representation = representation;
        }

        char getRepresentation() {
            return representation;
        }
    }

    private final Color color;
    private final Type type;
    private double points;
    private final char representation;
    private Position position;
    protected Image icon;

    public Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
        this.points = type.points;

        if (color == Color.WHITE)
            this.representation = Character.toLowerCase(type.representation);
        else
            this.representation = type.representation;

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

    public double getPoints() {
        return points;
    }

    public Position getPosition() {
        return position;
    }

    public abstract ArrayList<String> getPossibleMoves();

    public boolean isWhite() {
        return Objects.equals(color.name().toLowerCase(), "white");
    }

    public boolean isBlack() {
        return Objects.equals(color.name().toLowerCase(), "black");
    }

    public void updatePoints(double points) {
        this.points = points;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean compareTo(Piece that) {
        return this.getType().equals(that.getType()) && this.getColor().equals(that.getColor());
    }

    public Image getImage() {
        return icon;
    }
}
