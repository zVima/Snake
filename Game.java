package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * @author vincent_m
 */
public class Game implements KeyListener {

  private Snake snake;
  private Food food;

  private static int reward;

  public void startGame() {
    JFrame frame = new JFrame("Snake");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setResizable(false);

    snake = new Snake(Direction.RIGHT);
    food = new Food();
    GameBoard gameBoard = new GameBoard(snake, food);
    gameBoard.addKeyListener(this);
    gameBoard.setFocusable(true);
    gameBoard.requestFocus();
    frame.add(gameBoard);

    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    while (true) {
      gameLoop(gameBoard);
    }
  }

  private void gameLoop(GameBoard gameBoard) {
    reward = 0;
    boolean hasCollided = false;
    while (!hasCollided) {
      gameBoard.repaint();

      hasCollided = updateGame();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private boolean updateGame() {
    snake.move();

    if (snake.checkCollision()) {
      System.out.println("COLLISION");
      reward -= 5;
      return true;
    }

    Coordinate snakeHead = snake.getHead();
    Coordinate foodPos = food.getCoordinate();
    if (foodPos.getRow() == snakeHead.getRow() && foodPos.getCol() == snakeHead.getCol()) {
      snake.grow();
      food.spawnNewFood(snake.getSnakeBody());
      reward++;
      System.out.println("EATEN " + reward);
    }

    return false;
  }

  @Override
  public void keyTyped(final KeyEvent e) {

  }

  @Override
  public void keyPressed(final KeyEvent e) {
    int keyCode = e.getKeyCode();

    switch (keyCode) {
      case KeyEvent.VK_UP -> snake.setDirection(Direction.UP);
      case KeyEvent.VK_DOWN -> snake.setDirection(Direction.DOWN);
      case KeyEvent.VK_LEFT -> snake.setDirection(Direction.LEFT);
      case KeyEvent.VK_RIGHT -> snake.setDirection(Direction.RIGHT);
    }
  }

  @Override
  public void keyReleased(final KeyEvent e) {

  }

}
