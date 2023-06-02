package game;

/**
 * @author vincent_m
 */
public enum Direction {
  UP,
  DOWN,
  LEFT,
  RIGHT;

  private Direction opposite;

  static {
    UP.opposite = DOWN;
    DOWN.opposite = UP;
    LEFT.opposite = RIGHT;
    RIGHT.opposite = LEFT;
  }

  public boolean isOpposite(Direction input) {
    return this.opposite == input;
  }
}
