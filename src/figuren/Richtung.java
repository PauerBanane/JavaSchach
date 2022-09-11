package figuren;

/**
 * Diese Enumeration gibt die möglichen Richtungen an, welche von einem Feld aus existieren.
 * Dabei wird nur der Blickwinkel des weißen Spielers berücksichtigt.
 * @author Mike Nipkow
 */
public enum Richtung {

    VORNE,
    VORNE_RECHTS,
    RECHTS,
    HINTEN_RECHTS,
    HINTEN,
    HINTEN_LINKS,
    LINKS,
    VORNE_LINKS;

    private static Richtung[] diagonaleRichtungen = {VORNE_RECHTS, HINTEN_RECHTS, HINTEN_LINKS, VORNE_LINKS};
    private static Richtung[] horizontaleUndVertikaleRichtungen = {VORNE, RECHTS, HINTEN, LINKS};

    public static Richtung[] getDiagonaleRichtungen() {
        return diagonaleRichtungen;
    }

    public static Richtung[] getHorizontaleUndVertikaleRichtungen() {
        return horizontaleUndVertikaleRichtungen;
    }

    public boolean istVertikal() {
        return this == VORNE || this == HINTEN;
    }

    public boolean istHorizontal() {
        return this == LINKS || this == RECHTS;
    }

}
