package pieces;

public class PawnPiece extends Piece {
    public static Type Class = Type.PAWN;

    protected PawnPiece(Color color, Type type) {
        super(color, type);
    }

    public static PawnPiece create(Color color) {
        return new PawnPiece(color, Class);
    }

    @Override
    public boolean getPossibleMoves(Piece piece, int files, int rank){
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