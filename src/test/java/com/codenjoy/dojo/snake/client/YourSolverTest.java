package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.RandomDice;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by oleksandr.baglai on 01.12.2015.
 */
public class YourSolverTest {
    @Test
    public void testSameDirection() {
        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼ ▼  ☼" +
                "☼    ☼" +
                "☼ ☺  ☼" +
                "☼☼☼☼☼☼", "DOWN");

        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼    ☼" +
                "☼► ☺ ☼" +
                "☼    ☼" +
                "☼☼☼☼☼☼", "RIGHT");

        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼ ☺  ☼" +
                "☼    ☼" +
                "☼ ▲  ☼" +
                "☼☼☼☼☼☼", "UP");

        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼☺  ◄☼" +
                "☼    ☼" +
                "☼    ☼" +
                "☼☼☼☼☼☼", "LEFT");

    }

    @Test
    public void testEatMeHorizontalMiddle() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼ ☺ ═══════►  ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "DOWN");
    }

    @Test
    public void testEatMeHorizontalUpBreak() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼ ☺ ═══════►  ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "DOWN");
    }

    @Test
    public void testEatMeHorizontalDownBreak() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼ ☺ ═══════►  ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "UP");
    }

    @Test
    public void testEatMeVerticalMiddle() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼        ☺    ☼" +
                "☼        ║    ☼" +
                "☼        ║    ☼" +
                "☼        ║    ☼" +
                "☼        ║    ☼" +
                "☼        ▼    ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "RIGHT");
    }

    @Test
    public void testEatMeVerticalRightBreak() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼            ☺☼" +
                "☼            ║☼" +
                "☼            ║☼" +
                "☼            ║☼" +
                "☼            ║☼" +
                "☼            ▼☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "LEFT");
    }

    @Test
    public void testEatMeVerticalLeftBreak() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼☺            ☼" +
                "☼║            ☼" +
                "☼║            ☼" +
                "☼║            ☼" +
                "☼║            ☼" +
                "☼▼            ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "RIGHT");
    }

    @Test
    public void testVerticalBeforeBreakDown() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼    ☺        ☼" +
                "☼║            ☼" +
                "☼║            ☼" +
                "☼║            ☼" +
                "☼║            ☼" +
                "☼▼            ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "RIGHT");
    }

    @Test
    public void testVerticalBeforeBreakUp() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼▲             ☼" +
                "☼║            ☼" +
                "☼║            ☼" +
                "☼║            ☼" +
                "☼║            ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼           ☺ ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "RIGHT");
    }

    @Test
    public void testHorizontalBeforeBreakRight() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼ ☺           ☼" +
                "☼   ═════════►☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "UP");
    }


    @Test
    public void testHorizontalBeforeBreakLeft() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼◄═════════   ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼     ☺       ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "DOWN");
    }

    @Test
    public void testNotSameDirection() {
        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼ ►  ☼" +
                "☼    ☼" +
                "☼ ☺  ☼" +
                "☼☼☼☼☼☼", "DOWN");

        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼    ☼" +
                "☼▼ ☺ ☼" +
                "☼    ☼" +
                "☼☼☼☼☼☼", "RIGHT");

        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼ ☺  ☼" +
                "☼    ☼" +
                "☼ ►  ☼" +
                "☼☼☼☼☼☼", "UP");

        assertB("☼☼☼☼☼☼" +
                "☼    ☼" +
                "☼☺  ▲☼" +
                "☼    ☼" +
                "☼    ☼" +
                "☼☼☼☼☼☼", "LEFT");

    }

    private void assertB(String boardString, String expected) {
        // given
        YourSolver solver = new YourSolver(new RandomDice());

        // when
        String direction = solver.get((Board)new Board().forString(
                boardString));

        // then
        assertEquals(expected, direction);
    }
}
