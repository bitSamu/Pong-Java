package com.github.bitsamu.networking.server;

import com.github.bitsamu.game.Ball;
import com.github.bitsamu.game.Game;
import com.github.bitsamu.physics.PyhsicsEngine;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Game game;

    public Server(){
        this.game = new Game(true);
        startConnection();
    }

    public void startConnection(){
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started, waiting for clients...");

            // Accept client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket);

            // Create object streams
            ObjectOutputStream out = new ObjectOutputStream(
                    new BufferedOutputStream(clientSocket.getOutputStream())
            );
            ObjectInputStream in = new ObjectInputStream(
                    new BufferedInputStream(clientSocket.getInputStream())
            );

            // Exchange ball position with client
            while (true) {
                // Receive ball position from client
                Ball clientBall = (Ball) in.readObject();
                Ball ball = new Ball(clientBall.getX(), clientBall.getY());

                // Modify ball position (example)
                ball.setX(ball.getX() + 1);
                ball.setY(ball.getY() + 1);

                // Send updated ball position back to client
                out.writeObject(ball);
                out.flush(); // Flush to ensure data is sent immediately
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
