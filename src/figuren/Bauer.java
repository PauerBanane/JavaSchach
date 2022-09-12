package figuren;

import brett.Feld;
import brett.Schachbrett;
import brett.Zug;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert die Schachfigur 'Bauer'.
 * @author Mike Nipkow
 */
public class Bauer extends Figur {

    /**
     * Konstruktor für die Figur.
     *
     * @param spielerFarbe Farbe des dazugehörigen Spielers.
     */
    public Bauer(SpielerFarbe spielerFarbe) {
        super(spielerFarbe);
    }

    @Override
    public List<Feld> getMöglicheZüge(Schachbrett schachbrett) {
        // Liste möglicher Züge.
        List<Feld> möglicheZüge = new ArrayList<>();

        // Feld, auf dem die Figur aktuell steht.
        Feld aktuellesFeld = schachbrett.getFeld(this);

        // Leere Liste zurückgeben, wenn die Figur nicht mehr im Spiel ist.
        if (aktuellesFeld == null)
            return möglicheZüge;

        // Feld vor dem Bauern bekommen.
        Richtung richtung = spielerFarbe == SpielerFarbe.WEISS ? Richtung.VORNE : Richtung.HINTEN;
        Feld einFeldVor = schachbrett.getFeld(aktuellesFeld, richtung);

        // Fehler werfen, wenn das Feld nicht existiert.
        if (einFeldVor == null)
            throw new NullPointerException("Aufgrund eines Fehler konnte das Feld " +
                    "vor dem Bauern nicht ermittelt werden.");

        // Abfrage, ob das Feld noch frei ist. Wenn ja, als möglichen Zug hinzufügen.
        if (einFeldVor.getFigur() == null)
            möglicheZüge.add(einFeldVor);

        // Falls die Figur noch nicht bewegt wurde, kann Sie möglicherweise zwei Felder weit ziehen.
        if (!wurdeGezogen() && einFeldVor.getFigur() == null) {
            // Zweites Feld vor dem Bauern bekommen.
            Feld zweiFelderVor = schachbrett.getFeld(einFeldVor, richtung);

            // Fehler werfen, wenn das Feld nicht existiert.
            if (zweiFelderVor == null)
                throw new NullPointerException("Aufgrund eines Fehler konnte das zweite Feld " +
                        "vor dem Bauern nicht ermittelt werden.");

            // Abfrage, ob das Feld noch frei ist. Wenn ja, als möglichen Zug hinzufügen.
            if (zweiFelderVor.getFigur() == null)
                möglicheZüge.add(zweiFelderVor);
        }

        // Auswerten, ob eine Figur diagonal geschlagen werden kann.
        Feld einFeldDiagonalVorneLinks = schachbrett.getFeld(einFeldVor, Richtung.LINKS);
        Feld einFeldDiagonalVorneRechts = schachbrett.getFeld(einFeldVor, Richtung.RECHTS);

        // Felder hinzufügen, wenn sie existieren und eine gegnerische Figur darauf steht.
        if (einFeldDiagonalVorneLinks != null &&
                einFeldDiagonalVorneLinks.getFigur() != null &&
                einFeldDiagonalVorneLinks.getFigur().getSpielerFarbe() != this.spielerFarbe)
            möglicheZüge.add(einFeldDiagonalVorneLinks);

        if (einFeldDiagonalVorneRechts != null &&
                einFeldDiagonalVorneRechts.getFigur() != null &&
                einFeldDiagonalVorneRechts.getFigur().getSpielerFarbe() != this.spielerFarbe)
            möglicheZüge.add(einFeldDiagonalVorneRechts);

        // Auswerten, ob ein Bauer EnPassante geschlagen werden kann.
        // Dazu erst prüfen, ob dies der erste Zug ist.
        Zug letzterZug = schachbrett.getLetztenZug();

        if (letzterZug != null && letzterZug.getFigur() instanceof Bauer) {
            // Überprüfen, ob der Bauer im letzten Zug zwei Feld gezogen hat.
            int originYPosition = letzterZug.getOriginFeld().getYPosition();    // Reihe, vor dem letzten Zug.
            int zielYPosition = letzterZug.getZielFeld().getYPosition();        // Reihe, nach dem letzten Zug.
            int anzahlGezogenerFelder = Math.abs(originYPosition - zielYPosition);

            // Weitere Überprüfung nur fortsetzen, wenn der Bauer im letzten Zug zwei Felder weit gezogen wurde.
            if (anzahlGezogenerFelder == 2) {
                // Nun die Felder links und rechts auf gegnerischen Bauern überprüfen.
                Feld einFeldLinks = schachbrett.getFeld(aktuellesFeld, Richtung.LINKS);
                Feld einFeldRechts = schachbrett.getFeld(aktuellesFeld, Richtung.RECHTS);

                // Auswertung, ob die Felder existieren und ob dort gegnerische Bauern stehen.
                if (einFeldLinks != null && einFeldLinks.getFigur() != null &&
                        einFeldLinks.getFigur() instanceof Bauer &&
                        einFeldLinks.getFigur().getSpielerFarbe() != spielerFarbe) {

                    // Abfrage, ob die Figur, die letzten Zug gezogen wurde, der entsprechende Bauer ist.
                    if (letzterZug != null && letzterZug.getFigur() == einFeldLinks.getFigur())
                        möglicheZüge.add(einFeldDiagonalVorneLinks);
                }
                else if (einFeldRechts != null && einFeldRechts.getFigur() != null &&
                        einFeldRechts.getFigur() instanceof Bauer &&
                        einFeldRechts.getFigur().getSpielerFarbe() != spielerFarbe) {

                    // Abfrage, ob die Figur, die letzten Zug gezogen wurde, der entsprechende Bauer ist.
                    if (letzterZug != null && letzterZug.getFigur() == einFeldRechts.getFigur())
                        möglicheZüge.add(einFeldDiagonalVorneRechts);
                }
            }
        }


        return möglicheZüge;
    }
}
