package com.github.bitsamu.game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;

public class Block implements Serializable {
    public static final int WIDTH = 5;
    public static final int HEIGHT = 100;

    private int speed = 10;
    private int x;
    private int y;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public KeyAdapter getKeyListener() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) {
                    y -= speed;
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    y += speed;
                }
            }
        };
    }

    public Rectangle getBounds(){
        return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
