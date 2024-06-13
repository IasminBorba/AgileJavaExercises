package chess;

import pieces.*;
import util.StringUtil;
import java.util.ArrayList;
import java.util.Objects;

public class Board {
    int piecesWhite;
    int piecesBlack;
    private ArrayList<Piece> pieces = new ArrayList<>();
    public  StringBuilder piecesOnTheBoard = new StringBuilder();
    public Piece[][] board;

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

    public void addPiecesOfRank(Piece.Color color) {
        int rank;

        if (Objects.equals(color, Piece.Color.WHITE)) {
            rank = 0;
        } else {
            rank = 7;
        }

        Piece rook = Rook.create(color, this); addPiece(rook, 0, rank);
        Piece knight = Knight.create(color, this); addPiece(knight, 1, rank);
        Piece bishop = Bishop.create(color, this); addPiece(bishop, 2, rank);
        Piece queen = Queen.create(color, this); addPiece(queen, 3, rank);
        Piece king = King.create(color, this); addPiece(king, 4, rank);
        Piece bishop2 = Bishop.create(color, this); addPiece(bishop2, 5, rank);
        Piece knight2 = Knight.create(color, this); addPiece(knight2, 6, rank);
        Piece rook2 = Rook.create(color, this); addPiece(rook2, 7, rank);
    }

    public void addPiecesPawnOfRank(Piece.Color color) {
        for (int z = 0; z < 8; z++) {
            if (Objects.equals(color, Piece.Color.WHITE)) {
                addPiece(Pawn.create(color, this),z,1);
            } else {
                addPiece(Pawn.create(color, this), z, 6);
            }
        }
    }

    public void put(String str, Piece piece){
        int column = transformPosition2(str).getFirst();
        int rank = transformPosition2(str).getLast();

        addPiece(piece, column, rank);
    }

    public ArrayList<Integer> transformPosition2(String str){
        ArrayList<Integer> position = new ArrayList<>();
        int column;
        int rank;
        column = switch (str.charAt(0)) {
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
        position.add(column);

        rank = switch (str.charAt(1)) {
            case '1' -> 0;
            case '2' -> 1;
            case '3' -> 2;
            case '4' -> 3;
            case '5' -> 4;
            case '6' -> 5;
            case '7' -> 6;
            case '8' -> 7;
            default ->  9;
        };
        position.add(rank);

        return  position;
    }

    public String transformPositionString(int columnInt, int rankInt){
        StringBuilder str = new StringBuilder();

        String columnChar = switch (columnInt) {
            case 0 -> "a";
            case 1 -> "b";
            case 2 -> "c";
            case 3 -> "d";
            case 4 -> "e";
            case 5 -> "f";
            case 6 -> "g";
            case 7 -> "h";
            default -> "error";
        };
        str.append(columnChar);

        String rankChar = switch (rankInt) {
            case 0 -> "1";
            case 1 -> "2";
            case 2 -> "3";
            case 3 -> "4";
            case 4 -> "5";
            case 5 -> "6";
            case 6 -> "7";
            case 7 -> "8";
            default -> "error";
        };
        str.append(rankChar);

        return  str.toString();
    }

    public void addPiece(Piece piece, int file, int rank) {
        if (!pieces.isEmpty() && pieces.getFirst() == null){
            pieces.set(0, piece);
        } else {
            pieces.add(piece);

        }
        board[file][rank] = piece;
        alterPrint(piece, file, rank+1);
    }

    public void movePiece(String position, Piece piece){
        int column = transformPosition2(position).getFirst();
        int rank = transformPosition2(position).getLast();

        removePiece(piece);
        addPiece(piece, column, rank);
    }

    public void addPiecesBlank() {
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
        } else {
            piecesOnTheBoard.setCharAt(positionPiece, piece.getRepresentation());
        }
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
        return board[file][rank];
    }

    public Piece[][] getBoard() {
        return board;
    }
}