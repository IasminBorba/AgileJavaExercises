package ui;

import chess.Board;
import chess.Position;
import pieces.Piece;
import util.TransformCoordenate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class GameRolls {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    private final GameUI panel = new GameUI();
    static final String GAME_TITLE = "ChessGame";
    private final JFrame frame = new JFrame(GAME_TITLE);
    private final Board board = new Board();

    public static void main(String[] args) {
        new GameRolls().show();
    }

    GameRolls() {
        initialize();
    }

    public void show() {
        frame.setVisible(true);
    }

    private void initialize() {
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);

        board.initialize();

        createListeners();
    }

    private void createListeners() {
        createButtonListeners();
    }

    private void createButtonListeners() {
        addImageButton();

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();

                String coordinate = clickedButton.getName();
                if (coordinate == null)
                    return;

                Position test = TransformCoordenate.aplly(coordinate);
                int test2 = coordinate.charAt(1) + '0';

                Piece piece = board.getPiece(test.getColumn(), test.getRank());

                if (piece != null)
                    setColorPossibleMovePiece(piece.getPossibleMoves());
            }
        };

        for (JButton button : panel.getAllBoardButtons())
            button.addActionListener(buttonListener);
    }

    private void addImageButton() {
        Piece[][] teste = board.getBoard();

        board.iterateBoard((rank, column) -> {
            Piece piece = teste[column][rank];
            if (piece != null && piece.getType() == Piece.Type.QUEEN) {
                JButton button = panel.getButton(TransformCoordenate.teste(piece.getPosition()));
                button.setIcon(getImage(piece));
            } else if (piece != null) {
                JButton button = panel.getButton(TransformCoordenate.teste(piece.getPosition()));
                button.setText(piece.getType().name());
            }
        });
    }

    public ImageIcon getImage(Piece piece) {
        String path = getPathImage(piece);

        Image img;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image scaledImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImg);
    }

    public String getPathImage(Piece piece) {
        //Switch TYpe:

        return "src/exercises/resources/queenWhite.png";
    }

    public void setColorPossibleMovePiece(ArrayList<String> array) {
        for (String cordinate : array) {
            JButton button = panel.getButton(cordinate);
            button.setBackground(Color.GREEN);
        }
    }
}
