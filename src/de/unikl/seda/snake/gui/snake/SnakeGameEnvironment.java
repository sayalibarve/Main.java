package de.unikl.seda.snake.gui.snake;

import de.unikl.seda.snake.gui.tools.GameEnvironment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGameEnvironment extends GameEnvironment {
    private static final int gameInfoBannerHeight = 25;
    private final SnakeSceneDrawer drawer;
    private final String playerName;
    private int points = 0;
    private boolean[][] highlightedTiles;
    private SnakeGameEnvironment.AutoMovementThread autoMovementThread;
    int selectedTileX = 0;
    int selectedTileY = 0;
    private int count = 0;

    public SnakeGameEnvironment(String playerName, int height, int width, int squareSize) {
        // sets the size of the snake environment
        //super(height, width + gameInfoBannerHeight);
        super(width,height +25);
        this.playerName = playerName;
        this.drawer = new SnakeSceneDrawer(width, height, squareSize, 25);
        this.autoMovementThread = new SnakeGameEnvironment.AutoMovementThread();
        this.highlightedTiles = new boolean[this.drawer.getLevelTilesCountX()][this.drawer.getLevelTilesCountY()];
        this.autoMovementThread.start();
    }

    @Override
    protected void handleKeypressUp() {
        System.out.println("Up");
        --this.selectedTileY;
        ++this.points;
        this.autoMovementThread.setCurrentMovementDirection(SnakeGameEnvironment.MovementDirection.UP);
        if (this.selectedTileY < 0) {
            this.selectedTileY = this.drawer.getLevelTilesCountY() - 1;
        }
    }

    @Override
    protected void handleKeypressDown() {
        System.out.println("Down");
        ++this.selectedTileY;
        ++this.points;
        this.autoMovementThread.setCurrentMovementDirection(SnakeGameEnvironment.MovementDirection.DOWN);
        if (this.selectedTileY >= this.drawer.getLevelTilesCountY()) {
            this.selectedTileY = 0;
        }
    }

    @Override
    protected void handleKeypressLeft() {
        System.out.println("Left");
        --this.selectedTileX;
        ++this.points;
        this.autoMovementThread.setCurrentMovementDirection(SnakeGameEnvironment.MovementDirection.LEFT);
        if (this.selectedTileX < 0) {
            this.selectedTileX = this.drawer.getLevelTilesCountX() - 1;
        }
    }

    @Override
    protected void handleKeypressRight() {
        System.out.println("Right");
        ++this.selectedTileX;
        ++this.points;
        this.autoMovementThread.setCurrentMovementDirection(SnakeGameEnvironment.MovementDirection.RIGHT);
        if (this.selectedTileX >= this.drawer.getLevelTilesCountX()) {
            this.selectedTileX = 0;
        }
    }

    @Override
    protected void handleReturnPress() {
        System.out.println("RETURN");
        this.highlightedTiles[this.selectedTileX][this.selectedTileY] = !this.highlightedTiles[this.selectedTileX][this.selectedTileY];
        System.out.println("Highlight tile: " + this.selectedTileX + "x" + this.selectedTileY + " -> " + this.highlightedTiles[this.selectedTileX][this.selectedTileY]);
    }

    @Override
    protected void drawSnakeEnvironment(Graphics2D graphics) {
        this.drawer.drawLevel(graphics, this.selectedTileX, this.selectedTileY, this.highlightedTiles);
        this.drawer.drawInfos(graphics, this.playerName, this.points);
        this.drawer.drawSnake(graphics);
        this.drawer.drawFood(graphics);
    }

    private class AutoMovementThread extends Thread {
        private final SnakeGameEnvironment gameEnvironment = SnakeGameEnvironment.this;
        private SnakeGameEnvironment.MovementDirection currentMovementDirection;

        public AutoMovementThread() {
            this.setDaemon(true);
        }

        public void setCurrentMovementDirection(SnakeGameEnvironment.MovementDirection currentMovementDirection) {
            this.currentMovementDirection = currentMovementDirection;
        }

        public SnakeGameEnvironment.MovementDirection getCurrentMovementDirection() {
            return this.currentMovementDirection;
        }

        public void run() {
            while(true) {
                if (this.currentMovementDirection != null) {
                    switch(this.currentMovementDirection) {
                        case DOWN:
                            this.gameEnvironment.handleKeypressDown();
                            break;
                        case UP:
                            this.gameEnvironment.handleKeypressUp();
                            break;
                        case LEFT:
                            this.gameEnvironment.handleKeypressLeft();
                            break;
                        case RIGHT:
                            this.gameEnvironment.handleKeypressRight();
                    }
                }
                try {
                    Thread.sleep(250L);
                } catch (InterruptedException var2) {
                    var2.printStackTrace();
                }
            }
        }
    }

    private static enum MovementDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT;

        private MovementDirection() {
        }
    }
}
