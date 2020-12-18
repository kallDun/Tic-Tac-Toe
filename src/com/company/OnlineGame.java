package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class OnlineGame implements IPrint {

    private static final int PORT = 5000;
    private ServerSocket serverSocket;

    private final List<Player> players = new ArrayList<>();
    private Game ticTacToe;


    public OnlineGame() throws IOException {
        String str = System.getenv("PORT");
        int port = str != null ? Integer.parseInt(str) : PORT;
        serverSocket = new ServerSocket(port);

        while (true) {

            for (int i = players.size(); i < 2; i++)
                players.add(changeClient());

            ticTacToe = new Game(chooseSizeOfBoard()); // initialize new game 3x3

            byte checking;

            do {
                readCoordinates__ToMakeMove(getTurn());

                checking = ticTacToe.checkTable();

                for (var player : players) {
                    printShot_ForPlayer(player.getPlayerPrint(), ticTacToe.getTable()); // print table to each player
                }
                //printShotInConsole(ticTacToe.getTable()); // print table to server

            } while (checking == 0);

            writeResults(checking);

        }

    }

    private int chooseSizeOfBoard(){
        var ourPlayer = players.get(players.size() - 1);

        ourPlayer.getPlayerPrint().println("Write count of blocks in one row / col.");
        try {
            return parseInt(ourPlayer.getReader().readLine());
        } catch (IOException ignored) {}
        return 3;
    }

    private void readCoordinates__ToMakeMove(byte turn){
        boolean flag;

        do {
            players.get(turn).getPlayerPrint().printf("Write coordinates for '%s', %s.", ticTacToe.getTurn(), players.get(turn).getName());
            players.get(turn).getPlayerPrint().println();
            players.get(getAnotherTurn()).getPlayerPrint().printf("Waiting for %s bet by %s...", players.get(turn).getName(), ticTacToe.getTurn());
            players.get(getAnotherTurn()).getPlayerPrint().println();

            try {
                var text = "";
                try{
                    text = players.get(turn).getReader().readLine();
                } catch (Exception e){
                    reloadPlayer(turn);
                }

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

    private byte getAnotherTurn(){
        return (byte) (ticTacToe.getTurn() == Game.X ? 1 : 0);
    }

    private void writeResults(byte checking){
        var text = checking == 1 ? "Winner is 'X'!" : checking == 2 ? "Winner is 'O'!" : checking == 3 ? "It is draw!" : "No Players!";
        for (var player : players) {
            player.getPlayerPrint().println(text);
        }
        System.out.println(text);
    }

    private Player changeClient() {

        System.out.println("Waiting for player connection...");

        try {
            Socket client = serverSocket.accept();
            var inputStream = client.getInputStream();
            var outputStream = client.getOutputStream();

            return new Player(
                    client,
                    new BufferedReader(new InputStreamReader(inputStream)),
                    new PrintStream(outputStream));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void reloadPlayer(byte turn){
        players.remove(turn);
        players.add(turn, changeClient());
        printShot_ForPlayer(players.get(turn).getPlayerPrint(), ticTacToe.getTable());
    }

}
