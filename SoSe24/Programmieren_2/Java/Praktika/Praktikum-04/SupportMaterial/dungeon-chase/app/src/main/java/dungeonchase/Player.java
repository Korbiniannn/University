package dungeonchase;

public class Player extends Character{

    Player() {
        super("player");
    }

    @Override
    public String getImage() {
        return "player.png";
    }

    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        return playerMovement;
    }

}
