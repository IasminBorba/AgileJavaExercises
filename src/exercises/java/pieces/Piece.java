package pieces;

import java.util.Objects;

public class Piece {
    enum Color {WHITE, BLACK}

    enum Type {PAWN, KNIGHT, ROOK, BISHOP, QUEEN, KING, NO_PIECE}
    public char representation;
    private Color color;
    private Type type;
    public static char PAWN_REPRESENTATION;
    public static char ROOK_REPRESENTATION;
    public static char KNIGHT_REPRESENTATION;
    public static char BISHOP_REPRESENTATION;
    public static char QUEEN_REPRESENTATION;
    public static char KING_REPRESENTATION;

    private Piece(Color color, Type type){
        this.color = color;
        this.type = type;
    }

    private Piece(){
        this.representation = '.';
        this.type = Type.NO_PIECE;
    }

    public static Piece createWhitePawn(){
        return new Piece(Color.WHITE, Type.PAWN);
    }
    public static Piece createBlackPawn(){
        return new Piece(Color.BLACK, Type.PAWN);
    }

    public static Piece createWhiteRook(){
        return new Piece(Color.WHITE, Type.ROOK);
    }
    public static Piece createBlackRook(){
        return new Piece(Color.BLACK, Type.ROOK);
    }

    public static Piece createWhiteKnight(){
        return new Piece(Color.WHITE, Type.KNIGHT);
    }
    public static Piece createBlackKnight(){
        return new Piece(Color.BLACK, Type.KNIGHT);
    }

    public static Piece createWhiteBishop(){
        return new Piece(Color.WHITE, Type.BISHOP);
    }
    public static Piece createBlackBishop(){
        return new Piece(Color.BLACK, Type.BISHOP);
    }

    public static Piece createWhiteQueen(){
        return new Piece(Color.WHITE, Type.QUEEN);
    }
    public static Piece createBlackQuenn(){
        return new Piece(Color.BLACK, Type.QUEEN);
    }

    public static Piece createWhiteKing(){
        return new Piece(Color.WHITE, Type.KING);
    }
    public static Piece createBlackKing(){
        return new Piece(Color.BLACK, Type.KING);
    }



    public static Piece noPiece(){
        return new Piece();
    }
    public boolean isBlack(){
        return Objects.equals(color.name().toLowerCase(), "black");
    }

    public boolean isWhite(){
        return Objects.equals(color.name().toLowerCase(), "white");
    }

    public Type getType(){
        return  type;
    }

//    public char getRepresentation(){
//        return  representation;
//    }
}
