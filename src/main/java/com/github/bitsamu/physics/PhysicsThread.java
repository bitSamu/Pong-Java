package com.github.bitsamu.physics;

import com.github.bitsamu.game.Ball;
import com.github.bitsamu.game.Block;
import com.github.bitsamu.game.Game;
import com.github.bitsamu.physics.direction.Direction;

import java.awt.*;
import java.io.Serializable;

public class PhysicsThread extends Thread implements Serializable {

    private Game game;
    public PhysicsThread(Game game){
        this.game = game;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            try {
                applyPhysics();
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void applyPhysics(){
        Block me = this.game.getMe();
        Block opp = this.game.getOpponent();

        Ball ball = this.game.getBall();

        moveBall(ball);
        checkCollision(me, opp, ball);
    }

    public void moveBall(Ball ball){
        if (ball.getDirection_x() == Direction.X.LEFT) {
            ball.setX(ball.getX() - ball.getSpeed());
        } else if (ball.getDirection_x() == Direction.X.RIGHT) {
            ball.setX(ball.getX() + ball.getSpeed());
        }

        if (ball.getDirection_y() == Direction.Y.DOWN) {
            ball.setY(ball.getY() - ball.getSpeed());
        } else if (ball.getDirection_y() == Direction.Y.UP) {
            ball.setY(ball.getY() + ball.getSpeed());
        }
    }

    public void checkCollision(Block me, Block opp, Ball ball){
        Rectangle rectangle = opp.getBounds();
        rectangle.setBounds(rectangle.x - ball.getRadius(), rectangle.y, rectangle.width, rectangle.height);
        if(intersect(me.getBounds(), ball) || intersect(rectangle, ball)){
            ball.setDirection_x((ball.getDirection_x() == Direction.X.LEFT) ? Direction.X.RIGHT : Direction.X.LEFT);
            ball.setDirection_y((ball.getDirection_y() == Direction.Y.NONE) ? Direction.Y.DOWN : ball.getDirection_y());
        }

        else if(ball.getX() < 0 || ball.getX() > game.getWidth()){
            //ball.setDirection_x((ball.getDirection_x() == Direction.X.LEFT) ? Direction.X.RIGHT : Direction.X.LEFT);

            ball.setDirection_x(Direction.X.NONE);
            ball.setDirection_y(Direction.Y.NONE);

            if(ball.getX() < game.getWidth()){
                game.setOppScore(game.getOppScore() + 1);
            }
            else {
                game.setMyScore(game.getMyScore() + 1);
            }

            game.setActive(false);
        }
        else if(ball.getY() < 0 || ball.getY() > game.getHeight() - ball.getDiameter() * 2 - (ball.getRadius() + (ball.getRadius()/2))){
            ball.setDirection_y((ball.getDirection_y() == Direction.Y.UP) ? Direction.Y.DOWN : Direction.Y.UP);
        }
    }

    public boolean intersect(Rectangle block, Ball ball){
        return block.intersects(ball.getBounds());
    }
}
