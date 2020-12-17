package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static Game ticTacToe;

    public static void main(String[] args) {

        try {
            OnlineGame onlineGame = new OnlineGame();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        OfflineGame offlineGame = new OfflineGame();
    }


}