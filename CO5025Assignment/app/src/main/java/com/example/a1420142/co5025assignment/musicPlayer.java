package com.example.a1420142.co5025assignment;

import android.content.Context;
import android.media.MediaPlayer;


public class musicPlayer {

    //by having the music player work in a seperate java file it makes it easier to change the playing music from different activities
    public static MediaPlayer music;
    public static void musicPlayer (Context ctx, int raw_id){
        music = MediaPlayer.create(ctx, raw_id);
        music.setLooping(true);
        if (music.isPlaying()==false){
            music.start();
        }
    }
    public static void stopMusic(){
        music.stop();
    }
}
