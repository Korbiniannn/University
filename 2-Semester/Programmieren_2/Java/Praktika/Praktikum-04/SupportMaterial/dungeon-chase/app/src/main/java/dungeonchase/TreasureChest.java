package dungeonchase;

/**
 * Klasse für die Schatztruhe
 * erbt von Character
 */
public class TreasureChest extends Character{

    /**
     * Konstruktor für die Schatztruhe
     * ruft den Konstruktor der Superklasse auf
     */
    TreasureChest(){ // Konstruktor für die Schatztruhe
        super("Treasure Chest");
    }

    /**
     * Gibt das Bild der Schatztruhe zurück
     * @return Bild der Schatztruhe
     */
    public String getImage(){ // Gibt das Bild der Schatztruhe zurück
        return "treasure-chest.png";
    }

    /**
     * Die Schatztruhe bewegt sich nicht
     * deshalb wird immer NONE zurückgegeben
     * @param grid Spielfeld
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @param playerMovement Bewegung des Players
     * @return Bewegung der Schatztruhe (immer NONE)
     */
    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        return Direction.NONE;
    }

    /**
     * Kollisionsmethode für die Schatztruhe
     * die Truhe wird nur geöffnet, wenn der Spieler mit ihr kollidiert
     * @param other anderer Charakter
     * @return true, wenn die Schatztruhe mit dem Spieler kollidiert
     */
    @Override
    public boolean collisionFrom(Character other) {
        if(other instanceof Player){
            this.setAlive(false);
            return true;
        }
        return false;
    }
}
