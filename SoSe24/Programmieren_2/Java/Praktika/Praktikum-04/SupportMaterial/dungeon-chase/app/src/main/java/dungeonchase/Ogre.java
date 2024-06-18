package dungeonchase;

public class Ogre extends Character{

    Ogre(String name){
        super(name);
    }

    @Override
    public String getImage(){
        return "ogre.png";
    }

    @Override
    public Direction update(Grid grid, int x, int y, Direction playerMovement) {
        int dirX = grid.getPlayerX() - x;
        int dirY = grid.getPlayerY() - y;
        Direction movement;

        if(Math.abs(dirX) > Math.abs(dirY)){
            if (dirX > 0){
                movement = Direction.EAST;
            }
            else {
                movement = Direction.WEST;
            }
        } else {
            if (dirY > 0){
                movement = Direction.NORTH;
            }
            else {
                movement = Direction.SOUTH;
            }
        }
        return movement;
    }

}
