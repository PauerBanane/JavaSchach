package figuren;

import brett.Feld;
import brett.Schachbrett;

import java.util.ArrayList;
import java.util.List;

public class König extends Figur {
    /**
     * Konstruktor für die Figur.
     *
     * @param spielerFarbe Farbe des dazugehörigen Spielers.
     */
    public König(SpielerFarbe spielerFarbe) {
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

        // Felder in alle möglichen Richtungen überprüfen.
        for (Richtung richtung : Richtung.values()) {
            // Aktuelles Feld, das ausgewertet werden soll.
            Feld feld = schachbrett.getFeld(aktuellesFeld, richtung);

            // Wenn Feld nicht existiert, oder eigene Figur drauf steht, überspringen.
            if (feld == null || (feld.getFigur() != null && feld.getFigur().getSpielerFarbe() == this.spielerFarbe))
                continue;

            // Feld zu möglichem Zug hinzufügen.
            möglicheZüge.add(feld);
        }

        return möglicheZüge;
    }
}
