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
        , () -> new Blob(Helper.generateName("Blob"), 0.05)    // ### Blob
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

  /**
   * Aktualisiert den Spieler
   * bewegt den Spieler in die angegebene Richtung
   * aber nur, wenn das Ziel leer ist oder der Spieler mit einem Character kollidiert
   * @param playerMovement Bewegung des Spielers
   */
  public void updatePlayer(Direction playerMovement){ // bewegt den Spieler
    if(playerMovement == null){ // wenn playerMovement null ist, wird ein error geworfen
      throw new IllegalArgumentException("playerMovement darf nicht null sein");
    }
    player.update(this, playerX, playerY, playerMovement); // ruft die update Methode des Players auf, um die Bewegungsrichtung zu ermitteln
    int destinationX = playerX + playerMovement.getDx(); // x-Koordinate des Ziels
    int destinationY = playerY + playerMovement.getDy(); // y-Koordinate des Ziels
    if((destinationX >= 0 && destinationX < 16) && (destinationY >= 0 && destinationY < 16)){ // Prüft, ob das Ziel innerhalb des Spielfeldes ist
      if (grid[destinationX][destinationY] == null && grid[destinationX][destinationY] != player){ // wenn das Ziel leer ist, wird der Spieler bewegt
        grid[playerX][playerY] = null; // Spieler wird von der alten Position entfernt
        grid[destinationX][destinationY] = player; // Spieler wird an die neue Position gesetzt

        playerX = destinationX;// x-Koordinate des Spielers wird aktualisiert
        playerY = destinationY;// y-Koordinate des Spielers wird aktualisiert
      }
      if(grid[destinationX][destinationY] != null && grid[destinationX][destinationY] != player){ // wenn das Ziel nicht leer ist
        if(grid[destinationX][destinationY].collisionFrom(player)){ // wenn der Spieler mit dem Character kollidiert
          grid[playerX][playerY] = null; // Spieler wird von der alten Position entfernt
          grid[destinationX][destinationY] = player;// Spieler wird an die neue Position gesetzt

          playerX = destinationX; // x-Koordinate des Spielers wird aktualisiert
          playerY = destinationY; // y-Koordinate des Spielers wird aktualisiert
        }
      }
    }
  }

  /**
   * bewegt alle anderen Charaktere
   * unterscheidet zwischen replizierbaren und nicht replizierbaren Charakteren
   * und filtert tote, bereits aktualisierte, den Player, leere Felder und Charaktere, die sich nicht bewegen aus
   * @param playerMovement Bewegung des Spielers
   */
  public void updateOthers(Direction playerMovement) {
    currentIteration++; // erhöht die aktuelle Iteration

    for (int x = 0; x < GRID_WIDTH; x++) {// für jede x-Koordinate
      for (int y = 0; y < GRID_HEIGHT; y++) {// für jede y-Koordinate
        Character character = grid[x][y];// holt den Character der Position x,y im Spielfeld

        if (character == null || character == player || character.getLastUpdate() == currentIteration) { // wenn der Character null ist, der Spieler ist oder das letzte Update des Characters der aktuellen Iteration entspricht, wird der Character übersprungen
          continue;
        }
        if (!character.isAlive()) { // wenn der Character tot ist
          grid[x][y] = null;// Character wird von der Position x,y entfernt
          continue;
        }

        character.setLastUpdate(currentIteration);// setzt das letzte Update des Characters auf die aktuelle Iteration
        Direction dir; // variable für die Bewegungsrichtung des Replicas
        if (character instanceof Replicable && (dir = ((Replicable) character).replicationDirection()) != Direction.NONE) { // wenn der Character replizierbar ist und die Bewegungsrichtung des Replicas nicht NONE ist
          Character rep = ((Replicable) character).generateReplication();// generiert ein Replica des Characters

          int repDestinationX = x + dir.getDx();// x-Koordinate des Ziels des Replicas
          int repDestinationY = y + dir.getDy();// y-Koordinate des Ziels des Replicas

          if ((repDestinationX >= 0 && repDestinationX < GRID_WIDTH) && (repDestinationY >= 0 && repDestinationY < GRID_HEIGHT)) { // wenn das Ziel innerhalb des Spielfeldes ist
            if (grid[repDestinationX][repDestinationY] == null) { // wenn das Ziel leer ist, wird das Replica an die neue Position gesetzt
              grid[repDestinationX][repDestinationY] = rep;
            } else if (grid[repDestinationX][repDestinationY].collisionFrom(rep)) { // wenn das Ziel nicht leer ist und das Replica den anderen Character tötet, wird das Replica an die neue Position gesetzt
              grid[repDestinationX][repDestinationY] = rep;
            }
          }
        } else { // wenn der Character nicht replizierbar ist
          Direction movement = character.update(this, x, y, playerMovement); // ruft die update Methode des Characters auf, um die Bewegungsrichtung zu ermitteln
          if (movement == Direction.NONE) {
            continue;
          }
          int destinationX = x + movement.getDx(); // x-Koordinate des Ziels
          int destinationY = y + movement.getDy(); // y-Koordinate des Ziels

          if ((destinationX >= 0 && destinationX < GRID_WIDTH) && (destinationY >= 0 && destinationY < GRID_HEIGHT)) { // wenn das Ziel innerhalb des Spielfeldes ist
            if (grid[destinationX][destinationY] == null) { // wenn das Ziel leer ist, wird der Character bewegt

              grid[x][y] = null; // Character wird von der alten Position entfernt
              grid[destinationX][destinationY] = character; // Character wird an die neue Position gesetzt

            } else if (grid[destinationX][destinationY].collisionFrom(character)) {// wenn das Ziel nicht leer ist und der Character den anderen Character tötet

                grid[x][y] = null; // Character wird von der alten Position entfernt
                grid[destinationX][destinationY] = character; // Character wird an die neue Position gesetzt

            }
          }
        }
      }
    }
  }
}
