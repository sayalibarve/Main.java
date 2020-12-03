package de.unikl.seda.snake.gui.snake;

import java.awt.Color;
import java.awt.Graphics2D;

public class SnakeSceneDrawer {
    private final int levelWidth;
    private final int levelHeight;
    private final int levelOffsetY;
    private final int levelTilesCountX;
    private final int levelTilesCountY;
    private int levelTileWidth;
    private int levelTileHeight;

    public SnakeSceneDrawer(int levelWidth, int levelHeight, int tileSquareSize, int offsetY) {
        this.levelTileWidth = tileSquareSize;
        this.levelTileHeight = tileSquareSize;
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        this.levelOffsetY = offsetY;
        this.levelTilesCountX = levelWidth / this.levelTileWidth;
        this.levelTilesCountY = levelHeight / this.levelTileHeight;
    }

    public int getLevelTilesCountX() {
        return this.levelTilesCountX;
    }

    public int getLevelTilesCountY() {
        return this.levelTilesCountY;
    }

    public void drawLevel(Graphics2D graphics, int highlightedTileX, int highlightedTileY, boolean[][] specialHighlightedMap) {
        graphics.setColor(Color.BLACK);

        for(int tileY = 0; tileY < this.levelTilesCountY; ++tileY) {
            for(int tileX = 0; tileX < this.levelTilesCountX; ++tileX) {
                int x = tileX * this.levelTileWidth;
                int y = this.levelOffsetY + tileY * this.levelTileHeight;
                graphics.setColor(Color.BLACK);
                graphics.drawRect(x, y, this.levelTileWidth, this.levelTileHeight);
                if (specialHighlightedMap[tileX][tileY]) {
                    graphics.setColor(Color.RED);
                    graphics.fillRect(x, y, this.levelTileWidth, this.levelTileHeight);
                } else if (highlightedTileX == tileX && highlightedTileY == tileY) {
                    graphics.setColor(Color.YELLOW);
                    graphics.fillRect(x, y, this.levelTileWidth, this.levelTileHeight);
                }

                graphics.setColor(Color.BLACK);
            }
        }

    }

    public void drawInfo(Graphics2D graphics, String playerName, int points) {
        graphics.setColor(Color.BLACK);
        graphics.drawLine(0, this.levelOffsetY, this.levelWidth, this.levelOffsetY);
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, this.levelWidth, this.levelOffsetY);
        graphics.setColor(Color.GREEN);
        graphics.drawString("Player: " + playerName, 20, 18);
        graphics.drawString("Points: " + points, this.levelWidth - 110, 18);
    }
}
