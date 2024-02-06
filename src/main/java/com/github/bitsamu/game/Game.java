package com.github.bitsamu.game;

import com.github.bitsamu.physics.PyhsicsEngine;
import com.github.bitsamu.physics.direction.Direction;

import java.io.Serializable;

public class Game implements Serializable {
    private final int WIDTH = 600;
    private final int HEIGHT = 500;

    private final int block_offset = 20;

    private boolean isLeft;

    private Block left;
    private Block right;
    private Ball ball;

    private int ball_start_x = WIDTH / 2 - Ball.RADIUS * 2;
    private int ball_start_y = HEIGHT / 2 - Ball.RADIUS * 2;

    private int myScore = 0;
    private int oppScore = 0;

    private PyhsicsEngine pyhsicsEngine;

    private boolean isActive = true;

    public Game(boolean isLeft) {
        this.left = new Block(block_offset, HEIGHT / 2 - (Block.HEIGHT) );
        this.right = new Block(WIDTH - block_offset * 2, HEIGHT / 2 - (Block.HEIGHT));
        this.ball = new Ball(ball_start_x,ball_start_y);
        this.isLeft = isLeft;
        this.pyhsicsEngine = new PyhsicsEngine(this);
        initBoard();
    }

    private void initBoard(){

    }

    public int getWidth(){
        return WIDTH;
    }

    public int getHeight(){
        return HEIGHT;
    }

    public Block getLeft() {
        return left;
    }

    public Block getRight() {
        return right;
    }

    public Block getMe(){
        return isLeft ? getLeft() : getRight();
    }

    public Block getOpponent(){
        return isLeft ? getRight() : getLeft();
    }

    public Ball getBall() {
        return this.ball;
    }

    public int getMyScore() {
        return myScore;
    }

    public int getOppScore() {
        return oppScore;
    }

    public void setMyScore(int myScore) {
        this.myScore = myScore;
        this.ball.setDirection_x(Direction.X.RIGHT);
    }

    public void setOppScore(int oppScore) {
        this.oppScore = oppScore;
        this.ball.setDirection_x(Direction.X.LEFT);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;

        if(!isActive){
            reset();
            setActive(true);
        }
    }

    public void reset(){
        ball.setX(ball_start_x);
        ball.setY(ball_start_y);
    }

    public void setBall(Ball ball){
        this.ball = ball;
    }
}
