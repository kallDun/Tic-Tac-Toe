package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Player {

    private Socket socket;
    private String name;
    private BufferedReader reader;
    private PrintStream playerPrint;

    public Socket getSocket() {
        return socket;
    }

    public String getName() {
        return name;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public PrintStream getPlayerPrint() {
        return playerPrint;
    }

    public Player(Socket socket, BufferedReader reader, PrintStream playerPrint) {
        this.socket = socket;
        this.reader = reader;
        this.playerPrint = playerPrint;
        setName();
    }

    private void setName(){
        playerPrint.println("Print your name");
        try {
            name = reader.readLine();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        name = "Basic_name";
    }
}
