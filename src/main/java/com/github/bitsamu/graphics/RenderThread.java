package com.github.bitsamu.graphics;

public class RenderThread extends Thread{
    private Board board;

    public RenderThread(Board board) {
        this.board = board;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            try{
                board.update();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
