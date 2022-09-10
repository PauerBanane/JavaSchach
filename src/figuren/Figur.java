package figuren;

/**
 * Diese abstrakte Klasse ist die Basis für alle weiteren Schachfiguren.
 * @author Mike Nipkow
 */
public abstract class Figur {

    private SpielerFarbe spielerFarbe;  // Farbe der Figur.

    /**
     * Konstruktor für die Figur.
     * @param spielerFarbe  Farbe des dazugehörigen Spielers.
     */
    public Figur(SpielerFarbe spielerFarbe) {
        this.spielerFarbe = spielerFarbe;
    }

    /**
     * Methode um die Farbe des Spielers zu bekommen, dem die Figur gehört.
     * @return  Die Farbe des Spielers.
     */
    public SpielerFarbe getSpielerFarbe() {
        return spielerFarbe;
    }
}
