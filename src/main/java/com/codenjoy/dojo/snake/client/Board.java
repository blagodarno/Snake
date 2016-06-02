package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.client.*;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.snake.model.Elements;

import java.util.*;

/**
 * User: oleksandr.baglai
 * Date: 10/2/12
 * Time: 12:07 AM
 */
public class Board extends AbstractBoard<Elements> {

    @Override
    public Elements valueOf(char ch) {
        return Elements.valueOf(ch);
    }

    public List<Point> getApples() {
        return get(Elements.GOOD_APPLE);
    }

    public Direction getSnakeDirection() {
        Point head = getHead();
        if (isAt(head.getX(), head.getY(), Elements.HEAD_LEFT)) {
            return Direction.LEFT;
        } else if (isAt(head.getX(), head.getY(), Elements.HEAD_RIGHT)) {
            return Direction.RIGHT;
        } else if (isAt(head.getX(), head.getY(), Elements.HEAD_UP)) {
            return Direction.UP;
        } else {
            return Direction.DOWN;
        }
    }

    public Point getHead() {
        List<Point> result = get(
                Elements.HEAD_UP,
                Elements.HEAD_DOWN,
                Elements.HEAD_LEFT,
                Elements.HEAD_RIGHT);
        return result.get(0);
    }

    public int getHeadIndex() {
        List<Point> result = get(
                Elements.HEAD_UP,
                Elements.HEAD_DOWN,
                Elements.HEAD_LEFT,
                Elements.HEAD_RIGHT);
        return ((result.get(0).getX()+10)*100)+result.get(0).getY()+10;
    }

    public int getTailIndex() {
        List<Point> result = get(
                Elements.TAIL_END_DOWN,
                Elements.TAIL_END_UP,
                Elements.TAIL_END_LEFT,
                Elements.TAIL_END_RIGHT);
        return ((result.get(0).getX()+10)*100)+result.get(0).getY()+10;
    }

    public int getAppleIndex() {
        List<Point> result = get(Elements.GOOD_APPLE);
        return ((result.get(0).getX()+10)*100)+result.get(0).getY()+10;
    }

    public List<Point> getBarriers() {
        List<Point> result = getSnake();
        result.addAll(getStones());
        result.addAll(getWalls());
        return result;
    }

    public List<Point> getSnake() {
        List<Point> result = get(
                Elements.TAIL_END_DOWN,
                Elements.TAIL_END_LEFT,
                Elements.TAIL_END_UP,
                Elements.TAIL_END_RIGHT,
                Elements.TAIL_HORIZONTAL,
                Elements.TAIL_VERTICAL,
                Elements.TAIL_LEFT_DOWN,
                Elements.TAIL_LEFT_UP,
                Elements.TAIL_RIGHT_DOWN,
                Elements.TAIL_RIGHT_UP);
        result.add(0, getHead());
        return result;
    }

    @Override
    public String toString() {
        return String.format("Board:\n%s\n" +
            "Apple at: %s\n" +
            "Stones at: %s\n" +
            "Head at: %s\n" +
            "Snake at: %s\n" +
            "Current direction: %s",
                boardAsString(),
                getApples(),
                getStones(),
                getHead(),
                getSnake(),
                getSnakeDirection());
    }

    public List<Point> getStones() {
        return get(Elements.BAD_APPLE);
    }

    public List<Point> getWalls() {
        return get(Elements.BREAK);
    }

    public Point getTail() {
        for (int y = 1; y < field.length - 1; y++) {
            for (int x = 1; x < field.length - 1; x++) {
                char ch = field[x][y];
                if (ch == Elements.TAIL_END_DOWN.ch() ||
                        ch == Elements.TAIL_END_UP.ch() ||
                        ch == Elements.TAIL_END_RIGHT.ch() ||
                        ch == Elements.TAIL_END_LEFT.ch()) {
                    return new PointImpl(x, y);
                }
            }
        }
        return null;
    }

    public Point nextHopTailPoint() {
        Point tail = getTail();
        if (isAt(tail.getX(), tail.getY(), Elements.TAIL_END_DOWN)) {
            return new PointImpl(tail.getX(), tail.getY()-1);
        } else if (isAt(tail.getX(), tail.getY(), Elements.TAIL_END_UP)) {
            return new PointImpl(tail.getX(), tail.getY()+1);
        } else if (isAt(tail.getX(), tail.getY(), Elements.TAIL_END_LEFT)) {
            return new PointImpl(tail.getX()+1, tail.getY());
        } else {
            return new PointImpl(tail.getX()-1, tail.getY()-1);
        }
    }
}