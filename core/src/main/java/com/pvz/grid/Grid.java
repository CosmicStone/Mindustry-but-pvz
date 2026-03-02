package com.pvz.grid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.pvz.entity.EntityFactory;

import javax.swing.text.html.parser.Entity;

public class Grid {
    public static class Cell {
        public int row, col;
        public Rectangle rect;
        public Array<Entity> turrets;    //替换类

        public Cell(int row, int col, float x, float y, float width, float height) {
            this.row = row;
            this.col = col;
            rect = new Rectangle(x, y, width, height);
            turrets = new Array<>();
        }

        public boolean isPlantable() {
            return turrets.isEmpty();
        }

        public Rectangle getBounds() {
            return new Rectangle(rect);
        }
    }

    private int rows, cols;
    private float cellWidth, cellHeight;
    private float startX, startY;
    private Cell[][] cells;
    private Texture mapTexture;

    public Grid(int rows, int cols, float startX, float startY, float cellWidth, float cellHeight, Texture mapTexture) {
        this.rows = rows;
        this.cols = cols;
        this.startX = startX;
        this.startY = startY;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.mapTexture = mapTexture;
        initCells();
    }

    private void initCells() {
        cells = new Cell[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                float x = startX + c * cellWidth;
                float y = startY + r * cellHeight;  // 从下往上递增
                cells[r][c] = new Cell(r, c, x, y, cellWidth, cellHeight);
            }
        }
    }

    /** 根据世界坐标获取格子，若坐标在网格外则返回 null */
    public Cell getCellAt(float worldX, float worldY) {
        if (worldX < startX || worldX > startX + cols * cellWidth ||
            worldY < startY || worldY > startY + rows * cellHeight) {
            return null;
        }
        int col = (int) ((worldX - startX) / cellWidth);
        int row = (int) ((worldY - startY) / cellHeight);
        // 防止边界误差导致越界
        col = Math.min(col, cols - 1);
        row = Math.min(row, rows - 1);
        return cells[row][col];
    }

    public Cell getCellAt(int row, int col) {
        if (row < 0 || col < 0 || row >= rows || col >= cols) return null;
        return cells[row][col];
    }

    /** 在指定格子放置植物（如果可种植） */
    public boolean placeEntity(int row, int col, EntityFactory factory, String entityId) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) return false;
        Cell cell = cells[row][col];
        if (cell.isPlantable()) {
            //Entity entity = factory.create(entityId, cell.getBounds().x, cell.getBounds().y);
            //cell.turrets.add(entity);
            return true;
        }
        return false;
    }

    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {

        if (mapTexture != null) {
            batch.begin();
            batch.draw(mapTexture, startX, startY);
            batch.end();
        }

        // 绘制格子背景（可以用半透明矩形，或根据草地图片绘制）
        // 这里仅示例绘制网格线（调试用）
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setColor(Color.GREEN);
        for (int r = 0; r <= rows; r++) {
            float y = startY + r * cellHeight;
            shapeRenderer.line(startX, y, startX + cols * cellWidth, y);
        }
        for (int c = 0; c <= cols; c++) {
            float x = startX + c * cellWidth;
            shapeRenderer.line(x, startY, x, startY + rows * cellHeight);
        }

    }
}
