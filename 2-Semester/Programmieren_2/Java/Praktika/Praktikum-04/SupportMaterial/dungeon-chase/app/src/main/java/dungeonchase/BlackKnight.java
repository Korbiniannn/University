package dungeonchase;

/**
 * Klasse für den BlackKnight
 * erbt von Character
 */
public class BlackKnight extends Character{
    /**
     * Konstruktor für den BlackKnight
     * ruft den Konstruktor der Superklasse auf
     * @param name Name des BlackKnights
     */
    BlackKnight(String name){ // Konstruktor für den BlackKnight
        super(name);
    }

    /**
     * Gibt das Bild des BlackKnights zurück
     * @return Bild des BlackKnights
     */
    @Override
    public String getImage(){ // Gibt das Bild des BlackKnights zurück
        return "black-knight.png";
    }

    /**
     * Aktualisiert die Position des BlackKnights
     * bewegt sich immer in die entgegengesetzte Richtung des Spielers
     * @param grid Spielfeld
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @param playerMovement Bewegung des Players
     * @return Bewegung des BlackKnights
     */
    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        return playerMovement.opposite();
    }
}
