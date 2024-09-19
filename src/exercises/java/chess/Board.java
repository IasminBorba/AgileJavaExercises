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
        iterateBoard((rank, column) -> boardCells[column][rank] = null);
    }

    public void iterateBoard(CellAction cellAction) {
        iterateBoard(cellAction, rank -> {
        });
    }

    public void iterateBoard(CellAction cellAction, EndOfRowAction endOfRowAction) {
        for (int rank = 0; rank < 8; rank++) {
            for (int column = 0; column < 8; column++)
                cellAction.apply(rank, column);

            endOfRowAction.apply(rank);
        }
    }

    public void initialize() {
        placeInitialPieces(Color.WHITE);
        placeInitialPieces(Color.BLACK);
    }

    private void placeInitialPieces(Color color) {
        int rank = (color == Color.WHITE) ? 0 : 7;
        Piece[] pieces = {
                Rook.create(color),
                Knight.create(color),
                Bishop.create(color),
                Queen.create(color),
                King.create(color),
                Bishop.create(color),
                Knight.create(color),
                Rook.create(color)
        };

        for (int column = 0; column < pieces.length; column++) {
            addPieceToTheBoard(pieces[column], new Position(rank, column));
            addPawnPiece(new Position(rank, column));
        }
    }

    private void addPawnPiece(Position position) {
        if (position.getRank() == 0) {
            position.setRank(1);
            addPieceToTheBoard(Pawn.create(Color.WHITE), position);
        } else {
            position.setRank(6);
            addPieceToTheBoard(Pawn.create(Color.BLACK), position);
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
                boardCells[piecePosition.getColumn()][piecePosition.getRank()] = piece;
        }
    }

    private void clearBoard() {
        boardCells = new Piece[8][8];
    }

    private boolean isPositionEmpty(Position position) {
        return boardCells[position.getColumn()][position.getRank()] == null;
    }

    public Piece getPiece(int column, int rank) {
        return boardCells[column][rank];
    }

    public void addPiece(Piece piece, String coordinate) {
        Position position = TransformCoordenate.aplly(coordinate);
        addPieceToTheBoard(piece, position);
    }

    public void movePieceToPosition(String coordenate, Piece piece) {
        Position position = TransformCoordenate.aplly(coordenate);
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

    public List<Piece> getPiecesInRank(int rank) {
        List<Piece> rankPiece = new ArrayList<>();
        for (int column = 0; column < 8; column++) {
            Piece auxPIece = boardCells[column][rank - 1];
            if (auxPIece != null)
                rankPiece.add(auxPIece);
        }

        return rankPiece;
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
        return (new PrintBoard(this).print()).equals(new PrintBoard(that).print());
    }
}