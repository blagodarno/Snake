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
    public void testNotEatSelfBodyHorizontal() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼   ╔═══════╗ ☼" +
                "☼   ║◄══════╝ ☼" +
                "☼   ╙         ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼     ☺       ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "DOWN");
    }

    @Test
    public void testNotEatSelfBodyVertical() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼       ╔══╗  ☼" +
                "☼       ║  ║  ☼" +
                "☼       ▼  ║   ☼" +
                "☼  ════════╝  ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼     ☺       ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "LEFT");
    }

    @Test
    public void testNotEatSelfBodyCircle() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼            ☺☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼         ╘═╗ ☼" +
                "☼           ║ ☼" +
                "☼           ║ ☼" +
                "☼           ║ ☼" +
                "☼      ╔═══►║ ☼" +
                "☼      ╚════╝ ☼" +
                "☼             ☼" +
                "☼           ☻ ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "UP");
    }

    @Test
    public void testNotEatSelfBodyCross() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼        ╓    ☼" +
                "☼        ║    ☼" +
                "☼        ║    ☼" +
                "☼☻       ║  ☺ ☼" +
                "☼       ▲║    ☼" +
                "☼       ╚╝    ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "UP");
    }

    @Test
    public void testNotEatStone() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼        ╓    ☼" +
                "☼        ║    ☼" +
                "☼        ║    ☼" +
                "☼       ☻║  ☺ ☼" +
                "☼       ▲║    ☼" +
                "☼       ╚╝    ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "LEFT");
    }

    @Test
    public void testNotEatSelf2() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼╔╗           ☼" +
                "☼║║           ☼" +
                "☼║║ ☺       ☻ ☼" +
                "☼║║           ☼" +
                "☼║║           ☼" +
                "☼║║           ☼" +
                "☼║╚════╕      ☼" +
                "☼╚════►       ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "RIGHT");
    }

    @Test
    public void testNotEatSelf3() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼             ☼" +
                "☼     ☻       ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼    ☺        ☼" +
                "☼             ☼" +
                "☼             ☼" +
                "☼   ╘══╗      ☼" +
                "☼    ◄╗║      ☼" +
                "☼     ║║      ☼" +
                "☼     ║║      ☼" +
                "☼     ║║      ☼" +
                "☼     ╚╝      ☼" +
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "LEFT");
    }

    @Test
    public void testVerifeAccesstoTailNextStep() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼           ☻ ☼"+
                "☼◄═══════╗    ☼"+
                "☼        ║ ☺  ☼"+
                "☼        ╚═══╗☼"+
                "☼            ║☼"+
                "☼            ║☼"+
                "☼            ║☼"+
                "☼            ║☼"+
                "☼            ║☼"+
                "☼            ║☼"+
                "☼            ║☼"+
                "☼  ╘═════╗   ║☼"+
                "☼        ╚═══╝☼"+
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "DOWN");
    }
    @Test
    public void testVerifeAccesstoTailNextStep2() {
        assertB("☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                "☼ ☺      ▲    ☼"+
                "☼        ╚╗   ☼"+
                "☼       ╓ ║   ☼"+
                "☼       ║ ║   ☼"+
                "☼       ║☻║   ☼"+
                "☼       ╚═╝   ☼"+
                "☼             ☼"+
                "☼             ☼"+
                "☼             ☼"+
                "☼             ☼"+
                "☼             ☼"+
                "☼             ☼"+
                "☼             ☼"+
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼", "LEFT");
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
        YourSolverD solver = new YourSolverD(new RandomDice());

        // when
        String direction = solver.get((Board)new Board().forString(
                boardString));

        // then
        assertEquals(expected, direction);
    }
}
