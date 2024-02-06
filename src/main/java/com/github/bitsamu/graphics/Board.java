package com.github.bitsamu.graphics;

import com.github.bitsamu.game.Game;
import com.github.bitsamu.physics.PyhsicsEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JFrame {
    private GraphicsComponent graphicsComponent;
    private PyhsicsEngine pyhsicsEngine;
    private Game game;

    public Board(Game game){
        this.game = game;
        this.graphicsComponent = new GraphicsComponent(game, this);
        //this.pyhsicsEngine = new PyhsicsEngine(game);

        initBoard();
    }

    private void initBoard(){
        setTitle("Pong");
        setBackground(Color.BLACK);
        setSize(game.getWidth(), game.getHeight());
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(graphicsComponent);
        addKeyListener(game.getMe().getKeyListener());
        addKeyListener(game.getOpponent().getKeyListener());
    }

    public void update(){
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
    }


}
