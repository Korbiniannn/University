package dungeonchase;

import java.util.Random;

/**
 * Enum für die möglichen Richtungen in die sich ein Character bewegen kann
 */

public enum Direction { // enum-Werte sind die möglichen richtungen in die sich ein Character bewegen kann
    NONE(0, 0),
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1);

    /**
     * dx und dy sind die Werte um die sich ein Character in x- und y-Richtung bewegt
     */
    private final int dx;
    private final int dy;

    /**
     * Konstruktor für Direction Objekte
     * @param dx x-Wert
     * @param dy y-Wert
     */
    Direction(int dx, int dy){ // Konstruktor für Direction Objekte
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * getter für dx
     * @return dx
     */
    public int getDx(){ // getter für den X-Wert
        return dx;
    }
    /**
     * getter für dy
     * @return dy
     */
    public int getDy(){ // getter für den Y-Wert
        return dy;
    }

    /**
     * gibt die passende richtung für das jeweilige Koordinatenpaar zurück
     * @param dx x-Wert
     * @param dy y-Wert
     * @return die passende Richtung
     */
    public static Direction fromD(int dx, int dy){ // gibt die passende richtung für das jeweilige Koordinatenpaar zurück
        String str = dx+", "+dy;
        switch (str){
            case "0, 0" :
                return NONE;
            case "0, 1":
                return NORTH;
            case "1, 1":
                return NORTH_EAST;
            case "1, 0":
                return EAST;
            case "1, -1":
                return SOUTH_EAST;
            case "0, -1":
                return SOUTH;
            case "-1, -1":
                return SOUTH_WEST;
            case "-1, 0":
                return WEST;
            case "-1, 1":
                return NORTH_WEST;
            default:
                throw new IllegalArgumentException("ungültiges Parameter paar");
        }
    }

    /**
     * gibt die entgegengesetzte Richtung zurück
     * @return die entgegengesetzte Richtung
     */
    public Direction opposite(){ // gibt die entgegengesetzte Richtung zurück
        return fromD(-dx, -dy);
    }

    /**
     * gibt zufällig eine Richtung zurück
     * @return zufällige Richtung
     */
    public static Direction random4(){ // gibt zufällig eine Richtung zurück
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
