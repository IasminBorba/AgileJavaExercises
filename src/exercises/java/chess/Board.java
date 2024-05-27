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
        addPiecesToTheBoard();
        this.piecesWhite = getPiecesWhite();
        this.piecesBlack = getPiecesBlack();
    }
    public void addPiecesToTheBoard(){
        addPieces(Piece.Color.BLACK);
        addPiecesBlank();
        addPieces(Piece.Color.WHITE);
    }
    public void addPiecesBlank() {
        for (int x = 0; x < 4; x++) {
            Piece blank = Piece.noPiece();
            piecesOnTheBoard.append(String.valueOf(blank.getRepresentation()).repeat(8));
            piecesOnTheBoard.append(StringUtil.NEWLINE);
            ranks.add(new ArrayList<>());
            index++;
        }
    }

    public void addPieces(Piece.Color color){
        if (color == Piece.Color.BLACK){
            ranks.add(addPiecesOfRank(new ArrayList<>(), color));
            addPiecesOfRank(pieces, color);
            ArrayList<Piece> rankOthers = ranks.get(index);
            for (Piece pieces: rankOthers) {
                piecesOnTheBoard.append(pieces.getRepresentation());
                if (Objects.equals(color, Piece.Color.WHITE)){
                    piecesWhite++;
                } else {
                    piecesBlack++;
                }
            }
            piecesOnTheBoard.append(StringUtil.NEWLINE);
            index++;

            ranks.add(addPiecesPawnOfRank(new ArrayList<>(), color));
            addPiecesOfRank(pieces, color);
            ArrayList<Piece> rankPawns = ranks.get(index);
            for (Piece pieces: rankPawns) {
                piecesOnTheBoard.append(pieces.getRepresentation());
                if (Objects.equals(color, Piece.Color.WHITE)){
                    piecesWhite++;
                } else {
                    piecesBlack++;
                }
            }
            piecesOnTheBoard.append(StringUtil.NEWLINE);
            index++;
        } else {
            ranks.add(addPiecesPawnOfRank(new ArrayList<>(), color));
            addPiecesOfRank(pieces, color);
            ArrayList<Piece> rankPawns = ranks.get(index);
            for (Piece pieces: rankPawns) {
                piecesOnTheBoard.append(pieces.getRepresentation());
                if (Objects.equals(color, Piece.Color.WHITE)){
                    piecesWhite++;
                } else {
                    piecesBlack++;
                }
            }
            piecesOnTheBoard.append(StringUtil.NEWLINE);
            index++;

            ranks.add(addPiecesOfRank(new ArrayList<>(), color));
            addPiecesOfRank(pieces, color);
            ArrayList<Piece> rankOthers = ranks.get(index);
            for (Piece pieces: rankOthers) {
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
    }

    private ArrayList<Piece> addPiecesOfRank(ArrayList<Piece> aux, Piece.Color color) {
        if(Objects.equals(color, Piece.Color.WHITE)){
            aux.add(Piece.createWhitePiece(Piece.Type.ROOK));
            aux.add(Piece.createWhitePiece(Piece.Type.KNIGHT));
            aux.add(Piece.createWhitePiece(Piece.Type.BISHOP));
            aux.add(Piece.createWhitePiece(Piece.Type.QUEEN));
            aux.add(Piece.createWhitePiece(Piece.Type.KING));
            aux.add(Piece.createWhitePiece(Piece.Type.BISHOP));
            aux.add(Piece.createWhitePiece(Piece.Type.KNIGHT));
            aux.add(Piece.createWhitePiece(Piece.Type.ROOK));
        } else {
            aux.add(Piece.createBlackPiece(Piece.Type.ROOK));
            aux.add(Piece.createBlackPiece(Piece.Type.KNIGHT));
            aux.add(Piece.createBlackPiece(Piece.Type.BISHOP));
            aux.add(Piece.createBlackPiece(Piece.Type.QUEEN));
            aux.add(Piece.createBlackPiece(Piece.Type.KING));
            aux.add(Piece.createBlackPiece(Piece.Type.BISHOP));
            aux.add(Piece.createBlackPiece(Piece.Type.KNIGHT));
            aux.add(Piece.createBlackPiece(Piece.Type.ROOK));
        }
        return aux;
    }

    private ArrayList<Piece> addPiecesPawnOfRank(ArrayList<Piece> aux, Piece.Color color) {
        if(Objects.equals(color, Piece.Color.WHITE)){
            for (int z = 0; z < 8; z++) {
                aux.add(Piece.createWhitePiece(Piece.Type.PAWN));
            }
        } else {
            for (int z = 0; z < 8; z++) {
                aux.add(Piece.createBlackPiece(Piece.Type.PAWN));
            }
        }
        return aux;
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
}
