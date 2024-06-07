package chess;

import pieces.Piece;


public class Game {
    private final Board board;
    public double strengthWhite = 0;
    public double strengthBlack = 0;

    public Game(Board board) {
        this.board = board;
    }

    public void calculateStrength() {
        double auxWhiteStrength = 0;
        double auxBlackStrength = 0;

        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                Piece auxPiece = board.getPiece(z, x + 1);
                if (auxPiece != null) {
                    if (auxPiece.isWhite()) {
                        auxWhiteStrength += auxPiece.getPoints();
                    } else {
                        auxBlackStrength += auxPiece.getPoints();
                    }
                }
            }
        }

        strengthWhite = auxWhiteStrength;
        strengthBlack = auxBlackStrength;
    }

    public void alterPawnForceSameColumn() {
        Piece[][] boardArray = board.getBoard();

        for (int column = 0; column < 8; column++) {
            boolean sameColumnPawn = false;
            for (int row = 0; row < 8; row++) {
                Piece piece = boardArray[column][row];
                System.out.println(piece);
                if(piece != null) {
                    System.out.println(piece);
                    if (piece.getType() == Piece.Type.PAWN) {
                        for (int checkRow = 0; checkRow < 8; checkRow++) {
                            if (checkRow != row && boardArray[column][checkRow].getType() == Piece.Type.PAWN && boardArray[column][checkRow].getRepresentation() == piece.getRepresentation()) {
                                sameColumnPawn = true;
                                Piece auxPiece = boardArray[column][checkRow];
                                if (auxPiece.getPoints() == 1) {
                                    auxPiece.setPoints(0.5);
                                }
                            }
                        }
                    }
                }
            }
            if (sameColumnPawn) {
                for (int row = 0; row < 8; row++) {
                    Piece piece = boardArray[column][row];
                    if (piece.getPoints() == 1) {
                        piece.setPoints(0.5);
                    }
                }
            }
        }
    }

    double getStrengthBlackPiece(){
        alterPawnForceSameColumn();
        calculateStrength();
        return strengthBlack;
    }
    double getStrengthWhitePiece(){
        alterPawnForceSameColumn();
        calculateStrength();
        return strengthWhite;
    }
}
