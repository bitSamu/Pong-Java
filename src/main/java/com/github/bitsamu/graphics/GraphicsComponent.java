package com.github.bitsamu.graphics;

import com.github.bitsamu.game.Game;

import javax.swing.*;
import java.awt.*;

public class GraphicsComponent extends JPanel {

    private GraphicsEngine graphicsEngine;
    private Board board;

    public GraphicsComponent(Game game, Board board){
        graphicsEngine = new GraphicsEngine(game, board);
        this.board = board;
    }

    @Override
    public void paint(Graphics g) {
        graphicsEngine.paintGraphics((Graphics2D) g);
    }
}
