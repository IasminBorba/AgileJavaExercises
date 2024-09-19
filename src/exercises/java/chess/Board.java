package chess;

import pieces.*;
import pieces.Piece.*;
import chess.PositionAction.*;
import util.*;

import java.io.*;
import java.util.*;

public class Board implements Serializable {
    private Piece[][] boardCells;
    private ArrayList<Piece> piecesOnBoard = new ArrayList<>();

    public Board() {
        createBoard();
    }

    public void createBoard() {
        boardCells = new Piece[8][8];
        initializeBoardWithEmptyPieces();
    }

    private void initializeBoardWithEmptyPieces() {
        iterateBoard((row, file) -> boardCells[file][row] = null);
    }

    public void iterateBoard(CellAction cellAction) {
        iterateBoard(cellAction, row -> {
        });
    }

    public void iterateBoard(CellAction cellAction, EndOfRowAction endOfRowAction) {
        for (int row = 0; row < 8; row++) {
            for (int file = 0; file < 8; file++)
                cellAction.apply(row, file);

            endOfRowAction.apply(row);
        }
    }

    public void initialize() {
        placeInitialPieces(Color.WHITE);
        placeInitialPieces(Color.BLACK);
    }

    private void placeInitialPieces(Color color) {
        int row = (color == Color.WHITE) ? 0 : 7;
        Piece[] pieces = {
                Rook.createPiece(color),
                Knight.createPiece(color),
                Bishop.createPiece(color),
                Queen.createPiece(color),
                King.createPiece(color),
                Bishop.createPiece(color),
                Knight.createPiece(color),
                Rook.createPiece(color)
        };

        for (int file = 0; file < pieces.length; file++) {
            addPieceToTheBoard(pieces[file], new Position(row, file));
            addPawnPiece(new Position(row, file));
        }
    }

    private void addPawnPiece(Position position) {
        if (position.getRow() == 0) {
            position.setRow(1);
            addPieceToTheBoard(Pawn.createPiece(Color.WHITE), position);
        } else {
            position.setRow(6);
            addPieceToTheBoard(Pawn.createPiece(Color.BLACK), position);
        }
    }

    private void addPieceToTheBoard(Piece piece, Position position) {
        piece.setPosition(position);
        addOrUpdatePiece(piece);
        updateBoard();
    }

    private void addOrUpdatePiece(Piece piece) {
        if (isFirstPieceSlotEmpty())
            piecesOnBoard.set(0, piece);
        else
            piecesOnBoard.add(piece);
    }

    private boolean isFirstPieceSlotEmpty() {
        return (!piecesOnBoard.isEmpty() && piecesOnBoard.getFirst() == null);
    }

    private void updateBoard() {
        clearBoard();
        for (Piece piece : getPiecesOnBoard()) {
            Position piecePosition = piece.getPosition();
            if (isPositionEmpty(piecePosition))
                boardCells[piecePosition.getFile()][piecePosition.getRow()] = piece;
        }
    }

    private void clearBoard() {
        boardCells = new Piece[8][8];
    }

    private boolean isPositionEmpty(Position position) {
        return boardCells[position.getFile()][position.getRow()] == null;
    }

    public Piece getPiece(int file, int row) {
        return boardCells[file][row];
    }

    public void addPiece(Piece piece, String coordinate) {
        Position position = CoordinateTransformer.stringToPosition(coordinate);
        addPieceToTheBoard(piece, position);
    }

    public void movePieceToPosition(String coordenate, Piece piece) {
        Position position = CoordinateTransformer.stringToPosition(coordenate);
        piece.setPosition(position);

        updateBoard();
    }

    public void removePieceFromTheBoard(Piece piece) {
        ArrayList<Piece> remainingPieces = new ArrayList<>();
        for (Piece p : piecesOnBoard)
            if (piece != p)
                remainingPieces.add(p);

        piecesOnBoard = remainingPieces;

        updateBoard();
    }

    public ArrayList<Piece> getPiecesOnBoard() {
        return piecesOnBoard;
    }

    public Piece[][] getBoardCells() {
        return boardCells;
    }

    public List<Piece> getPiecesInRow(int row) {
        List<Piece> rowPiece = new ArrayList<>();
        for (int file = 0; file < 8; file++) {
            Piece auxPIece = boardCells[file][row - 1];
            if (auxPIece != null)
                rowPiece.add(auxPIece);
        }

        return rowPiece;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (this == object)
            return true;
        if (this.getClass() != object.getClass())
            return false;

        Board that = (Board) object;
        return (new BoardPrinter(this).visualize()).equals(new BoardPrinter(that).visualize());
    }
}