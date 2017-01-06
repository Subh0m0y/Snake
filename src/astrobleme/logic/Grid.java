/*
 * The MIT License (MIT)
 * Copyright (c) 2016-2017 Subhomoy Haldar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package astrobleme.logic;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * The positional system for the game. This grid will be rendered in the Canvas.
 *
 * @author Subhomoy Haldar
 * @version 2016.12.17
 */
public class Grid {
    /**
     * The side length of each square point in the grid.
     */
    public static final int SIZE = 10;
    public static final Color COLOR = new Color(0.1, 0.1, 0.1, 1);

    private final int cols;     // The number of columns
    private final int rows;     // The number of rows

    private Snake snake;
    private Food food;

    public Grid(final double width, final double height) {
        rows = (int) width / SIZE;
        cols = (int) height / SIZE;

        // initialize the snake at the centre of the screen
        snake = new Snake(this, new Point(rows / 2, cols / 2));

        // put the food at a random location
        food = new Food(getRandomPoint());
    }

    Point wrap(Point point) {
        int x = point.getX();
        int y = point.getY();
        if (x >= rows) x = 0;
        if (y >= cols) y = 0;
        if (x < 0) x = rows - 1;
        if (y < 0) y = cols - 1;
        return new Point(x, y);
    }

    private Point getRandomPoint() {
        Random random = new Random();
        Point point;
        do {
            point = new Point(random.nextInt(rows), random.nextInt(cols));
        } while (snake.getPoints().contains(point));
        return point;
    }

    /**
     * This method is called in every cycle of execution.
     */
    void update() {
        if (food.getPoint().equals(snake.getHead())) {
            snake.extend();
            food.setPoint(getRandomPoint());
        } else {
            snake.move();
        }
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public double getWidth() {
        return rows * SIZE;
    }

    public double getHeight() {
        return cols * SIZE;
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }
}
