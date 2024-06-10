package pieces;

public class BishopPiece extends Piece {
    public static Type Class = Type.BISHOP;

    protected BishopPiece(Color color, Type type) {
        super(color, type);
    }

    public static BishopPiece create(Color color) {
        return new BishopPiece(color, Class);
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
