package chess;

import util.TransformCoordenate;

public class Position {
    private int row;
    private int file;

    public Position(int row, int file) {
        this.row = row;
        this.file = file;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    public String toAlgebraicNotation() {
        return String.format(TransformCoordenate.convertCoordinate(file, row));
    }
}
