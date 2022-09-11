package figuren;

import brett.Feld;
import brett.Schachbrett;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Diese abstrakte Klasse ist die Basis für alle weiteren Schachfiguren.
 * @author Mike Nipkow
 */
public abstract class Figur {

    protected SpielerFarbe spielerFarbe;    // Farbe der Figur.
    private BufferedImage bild;             // Bild der Figur.
    private boolean wurdeGezogen;           // Boolean der angibt, ob die Figur schon gezogen wurde.

    /**
     * Konstruktor für die Figur.
     * @param spielerFarbe  Farbe des dazugehörigen Spielers.
     */
    public Figur(SpielerFarbe spielerFarbe) {
        this.spielerFarbe = spielerFarbe;

        // Bild laden.
        this.bildLaden();
    }

    /**
     * Diese Methode lädt das Bild einer Figur. Der Pfad zu dem Bild wird in den vererbten Klassen vorgegeben.
     */
    private void bildLaden() {
        // Pfad vom Bild abrufen.
        String ordnerPfad = File.separator + "bilder" + File.separator;
        String bildName = getClass().getSimpleName() + "_" + spielerFarbe.getName() + ".png";
        String bildPfad = ordnerPfad + File.separator + bildName;

        // Bild laden.
        try {
            this.bild = ImageIO.read(getClass().getResource(bildPfad));
        } catch (Exception e) {
            System.out.println("Das Bild konnte nicht geladen werden: " + bildPfad);
            throw new RuntimeException(e);
        }
    }

    /**
     * Die Methode gibt alle Felder zurück, auf denen die Figur theoretisch gesetzt werden kann.
     * Hier wird allerdings noch nicht berücksichtigt, ob der Zug wirklich legal ist, zum Beispiel, wenn der
     * eigene König durch diesem Zug im Schach stehen würde.
     * @return  Liste aller möglichen Züge.
     */
    public abstract List<Feld> getMöglicheZüge(Schachbrett schachbrett);

    /**
     * Methode um das Bild der Figur zu bekommen.
     * @return  Das Icon der Figur.
     */
    public BufferedImage getBild() {
        return bild;
    }

    /**
     * Methode um die Farbe des Spielers zu bekommen, dem die Figur gehört.
     * @return  Die Farbe des Spielers.
     */
    public SpielerFarbe getSpielerFarbe() {
        return spielerFarbe;
    }

    /**
     * Diese Methode wertet aus, ob die Figur bereits gezogen wurde.
     * Dies ist zum Beispiel für den doppelten Bauernzug, oder die Rochade notwendig.
     * @return  Auswertung, ob die Figur schon gezogen wurde.
     */
    public boolean wurdeGezogen() {
        return wurdeGezogen;
    }

    /**
     * Diese Methode wird aufgerufen, wenn die Figur gezogen wurde. Dadurch kann im Nachhinein festgestellt werden,
     * ob die Figur schonmal bewegt wurde, oder nicht.
     */
    public void setWurdeGezogen() {
        this.wurdeGezogen = true;
    }
}
