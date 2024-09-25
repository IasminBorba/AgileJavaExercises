package chess;

import ui.ChessGameUIController;
import ui.GameUI;

public class ChessGameApp {
    public static void main(String[] args) {
        GameUI gameUI = new GameUI();

        Board chessGame = new Board();
        chessGame.initialize();

        ChessGameUIController controller = new ChessGameUIController(gameUI, chessGame);

        gameUI.display();
    }
}
