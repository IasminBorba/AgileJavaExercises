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

    float strengthWhite = 0;
    float strengthBlack = 0;

    public void initialize() {
        this.piecesWhite = getPiecesWhite();
        this.piecesBlack = getPiecesBlack();

        addPiecesOfRank(new ArrayList<>(), Piece.Color.WHITE);
        addPiecesPawnOfRank(new ArrayList<>(), Piece.Color.WHITE);
        addPiecesBlank(new ArrayList<>(), 4);
        addPiecesPawnOfRank(new ArrayList<>(), Piece.Color.BLACK);
        addPiecesOfRank(new ArrayList<>(), Piece.Color.BLACK);
    }

    public void createBoard() {
        addPiecesBlank(new ArrayList<>(), 8);
    }

    private void addPiecesBlank(ArrayList<Piece> aux, int rank) {
        Piece blank = Piece.noPiece();
        for (int x = 0; x < rank; x++){
            for (int z = 0; z < 8; z++) {
                aux.add(blank);
                piecesOnTheBoard.append(blank.getRepresentation());
            }
            piecesOnTheBoard.append(StringUtil.NEWLINE);
            index++;
            ranks.add(aux);
        }
    }

    public void addPiece(Piece piece, char files, int rank){
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
        boardRank.set(num, piece);
        pieces.add(piece);

        alterPrint(piece, num, rank);
        evoluationPieces(piece);
    }

    public void alterPrint(Piece piece, int files, int rank){
        int lines = 8-rank;
        int positionPiece = (lines*8) + files + lines;

        piecesOnTheBoard.setCharAt(positionPiece, piece.getRepresentation());
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
    void evoluationPieces(Piece piece){
        switch (piece.getType()) {
            case Piece.Type.QUEEN -> {
                if (piece.isWhite()){
                    strengthWhite += 9;
                } else {
                    strengthBlack += 9;
                }
            }
            case Piece.Type.ROOK -> {
                if (piece.isWhite()){
                    strengthWhite += 5;
                } else {
                    strengthBlack += 5;
                }
            }
            case BISHOP -> {
                if (piece.isWhite()){
                    strengthWhite += 3;
                } else {
                    strengthBlack += 3;
                }
            }
            case KNIGHT -> {
                if (piece.isWhite()){
                    strengthWhite += 2.5F;
                } else {
                    strengthBlack += 2.5F;
                }
            }
//            case PAWN -> {
//                calculationPawnPoints();
//            }
        }
    }

//    void calculationPawnPoints() {
//        ArrayList<int> whitePawnsPositions = new ArrayList<int>();
//        ArrayList<int> blackPawnsPositions = new ArrayList<int>();
//
//        for (int rank = 0; rank < ranks.size(); rank++) {
//            ArrayList<Piece> boardRank = ranks.get(rank);
//            for (int file = 0; file < boardRank.size(); file++) {
//                Piece piece = boardRank.get(file);
//                if (piece.getType() == Piece.Type.PAWN) {
//                    if (piece.isWhite()) {
//                        whitePawnsPositions.add(file);
//                    } else {
//                        blackPawnsPositions.add(file);
//                    }
//                }
//            }
//        }
//    }

    float getEvoluationBlackPieces(){
        return strengthBlack;
    }
    float getEvoluationWhitePieces(){
        return strengthWhite;
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
        return boardRank.get(num).getRepresentation();
    }
}
