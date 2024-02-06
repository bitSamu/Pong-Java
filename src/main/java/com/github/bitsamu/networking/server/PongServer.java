package com.github.bitsamu.networking.server;

import com.github.bitsamu.game.Ball;
import com.github.bitsamu.game.Game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PongServer {
    private Game game;

    public PongServer(){
        this.game = new Game(true);
        startConnection();
    }

    public void startConnection(){
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started, waiting for clients...");

            // Accept client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket);

            // Create object streams
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));

            out.writeObject(game);

            while (true){
                System.out.println(game.getBall().getX());
                Ball updatedBall = new Ball(game.getBall().getX(), game.getBall().getY());
                out.writeObject(updatedBall);
                out.flush(); // Flush the buffered output stream
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        PongServer server = new PongServer();
    }
}
