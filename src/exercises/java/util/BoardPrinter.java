package util;

import chess.Board;
import chess.Position;
import pieces.Piece;

public class BoardPrinter {
    private final StringBuilder boardRepresentation = new StringBuilder();
    private final Board board;

    public BoardPrinter(Board board) {
        this.board = board;
        setupEmptyBoard();
    }

    private void setupEmptyBoard() {
        board.iterateBoard(
                (rank, column) -> boardRepresentation.append("."),
                rank -> boardRepresentation.append(System.lineSeparator())
        );
    }

    public String visualize() {
        updateBoardRepresentation();
        return boardRepresentation.toString();
    }

    private void updateBoardRepresentation() {
        Piece[][] boardMap = board.getBoardCells();

        board.iterateBoard((rank, column) -> {
                    Piece piece = boardMap[column][rank];
                    if (piece != null)
                        placePieceOnBoardRepresentation(piece);
                }
        );
    }

    private void placePieceOnBoardRepresentation(Piece piece) {
        char pieceRepresentation = piece.getRepresentation();
        Position positionOnTheBoard = piece.getPosition();
        int newPosition = calculateStringBuilderPosition(positionOnTheBoard);

        boardRepresentation.setCharAt(newPosition, pieceRepresentation);
    }

    private int calculateStringBuilderPosition(Position position) {
        int invertedRank = 7 - position.getRow();
        int rowOffsetInStringBuilder = (invertedRank * 9);

        return rowOffsetInStringBuilder + position.getFile();
    }
}
