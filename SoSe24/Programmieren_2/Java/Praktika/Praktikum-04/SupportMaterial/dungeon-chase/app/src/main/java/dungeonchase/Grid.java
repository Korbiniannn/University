package dungeonchase;

public class Grid {

  public static final int GRID_WIDTH = 16;
  public static final int GRID_HEIGHT = 16;

  private Character[][] grid; // ### Character

  private int playerX;

  private int playerY;

  private Player player; // ### Player

  private int currentIteration;


  private TreasureChest treasureChest; // ### TreasureChest

  public Grid(){
    currentIteration = 0;
    initGrid();
  }


  @SuppressWarnings("unchecked")
  private void initGrid(){
    // Character
    grid = Helper.fillGrid(
        10, // Anzahl der zu erzeugenden Gegner
          () -> new Ogre(Helper.generateName("Ogre"))          // ### Ogre
        , () -> new Wall()                                     // ### Wall
        , () -> new BlackKnight(Helper.generateName("Knight")) // ### BlackKnight
        , () -> new Bomb()                                     // ### Bomb
        // , () -> new Bat(Helper.generateName("Bat"))            // ### Bat
        // , () -> new Blob(Helper.generateName("Blob"), 0.05)    // ### Blob
        // , () -> new Fire(0.1)                                  // ### Fire
        );

    //Player
    playerX = 1;
    playerY = GRID_HEIGHT/2;
    player = new Player();
    grid[playerX][playerY] = player;

    //TreasureChest
    treasureChest = new TreasureChest();
    grid[GRID_WIDTH-2][GRID_HEIGHT/2] = treasureChest;


  }

  //Player
  public Player getPlayer(){
    return player;
  }
  

  public int getPlayerX(){
    return playerX;
  }

  public int getPlayerY(){
    return playerY;
  }

  // TrasureChest
  public TreasureChest getTreasureChest(){
    return treasureChest;
  }


  //Character
  public Character get(int x, int y){
    if (x < 0 || x >= GRID_WIDTH)
      throw new IllegalArgumentException("x outside of range");

    if (y < 0 || y >= GRID_HEIGHT)
      throw new IllegalArgumentException("y outside of range");

    return this.grid[x][y];
  }

  public void updatePlayer(Direction playerMovement){
    if(playerMovement == null){
      throw new IllegalArgumentException("playerMovement darf nicht null sein");
    }
    player.update(this, playerX, playerY, playerMovement);
    int destinationX = playerX + playerMovement.getDx();
    int destinationY = playerY + playerMovement.getDy();
    if((destinationX >= 0 && destinationX < 16) && (destinationY >= 0 && destinationY < 16)){
      if (grid[destinationX][destinationY] == null){
        grid[playerX][playerY] = null;
        grid[destinationX][destinationY] = player;

        playerX = destinationX;
        playerY = destinationY;
      }
      if(grid[destinationX][destinationY] != null){
        if(grid[destinationX][destinationY].collisionFrom(player)){
          grid[playerX][playerY] = null;
          grid[destinationX][destinationY] = player;

          playerX = destinationX;
          playerY = destinationY;
        }
      }
    }
  }

  public void updateOthers(Direction playerMovement) {
    currentIteration++;

    for (int x = 0; x < GRID_WIDTH; x++) {
      for (int y = 0; y < GRID_HEIGHT; y++) {
        Character character = grid[x][y];

        if (character == null || character == player || character.getLastUpdate() == currentIteration) {
          continue;
        }

        character.setLastUpdate(currentIteration);
        Direction movement = character.update(this, x, y, playerMovement);

        int destinationX = x + movement.getDx();
        int destinationY = y + movement.getDy();

        if ((destinationX >= 0 && destinationX < GRID_WIDTH) && (destinationY >= 0 && destinationY < GRID_HEIGHT)) {
          if (grid[destinationX][destinationY] == null) {
            grid[x][y] = null;
            grid[destinationX][destinationY] = character;
          } else if (grid[destinationX][destinationY].collisionFrom(character)) {
            grid[x][y] = null;
            grid[destinationX][destinationY] = character;
          }
        }

        if (!character.isAlive()) {
          grid[x][y] = null;
        }
      }
    }
  }
}
