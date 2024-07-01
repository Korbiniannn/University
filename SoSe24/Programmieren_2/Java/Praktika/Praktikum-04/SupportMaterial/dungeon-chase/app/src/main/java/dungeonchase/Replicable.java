package dungeonchase;

/**
 * Interface für replizierbare Objekte
 */
public interface Replicable {

    /**
     * Gibt die Richtung der Replikation zurück
     * @return Richtung der Replikation
     */
    Direction replicationDirection();

    /**
     * Generiert eine Replikation des Objekts
     * @return Replikation des Objekts
     */
    Character generateReplication();
}
