package com.github.bitsamu;

import com.github.bitsamu.game.Game;
import com.github.bitsamu.graphics.Board;
import com.github.bitsamu.networking.client.Client;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}