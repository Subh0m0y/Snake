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

/**
 * A simple Point class to store two coordinates.
 *
 * @author Subhomoy Haldar
 * @version 2016.12.17
 */
public class Point {
    private final int x;    // The X coordinate
    private final int y;    // The Y coordinate

    /**
     * The package-visible constructor. Not meant to be used outside the package.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return The X coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * @return The Y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * @param dx The change in x.
     * @param dy The change in y.
     * @return A new Point which is the result of translation of this point.
     */
    public Point translate(int dx, int dy) {
        return new Point(x + dx, y + dy);
    }

    /**
     * @param other The "other" point to compare against.
     * @return {@code true} if the other Object is an instance of Point and
     * has the same coordinates.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Point)) return false;
        Point point = (Point) other;
        return x == point.x & y == point.y;
    }

    public String toString() {
        return x + ", " + y;
    }
}
