package brett;

import figuren.Figur;
import figuren.SpielerFarbe;

/**
 * Diese Klasse repräsentiert einen Zug, der durch einen Spieler ausgeführt wurde.
 * @author Mike Nipkow
 */
public class Zug {

    private final Figur figur;              // Figur, die gezogen wurde.
    private final Figur geschlageneFigur;   // Figur, die gezogen wurde.
    private final Feld originFeld;          // Feld, auf dem die Figur vor dem Zug stand.
    private final Feld zielFeld;            // Feld, auf das die Figur gezogen wurde.

    /**
     * Konstruktor zum Erstellen eines Zug-Objekts.
     * @param figur             Figur, die gezogen wurde.
     * @param geschlageneFigur  Ggf. Figur, die geschlagen wurde.
     * @param originFeld        Feld, auf dem die Figur vorher stand.
     * @param zielFeld          Feld, auf das die Figur gezogen wurde.
     */
    public Zug(Figur figur, Figur geschlageneFigur, Feld originFeld, Feld zielFeld) {
        this.figur = figur;
        this.geschlageneFigur = geschlageneFigur;
        this.originFeld = originFeld;
        this.zielFeld = zielFeld;
    }

    /**
     * Konstruktor zum Erstellen eines Zug-Objekts.
     * @param figur         Figur, die gezogen wurde.
     * @param originFeld    Feld, auf dem die Figur vorher stand.
     * @param zielFeld      Feld, auf das die Figur gezogen wurde.
     */
    public Zug(Figur figur, Feld originFeld, Feld zielFeld) {
        this(figur, null, originFeld, zielFeld);
    }

    /**
     * Methode mit welcher die Farbe des ausführenden Spielers herausgefunden werden kann.
     * @return  Farbe des ausführenden Spielers.
     */
    public SpielerFarbe getSpielerfarbe() {
        return figur.getSpielerFarbe();
    }

    /**
     * Methode, welche das Feld zurückgibt, auf welchem die Figur vor dem Zug stand.
     * @return  Anfangsfeld.
     */
    public Feld getOriginFeld() {
        return originFeld;
    }

    /**
     * Methode, welche das Feld zurückgibt, auf welches die Figur gezogen wurde.
     * @return  Zielfeld.
     */
    public Feld getZielFeld() {
        return zielFeld;
    }

    /**
     * Methode, welche die gezogene Figur zurückgibt.
     * @return  Gezogene Figur.
     */
    public Figur getFigur() {
        return figur;
    }

    /**
     * Methode um rauszufinden, ob bei dem Zug eine Figur geschlagen wurde.
     * @return  Boolean der Auskunft gibt, ob eine Figur geschlagen wurde.
     */
    public boolean figurGeschlagen() {
        return geschlageneFigur != null;
    }

    /**
     * Getter Methode für die geschlagene Figur.
     * @return  Geschlagene Figur, oder null, wenn keine Figur geschlagen wurde.
     */
    public Figur getGeschlageneFigur() {
        return geschlageneFigur;
    }
}
