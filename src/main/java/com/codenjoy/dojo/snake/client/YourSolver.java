package com.codenjoy.dojo.snake.client;


import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.RandomDice;
import com.codenjoy.dojo.snake.model.Elements;

/**
 * User: your name
 */
public class YourSolver implements Solver<Board> {

    private static final String USER_NAME = "blagodarno@gmail.com";

    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;

//        Point point = board.getApples().get(0);
//        point.getX()
//        point.getY()

        char[][] field = board.getField();

        // found hrad of snake
        int snakeHeadX = -1;
        int snakeHeadY = -1;
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field.length; y++) {
                char ch = field[x][y];
                if (ch == Elements.HEAD_DOWN.ch() ||
                    ch == Elements.HEAD_UP.ch() ||
                    ch == Elements.HEAD_LEFT.ch() ||
                    ch == Elements.HEAD_RIGHT.ch())
                {
                    snakeHeadX = x;
                    snakeHeadY = y;
                    break;

                }
            }
            if (snakeHeadX != -1) {
                break;
            }
        }

        // found apple
        int appleX = -1;
        int appleY = -1;
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field.length; y++) {
                char ch = field[x][y];
                if (ch == Elements.GOOD_APPLE.ch()) {
                    appleX = x;
                    appleY = y;
                    break;

                }
            }
            if (appleX != -1) {
                break;
            }
        }

        int dx = snakeHeadX - appleX;
        int dy = snakeHeadY - appleY;

        // don't eat break ( snake in vertical position )
        if (((field[snakeHeadX][snakeHeadY+1]=='☼') && (field[snakeHeadX][snakeHeadY]=='▼')) ||
                ((field[snakeHeadX][snakeHeadY-1]=='☼')&& (field[snakeHeadX][snakeHeadY]=='▲')) ) {
            if (dx<0) {
                return Direction.RIGHT.toString();
            } else {
                return Direction.LEFT.toString();
            }
        }
        // don't eat break ( snake in horizontal position )
        if (((field[snakeHeadX+1][snakeHeadY]=='☼') && (field[snakeHeadX][snakeHeadY]=='►')) ||
                (field[snakeHeadX-1][snakeHeadY]=='☼')&& (field[snakeHeadX][snakeHeadY]=='◄') ) {
            if (dy<0) {
                return Direction.DOWN.toString();
            } else {
                return Direction.UP.toString();
            }
        }


        // don't eat snake-body in circle-move( snake in vertical position )
        if ((((field[snakeHeadX][snakeHeadY+1]=='═') || (field[snakeHeadX][snakeHeadY+1]=='╘') ||
                (field[snakeHeadX][snakeHeadY+1]=='╕') ) && (field[snakeHeadX][snakeHeadY]=='▼')) ||
                (((field[snakeHeadX][snakeHeadY-1]=='═') || (field[snakeHeadX][snakeHeadY+1]=='╘') ||
                        (field[snakeHeadX][snakeHeadY+1]=='╕') ) && (field[snakeHeadX][snakeHeadY]=='▲')) ) {
            int bodyX = 0;
            for (int x = 0; x < field.length; x++) {
                char ch = field[x][snakeHeadY];
                if (ch == Elements.TAIL_VERTICAL.ch()) {
                    bodyX = x;
                    break;
                }
            }
            if (bodyX < snakeHeadX) {
                return Direction.RIGHT.toString();
            } else {
                return Direction.LEFT.toString();
            }
        }
        // don't eat snake-body in circle-move( snake in horizontal position )
        if ((((field[snakeHeadX+1][snakeHeadY]=='║') || (field[snakeHeadX+1][snakeHeadY]=='╙') ||
                (field[snakeHeadX+1][snakeHeadY]=='╓')) && (field[snakeHeadX][snakeHeadY]=='►')) ||
                (((field[snakeHeadX-1][snakeHeadY]=='║') || (field[snakeHeadX+1][snakeHeadY]=='╙') ||
                        (field[snakeHeadX+1][snakeHeadY]=='╓'))&& (field[snakeHeadX][snakeHeadY]=='◄') )) {
            int bodyY = 0;
            for (int y = 0; y < field.length; y++) {
                char ch = field[snakeHeadX][y];
                if (ch == Elements.TAIL_HORIZONTAL.ch()) {
                    bodyY = y;
                    break;
                }
            }
            if (bodyY < snakeHeadY) {
                return Direction.DOWN.toString();
            } else {
                return Direction.UP.toString();
            }
        }



        // don't eat self when apple direct behind ( snake in vertical position )
        if (snakeHeadX == appleX && (((snakeHeadY > appleY) && (field[snakeHeadX][snakeHeadY]=='▼')) ||
                ((snakeHeadY < appleY)&& (field[snakeHeadX][snakeHeadY]=='▲')) )) {
            if (field[snakeHeadX + 1][snakeHeadY] == ' ') {
                return Direction.RIGHT.toString();
            } else {
                return Direction.LEFT.toString();
            }
        }
        // don't eat self when apple direct behind ( snake in horizontal position )
        if (snakeHeadY == appleY && (((snakeHeadX > appleX) && (field[snakeHeadX][snakeHeadY]=='►')) ||
                (snakeHeadX < appleX)&& (field[snakeHeadX][snakeHeadY]=='◄') )) {
            if (field[snakeHeadX][snakeHeadY+1] == ' ') {
                return Direction.DOWN.toString();
            } else {
                return Direction.UP.toString();
            }
        }



        if (dx < 0) {
            return Direction.RIGHT.toString();
        }
        if (dx > 0) {
            return Direction.LEFT.toString();
        }
        if (dy < 0) {
            return Direction.DOWN.toString();
        }
        if (dy > 0) {
            return Direction.UP.toString();
        }



        return Direction.UP.toString();
    }

    public static void main(String[] args) {
        start(USER_NAME, WebSocketRunner.Host.REMOTE);
    }

    public static void start(String name, WebSocketRunner.Host server) {
        try {
            WebSocketRunner.run(server, name,
                    new YourSolver(new RandomDice()),
                    new Board());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
