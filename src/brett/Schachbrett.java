package brett;

import figuren.Figur;
import figuren.SpielerFarbe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasse, die ein Schachbrett repräsentiert.
 * @author Mike Nipkow
 */
public class Schachbrett {

    private Feld[][] felder = new Feld[8][8];   // 64 Felder des Bretts.
    private List<Figur> figuren = new ArrayList<>();

    /**
     * Konstruktor für das Schachbrett.
     */
    public Schachbrett() {

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

    public List<Figur> getFiguren(SpielerFarbe spielerFarbe) {
        // Sicherstellen, dass eine Farbe übergeben wurde.
        assert spielerFarbe != null : "Es wurde keine gültige Spielerfarbe angegeben.";

        return figuren.stream()
                .filter(figur -> figur.getSpielerFarbe() == spielerFarbe)
                .collect(Collectors.toList());
    }

    public List<Figur> getFiguren() {
        return figuren;
    }

}
