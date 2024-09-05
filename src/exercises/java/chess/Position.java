package chess;

public class Position {
    private int rank;
    private int column;

    public Position(int rank, int column) {
        this.rank = rank;
        this.column = column;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
