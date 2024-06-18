package dungeonchase;


public class Wall extends Character{

    Wall() {
        super("Wall");
    }

    @Override
    public String getImage(){
        return "wall.png";
    }

    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        return Direction.NONE;
    }

    @Override
    public boolean collisionFrom(Character other){
        return false;
    }
}
