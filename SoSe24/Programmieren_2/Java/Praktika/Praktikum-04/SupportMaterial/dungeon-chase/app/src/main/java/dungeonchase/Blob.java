package dungeonchase;

public class Blob extends Character implements Replicable{

    double replicationProbability;

    Blob(String name, double replicationProbability){
        super(name);

        if(replicationProbability>1 || replicationProbability<0){
            throw new IllegalArgumentException("Wahrscheinlichkeit muss größer gleich 0 und kleiner gleich 1 sein!");
        }
        this.replicationProbability = replicationProbability;
    }

    @Override
    public String getImage() {
        return "blob.png";
    }

    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        return Direction.random4();
    }

    @Override
    public Direction replicationDirection() {
        if (Math.random() < this.replicationProbability){
            return Direction.random4();
        }
        return Direction.NONE;
    }

    @Override
    public Character generateReplication() {
        return new Blob("blob", this.replicationProbability);
    }
}
