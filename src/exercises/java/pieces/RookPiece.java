package pieces;

public class RookPiece extends Piece {
    public static Type Class = Type.ROOK;

    protected RookPiece(Color color, Type type) {
        super(color, type);
    }

    public static RookPiece create(Color color) {
        return new RookPiece(color, Class);
    }

    @Override
    public boolean getPossibleMoves(char files, int rank){
        boolean permission = false;
        if (rank > 8){
            return false;
        }
//        int file = Board.transformPosition(files);
//        if (file == 9) {
//            return false;
//        }
//
//        permission = switch (piece.getType()){
//            case KING -> Game.newKingPosition(piece, file, rank);
//            case PAWN -> false;
//            case KNIGHT -> false;
//            case ROOK -> false;
//            case BISHOP -> false;
//            case QUEEN -> Game.newQueenPosition(piece, file, rank);
//            case NO_PIECE -> false;
//        };


        return permission;
    }
}