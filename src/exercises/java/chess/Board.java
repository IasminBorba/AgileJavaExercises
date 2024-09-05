package chess;

import pieces.*;
import util.StringUtil;

import java.io.*;
import java.util.*;

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
        addStartingPieces(Piece.Color.WHITE);
        addStartingPieces(Piece.Color.BLACK);
    }

    public void createBoard() {
        board = new Piece[8][8];
        fillBoardEmptyPieces();
    }

    public void fillBoardEmptyPieces() {
        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                piecesOnTheBoard.append('.');
                board[z][x] = null;
            }
            piecesOnTheBoard.append(StringUtil.NEWLINE);
        }
    }

    public void addStartingPieces(Piece.Color color) {
        int rank = (color == Piece.Color.WHITE) ? 0 : 7;
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
            addPieceToTheBoard(Pawn.create(Piece.Color.WHITE), position);
        } else {
            position.setRank(6);
            addPieceToTheBoard(Pawn.create(Piece.Color.BLACK), position);
        }
    }

    public void addPiece(String coordinate, Piece piece) {
        Position position = transformCoordenate(coordinate);
        addPieceToTheBoard(piece, position);
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

    public void movePiece(String coordenate, Piece piece) {
        Position position = transformCoordenate(coordenate);
        piece.setPosition(position.getColumn(), position.getRank());
        updateBoard();
    }

    private void updateBoard() {
        cleanBoard();
        int i=0;
        for (Piece piece : getPieces()) {
            board[piece.column][piece.rank] = piece;
            updatePrint();
        }
    }

    private void cleanBoard() {
        board = new Piece[8][8];
    }

    public void updatePrint() {
        for (int rank = 0; rank < 8; rank++) {
            for (int column = 0; column < 8; column++) {
                if(board[column][rank] != null) {
                    int linha = 7 - rank;
                    int positionPiece = (linha*9) + column;
                    piecesOnTheBoard.setCharAt(positionPiece, getPiece(column, rank).getRepresentation());
                }
            }
        }
    }

    public Position transformCoordenate(String coordenate) {
        int column = (switch (String.valueOf(coordenate.charAt(0))) {
            case "a" -> 0;
            case "b" -> 1;
            case "c" -> 2;
            case "d" -> 3;
            case "e" -> 4;
            case "f" -> 5;
            case "g" -> 6;
            case "h" -> 7;
            default -> 9;
        });

        String rank = String.valueOf(coordenate.charAt(1));
        return new Position((Integer.parseInt(rank)) - 1, column);
    }

    public boolean addPiece(Piece piece, char columnChar, int rank) {
        int aux = rank - 1;
        int column = transformColumnChar(columnChar);
        if (column == 9)
            return false;

        pieces.add(piece);
        board[column][aux] = piece;
        alterPosition(piece, column, rank);
        updateQuantityOfPieces();
        return true;
    }

    public void alterPosition(Piece piece, int column, int rank) {
        piece.setPosition(column, rank);
    }

    public void removePieceFromTheBoard(Piece piece) {
        ArrayList<Piece> auxArray = new ArrayList<>();
        for (Piece p : pieces)
            if (piece != p)
                auxArray.add(p);

        pieces = auxArray;

        for (int x = 0; x < 8; x++)
            for (int z = 0; z < 8; z++)
                if (piece == board[z][x])
                    board[z][x] = null;

        updateQuantityOfPieces();
    }

    public void updateQuantityOfPieces() {
        int countingWhitePieces = 0;
        int countingBlackPieces = 0;
        for (int x = 0; x < 8; x++)
            for (int z = 0; z < 8; z++) {
                Piece piece = board[z][x];
                if (piece != null && piece.isWhite())
                    countingWhitePieces++;
                if (piece != null && piece.isBlack())
                    countingBlackPieces++;
            }

        piecesWhite = countingWhitePieces;
        piecesBlack = countingBlackPieces;
    }

    public static int transformColumnChar(char column) {
        return switch (column) {
            case 'a' -> 0;
            case 'b' -> 1;
            case 'c' -> 2;
            case 'd' -> 3;
            case 'e' -> 4;
            case 'f' -> 5;
            case 'g' -> 6;
            case 'h' -> 7;
            default -> 9;
        };
    }

    public String print() {
        updatePrint();
        return piecesOnTheBoard.toString();
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Piece getPiece(int column, int rank) {
        return board[column][rank];
    }

    char getPieceRepresentation(char columnChar, int rank) {
        int column = transformColumnChar(columnChar);
        Piece piece = board[column][rank - 1];
        if (piece == null)
            return '.';
        return piece.getRepresentation();
    }

    int getPiecesWhite() {
        updateQuantityOfPieces();
        return piecesWhite;
    }

    int getPiecesBlack() {
        updateQuantityOfPieces();
        return piecesBlack;
    }

    int pieceCount() {
        return pieces.size();
    }

    String getRank(int rank) {
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

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public ArrayList<String> getPiecesRepresentation() {
        ArrayList<String> representationPieces = new ArrayList<>();
        for (Piece piece : pieces)
            representationPieces.add(piece.getType().toString());

        return representationPieces;
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