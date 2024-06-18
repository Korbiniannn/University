package dungeonchase;

public class BlackKnight extends Character{

    BlackKnight(String name){
        super(name);
    }

    @Override
    public String getImage(){
        return "black-knight.png";
    }

    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        return playerMovement.opposite();
    }
}
