package brett;

import figuren.Figur;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Diese Klasse repräsentiert ein Schachfeld und dessen Eigenschaften.
 * @author Mike Nipkow
 */
public class Feld extends JButton {

    private final int y;            // Y-Position auf dem Schachbrett.
    private final int x;            // X-Position auf dem Schachbrett.
    private Figur figur;            // Figur, die das Feld besetzt.
    private Color standardFarbe;    // Standardfarbe des Felds, wenn es nicht ausgewählt ist.
    private boolean markiert;       // Boolean der angibt, ob das Feld aktuell markiert ist.

    /**
     * Konstruktor für das Schachfeld.
     * @param y     Y-Position des Felds.
     * @param x     X-Position des Felds.
     * @param figur Figur, die auf dem Feld steht.
     */
    public Feld(int y, int x, Figur figur) {
        this.y = y;
        this.x = x;
        this.figur = figur;
    }

    /**
     * Konstruktor für das Schachfeld.
     * @param y Y-Position des Felds.
     * @param x X-Position des Felds.
     */
    public Feld(int y, int x) {
        this(y, x, null);
    }

    /**
     * Methode um abzufragen, ob auf dem Feld eine Figur steht.
     * @return  Auswertung, ob das Feld besetzt ist.
     */
    public boolean istBesetzt() {
        return this.figur != null;
    }

    /**
     * Methode um die Figur von dem Feld zu entfernen, die aktuell darauf steht.
     * @return  Die Figur, die bisher auf dem Feld stand.
     */
    public Figur figurEntfernen() {
        Figur aktuelleFigur = this.figur;
        setIcon(null);
        this.figur = null;

        return aktuelleFigur;
    }

    /**
     * Methode um die Figur zu bekommen, die auf dem Feld steht.
     * @return  Die Figur, die das Feld besetzt.
     */
    public Figur getFigur() {
        return figur;
    }

    /**
     * Setzt die Figur, die auf diesem Feld steht.
     * @param figur Figur, die auf das Feld gesetzt werden soll.
     */
    public void setFigur(Figur figur) {
        this.figur = figur;
        if (figur != null) {
            BufferedImage bild = figur.getBild();
            setIcon(new ImageIcon(bild));
        }
    }

    /**
     * Setzt die Standardhintergrundfarbe des Felds, wenn es nicht ausgewählt ist.
     * @param standardFarbe Gewünschte Farbe.
     */
    public void setStandardFarbe(Color standardFarbe) {
        this.standardFarbe = standardFarbe;
        setBackground(standardFarbe);
    }

    /**
     * Methode zum Setzen der Standard-Hintergrundfarbe.
     */
    public void resetHintergrundfarbe() {
        setBackground(standardFarbe);
        markiert = false;
    }

    /**
     * Diese Methode setzt die Hintergrundfarbe des Feld und markiert diese Änderung in dem Objekt.
     * @param bg the desired background <code>Color</code>
     */
    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        markiert = true;
    }

    /**
     * Diese Methode gibt die Y-Position des Feldes auf dem Schachbrett wieder.
     * @return  Y-Position auf dem Schachbrett.
     */
    public int getYPosition() {
        return this.y;
    }

    /**
     * Diese Methode gibt die X-Position des Feldes auf dem Schachbrett wieder.
     * @return  X-Position auf dem Schachbrett.
     */
    public int getXPosition() {
        return this.x;
    }

    /**
     * Diese Methode gibt an, ob das Feld aktuell markiert ist.
     * @return  Auswertung, ob das Feld markiert ist oder nicht.
     */
    public boolean istMarkiert() {
        return markiert;
    }
}
