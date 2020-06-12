package com.example.ginoamaury.ufpsradio.util;

import android.media.MediaPlayer;

/**
 * author carlos22ivan
 * author gino
 */
public class MediaPlayerWrapper {

    private static MediaPlayer mediaPlayer;

    /**
     * @return intancia de mediaPlayer
     */
    public static MediaPlayer getInstance() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        return mediaPlayer;
    }

    /**
     * @param mp
     */
    public static void setMediaPlayer(MediaPlayer mp) {
        mediaPlayer = mp;
    }
}
