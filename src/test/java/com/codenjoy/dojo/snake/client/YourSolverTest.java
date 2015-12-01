package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.RandomDice;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by oleksandr.baglai on 01.12.2015.
 */
public class YourSolverTest {
    @Test
    public void test() {
        assertB("☼☼☼☼☼☼" +
                "☼ ╓  ☼" +
                "☼ ▼  ☼" +
                "☼    ☼" +
                "☼☺ ☻ ☼" +
                "☼☼☼☼☼☼", "DOWN");

        assertB("☼☼☼☼☼☼" +
                "☼ ▲  ☼" +
                "☼ ╙  ☼" +
                "☼    ☼" +
                "☼☺ ☻ ☼" +
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