package figuren;

import brett.Feld;
import brett.Schachbrett;

import java.util.ArrayList;
import java.util.List;

public class Turm extends Figur {

    /**
     * Konstruktor für die Figur.
     *
     * @param spielerFarbe Farbe des dazugehörigen Spielers.
     */
    public Turm(SpielerFarbe spielerFarbe) {
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
            // Aktuelles Feld, das ausgewertet werden soll.
            Feld feld = schachbrett.getFeld(aktuellesFeld, richtung);

            // Immer weiter diagonal gehen, bis das Brett endet, ohne eine Figur das nächste Feld blockiert.
            while (feld != null &&
                    (feld.getFigur() == null || feld.getFigur().getSpielerFarbe() != this.spielerFarbe)) {
                // Feld zu möglichen Zügen hinzufügen.
                möglicheZüge.add(feld);

                // Schleife beenden, wenn auf dem Feld eine gegnerische Figur steht.
                if (feld.getFigur() != null && feld.getFigur().getSpielerFarbe() != this.spielerFarbe)
                    break;

                // Nächstes Feld finden.
                feld = schachbrett.getFeld(feld, richtung);
            }
        }

        return möglicheZüge;
    }
}
