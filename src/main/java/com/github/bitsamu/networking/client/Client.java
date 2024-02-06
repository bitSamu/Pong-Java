package com.github.bitsamu.networking.client;

import com.github.bitsamu.game.Ball;
import com.github.bitsamu.game.Game;
import com.github.bitsamu.graphics.Board;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public void start(){
        Game game = new Game(true);
        SwingUtilities.invokeLater(() -> {
            Board board = new Board(game);
            board.setVisible(true);
            gameLoop(game);
        });
    }

    public void gameLoop(Game game){
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connected to server");

            // Create object streams
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // Exchange ball position with server
            while (true) {
                // Send ball position to server
                out.writeObject(game.getBall());
                out.flush();

                game.setBall((Ball) in.readObject());

                // Display ball position (or update game state)
                System.out.println("Received ball position: (" + game.getBall().getX() + ", " + game.getBall().getY() + ")");

                // Simulate delay
                Thread.sleep(1000);
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
