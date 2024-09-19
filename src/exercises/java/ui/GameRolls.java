package ui;

import chess.*;
import pieces.Piece;
import util.CoordinateTransformer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class GameRolls {
    private final GameUI panel = new GameUI();
    private final JFrame frame = new JFrame("ChessGame");
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
        frame.setSize(800, 800);
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

                Position test = CoordinateTransformer.stringToPosition(coordinate);
                int test2 = coordinate.charAt(1) + '0';

                Piece piece = board.getPiece(test.getFile(), test.getRow());

                if (piece != null)
                    setColorPossibleMovePiece(piece.getPossibleMoves());
            }
        };

        for (JButton button : panel.getAllBoardButtons())
            button.addActionListener(buttonListener);
    }

    private void addImageButton() {
        Piece[][] teste = board.getBoardCells();

        board.iterateBoard((rank, column) -> {
            Piece piece = teste[column][rank];
            if (piece != null && piece.getType() == Piece.Type.QUEEN) {
                JButton button = panel.getButton(CoordinateTransformer.positionToString(piece.getPosition()));
                button.setIcon(getImage(piece));
            } else if (piece != null) {
                JButton button = panel.getButton(CoordinateTransformer.positionToString(piece.getPosition()));
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
