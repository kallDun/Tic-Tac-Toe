package com.company;

import java.util.Arrays;

public class Game {

    public static final char Z = ' ';
    public static final char X = 'X';
    public static final char O = 'O';

    private char turn;

    public char getTurn() {
        return turn;
    }

    public char[][] getTable() {
        return table;
    }

    private char[][] table;

    public Game() {
        this(3);
    }

    public Game(int n) {
        table = new char[n][n];

        for (var row : table)
            Arrays.fill(row, ' ');

        turn = X;
    }

    public Game(char[][] Array) {
        table = Array;
    }

    public byte checkTable() {

        int counter_forX = 0;
        int counter_forO = 0;

        for (int i = 0; i < table.length; i++) {

            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j] == X) counter_forX++;
                if (table[i][j] == O) counter_forO++;
            }

            if (counter_forX == table.length) return 1;
            if (counter_forO == table.length) return 2;
            counter_forX = 0;
            counter_forO = 0;
        }

        for (int j = 0; j < table.length; j++) {

            for (int i = 0; i < table[0].length; i++) {
                if (table[i][j] == X) counter_forX++;
                if (table[i][j] == O) counter_forO++;
            }

            if (counter_forX == table.length) return 1;
            if (counter_forO == table.length) return 2;
            counter_forX = 0;
            counter_forO = 0;
        }

        for (int i = 0; i < table.length; i++) {
            if (table[i][i] == X) counter_forX++;
            if (table[i][i] == O) counter_forO++;
        }
        if (counter_forX == table.length) return 1;
        if (counter_forO == table.length) return 2;
        counter_forX = 0;
        counter_forO = 0;


        for (int i = 0; i < table.length; i++) {
            if (table[i][table.length - i - 1] == X) counter_forX++;
            if (table[i][table.length - i - 1] == O) counter_forO++;
        }
        if (counter_forX == table.length) return 1;
        if (counter_forO == table.length) return 2;

        return (byte) (isPlaying() ? 0 : 3);
    }

    public boolean isPlaying() {
        for (char[] chars : table) {
            for (int j = 0; j < table[0].length; j++) {
                if (chars[j] == Z) return true;
            }
        }
        return false;
    }

    public boolean couldMakeMove(int x, int y) {
        if (table[x - 1][y - 1] != Z) return false;

        table[x - 1][y - 1] = turn;
        changeTurn();
        return true;
    }

    private void changeTurn() {
        turn = turn == X ? O : X;
    }


}
