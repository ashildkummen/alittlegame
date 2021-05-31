package com.mygame.game;

// To put all constants in - for easy changing later on

public class Constants {

  // --------- WORLD -------- //
  public static final float WORLD_SIZE = 10.0f;
  public static final int screenHeight = 750;
  public static final int screenWidth = 1200;
  public static final int floorHeight = screenHeight - 495;


  // --------- PLAYER -------- //
  public class Player {
    public static final int width = 76; // taken from player image size
    public static final int height = 71; // taken from player image size
    public static final float speed = 5f;
    public static final float weight = 0.8f;
    public static final float firstJumpStrength = 17f;
    public static final float restJumpStrength = 10f;
  }


  // --------- ASSETS -------- //
  public static final String backgroundImageUrl = "backgrounds/background.jpeg";
  public static final String playerImgUrl = "chars/player.png";

  public static final String jumpSoundUrl = "sounds/jump.wav";
  public static final String musicUrl = "music/music.mp3";



}
