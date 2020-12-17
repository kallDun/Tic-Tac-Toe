package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class OnlineGame implements IPrint {

    private static final int PORT = 5000;

    private final List<Socket> players = new ArrayList<>();
    private final List<String> names = new ArrayList<>();
    private final List<BufferedReader> readerPlayerList = new ArrayList<>();
    private final List<PrintStream> printPlayerList = new ArrayList<>();

    private Game ticTacToe;


    public OnlineGame() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        while (true) {

            for (int i = players.size(); i < 2; i++)
                addClient(serverSocket);

            ticTacToe = new Game(); // initialize new game 3x3

            byte checking;

            do {
                readCoordinates__ToMakeMove(getTurn());

                checking = ticTacToe.checkTable();

                for (var player : printPlayerList) {
                    printShot_ForPlayer(player, ticTacToe.getTable()); // print table to each player
                }
                //printShotInConsole(ticTacToe.getTable()); // print table to server

            } while (checking == 0);

            writeResults(checking);

        }

    }

    private void readCoordinates__ToMakeMove(byte turn){
        boolean flag;

        do {
            printPlayerList.get(turn).printf("Write coordinates for '%s', %s.", ticTacToe.getTurn(), names.get(turn));
            printPlayerList.get(getAnotherTurn(turn)).printf("Waiting for %s bet by %s...", names.get(turn), ticTacToe.getTurn());

            try {

                String text = readerPlayerList.get(turn).readLine();
                var str = text.split(" ");
                int x = parseInt(str[0]);
                int y = parseInt(str[1]);

                try {
                    flag = ticTacToe.couldMakeMove(x, y);
                } catch (Exception e) {
                    System.out.println("Numbers are not bound of array!");
                    flag = false;
                }

            } catch (Exception e) {
                System.out.println("Invalid X!");
                flag = false;
            }

        } while (!flag);
    }

    private byte getTurn(){
        return (byte) (ticTacToe.getTurn() == Game.X ? 0 : 1);
    }

    private byte getAnotherTurn(byte turn){
        return (byte) (turn == 1 ? 0 : 1);
    }

    private void writeResults(byte checking){
        var text = checking == 1 ? "Winner is 'X'!" : checking == 2 ? "Winner is 'O'!" : checking == 3 ? "It is draw!" : "No Players!";
        for (var player : printPlayerList) {
            player.println(text);
        }
        System.out.println(text);
    }

    private void addClient(ServerSocket serverSocket) {

        System.out.println("Waiting for player connection...");

        try {
            Socket client = serverSocket.accept();
            var inputStream = client.getInputStream();
            var outputStream = client.getOutputStream();

            readerPlayerList.add(new BufferedReader(new InputStreamReader(inputStream)));
            printPlayerList.add(new PrintStream(outputStream));

            players.add(client);
            names.add(inputName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String inputName(){
        printPlayerList.get(printPlayerList.size() - 1).println("Print your name");
        try {
            return readerPlayerList.get(readerPlayerList.size() - 1).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "BaseName";
    }

   /* private boolean checkClients() {
        boolean flag = false;

        for (int i = 1; i >= 0; i--) {
            if (!players.get(i).isConnected()) {
                players.remove(i);
                readerPlayerList.remove(i);
                printPlayerList.remove(i);
                flag = true;
            }
        }
        return flag;
    }*/

}
