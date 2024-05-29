package chess;

import pieces.Piece;
import util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Board {
    int piecesWhite;
    int piecesBlack;
    private final ArrayList<Piece> pieces = new ArrayList<>();
    private final ArrayList<ArrayList<Piece>> ranks = new ArrayList<>();
//    public HashMap<int, int> pawnsWhiteInColumns = new HashMap<int, int>();
//    public HashMap<int, int> pawnsBlackInColumns = new HashMap<int, int>();
    public static final StringBuilder piecesOnTheBoard = new StringBuilder();
    public int index = 0;
    float strengthWhite = 0;
    float strengthBlack = 0;

    public void initialize() {
        this.piecesWhite = getPiecesWhite();
        this.piecesBlack = getPiecesBlack();

        addPiecesOfRank(new ArrayList<>(), Piece.Color.WHITE);
        addPiecesPawnOfRank(new ArrayList<>(), Piece.Color.WHITE);
        addPiecesBlank(4);
        addPiecesPawnOfRank(new ArrayList<>(), Piece.Color.BLACK);
        addPiecesOfRank(new ArrayList<>(), Piece.Color.BLACK);
    }

    public void createBoard() {
        addPiecesBlank(8);
    }

    private void addPiecesBlank(int rank) {
        Piece blank = Piece.noPiece();
        for (int x = 0; x < rank; x++) {
            ArrayList<Piece> aux = new ArrayList<>();
            for (int z = 0; z < 8; z++) {
                aux.add(blank);
                piecesOnTheBoard.append(blank.getRepresentation());
            }
            piecesOnTheBoard.append(StringUtil.NEWLINE);
            index++;
            ranks.add(aux);
        }
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
        if (Objects.equals(color, Piece.Color.WHITE)) {
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

    public void addPiece(Piece piece, char files, int rank) {
        int file = transformPosition(files);
        int aux = rank - 1;
        ArrayList<Piece> boardRank = ranks.get(aux);
        boardRank.set(file, piece);

        pieces.add(piece);

        alterPrint(piece, file, rank);
//        teste(piece, files, rank);
        evoluationPieces(piece);
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
        }
    }

    void calculationPawnPoints() {
        var whitePawnsPositions = new ArrayList<>();
        var blackPawnsPositions = new ArrayList<>();

        for (int rank = 0; rank < 8; rank++) {
            ArrayList<Piece> boardRank = ranks.get(rank);
            for (int file = 0; file < boardRank.size(); file++) {
                Piece piece = boardRank.get(file);
                if (piece.getType() == Piece.Type.PAWN) {
                    if (piece.isWhite()) {
                        whitePawnsPositions.add(file);
                    } else {
                        blackPawnsPositions.add(file);
                    }
                }
            }
        }

        int duplicateWhite = 0;
        for (int i = 0; i < whitePawnsPositions.size(); i++){
            for (int j = i+1; j < whitePawnsPositions.size(); j++) {
                if (whitePawnsPositions.get(i) == whitePawnsPositions.get(j)){
                    duplicateWhite++;
                }
            }
        }
        if (duplicateWhite != 0){
            strengthWhite += (duplicateWhite*0.5F) + 0.5F;
            for (int x = duplicateWhite+1; x < whitePawnsPositions.size(); x++){
                strengthWhite += 1;
            }
        } else {
            strengthWhite += whitePawnsPositions.size();
        }

        int duplicateBlack = 0;
        for (int i = 0; i < blackPawnsPositions.size(); i++){
            for (int j = i+1; j < blackPawnsPositions.size(); j++) {
                if (blackPawnsPositions.get(i) == blackPawnsPositions.get(j)){
                    duplicateBlack++;
                }
            }
        }

        if (duplicateBlack != 0){
            strengthBlack += (duplicateBlack*0.5F) + 0.5F;
            for (int x = duplicateBlack+1; x < blackPawnsPositions.size(); x++){
                strengthBlack += 1;
            }
        } else {
            strengthBlack += blackPawnsPositions.size();
        }
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
            rankPiece.append(piece.getRepresentation());
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
        int file = transformPosition(files);
        ArrayList<Piece> boardRank = ranks.get(rank-1);
        return boardRank.get(file).getRepresentation();
    }

    int transformPosition(char file){
        int num = 0;
        switch (file) {
            case 'a' -> num = 0;
            case 'b' -> num = 1;
            case 'c' -> num = 2;
            case 'd' -> num = 3;
            case 'e' -> num = 4;
            case 'f' -> num = 5;
            case 'g' -> num = 6;
            case 'h' -> num = 7;
        }
        return num;
    }

    public void printRanks(){
        StringBuilder stringRanks = new StringBuilder();

        for (int x = 0; x < 8; x++){
            ArrayList<Piece> list = ranks.get(x);
            for (Piece piece: list){
                stringRanks.append(piece.getRepresentation());
            }
            stringRanks.append("\n");
        }

        System.out.println(stringRanks);
    }

    void calculateFinal() {
        for (int rank = 0; rank < 8; rank++) {
            ArrayList<Piece> boardRank = ranks.get(rank);
            for (int file = 0; file < boardRank.size(); file++) {
                Piece piece = boardRank.get(file);
                switch (piece.getType()) {
                    case QUEEN -> {
                        if (piece.isWhite()) {
                            strengthWhite += Piece.Type.QUEEN.strengthPiece;
                        } else {
                            strengthBlack += Piece.Type.QUEEN.strengthPiece;
                        }
                    }
                    case ROOK -> {
                        if (piece.isWhite()) {
                            strengthWhite += Piece.Type.ROOK.strengthPiece;
                        } else {
                            strengthBlack += Piece.Type.ROOK.strengthPiece;
                        }
                    }
                    case BISHOP -> {
                        if (piece.isWhite()) {
                            strengthWhite += Piece.Type.BISHOP.strengthPiece;
                        } else {
                            strengthBlack += Piece.Type.BISHOP.strengthPiece;
                        }
                    }
                    case KNIGHT -> {
                        if (piece.isWhite()) {
                            strengthWhite += Piece.Type.KNIGHT.strengthPiece;
                        } else {
                            strengthBlack += Piece.Type.KNIGHT.strengthPiece;
                        }
                    }
//                    case PAWN -> {
//                        if (piece.isWhite()) {
//                            whitePawnsPositions.add(file);
//                        } else {
//                            blackPawnsPositions.add(file);
//                        }
//                    }
                }
            }

        }
        System.out.println(strengthWhite);
        System.out.println(strengthBlack);
    }

//    void testeCalculatePawn(Piece pieceTo, int indexFile) {
//        if (pieceTo.getType() == Piece.Type.PAWN) {
//            for (int rank = 0; rank < 8; rank++) {
//                ArrayList<Piece> boardRank = ranks.get(rank);
//                Piece piece = boardRank.get(indexFile);
//                if (piece.getRepresentation() == pieceTo.getRepresentation()) {
//                    if (piece != pieceTo) {
//                        if (pieceTo.isWhite()) {
//                            pawnsWhiteInColumns.put(indexFile, rank);
//                            strengthWhite += 0.5F;
//                        } else {
//                            strengthBlack += 0.5F;
//                        }
//                    } else {
//                        if (pieceTo.isWhite()) {
//                            pawnsWhiteInColumns.put(indexFile, rank);
//                            strengthWhite += 1;
//                        } else {
//                            pawnsBlackInColumns.put(indexFile, rank);
//                            strengthBlack += 1;
//                        }
//                    }
//                }
//            }
//        }
//    }
}

