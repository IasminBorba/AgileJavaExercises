package chess;

import pieces.Piece;

import static chess.Board.transformPosition;


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
                if (auxPiece.getType() != Piece.Type.NO_PIECE) {
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

    public boolean newKingPosition(Piece piece, char files, int rank) {
        boolean permission = false;
        int aux = rank - 1;
        int file = transformPosition(files);
        if (file == 9) {
            return false;
        }

        for (int x = 0; x < 8; x++) {
            for (int z = 0; z < 8; z++) {
                if (piece == board.getPiece(z, x + 1)) {
                    if ((file == 1 + z) && (aux == x)) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    } else if ((file == 1 + z) && aux == x - 1) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    } else if ((file == 1 + z) && aux == x + 1) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    } else if ((file == z - 1) && aux == x) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    } else if ((file == z - 1) && aux == x - 1) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    } else if ((file == z - 1) && aux == x + 1) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    } else if ((file == z) && (aux == x + 1)) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    } else if ((file == z) && (aux == x - 1)) {
                        board.removePiece(piece);
                        board.addPiece(piece, file, rank);
                        permission = true;
                    }
                }
            }
        }
        return permission;
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
