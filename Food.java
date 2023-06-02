package game;

import java.util.List;
import java.util.Random;

/**
 * @author vincent_m
 */
public class Food {

  private static Coordinate coordinate;
  private final Random random = new Random();

  public Food() {
    int randomRow = random.nextInt(GameBoard.ROWS);
    int randomCol = random.nextInt(GameBoard.COLS);
    coordinate = new Coordinate(randomRow, randomCol);
  }

  public void spawnNewFood(List<Coordinate> snakeBody) {
    boolean validPos = true;

    do {
      int randomRow = random.nextInt(GameBoard.ROWS);
      int randomCol = random.nextInt(GameBoard.COLS);

      for (Coordinate cord : snakeBody) {
        if (cord.getRow() == randomRow && cord.getCol() == randomCol) {
          validPos = false;
          break;
        }
      }

      if (validPos) {
        coordinate.setRow(randomRow);
        coordinate.setCol(randomCol);
      }
    } while (!validPos);
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

}
