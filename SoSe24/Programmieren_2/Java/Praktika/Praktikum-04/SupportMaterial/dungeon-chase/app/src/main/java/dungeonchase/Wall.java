package dungeonchase;

/**
 * Wall ist eine Klasse, die ein Hindernis im Spiel darstellt.
 * Sie erbt von Character.
 * Sie interagiert nicht mit anderen Charakteren.
 */
public class Wall extends Character{

    /**
     * Konstruktor für die Wand
     */
    Wall() { // Konstruktor für die Wand
        super("Wall");
    }
    /**
     * Gibt das Bild der Wand zurück
     * @return Bild der Wand
     */
    @Override
    public String getImage(){// Gibt das Bild der Wand zurück
        return "wall.png";
    }

    /**
     * Aktualisiert die Position der Wand
     * @param grid Spielfeld
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @param playerMovement Bewegung des Players
     * @return Bewegung der Wand (immer NONE)
     */
    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) { // hat immer die Bewegung NONE, da sich die Wand nicht bewegt
        return Direction.NONE;
    }

    /**
     * Kollisionsmethode für die Wand
     * @param other anderer Charakter
     * @return false, da die Wand nicht mit anderen Charakteren kollidieren kann
     */
    @Override
    public boolean collisionFrom(Character other){ // kann nicht sterben
        return false;
    }
}
