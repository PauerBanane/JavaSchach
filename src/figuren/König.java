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

        // Auswertung der Rochade. Nur, wenn der König noch nicht gezogen wurde.
        if (!wurdeGezogen()) {
            // Für Links und Rechts Auswertung wiederholen.
            for (Richtung richtung : Richtung.getHorizontaleRichtungen()) {
                // Aktuelles Feld, das ausgewertet wird.
                Feld feld = schachbrett.getFeld(aktuellesFeld, richtung);

                // Prüfen, ob die Felder links und/oder rechts bis zum Turm frei sind.
                while (feld != null) {

                    // Abbrechen, wenn auf dem Feld eine Figur steht, die kein Turm ist,
                    // oder wenn der Turm schon gezogen hat.
                    Figur figurAufDemFeld = feld.getFigur();
                    if (figurAufDemFeld != null &&
                            (!(figurAufDemFeld instanceof Turm) || figurAufDemFeld.wurdeGezogen()))
                        break;

                    // Feld des Turms als möglichen Zug hinzufügen, damit der Anwender erkennt, dass er rochieren kann.
                    // Nur, wenn auf dem Feld eine Figur steht.
                    if (figurAufDemFeld != null)
                        möglicheZüge.add(feld);

                    // Nächstes Feld.
                    feld = schachbrett.getFeld(feld, richtung);
                }
            }
        }

        return möglicheZüge;
    }
}
