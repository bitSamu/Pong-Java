package com.github.bitsamu.physics;

import com.github.bitsamu.game.Ball;
import com.github.bitsamu.game.Game;
import com.github.bitsamu.physics.direction.Direction;

import java.io.Serializable;

public class PyhsicsEngine implements Serializable {
    private Game game;
    private PhysicsThread physicsThread;

    public PyhsicsEngine(Game game) {
        this.game = game;
        this.physicsThread = new PhysicsThread(game);

        physicsThread.start();
    }
}
