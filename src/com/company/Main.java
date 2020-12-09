package com.company;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static Game ticTacToe;

    public static void main(String[] args) {
        do {
            System.out.println("Write count of blocks in one row / col.");
            int count = scanner.nextInt();
            ticTacToe = new Game(count);
            printShot();

            byte checking;
            do {

                boolean flag;
                do {
                    System.out.println("Write coordinates for " + ticTacToe.getTurn());
                    try {
                        int x = scanner.nextInt();
                        try {
                            int y = scanner.nextInt();
                            try {
                                flag = ticTacToe.couldMakeMove(x, y);
                            } catch (Exception e) {
                                System.out.println("Numbers are not bound of array!");
                                flag = false;
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid Y!");
                            flag = false;
                            scanner.next();
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid X!");
                        flag = false;
                        scanner.next();
                    }

                } while (!flag);

                checking = ticTacToe.checkTable();
                printShot();

            } while (checking == 0);

            System.out.println(checking == 1 ? "Winner is 'X'!" : checking == 2 ? "Winner is 'O'!" : "It is draw!");
            System.out.println("\nWrite 'restart' to restart the game.");

        } while (scanner.next().compareTo("restart") == 0);
    }

    private static void printShot() {

        for (int i = 0; i < ticTacToe.getTableArray().length; i++) {
            printSingleLine();
            System.out.println();
            for (int j = 0; j < ticTacToe.getTableArray()[0].length; j++) {
                System.out.print(" | ");
                System.out.print(ticTacToe.getTableArray()[i][j] == Game.Z ? " " : ticTacToe.getTableArray()[i][j] == Game.X ? "X" : "O");
                if (j == ticTacToe.getTableArray()[0].length - 1) System.out.print(" | ");
            }
            if (i == ticTacToe.getTableArray().length - 1) printSingleLine();
        }
        System.out.println();
    }

    private static void printSingleLine() {
        System.out.println();
        for (int i = 0; i < ticTacToe.getTableArray().length; i++) {
            System.out.print(i == 0 ? " |---" : i != ticTacToe.getTableArray().length - 1 ? "+---" : "+---|");
        }
    }

}