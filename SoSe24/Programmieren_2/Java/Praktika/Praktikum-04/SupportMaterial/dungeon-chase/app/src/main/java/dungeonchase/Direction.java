package dungeonchase;

import java.util.Random;

public enum Direction {
    NONE(0, 0),
    NORTH(0, +1),
    NORTH_EAST(+1, +1),
    EAST(+1, 0),
    SOUTH_EAST(+1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, +1);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy){
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx(){
        return dx;
    }

    public int getDy(){
        return dy;
    }


    public static Direction fromD(int dx, int dy){
        String str = dx+", "+dy;
        switch (str){
            case "0, 0" :
                return NONE;
            case "0, +1":
                return NORTH;
            case "+1, +1":
                return NORTH_EAST;
            case "+1, 0":
                return EAST;
            case "+1, -1":
                return SOUTH_EAST;
            case "0, -1":
                return SOUTH;
            case "-1, -1":
                return SOUTH_WEST;
            case "-1, 0":
                return WEST;
            case "-1, +1":
                return NORTH_WEST;
            default:
                throw new IllegalArgumentException("ungültiges Parameter paar");
        }
    }

    public Direction opposite(){
        return fromD(-dx, -dy);
    }

    public static Direction random4(){
        Random randint = new Random();

        int num = randint.nextInt(0, 4);

        switch (num){
            case 0:
                return NORTH;
            case 1:
                return EAST;
            case 2:
                return SOUTH;
            case 3:
                return WEST;
            default:
                return NONE;
        }
    }
}
