package pieces;

public class KingPiece extends Piece {
    public static Type Class = Type.KING;

    protected KingPiece(Color color, Type type) {
        super(color, type);
    }

    public static KingPiece create(Color color) {
        return new KingPiece(color, Class);
    }

    @Override
    public boolean getPossibleMoves(int files, int rank){
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
