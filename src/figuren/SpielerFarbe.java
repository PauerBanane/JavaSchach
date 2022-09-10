package figuren;

import java.awt.*;

/**
 * Diese Enumeration wird für die Farbe der Figuren, sowie der Darstellung des Spielers der am Zug ist, verwendet.
 * @author Mike Nipkow
 */
public enum SpielerFarbe {

    SCHWARZ("Schwarz", Color.BLACK),
    WEISS("Weiß", Color.WHITE);

    private String name;
    private Color farbe;

    /**
     * Konstruktor für die figuren.SpielerFarbe.
     * @param name  Anzeigename der Farbe.
     * @param farbe Farbe zur Darstellung im Swing-Framework.
     */
    SpielerFarbe(String name, Color farbe) {
        this.name = name;
        this.farbe = farbe;
    }

    /**
     * Anzeigenamen zurückgeben.
     * @return  Den Anzeigenamen.
     */
    public String getName() {
        return name;
    }

    /**
     * Die Farbe zurückgeben.
     * @return  Die anzuzeigende Farbe.
     */
    public Color getFarbe() {
        return farbe;
    }
}
