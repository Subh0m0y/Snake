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

import astrobleme.gui.Painter;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author Subhomoy Haldar
 * @version 2016.12.17
 */
public class GameLoop implements Runnable {
    public static final int FRAME_RATE = 20;

    private final Grid grid;
    private final GraphicsContext context;
    private float interval;
    private boolean running;
    private boolean stopped;
    public boolean keyIsPressed;

    public GameLoop(final Grid grid, final GraphicsContext context) {
        this.grid = grid;
        this.context = context;
        interval = 1000.0f / FRAME_RATE; // 1000 ms in a second
        running = true;
        stopped = false;
        keyIsPressed = false;
    }

    @Override
    public void run() {
        while (true) {
            // Time the update and paint calls
            if (running);
            else {
//                System.out.println("Pausing...... ");
                try {
                    Thread.sleep((long) (interval));
                } catch (InterruptedException ignore){}
                continue;
            }
            float time = System.currentTimeMillis();

            grid.update();
            Painter.paint(grid, context);

            if (!grid.getSnake().isSafe()) {
                stop();
                Painter.paintResetMessage(context);
//                keyIsPressed = false;
                break;
            }

            time = System.currentTimeMillis() - time;

            // Adjust the timing correctly
            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException ignore) {
                }
            }
            keyIsPressed = false;
//            System.out.println("Running...... ");
        }
    }


    public boolean isKeyPressed() {
        return keyIsPressed;
    }
    public void setKeyUnpressed(){
        keyIsPressed = false;
    }
    public void setKeyPressed() {
        keyIsPressed = true;
    }

    public void resume() {
        running = true;
    }
    public void pause() {
        running = false;
    }
    public boolean isRunning(){
        return running;
    }
    public void stop() {
        stopped = true;
    }

    public boolean isOver() {
        return stopped;
    }
}
