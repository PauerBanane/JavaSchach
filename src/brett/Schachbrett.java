package brett;

import figuren.*;
import gui.SchachbrettGui;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasse, die ein Schachbrett repräsentiert.
 * @author Mike Nipkow
 */
public class Schachbrett {

    public static void main(String[] args) {
        Schachbrett schachbrett = new Schachbrett();
    }

    private SchachbrettGui gui;
    private Feld[][] felder = new Feld[8][8];                       // 64 Felder des Bretts.
    private List<Figur> figuren = new ArrayList<>();                // Liste aller Figuren.
    private SpielerFarbe aktuellAnDerReihe = SpielerFarbe.WEISS;    // Spieler, der als nächstes ziehen muss.
    private Feld ausgewähltesFeld;                                  // Feld, das der Spieler als erstes ausgewählt hat.
    private List<Zug> ausgeführteZüge = new LinkedList<>();         // LinkedList für alle ausgeführten Züge.

    /**
     * Konstruktor für das Schachbrett.
     */
    public Schachbrett() {
        // Schachbrett initialisieren.
        init();

        // GUI öffnen.
        gui = new SchachbrettGui(this);
        gui.anzeigen();
    }

    /**
     * Methode um das Schachbrett zu initialisieren.
     */
    private void init() {
        // Alle Felder initialisieren.
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                // Neues Feld erstellen um im Array einfügen.
                Feld feld = new Feld(y, x);
                felder[y][x] = feld;

                // Ggf. Figur erstellen.
                Figur figur = null;
                if (y == 1)
                    figur = new Bauer(SpielerFarbe.SCHWARZ);
                else if (y == 6)
                    figur = new Bauer(SpielerFarbe.WEISS);
                else if (y == 0 || y == 7) {
                    // Spielerfarbe abhängig von der Reihe.
                    SpielerFarbe spielerFarbe = y == 0 ? SpielerFarbe.SCHWARZ : SpielerFarbe.WEISS;

                    switch (x) {
                        case 0, 7:
                            figur = new Turm(spielerFarbe);
                            break;
                        case 1, 6:
                            figur = new Springer(spielerFarbe);
                            break;
                        case 2, 5:
                            figur = new Läufer(spielerFarbe);
                            break;
                        case 3:
                            figur = new Dame(spielerFarbe);
                            break;
                        case 4:
                            figur = new König(spielerFarbe);
                            break;
                    }
                }

                // Figur auf das Feld stellen.
                feld.setFigur(figur);
                figuren.add(figur);
            }
        }
    }

    /**
     * Methode zur Behandlung eines Klicks im GUI.
     * @param y Y-Position des geklickten Felds.
     * @param x X-Position des geklickten Felds.
     */
    public void aktionBeiFeldauswahl(int y, int x) {
        // Feld und Figur anhand der Position ermitteln.
        Feld feld = getFeld(y, x);
        Figur figur = feld.getFigur();

        // Aktion, wenn noch kein Feld ausgewählt ist.
        if (ausgewähltesFeld == null) {
            // Abfrage, ob der Spieler eine seiner Figuren angeklickt hat.
            if (figur == null || figur.getSpielerFarbe() != aktuellAnDerReihe)
                return;

            ausgewähltesFeld = feld;
            feld.setBackground(Color.GREEN.darker());

            // Mögliche Züge abrufen, um diese zu markieren.
            List<Feld> möglicheZüge = figur.getMöglicheZüge(this);
            zügeMarkieren(möglicheZüge);
        }
        // Aktion, wenn bereits ein Feld ausgewählt wurde.
        else {
            // Markierung aufheben, wenn dieselbe Figur nochmal angeklickt wird.
            if (feld == ausgewähltesFeld) {
                ausgewähltesFeld = null;
                alleMarkierungenEntfernen();
                return;
            }

            // Markierung ändern, wenn der Spieler eine andere Figur von sich auswählt.
            if (figur != null && figur.getSpielerFarbe() == aktuellAnDerReihe) {
                alleMarkierungenEntfernen();
                ausgewähltesFeld = feld;
                feld.setBackground(Color.GREEN.darker());

                // Mögliche Züge abrufen, um diese zu markieren.
                List<Feld> möglicheZüge = figur.getMöglicheZüge(this);
                zügeMarkieren(möglicheZüge);
                return;
            }

            // Mögliche Züge abrufen.
            List<Feld> möglicheZüge = ausgewähltesFeld.getFigur().getMöglicheZüge(this);

            // Wenn der Spieler auf ein illegales Feld klickt, nichts unternehmen.
            if (!möglicheZüge.contains(feld))
                return;

            // Figur bekommen, die ggf. geschlagen wird.
            Figur geschlageneFigur = feld.getFigur();

            // Figur auf das neue Feld setzen.
            feld.setFigur(ausgewähltesFeld.figurEntfernen());

            // Figur als 'wurde gezogen' markieren.
            feld.getFigur().setWurdeGezogen();

            // Zug erstellen und dokumentieren.
            Zug zug = new Zug(feld.getFigur(), geschlageneFigur, ausgewähltesFeld, feld);
            ausgeführteZüge.add(zug);

            alleMarkierungenEntfernen();
            ausgewähltesFeld = null;
            aktuellAnDerReihe = aktuellAnDerReihe == SpielerFarbe.WEISS ? SpielerFarbe.SCHWARZ : SpielerFarbe.WEISS;
        }
    }

    /**
     * Diese Methode markiert alle mögliche Züge, damit der Spieler direkt weiß, was er ziehen kann.
     * @param möglicheZüge  Liste der möglichen Felder, auf die die Figur ziehen kann.
     */
    private void zügeMarkieren(List<Feld> möglicheZüge) {
        // Durch alle Züge gehen und die markieren.
        for (Feld möglichesFeld : möglicheZüge) {
            if (möglichesFeld.getFigur() == null)
                möglichesFeld.setBackground(Color.YELLOW.darker());
            else
                möglichesFeld.setBackground(Color.RED.darker());
        }
    }

    /**
     * Diese Methode setzt alle Feldmarkierungen zurück.
     */
    private void alleMarkierungenEntfernen() {
        for (Feld[] feldArray : felder)
            for (Feld feld : feldArray)
                feld.resetHintergrundfarbe();
    }

    /**
     * Diese Methode gibt das Feld zurück, welches in der Richtung des Urprungsfeldes liegt.
     * @param ursprung  Feld, von welchem ausgegangen wird.
     * @param richtung  Richtung, in welchem das gewünschte Feld vom Urpsrungsfeld aus liegt.
     * @return          Das gewünschte Feld.
     */
    public Feld getFeld(Feld ursprung, Richtung richtung) {
        // Sicherstellen, dass die Parameter korrekt sind.
        if (ursprung == null)
            throw new NullPointerException("Das Ursprungsfeld wurde darf nicht null sein.");
        if (richtung == null)
            throw new NullPointerException("Die Richtung darf nicht null sein.");

        // Variablen für das Zielfeld.
        int originY = ursprung.getYPosition();
        int originX = ursprung.getXPosition();
        int zielY = originY;
        int zielX = originX;

        // Zielposition durch Auswertung der Richtung bekommen.
        switch (richtung) {
            case VORNE:
                zielY--;
                break;
            case VORNE_RECHTS:
                zielY--;
                zielX++;
                break;
            case RECHTS:
                zielX++;
                break;
            case HINTEN_RECHTS:
                zielY++;
                zielX++;
                break;
            case HINTEN:
                zielY++;
                break;
            case HINTEN_LINKS:
                zielY++;
                zielX--;
                break;
            case LINKS:
                zielX--;
                break;
            case VORNE_LINKS:
                zielY--;
                zielX--;
                break;
        }

        // Null zurückgeben, wenn die Position nicht auf dem Schachbrett liegt.
        if (zielY < 0 || zielY > 7 || zielX < 0 || zielX > 7)
            return null;

        // Feld zurückgeben.
        return getFeld(zielY, zielX);
    }

    /**
     * Diese Methode gibt das Feld einer Figur zurück.
     * Dabei wird durch jedes Feld iteriert und die Figur darauf mit der angegebenen verglichen. Dadurch muss
     * in dem Figur-Objekt keine separate Referenz zum Feld hinterlegt werden.
     * @param figur Die Figur, dessen Feld man wissen möchte.
     * @return      Das Feld, auf dem die Figur steht, oder null, wenn die Figur auf keinem Feld steht.
     */
    public Feld getFeld(Figur figur) {
        for (Feld[] feldArray : felder)
            for (Feld feld : feldArray)
                if (feld != null && feld.getFigur() == figur)
                    return feld;

        return null;
    }

    /**
     * Methode um ein Feld des Schachbretts anhand der X-Position (Spalte) und Y-Position (Reihe) zu erhalten.
     * @param y Reihe des Schachbretts.
     * @param x Spalte des Schachbretts.
     * @return  Das Feld an der entsprechenden Position.
     */
    public Feld getFeld(int y, int x) {
        // Sicherstellen, dass das Feld existiert.
        assert (y >= 0 && y <= 7) : "Die angegebene Reihe liegt nicht innerhalb des Bretts: " + y;
        assert (x >= 0 && x <= 7) : "Die angegebene Spalte liegt nicht innerhalb des Bretts: " + x;

        return felder[y][x];
    }

    /**
     * Figur auf einem bestimmten Feld zurückgeben.
     * @param y Reihe des Schachbretts.
     * @param x Spalte des Schachbretts.
     * @return  Figur, die auf dem Feld steht, oder null, wenn keine Figur auf dem Feld steht.
     */
    public Figur getFigur(int y, int x) {
        // Feld abrufen.
        Feld feld = getFeld(y, x);

        // Sicherstellen, dass das Feld initialisiert wurde.
        assert feld != null : "Das Feld in der Reihe " + y + " und Spalte " + x + " wurde nicht initialisiert.";

        return feld.getFigur();
    }

    /**
     * Diese Methode gibt alle Figuren einer Farbe zurück.
     * @param spielerFarbe  Farbe der Figuren.
     * @return  Alle Figuren der gewünschten Farbe.
     */
    public List<Figur> getFiguren(SpielerFarbe spielerFarbe) {
        // Sicherstellen, dass eine Farbe übergeben wurde.
        assert spielerFarbe != null : "Es wurde keine gültige Spielerfarbe angegeben.";

        return figuren.stream()
                .filter(figur -> figur.getSpielerFarbe() == spielerFarbe)
                .collect(Collectors.toList());
    }

    /**
     * Diese Methode gibt alle Figuren des Spiels zurück.
     * @return  Alle Figuren als Liste.
     */
    public List<Figur> getFiguren() {
        return figuren;
    }

    /**
     * Methode um das aktuell ausgewählte Feld zurückzugeben.
     * @return  Das aktuell ausgewählte Feld.
     */
    public Feld getAusgewähltesFeld() {
        return ausgewähltesFeld;
    }

    /**
     * Methode, welche den zuletzt ausgeführten Zug zurückgibt.
     * @return  Zuletzt ausgeführter Zug, oder null, wenn noch kein Zug ausgeführt wurde.
     */
    public Zug getLetztenZug() {
        // Null zurückgeben, wenn noch kein Zug ausgeführt wurde.
        if (ausgeführteZüge.isEmpty())
            return null;

        return ausgeführteZüge.get(ausgeführteZüge.size() - 1);
    }
}
