package dungeonchase;

/**
 * Ogre Klasse erbt von Character und ist der erste Gegner
 */
public class Ogre extends Character{ // Ogre Klasse ist der erste Gegner

    /**
     * Konstruktor für den Ogre
     * @param name Name des Ogres
     */
    Ogre(String name){ // Konstruktor für den Ogre
        super(name);
    }
    /**
     * Gibt das Bild des Ogres zurück
     * @return Bild des Ogres
     */
    @Override
    public String getImage(){ // Gibt das Bild des Ogres zurück
        return "ogre.png";
    }
    /**
     * Aktualisiert die Position des Ogres
     * nimmt immer den kürzesten Weg zum Spieler
     * @param grid Spielfeld
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @param playerMovement Bewegung des Players
     * @return Bewegung des Ogres
     */
    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) { // gibt die Bewegung des Ogres zurück
        int dirX = grid.getPlayerX() - x;
        int dirY = grid.getPlayerY() - y;
        Direction movement;

        if(Math.abs(dirX) > Math.abs(dirY)){
            if (dirX > 0){
                movement = Direction.EAST;
            }
            else {
                movement = Direction.WEST;
            }
        } else {
            if (dirY > 0){
                movement = Direction.NORTH;
            }
            else {
                movement = Direction.SOUTH;
            }
        }
        return movement;
    }

}
