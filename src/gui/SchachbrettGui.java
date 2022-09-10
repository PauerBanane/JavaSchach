package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Die Klasse SchachbrettGui verwaltet die grafische Oberfläche des Spiels.
 * @author Mike Nipkow
 */
public class SchachbrettGui {

    private final Dimension DIMENSION = new Dimension(64, 64);
    private final String SPALTEN_BESCHRIFTUNG = "ABCDEFGH";
    private JPanel gui = new JPanel();

    /**
     * Konstruktor für das Schachbrett-GUI.
     */
    public SchachbrettGui() {
        init();
    }

    /**
     * Methode zum Initialisieren der grafischen Oberfläche.
     */
    private void init() {
        // Rand um das eigentliche Schachbrett für bessere Optik.
        int randBreite = 25;
        EmptyBorder rand = new EmptyBorder(64, randBreite, randBreite, 64);
        gui.setBorder(rand);

        // Layout für das eigentliche Schachbrett.
        GridLayout layout = new GridLayout(9, 9);   // 9x9 um Koordinaten an den Seiten anzuzeigen.
        gui.setLayout(layout);

        // Einzelne Felder in dem Layout einfügen.
        // Variable um festzulegen, ob ein helles oder dunkles Schachfeld erzeugt werden soll.
        boolean hellesFeld = false;
        for (int reihe = 0; reihe < 9; reihe++) {
            for (int spalte = 0; spalte < 9; spalte++) {
                // Variable für Komponente erstellen.
                JComponent komponente = null;

                // Beschriftungsfläche für erste Spalte und letzte Reihe.
                if (reihe == 8 || spalte == 0)
                    komponente = getInfoFläche(reihe, spalte);
                else
                    komponente = getSchachfeldSchaltfläche(reihe, spalte, hellesFeld);

                // Komponente zum GUI hinzufügen.
                gui.add(komponente);

                // Im nächsten Durchgang die Hintergrundfarbe ändern.
                hellesFeld = !hellesFeld;
            }
        }
    }

    /**
     * Schaltfläche für ein Schachfeld erstellen.
     * @param reihe         Reihe in dem Layout.
     * @param spalte        Spalte in dem Layout.
     * @param hellesFeld    Helles oder dunkles Schachfeld.
     * @return              Schaltfläche für ein Schachfeld.
     */
    private JButton getSchachfeldSchaltfläche(int reihe, int spalte, boolean hellesFeld) {
        // Schaltfläche erstellen.
        JButton schaltfläche = new JButton();
        schaltfläche.setBorder(new LineBorder(Color.BLACK));            // Rahmen um das Feld einblenden.
        schaltfläche.setPreferredSize(DIMENSION);                       // Größe der Schaltfläche.
        schaltfläche.setBackground(hellesFeld ?                         // Farbe des Feldes festlegen.
                             Color.GRAY.brighter() :
                             Color.GRAY.darker());
        schaltfläche.setOpaque(true);                                   // Hintergrundfarbe immer anzeigen.

        // TODO: Schachfigur auf dem Feld einlesen und Bild einblenden.

        return schaltfläche;
    }

    /**
     * Fläche für eine Beschriftung erstellen.
     * @param reihe     Die Reihe im Layout.
     * @param spalte    Die Spalte im Layout.
     * @return          Die Fläche, die angezeigt werden soll.
     */
    private JLabel getInfoFläche(int reihe, int spalte) {
        // Fläche erstellen.
        JLabel fläche = new JLabel();
        fläche.setPreferredSize(DIMENSION);                             // Größe der Schaltfläche.
        fläche.setHorizontalAlignment(SwingConstants.CENTER);           // Text in der Mitte anordnen.
        fläche.setBackground(Color.WHITE);                              // Hintergrundfarbe weiß.

        // Variable für den anzuzeigenden Text erstellen.
        String text = null;

        // A-H zuweisen, wenn es in der ersten Spalte ist und nicht in der letzten Reihe.
        if (spalte == 0 && reihe != 8)
            text = String.valueOf(8 - reihe);

        // 1-8 zuweisen, wenn es in der letzten Reihe ist und nicht in der ersten Spalte.
        else if (reihe == 8 && spalte != 0)
            text = String.valueOf(SPALTEN_BESCHRIFTUNG.charAt(spalte - 1));

        // Text in das Feld schreiben.
        fläche.setText(text);

        return fläche;
    }

    /**
     * Die Methode konvertiert die Spalte des Layouts zu der Spalte des Schachbrett-Arrays.
     * Beispiel:    Layout Spalte 0 (links) stellt in der Oberfläche nur ein Hinweisfeld dar und kein Schachfeld.
     *              Daher wird von der Layout-Spalte die Zahl '1' subtrahiert, um die Schachfeld-Spalte
     *              zu erhalten.
     * @param layoutSpalte  Die Spalte im Layout.
     * @return              Die Spalte im Schachbrett-Array.
     */
    private int layoutSpalteZuSchachbrettSpalte(int layoutSpalte) {
        return layoutSpalte - 1;
    }

    /**
     * Gibt das GUI zurück.
     * @return Das erstellte GUI.
     */
    public JPanel getGui() {
        return gui;
    }
}
