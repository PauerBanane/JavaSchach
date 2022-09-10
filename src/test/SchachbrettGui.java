package test;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SchachbrettGui {

    private JPanel gui;
    private final String COLS = "ABCDEFGH";

    public static void main(String[] args) {
        gui.SchachbrettGui gui = new gui.SchachbrettGui();

        JFrame frame = new JFrame();
        frame.setContentPane(gui.getGui());

        frame.pack();
        frame.setMinimumSize(frame.getSize());

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        /*
        SchachbrettGui schachbrettGui = new SchachbrettGui();
        schachbrettGui.init();

        JFrame frame = new JFrame();
        frame.setContentPane(schachbrettGui.gui);

        frame.pack();
        frame.setMinimumSize(frame.getSize());

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        */
    }

    public void init() {
        // Komponente hinzufügen
        gui = new JPanel();
        gui.setBorder(new EmptyBorder(25, 25, 25, 25));

        // Layout setzen
        GridLayout layout = new GridLayout(9, 9);
        gui.setLayout(layout);

        // Testbild
        BufferedImage image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);

        // Buttons erstellen
        boolean lightSquare = false;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JButton button = new JButton();
                ImageIcon icon = new ImageIcon(image);
                button.setIcon(icon);
                button.setBorder(new LineBorder(Color.BLACK));
                button.setPreferredSize(new Dimension(64, 64));
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                if (row != 8 && col != 0)
                    button.setBackground(lightSquare ? Color.GRAY.darker() : Color.GRAY.brighter());
                else
                    button.setBackground(Color.white);

                if (col == 0 && row < 8)
                    button.setText(String.valueOf(8 - row));

                if (row == 8 && col != 0) {
                    button.setText(String.valueOf(COLS.charAt(col - 1)));
                }

                lightSquare = !lightSquare;

                gui.add(button);
            }
        }
    }

}
