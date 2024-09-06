package chess;

public interface PositionAction {
    @FunctionalInterface
    interface CellAction {
        void apply(int rank, int column);
    }

    @FunctionalInterface
    interface EndOfRowAction {
        void apply(int rank);
    }
}
