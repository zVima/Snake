package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author vincent_m
 */
public class GameBoard extends JPanel {

  public static final int WIDTH = 800;
  public static final int HEIGHT = 800;
  public static final int CELL_SIZE = 40;
  public static final int ROWS = WIDTH / CELL_SIZE;
  public static final int COLS = HEIGHT / CELL_SIZE;

  private final Snake snake;
  private final Food food;

  public GameBoard(Snake snake, Food food) {
    setPreferredSize(new Dimension(800, 800));
    setBackground(Color.BLACK);

    this.snake = snake;
    this.food = food;
  }

  @Override
  protected void paintComponent(final Graphics g) {
    super.paintComponent(g);

    drawSnake(g);

    drawFood(g);
  }

  private void drawSnake(Graphics g) {
    Color headColor = new Color(0, 255, 0);
    Color endColor = new Color(0, 100, 0);

    List<Coordinate> snakeBody = snake.getSnakeBody();
    for (int i = 0; i < snakeBody.size(); i++) {
      float t = (float) i / snakeBody.size();

      int red = clamp((int) lerp(headColor.getRed(), endColor.getRed(), t), 0, 255);
      int green = clamp((int) lerp(headColor.getGreen(), endColor.getGreen(), t), 0, 255);
      int blue = clamp((int) lerp(headColor.getBlue(), endColor.getBlue(), t), 0, 255);

      Color bodyColor = new Color(red, green, blue);
      Coordinate segment = snakeBody.get(i);

      int x = segment.getCol() * CELL_SIZE;
      int y = segment.getRow() * CELL_SIZE;

      g.setColor(bodyColor);
      g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
    }
  }

  private void drawFood(Graphics g) {
    int x = food.getCoordinate().getCol() * CELL_SIZE;
    int y = food.getCoordinate().getRow() * CELL_SIZE;

    g.setColor(Color.RED);
    g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
  }

  private float lerp(float start, float end, float t) {
    if (start <= end) {
      return start + (end - start) * t;
    } else {
      return start - (start - end) * t;
    }
  }

  private int clamp(int value, int min, int max) {
    return Math.max(min, Math.min(value, max));
  }

}
