package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            OnlineGame onlineGame = new OnlineGame();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //OfflineGame offlineGame = new OfflineGame();
    }


}