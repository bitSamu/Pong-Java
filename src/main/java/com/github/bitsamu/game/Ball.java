package com.github.bitsamu.game;

import com.github.bitsamu.physics.direction.Direction;

import java.awt.*;
import java.io.Serializable;

public class Ball implements Serializable {
    public static final int RADIUS = 10;
    private int x;
    private int y;

    private Direction.X direction_x;
    private Direction.Y direction_y;

    private final int speed = 3;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;

        this.direction_x = Direction.X.LEFT;
        this.direction_y = Direction.Y.NONE;
    }

    public int getRadius() {
        return RADIUS;
    }

    public int getDiameter(){
        return getRadius() * 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public Direction.X getDirection_x() {
        return direction_x;
    }

    public void setDirection_x(Direction.X direction_x) {
        this.direction_x = direction_x;
    }

    public Direction.Y getDirection_y() {
        return direction_y;
    }

    public void setDirection_y(Direction.Y direction_y) {
        this.direction_y = direction_y;
    }

    public Rectangle getBounds(){
        return new Rectangle(this.getX(), this.getY(), this.getRadius(), this.getRadius());
    }
}
