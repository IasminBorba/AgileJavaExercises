package util;

import chess.Board;
import chess.Position;
import pieces.Piece;

public class PrintBoard {
    private final StringBuilder piecesOnTheBoard = new StringBuilder();
    private final Board board;

    public PrintBoard(Board board) {
        this.board = board;
        initialize();
    }

    private void initialize() {
        board.iterateBoard(
                (rank, column) -> piecesOnTheBoard.append("."),
                rank -> piecesOnTheBoard.append(System.lineSeparator())
        );
    }

    public String print() {
        updatePrint();
        return piecesOnTheBoard.toString();
    }

    private void updatePrint() {
        Piece[][] boardMap = board.getBoardCells();

        board.iterateBoard((rank, column) -> {
                    Piece piece = boardMap[column][rank];
                    if (piece != null)
                        modifyStringBuilderPosition(piece);
                }
        );
    }

    private void modifyStringBuilderPosition(Piece piece) {
        char pieceRepresentation = piece.getRepresentation();
        Position positionOnTheBoard = piece.getPosition();
        int newPosition = calculateStringBuilderPosition(positionOnTheBoard);

        piecesOnTheBoard.setCharAt(newPosition, pieceRepresentation);
    }

    private int calculateStringBuilderPosition(Position position) {
        int invertedRank = 7 - position.getRow();
        int rowOffsetInStringBuilder = (invertedRank * 9);

        return rowOffsetInStringBuilder + position.getFile();
    }
}
