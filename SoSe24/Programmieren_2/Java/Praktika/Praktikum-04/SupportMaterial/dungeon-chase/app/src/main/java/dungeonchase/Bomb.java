package dungeonchase;

public class Bomb extends Character{

    Bomb() {
        super("Bomb");
    }

    @Override
    public String getImage() {
        return "bomb.png";
    }

    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        return Direction.NONE;
    }

    @Override
    public boolean collisionFrom(Character other) {
        if(super.collisionFrom(other)){
            other.setAlive(false);
            return true;
        }
        return false;
    }
}
