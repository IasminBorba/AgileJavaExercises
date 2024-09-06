package chess;

import pieces.*;
import pieces.Piece.*;
import util.StringUtil;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import chess.PositionAction.*;
import util.TransformCoordenate;

public class Board implements Serializable {
    public Piece[][] board;
    public int piecesWhite;
    public int piecesBlack;
    protected ArrayList<Piece> pieces = new ArrayList<>();
    public StringBuilder piecesOnTheBoard = new StringBuilder();

    public Board() {
        createBoard();
    }

    public void initialize() {
        addStartingPieces(Color.WHITE);
        addStartingPieces(Color.BLACK);
    }

    public void addPiece(Piece piece, String coordinate) {
        Position position = TransformCoordenate.aplly(coordinate);
        addPieceToTheBoard(piece, position);
    }

    public void movePiece(String coordenate, Piece piece) {
        Position position = TransformCoordenate.aplly(coordenate);
        piece.setPosition(position.getColumn(), position.getRank());

        updateBoard();
    }

    public void removePieceFromTheBoard(Piece piece) {
        ArrayList<Piece> auxArray = new ArrayList<>();
        for (Piece p : pieces)
            if (piece != p)
                auxArray.add(p);

        pieces = auxArray;

        iterateBoard ((rank, column) -> {
            if (piece == board[column][rank])
                board[column][rank] = null;
        });

        updateQuantityOfPieces();
    }

    public String print() {
        updatePrint();
        return piecesOnTheBoard.toString();
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public int getPiecesWhite() {
        updateQuantityOfPieces();
        return piecesWhite;
    }

    public int getPiecesBlack() {
        updateQuantityOfPieces();
        return piecesBlack;
    }

    public int pieceCount() {
        return pieces.size();
    }

    public String getRank(int rank) {
        StringBuilder rankPiece = new StringBuilder();
        for (int column = 0; column < 8; column++) {
            Piece auxPIece = board[column][rank - 1];
            if (auxPIece == null)
                rankPiece.append('.');
            else
                rankPiece.append(auxPIece.getRepresentation());
        }

        return rankPiece.toString();
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Piece getPiece(int column, int rank) {
        return board[column][rank];
    }

    char getPieceRepresentation(char columnChar, int rank) {
        int column = TransformCoordenate.convertColumnToIndex(columnChar);
        Piece piece = board[column][rank - 1];
        if (piece == null)
            return '.';

        return piece.getRepresentation();
    }

    public void iterateBoard(CellAction cellAction) {
        iterateBoard(cellAction, rank -> {});
    }

    public void iterateBoard(CellAction cellAction, EndOfRowAction endOfRowAction) {
        for (int rank = 0; rank < 8; rank++) {
            for (int column = 0; column < 8; column++)
                cellAction.apply(rank, column);

            endOfRowAction.apply(rank);
        }
    }

    public void createBoard() {
        board = new Piece[8][8];
        fillBoardEmptyPieces();
    }

    public void fillBoardEmptyPieces() {
        iterateBoard(
                (rank, column) -> {
                    piecesOnTheBoard.append('.');
                    board[column][rank] = null;
                },
                rank -> piecesOnTheBoard.append(StringUtil.NEWLINE)
        );
    }

    public void addStartingPieces(Color color) {
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
            Position position = new Position(rank, column);
            addPieceToTheBoard(pieces[column], position);
            addPawnPiece(position);
        }
    }

    public void addPawnPiece(Position position) {
        if (position.getRank() == 0) {
            position.setRank(1);
            addPieceToTheBoard(Pawn.create(Color.WHITE), position);
        } else {
            position.setRank(6);
            addPieceToTheBoard(Pawn.create(Color.BLACK), position);
        }
    }

    public void addPieceToTheBoard(Piece piece, Position position) {
        piece.setPosition(position.getColumn(), position.getRank());

        updatePiece(piece);
        updateBoard();
    }

    private void updatePiece(Piece piece) {
        if (verifyEmptyFirstPiece())
            pieces.set(0, piece);
        else
            pieces.add(piece);

        updateQuantityOfPieces();
    }

    private boolean verifyEmptyFirstPiece() {
        return (!pieces.isEmpty() && pieces.getFirst() == null);
    }

    private void updateBoard() {
        cleanBoard();

        for (Piece piece : getPieces()) {
            board[piece.column][piece.rank] = piece;
            updatePrint();
        }
    }

    private void cleanBoard() {
        board = new Piece[8][8];
    }

    public void updatePrint() {
        iterateBoard(
                (rank, column) -> modifyStringBuilderPosition(column, rank)
        );
    }

    private void modifyStringBuilderPosition(int column, int rank) {
        if(!pieceOnTheBoardIsEmpty(column, rank)) {
            char pieceRepresentation = getPiece(column, rank).getRepresentation();
            int position = calculateStringBuilderPosition(column, rank);

            piecesOnTheBoard.setCharAt(position, pieceRepresentation);
        }
    }

    private boolean pieceOnTheBoardIsEmpty(int column, int rank) {
        return board[column][rank] == null;
    }

    private int calculateStringBuilderPosition(int column, int rank) {
        int invertedRank = 7 - rank;
        int rowOffsetInStringBuilder = (invertedRank * 9);

        return rowOffsetInStringBuilder + column;
    }

    private void updateQuantityOfPieces() {
        AtomicInteger countingWhitePieces = new AtomicInteger();
        AtomicInteger countingBlackPieces = new AtomicInteger();

        iterateBoard((rank, column) -> {
            Piece piece = board[column][rank];
            if (piece != null && piece.isWhite())
                countingWhitePieces.getAndIncrement();
            if (piece != null && piece.isBlack())
                countingBlackPieces.getAndIncrement();
        });

        piecesWhite = countingWhitePieces.get();
        piecesBlack = countingBlackPieces.get();
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
        return this.print().equals(that.print());
    }
}