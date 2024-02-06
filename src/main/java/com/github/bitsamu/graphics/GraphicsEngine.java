package com.github.bitsamu.graphics;

import com.github.bitsamu.game.Ball;
import com.github.bitsamu.game.Block;
import com.github.bitsamu.game.Game;

import java.awt.*;

public class GraphicsEngine {
    private Game game;
    private RenderThread renderer;

    public GraphicsEngine(Game game, Board board) {
        this.game = game;
        renderer = new RenderThread(board);
        renderer.start();
    }

    public void paintGraphics(Graphics2D g2d){
        paintGame(g2d);
    }

    private void paintGame(Graphics2D g2d){
        paintMe(g2d);
        paintOpponent(g2d);
        paintBall(g2d);
        paintScore(g2d);
        paintMiddleLine(g2d);
    }

    private void paintMe(Graphics2D g2d){
        Block block = this.game.getMe();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(block.getX(), block.getY(), block.getWidth(), block.getHeight());
    }


    private void paintOpponent(Graphics2D g2d){
        Block block = this.game.getOpponent();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(block.getX(), block.getY(), block.getWidth(), block.getHeight());
    }

    private void paintBall(Graphics2D g2d){
        Ball ball = this.game.getBall();
        g2d.setColor(Color.WHITE);
        g2d.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
    }

    private void paintScore(Graphics2D g2d){
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Pong Font", Font.PLAIN, 36));
        g2d.drawString(String.valueOf(game.getMyScore()), 100, 100);
        g2d.drawString(String.valueOf(game.getOppScore()), game.getWidth() - 100 - 50, 100);
    }

    private void paintMiddleLine(Graphics2D g2d){
        int dashWidth = 6;
        int dashHeight = 6;
        int dashSpace = 10;
        g2d.setColor(Color.WHITE);

        for (int y = 0; y < this.game.getHeight(); y += dashHeight + dashSpace) {
            g2d.fillRect(this.game.getWidth() / 2 - dashWidth / 2, y, dashWidth, dashHeight);
        }
    }
}
