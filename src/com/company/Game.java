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

    public char[][] getTableArray() {
        return tableArray;
    }

    private char[][] tableArray;

    public Game() {
        this(3);
    }

    public Game(int n) {
        tableArray = new char[n][n];

        for (var row : tableArray)
            Arrays.fill(row, ' ');

        turn = X;
    }

    public Game(char[][] Array) {
        tableArray = Array;
    }

    public byte checkTable() {

        int counter_forX = 0;
        int counter_forO = 0;

        for (int i = 0; i < tableArray.length; i++) {

            for (int j = 0; j < tableArray[0].length; j++) {
                if (tableArray[i][j] == X) counter_forX++;
                if (tableArray[i][j] == O) counter_forO++;
            }

            if (counter_forX == tableArray.length) return 1;
            if (counter_forO == tableArray.length) return 2;
            counter_forX = 0;
            counter_forO = 0;
        }

        for (int j = 0; j < tableArray.length; j++) {

            for (int i = 0; i < tableArray[0].length; i++) {
                if (tableArray[i][j] == X) counter_forX++;
                if (tableArray[i][j] == O) counter_forO++;
            }

            if (counter_forX == tableArray.length) return 1;
            if (counter_forO == tableArray.length) return 2;
            counter_forX = 0;
            counter_forO = 0;
        }

        for (int i = 0; i < tableArray.length; i++) {
            if (tableArray[i][i] == X) counter_forX++;
            if (tableArray[i][i] == O) counter_forO++;
        }
        if (counter_forX == tableArray.length) return 1;
        if (counter_forO == tableArray.length) return 2;
        counter_forX = 0;
        counter_forO = 0;


        for (int i = 0; i < tableArray.length; i++) {
            if (tableArray[i][tableArray.length - i - 1] == X) counter_forX++;
            if (tableArray[i][tableArray.length - i - 1] == O) counter_forO++;
        }
        if (counter_forX == tableArray.length) return 1;
        if (counter_forO == tableArray.length) return 2;

        return (byte) (isPlaying() ? 0 : 3);
    }

    public boolean isPlaying() {
        for (char[] chars : tableArray) {
            for (int j = 0; j < tableArray[0].length; j++) {
                if (chars[j] == Z) return true;
            }
        }
        return false;
    }

    public boolean couldMakeMove(int x, int y) {
        if (tableArray[x - 1][y - 1] != Z) return false;

        tableArray[x - 1][y - 1] = turn;
        changeTurn();
        return true;
    }

    private void changeTurn() {
        turn = turn == X ? O : X;
    }


}
