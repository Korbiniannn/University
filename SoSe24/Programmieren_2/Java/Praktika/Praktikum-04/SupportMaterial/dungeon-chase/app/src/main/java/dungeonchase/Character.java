package dungeonchase;

public abstract class Character {

    String name;
    boolean alive;
    int lastUpdate;

    Character(String name){
        if(name == "" || name == null){
            throw new IllegalArgumentException("name darf nicht leer oder null sein");
        }

        this.name = name;
        this.alive = true;
        this.lastUpdate = 0;
    }

    public String getName(){
        return name;
    }

    public boolean isAlive(){
        return alive;
    }

    public int getLastUpdate(){
        return lastUpdate;
    }
    protected void setAlive(boolean alive){ // protected damit man nicht betrügen kann, aber von unterklasse noch verändert werden kann
        this.alive = alive;
    }

    public void setLastUpdate(int lastUpdate){
        this.lastUpdate = lastUpdate;
    }

    public abstract String getImage();

    public abstract Direction update(Grid grid, int x, int y, Direction playerMovement);

    public boolean collisionFrom(Character other) {
        if (other != null && other != this){
            setAlive(false);
            return true;
        }
        return false;
    }
}
