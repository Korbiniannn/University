package dungeonchase;

/**
 * Klasse für den Blob
 * erbt von Character
 * implementiert Replicable
 */
public class Blob extends Character implements Replicable{

    /**
     * Wahrscheinlichkeit für die Replikation
     */
    double replicationProbability;

    /**
     * Konstruktor für den Blob
     * ruft den Konstruktor der Superklasse auf
     * @param name Name des Blobs
     * @param replicationProbability Wahrscheinlichkeit für die Replikation
     */
    Blob(String name, double replicationProbability){ // Konstruktor für den Blob
        super(name);

        if(replicationProbability>1 || replicationProbability<0){
            throw new IllegalArgumentException("Wahrscheinlichkeit muss größer gleich 0 und kleiner gleich 1 sein!");
        }
        this.replicationProbability = replicationProbability;
    }

    /**
     * Gibt das Bild des Blobs zurück
     * @return Bild des Blobs
     */
    @Override
    public String getImage() { // Gibt das Bild des Blobs zurück
        return "blob.png";
    }

    /**
     * Aktualisiert die Position des Blobs
     * bewegt sich zufällig
     * @param grid Spielfeld
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @param playerMovement Bewegung des Players
     * @return Bewegung des Blobs
     */
    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        return Direction.random4(); // gibt eine zufällige Richtung zurück, der blob bewegt sich zufällig
    }

    /**
     * gibt die Replikationsrichtung zurück
     * @return Replikationsrichtung
     */
    @Override
    public Direction replicationDirection() { // gibt die Replikationsrichtung zurück
        if (Math.random() < this.replicationProbability){
            return Direction.random4(); // gibt eine zufällige Richtung zurück
        }
        return Direction.NONE;
    }

    /**
     * Gibt eine Replikation des Blobs zurück
     * @return Replikation des Blobs
     */
    @Override
    public Character generateReplication() { // Gibt eine Replikation des Blobs zurück
        return new Blob("blob", this.replicationProbability);
    }
}
