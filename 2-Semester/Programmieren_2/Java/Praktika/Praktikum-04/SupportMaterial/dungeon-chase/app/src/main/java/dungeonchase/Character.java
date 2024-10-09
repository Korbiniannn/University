package dungeonchase;

/**
 * Abstrakte Klasse für alle Charaktere des Spiels
 */
public abstract class Character { // abstrakte Oberklasse für alle Charaktere des Spiels

    /**
     * Attribute für die Klasse Character
     */
    String name;
    boolean alive;
    int lastUpdate;

    /**
     * Konstruktor für die Klasse Character
     * @param name Name des Charakters
     */
    Character(String name){ // Konstruktor für die Klasse Character
        if(name == "" || name == null){ // wenn name leer oder null ist, dann Fehlermeldung
            throw new IllegalArgumentException("name darf nicht leer oder null sein");
        }

        this.name = name;
        this.alive = true;
        this.lastUpdate = 0;
    }
    /**
     * getter für den Namen
     * @return name
     */
    public String getName(){ // getter für den Namen
        return name;
    }
    /**
     * getter für alive
     * @return alive
     */
    public boolean isAlive(){ // getter für alive
        return this.alive;
    }
    /**
     * getter für lastUpdate
     * @return lastUpdate
     */
    public int getLastUpdate(){ // getter für lastUpdate
        return this.lastUpdate;
    }
    /**
     * setter für alive
     * protected damit man nicht betrügen kann, aber von unterklasse noch verändert werden kann
     * @param alive boolean, ob der Charakter lebt
     */
    protected void setAlive(boolean alive){ // protected damit man nicht betrügen kann, aber von unterklasse noch verändert werden kann
        this.alive = alive;
    }
    /**
     * setter für lastUpdate
     * @param lastUpdate letztes Update
     */
    public void setLastUpdate(int lastUpdate){ // setter für lastUpdate
        this.lastUpdate = lastUpdate;
    }
    /**
     * abstrakte Methode für das Bild des Charakters
     * @return String mit dem Namen des Bildes des Charakters
     */
    public abstract String getImage(); // abstrakte Methode für das Bild des Charakters

    /**
     * abstrakte Methode für die Bewegungsrichtung des Charakters
     * @param grid Spielfeld
     * @param x x-Koordinate
     * @param y y-Koordinate
     * @param playerMovement Bewegungsrichtung des Spielers
     * @return Bewegungsrichtung des Charakters
     */
    public abstract Direction update(Grid grid, int x, int y, Direction playerMovement); // abstrakte Methode für die Bewegungsrichtung des Charakters

    /**
     * Methode für die Kollision mit einem anderen Charakter
     * @param other anderer Charakter
     * @return boolean, ob es eine Kollision gab
     */
    public boolean collisionFrom(Character other) { // Methode für die Kollision mit einem anderen Charakter
        this.setAlive(false);
        return true;
    }
}
