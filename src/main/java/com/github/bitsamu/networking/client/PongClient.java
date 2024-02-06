package com.github.bitsamu.networking.client;

import com.github.bitsamu.game.Ball;
import com.github.bitsamu.game.Game;
import com.github.bitsamu.graphics.Board;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class PongClient {

    public PongClient(){
        startConnection();
    }

    public void startConnection(){
        try {
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connected to server");

            // Create object streams
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

            Game game = (Game) in.readObject();

            Board board = new Board(game);

            SwingUtilities.invokeLater(() -> {
                board.setVisible(true);
            });

            Ball ball;


            while (true){
                game.setBall((Ball) in.readObject());
                board.update();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        PongClient client = new PongClient();
    }
}
