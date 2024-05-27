package chess;

import pieces.Piece;
import util.StringUtil;

import java.util.ArrayList;
import java.util.Objects;

public class Board {
    int piecesWhite;
    int piecesBlack;
    private final ArrayList<Piece> pieces = new ArrayList<>();
    private final ArrayList<ArrayList<Piece>> ranks = new ArrayList<>();
    public static final StringBuilder piecesOnTheBoard = new StringBuilder();

    public int index = 0;
    public Board(){
        initialize();
    }

    public void initialize() {
        this.piecesWhite = getPiecesWhite();
        this.piecesBlack = getPiecesBlack();

        addPiecesOfRank(new ArrayList<>(), Piece.Color.WHITE);
        addPiecesPawnOfRank(new ArrayList<>(), Piece.Color.WHITE);
        addPiecesBlank();
        addPiecesPawnOfRank(new ArrayList<>(), Piece.Color.BLACK);
        addPiecesOfRank(new ArrayList<>(), Piece.Color.BLACK);
    }

//    public void addPiecesToTheBoard(){
//        addPiecesOfRank(new ArrayList<>(), Piece.Color.WHITE);
//        addPiecesPawnOfRank(new ArrayList<>(), Piece.Color.WHITE);
//        addPiecesBlank();
//        addPiecesPawnOfRank(new ArrayList<>(), Piece.Color.BLACK);
//        addPiecesOfRank(new ArrayList<>(), Piece.Color.BLACK);
//    }

    public void addPiecesBlank() {
        for (int x = 0; x < 4; x++) {
            Piece blank = Piece.noPiece();
            piecesOnTheBoard.append(String.valueOf(blank.getRepresentation()).repeat(8));
            piecesOnTheBoard.append(StringUtil.NEWLINE);
            ranks.add(new ArrayList<>());
            index++;
        }
    }

    void addPiecesOnTheBoard(ArrayList<Piece> rank, Piece.Color color){
        for (Piece pieces: rank) {
            piecesOnTheBoard.append(pieces.getRepresentation());
            if (Objects.equals(color, Piece.Color.WHITE)){
                piecesWhite++;
            } else {
                piecesBlack++;
            }
        }
        piecesOnTheBoard.append(StringUtil.NEWLINE);
        index++;
    }

    private void addPiecesOfRank(ArrayList<Piece> aux, Piece.Color color) {
        Piece.Type[] types = {Piece.Type.ROOK, Piece.Type.KNIGHT, Piece.Type.BISHOP, Piece.Type.QUEEN, Piece.Type.KING, Piece.Type.BISHOP, Piece.Type.KNIGHT, Piece.Type.ROOK};
        for (Piece.Type type : types) {
            if (Objects.equals(color, Piece.Color.WHITE)) {
                aux.add(Piece.createWhitePiece(type));
                pieces.add(Piece.createWhitePiece(type));
            } else if (Objects.equals(color, Piece.Color.BLACK)) {
                aux.add(Piece.createBlackPiece(type));
                pieces.add(Piece.createBlackPiece(type));
            }
        }
        ranks.add(aux);
        addPiecesOnTheBoard(ranks.get(index), color);
    }

    private void addPiecesPawnOfRank(ArrayList<Piece> aux, Piece.Color color) {
        if(Objects.equals(color, Piece.Color.WHITE)){
            for (int z = 0; z < 8; z++) {
                aux.add(Piece.createWhitePiece(Piece.Type.PAWN));
                pieces.add(Piece.createWhitePiece(Piece.Type.PAWN));
            }
        } else {
            for (int z = 0; z < 8; z++) {
                aux.add(Piece.createBlackPiece(Piece.Type.PAWN));
                pieces.add(Piece.createBlackPiece(Piece.Type.PAWN));
            }
        }
        ranks.add(aux);
        addPiecesOnTheBoard(ranks.get(index), color);
    }
    public String print(){
        return piecesOnTheBoard.toString();
    }

    int pieceCount(){
        return pieces.size();
    }

    String getRank(int rank) {
        ArrayList<Piece> boardRank = ranks.get(rank - 1);
        StringBuilder rankPiece = new StringBuilder();
        for (Piece piece : boardRank) {
            rankPiece.append(piece != null ? piece.getRepresentation() : ".");
        }
        return rankPiece.toString();
    }
    int getPiecesWhite(){
        return piecesWhite;
    }

    int getPiecesBlack(){
        return piecesBlack;
    }

    char getPiece(char files, int rank){
        int num = 0;
        switch (files) {
            case 'a' -> num = 0;
            case 'b' -> num = 1;
            case 'c' -> num = 2;
            case 'd' -> num = 3;
            case 'e' -> num = 4;
            case 'f' -> num = 5;
            case 'g' -> num = 6;
            case 'h' -> num = 7;
        }
        ArrayList<Piece> boardRank = ranks.get(rank-1);
        System.out.println(boardRank.get(num).getRepresentation());
        return boardRank.get(num).getRepresentation();
    }
}
