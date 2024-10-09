package dungeonchase;

/**
 * Klasse für die Bombe
 * erbt von Character
 * explodiert, wenn sie mit einem anderen Charakter kollidiert
 */
public class Bomb extends Character{ // Klasse für die Bombe

    /**
     * Konstruktor für die Bombe
     * ruft den Konstruktor der Superklasse auf
     */
    Bomb() { // Konstruktor für die Bombe
        super("Bomb");
    }

    /**
     * Gibt das Bild der Bombe zurück
     * @return Bild der Bombe
     */
    @Override
    public String getImage() { // Gibt das Bild der Bombe zurück
        return "bomb.png";
    }

    /**
     * Die Bombe bewegt sich nicht
     * deshalb wird immer NONE zurückgegeben
     * @param grid Spielfeld
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @param playerMovement Bewegung des Players
     * @return Bewegung der Bombe (immer NONE)
     */
    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) { // hat immer die Bewegung NONE, da sich die Bombe nicht bewegt
        return Direction.NONE;
    }

    /**
     * Kollisionsmethode für die Bombe
     * explodiert, wenn sie mit einem anderen Charakter kollidiert
     * @param other anderer Charakter
     * @return true, wenn die Bombe explodiert
     */
    @Override
    public boolean collisionFrom(Character other) {// explodiert, wenn sie mit einem anderen Charakter kollidiert
        other.setAlive(false);
        super.collisionFrom(other);
        return true;
    }
}
