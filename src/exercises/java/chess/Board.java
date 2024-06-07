package chess;

import pieces.*;
import util.StringUtil;
import java.util.ArrayList;
import java.util.Objects;

public class Board {
    int piecesWhite;
    int piecesBlack;
    private ArrayList<Piece> pieces = new ArrayList<>();
    public final StringBuilder piecesOnTheBoard = new StringBuilder();
    private Piece[][] board;

    public void initialize() {
        createBoard();
        addPiecesOfRank(Piece.Color.WHITE);
        addPiecesPawnOfRank(Piece.Color.WHITE);
        addPiecesPawnOfRank(Piece.Color.BLACK);
        addPiecesOfRank(Piece.Color.BLACK);
    }

    public void createBoard() {
        board = new Piece[8][8];
        addPiecesBlank();
    }

    private void addPiecesOfRank(Piece.Color color) {
        int rank;

        if (Objects.equals(color, Piece.Color.WHITE)) {
            rank = 1;
        } else {
            rank = 8;
        }

        Piece rook = RookPiece.create(color); addPiece(rook, 0, rank);
        Piece knight = KnightPiece.create(color); addPiece(knight, 1, rank);
        Piece bishop = BishopPiece.create(color); addPiece(bishop, 2, rank);
        Piece queen = QueenPiece.create(color); addPiece(queen, 3, rank);
        Piece king = KingPiece.create(color); addPiece(king, 4, rank);
        Piece bishop2 = BishopPiece.create(color); addPiece(bishop2, 5, rank);
        Piece knight2 = KnightPiece.create(color); addPiece(knight2, 6, rank);
        Piece rook2 = RookPiece.create(color); addPiece(rook2, 7, rank);
    }

    private void addPiecesPawnOfRank(Piece.Color color) {
        for (int z = 0; z < 8; z++) {
            if (Objects.equals(color, Piece.Color.WHITE)) {
                addPiece(PawnPiece.create(color),z,2);
            } else {
                addPiece(PawnPiece.create(color), z, 7);
            }
        }
    }

    public void addPiece(Piece piece, int file, int rank) {
        int aux = rank - 1;

        if (!pieces.isEmpty() && pieces.getFirst() == null){
            pieces.set(0, piece);
        } else {
            pieces.add(piece);

        }
        board[file][aux] = piece;
        alterPrint(piece, file, rank);
    }

    private void addPiecesBlank() {
        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                piecesOnTheBoard.append('.');
                board[z][x] = null;
            }
            piecesOnTheBoard.append(StringUtil.NEWLINE);
        }
    }

    public boolean addPiece(Piece piece, char files, int rank) {
        int aux = rank - 1;
        int file = transformPosition(files);
        if (file == 9){
            return false;
        }

        pieces.add(piece);
        board[file][aux] = piece;
        alterPrint(piece, file, rank);

        return true;
    }

    public void removePiece(Piece piece) {
        ArrayList<Piece> auxArray = new ArrayList<>();
        for (Piece p : pieces) {
            if (pieces.size() == 1) {
                auxArray.add(null);
            } else {
                if (piece != p) {
                    auxArray.add(p);
                }
            }
        }
        pieces = auxArray;

        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                if (piece == board[z][x]) {
                    board[z][x] = null;
                    alterPrint(null,z,x+1);
                }
            }
        }
    }

    public void alterPrint(Piece piece, int files, int rank){
        int lines = 8-rank;
        int positionPiece = (lines*8) + files + lines;

        if(piece == null){
            piecesOnTheBoard.setCharAt(positionPiece, '.');
        }
        piecesOnTheBoard.setCharAt(positionPiece, piece.getRepresentation());
    }

    public static int transformPosition(char file){
        return switch (file) {
            case 'a' -> 0;
            case 'b' -> 1;
            case 'c' -> 2;
            case 'd' -> 3;
            case 'e' -> 4;
            case 'f' -> 5;
            case 'g' -> 6;
            case 'h' -> 7;
            default ->  9;
        };
    }

    public String print(){
        return piecesOnTheBoard.toString();
    }

    int pieceCount(){
        return pieces.size();
    }

    String getRank(int rank) {
        StringBuilder rankPiece = new StringBuilder();
        for (int x = 0; x < 8; x++) {
            Piece auxPIece = board[x][rank-1];
            if(auxPIece == null){
                rankPiece.append('.');
            } else {
                rankPiece.append(auxPIece.getRepresentation());
            }
        }
        return rankPiece.toString();
    }

    int getPiecesWhite(){
        int aux = 0;
        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                Piece auxPIece = board[z][x];
                if (auxPIece != null && auxPIece.isWhite()){
                    aux++;
                }
            }
        }
        return piecesWhite = aux;
    }

    int getPiecesBlack(){
        int aux = 0;
        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                Piece auxPIece = board[z][x];
                if (auxPIece != null && auxPIece.isBlack()){
                    aux++;
                }
            }
        }
        return piecesBlack = aux;
    }

    char getPieceRepresentation(char files, int rank){
        int file = transformPosition(files);
        Piece piece = board[file][rank-1];
        if(piece == null){
            return '.';
        }
        return piece.getRepresentation();
    }

    public Piece getPiece(int file, int rank) {
        return board[file][rank - 1];
    }

    public Piece[][] getBoard() {
        return board;
    }
}