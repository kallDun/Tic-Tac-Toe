package com.company;

import java.util.Scanner;

public class OfflineGame implements IPrint {

    private static final Scanner scanner = new Scanner(System.in);
    private static Game ticTacToe;

    public OfflineGame(){
        do {
            System.out.println("Write count of blocks in one row / col.");
            int count = scanner.nextInt();
            ticTacToe = new Game(count);
            printShotInConsole(ticTacToe.getTable());

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
                printShotInConsole(ticTacToe.getTable());

            } while (checking == 0);

            System.out.println(checking == 1 ? "Winner is 'X'!" : checking == 2 ? "Winner is 'O'!" : "It is draw!");
            System.out.println("\nWrite 'restart' to restart the game.");

        } while (scanner.next().compareTo("restart") == 0);
    }


}
