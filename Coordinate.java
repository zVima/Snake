package game;

/**
 * @author vincent_m
 */
public class Coordinate {

  private int row;
  private int col;

  public Coordinate(final int row, final int col) {
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public void setRow(final int row) {
    this.row = row;
  }

  public int getCol() {
    return col;
  }

  public void setCol(final int col) {
    this.col = col;
  }

}
