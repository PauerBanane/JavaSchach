package figuren;

import brett.Feld;
import brett.Schachbrett;

import java.util.ArrayList;
import java.util.List;

public class Springer extends Figur {
    /**
     * Konstruktor für die Figur.
     *
     * @param spielerFarbe Farbe des dazugehörigen Spielers.
     */
    public Springer(SpielerFarbe spielerFarbe) {
        super(spielerFarbe);
    }

    @Override
    public List<Feld> getMöglicheZüge(Schachbrett schachbrett) {
        // Liste möglicher Züge.
        List<Feld> möglicheZüge = new ArrayList<>();

        // Aktuelles Feld bekommen.
        Feld aktuellesFeld = schachbrett.getFeld(this);

        // Leere Liste zurückgeben, wenn die Figur nicht mehr im Spiel ist.
        if (aktuellesFeld == null)
            return möglicheZüge;

        // In alle Richtungen nach möglichen Zügen suchen.
        for (Richtung richtung : Richtung.getHorizontaleUndVertikaleRichtungen()) {
            // Ein Feld in bestimmte Richtung weiter.
            Feld einFeldWeiter = schachbrett.getFeld(aktuellesFeld, richtung);

            // Weiter, wenn Feld nicht existiert.
            if (einFeldWeiter == null)
                continue;

            // Zwei Felder in bestimmte Richtung weiter.
            Feld zweiFelderWeiter = schachbrett.getFeld(einFeldWeiter, richtung);

            // Weiter, wenn Feld nicht existiert.
            if (zweiFelderWeiter == null)
                continue;

            // Richtung für 'links' und 'rechts' vom zweiten Feld festlegen, da abhängig von horizontaler oder
            // vertikaler Bewegung.
            Richtung links = richtung.istVertikal() ? Richtung.LINKS : Richtung.HINTEN;
            Richtung rechts = richtung.istVertikal() ? Richtung.RECHTS : Richtung.VORNE;

            // Von zweitem Feld aus, eins nach links und eins nach rechts.
            Feld zweiWeiterDannLinks = schachbrett.getFeld(zweiFelderWeiter, links);
            Feld zweiWeiterDannRechts = schachbrett.getFeld(zweiFelderWeiter, rechts);

            // Felder hinzufügen, wenn Sie existieren und dort keine eigene Figur steht.
            if (zweiWeiterDannLinks != null &&
                    (zweiWeiterDannLinks.getFigur() == null ||
                    zweiWeiterDannLinks.getFigur().getSpielerFarbe() != this.spielerFarbe))
                möglicheZüge.add(zweiWeiterDannLinks);
            if (zweiWeiterDannRechts != null &&
                    (zweiWeiterDannRechts.getFigur() == null ||
                    zweiWeiterDannRechts.getFigur().getSpielerFarbe() != this.spielerFarbe))
                möglicheZüge.add(zweiWeiterDannRechts);
        }

        return möglicheZüge;
    }
}
