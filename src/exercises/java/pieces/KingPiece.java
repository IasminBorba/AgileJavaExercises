package pieces;

public class KingPiece extends Piece {

//    public enum Class { KING}
    public static KingPiece create(Color color) {
        Type type = Type.KING;
        return new KingPiece(color, type);
    }

    protected KingPiece(Color color, Type type) {
        super(color, type);
    }
}
