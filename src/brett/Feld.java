package brett;

import figuren.Figur;

/**
 * Diese Klasse repräsentiert ein Schachfeld und dessen Eigenschaften.
 * @author Mike Nipkow
 */
public class Feld {

    int y;              // Y-Position auf dem Schachbrett.
    int x;              // X-Position auf dem Schachbrett.
    Figur figur;        // Figur, die das Feld besetzt.

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
    }
}
