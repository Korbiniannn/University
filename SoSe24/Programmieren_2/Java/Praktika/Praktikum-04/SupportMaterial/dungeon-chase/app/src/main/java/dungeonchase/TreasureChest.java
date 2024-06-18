package dungeonchase;

public class TreasureChest extends Character{

    TreasureChest(){
        super("Treasure Chest");
    }

    public String getImage(){
        return "treasure-chest.png";
    }

    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        return Direction.NONE;
    }

    @Override
    public boolean collisionFrom(Character other) {
        if(other instanceof Player){
            this.setAlive(false);
            return true;
        }
        return false;
    }
}
