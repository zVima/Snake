package game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author vincent_m
 */
public class Snake {

  private List<Coordinate> snakeBody;
  private Direction direction;

  public Snake(Direction direction) {
    snakeBody = new ArrayList<>();
    snakeBody.add(new Coordinate(GameBoard.ROWS / 2, GameBoard.COLS / 2));
    this.direction = direction;
  }

  public void move() {
    Coordinate head = getHead();

    int newHeadRow = head.getRow();
    int newHeadCol = head.getCol();

    switch (direction) {
      case UP -> newHeadRow--;
      case DOWN -> newHeadRow++;
      case LEFT -> newHeadCol--;
      case RIGHT -> newHeadCol++;
    }

    updatePosition(new Coordinate(newHeadRow, newHeadCol));
  }

  private void updatePosition(Coordinate newSegment) {
    snakeBody.add(0, newSegment);
    snakeBody.remove(snakeBody.size() - 1);
  }

  public boolean checkCollision() {
    Coordinate head = getHead();

    if (head.getRow() == GameBoard.COLS || head.getRow() == -1 ||
        head.getCol() == GameBoard.ROWS || head.getCol() == -1) {
      return true;
    }

    for (Coordinate segment : getBody()) {
      if (segment.getRow() == head.getRow() && segment.getCol() == head.getCol()) {
        return true;
      }
    }

    return false;
  }

  public void grow() {
    Coordinate tail = snakeBody.get(snakeBody.size() - 1);
    snakeBody.add(new Coordinate(tail.getRow(), tail.getCol()));
  }

  public Coordinate getHead() {
    return snakeBody.get(0);
  }

  public List<Coordinate> getBody() {
    return IntStream.range(1, snakeBody.size())
        .mapToObj(i -> snakeBody.get(i))
        .collect(Collectors.toList());
  }

  public List<Coordinate> getSnakeBody() {
    return snakeBody;
  }

  public void setSnakeBody(final List<Coordinate> snakeBody) {
    this.snakeBody = snakeBody;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(final Direction direction) {
    if (!this.direction.isOpposite(direction)) {
      this.direction = direction;
    }
  }

}
