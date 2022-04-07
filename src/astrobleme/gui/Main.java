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

package astrobleme.gui;

import astrobleme.logic.GameLoop;
import astrobleme.logic.Grid;
import astrobleme.logic.Snake;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * This is the place where the threads are dispatched.
 *
 * @author Subhomoy Haldar
 * @version 2016.12.17
 */
public class Main extends Application {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;

    private GameLoop loop;
    private Grid grid;
    private GraphicsContext context;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        context = canvas.getGraphicsContext2D();

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(e -> {
            Snake snake = grid.getSnake();
            if (loop.isKeyPressed()) {
                if(loop.isRunning()){
                    System.out.println("Pressed ");
                    return;
                }
                else{
                }
            }
            switch (e.getCode()) {
                case UP:
                    snake.setUp();
                    System.out.println("up ");
                    break;
                case DOWN:
                    snake.setDown();
                    System.out.println("down ");
                    break;
                case LEFT:
                    snake.setLeft();
                    System.out.println("left ");
                    break;
                case RIGHT:
                    snake.setRight();
                    System.out.println("right ");
                    break;
                case P:
                    System.out.println("P " + loop.isOver());
                    loop.setKeyUnpressed();
                    if(loop.isRunning()){
                        loop.pause();
                        System.out.println("Pause. ");
                    }

                    else{
                        loop.resume();
                        System.out.println("Resume. ");
                    }
                    break;
                case ENTER:
                    System.out.println("Enter ");
//                    loop.setKeyUnpressed();
                    if (loop.isOver()) {
                        reset();
                        System.out.println("********* Restart ********");
                        (new Thread(loop)).start();
                    }
                    break;
                default: System.out.println("Other ");
            }
            if(loop.isOver()) return;
            else loop.setKeyPressed();
        });

        reset();

        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Snake");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();

        (new Thread(loop)).start();
    }

    private void reset() {
        grid = new Grid(WIDTH, HEIGHT);
        loop = new GameLoop(grid, context);
        Painter.paint(grid, context);
    }
}
