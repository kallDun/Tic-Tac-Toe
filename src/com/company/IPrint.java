package com.company;

import java.io.PrintStream;

public interface IPrint {

    /*default String stringShotInConsole(char[][] table) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < table.length; i++) {
            stringBuilder.append(stringSingleLine(table));
            stringBuilder.append("\n");

            for (int j = 0; j < table[0].length; j++) {
                stringBuilder.append(" | ");
                stringBuilder.append(table[i][j] == Game.Z ? " " : table[i][j] == Game.X ? "X" : "O");
                if (j == table[0].length - 1) stringBuilder.append(" | ");
            }
            if (i == table.length - 1) stringBuilder.append(stringSingleLine(table));
        }
        stringBuilder.append("\n");

        return String.valueOf(stringBuilder);
    }

    default String stringSingleLine(char[][] table) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\n");
        for (int i = 0; i < table.length; i++) {
            stringBuilder.append(i == 0 ? " |---" : i != table.length - 1 ? "+---" : "+---|");
        }

        return String.valueOf(stringBuilder);
    }*/


    default void printShotInConsole(char[][] table) {
        for (int i = 0; i < table.length; i++) {
            printSingleLineInConsole(table);
            System.out.println();
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(" | ");
                System.out.print(table[i][j] == Game.Z ? " " : table[i][j] == Game.X ? "X" : "O");
                if (j == table[0].length - 1) System.out.print(" | ");
            }
            if (i == table.length - 1) printSingleLineInConsole(table);
        }
        System.out.println();
    }

    default void printSingleLineInConsole(char[][] table) {
        System.out.println();
        for (int i = 0; i < table.length; i++) {
            System.out.print(i == 0 ? " |---" : i != table.length - 1 ? "+---" : "+---|");
        }
    }

    default void printShot_ForPlayer(PrintStream player, char[][] table){
        for (int i = 0; i < table.length; i++) {
            printSingleLine_ForPlayer(player, table);
            player.println();
            for (int j = 0; j < table[0].length; j++) {
                player.print(" | ");
                player.print(table[i][j] == Game.Z ? " " : table[i][j] == Game.X ? "X" : "O");
                if (j == table[0].length - 1) player.print(" | ");
            }
            if (i == table.length - 1) printSingleLine_ForPlayer(player, table);
        }
        player.println();
    }

    default void printSingleLine_ForPlayer(PrintStream player, char[][] table){
        player.println();
        for (int i = 0; i < table.length; i++) {
            player.print(i == 0 ? " |---" : i != table.length - 1 ? "+---" : "+---|");
        }
    }
}
