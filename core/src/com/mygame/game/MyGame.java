package com.mygame.game;

import com.badlogic.gdx.Game;

// run with: ./gradlew desktop:run

public class MyGame extends Game {
    @Override
    public void create() {
        showMenuScreen();
    }

    public void showMenuScreen() {
        setScreen(new MenuScreen(this));
    }

    public void showGameScreen() {
        setScreen(new GameScreen(this));
    }
}
