package chess;

import pieces.*;
import pieces.Piece.*;
import util.PrintBoard;
import chess.PositionAction.*;
import util.TransformCoordenate;

import java.io.*;
import java.util.*;

public class Board implements Serializable {
    private Piece[][] board;
    private ArrayList<Piece> pieces = new ArrayList<>();

    public Board() {
        createBoard();
    }

    public void createBoard() {
        board = new Piece[8][8];
        fillBoardEmptyPieces();
    }

    private void fillBoardEmptyPieces() {
        iterateBoard((rank, column) -> board[column][rank] = null);
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
        addStartingPieces(Color.WHITE);
        addStartingPieces(Color.BLACK);
    }

    private void addStartingPieces(Color color) {
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
        updatePiece(piece);
        updateBoard();
    }

    private void updatePiece(Piece piece) {
        if (verifyEmptyFirstPiece())
            pieces.set(0, piece);
        else
            pieces.add(piece);
    }

    private boolean verifyEmptyFirstPiece() {
        return (!pieces.isEmpty() && pieces.getFirst() == null);
    }

    private void updateBoard() {
        cleanBoard();
        for (Piece piece : getPieces()) {
            Position piecePosition = piece.getPosition();
            if (pieceOnTheBoardIsEmpty(piecePosition))
                board[piecePosition.getColumn()][piecePosition.getRank()] = piece;
        }
    }

    private void cleanBoard() {
        board = new Piece[8][8];
    }

    private boolean pieceOnTheBoardIsEmpty(Position position) {
        return board[position.getColumn()][position.getRank()] == null;
    }

    public Piece getPiece(int column, int rank) {
        return board[column][rank];
    }

    public void addPiece(Piece piece, String coordinate) {
        Position position = TransformCoordenate.aplly(coordinate);
        addPieceToTheBoard(piece, position);
    }

    public void movePiece(String coordenate, Piece piece) {
        Position position = TransformCoordenate.aplly(coordenate);
        piece.setPosition(position);

        updateBoard();
    }

    public void removePieceFromTheBoard(Piece piece) {
        ArrayList<Piece> auxArray = new ArrayList<>();
        for (Piece p : pieces)
            if (piece != p)
                auxArray.add(p);

        pieces = auxArray;

        updateBoard();
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public List<Piece> getRank(int rank) {
        List<Piece> rankPiece = new ArrayList<>();
        for (int column = 0; column < 8; column++) {
            Piece auxPIece = board[column][rank - 1];
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