package com.company;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void checkTable__forWin_X() {
        var fullTable = new char[3][3];
        for (var row : fullTable) {
            Arrays.fill(row, Game.X);
        }
        Game game = new Game(fullTable);

        assertEquals(1, game.checkTable());

    }

    @Test
    void checkTable__forWin_O() {
        var fullTable = new char[3][3];
        for (var row : fullTable) {
            Arrays.fill(row, Game.O);
        }
        Game game = new Game(fullTable);

        assertEquals(2, game.checkTable());

    }

    @Test
    void checkTable__forDraw() {
        var fullTable = new char[][]{
                {Game.X, Game.O, Game.X},
                {Game.O, Game.O, Game.X},
                {Game.O, Game.X, Game.O}
        };

        Game game = new Game(fullTable);

        assertEquals(3, game.checkTable());

    }

    @Test
    void isPlaying() {
        Game game = new Game(3);
        assertTrue(game.isPlaying());

        var fullTable = new char[3][3];
        for (var row : fullTable) {
            Arrays.fill(row, Game.X);
        }

        game = new Game(fullTable);
        assertFalse(game.isPlaying());
    }

    @Test
    void testCouldMakeMove() {
        Game game = new Game(3);

        assertTrue(game.couldMakeMove(1, 1));
        assertFalse(game.couldMakeMove(1, 1));

    }
}