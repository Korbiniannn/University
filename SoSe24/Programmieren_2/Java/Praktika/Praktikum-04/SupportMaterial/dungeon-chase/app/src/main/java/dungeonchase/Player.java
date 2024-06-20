package dungeonchase;
/**
 * Klasse für den Player
 * erbt von Character
 */
public class Player extends Character{

    /**
     * Konstruktor für den Player
     * ruft den Konstruktor der Superklasse auf
     */
    Player() { // Konstruktor für den Player
        super("player");
    }
    /**
     * Gibt das Bild des Players zurück
     * @return Bild des Players
     */
    @Override
    public String getImage() { // Gibt das Bild des Players zurück
        return "player.png";
    }
    /**
     * Aktualisiert die Position des Players
     * @param grid Spielfeld
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @param playerMovement Bewegung des Players
     * @return Bewegung des Players
     */
    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) { // Aktualisiert die Position des Players
        return playerMovement;
    }

}
