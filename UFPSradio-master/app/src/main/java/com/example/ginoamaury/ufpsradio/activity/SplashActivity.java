package com.example.ginoamaury.ufpsradio.activity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ginoamaury.ufpsradio.R;
import com.example.ginoamaury.ufpsradio.util.MediaPlayerWrapper;
import com.example.ginoamaury.ufpsradio.util.Metodos;
import com.example.ginoamaury.ufpsradio.util.Variable;

import java.io.IOException;

/**
 * author carlos22ivan
 * author gino
 */
public class SplashActivity extends AppCompatActivity {

    private Context context;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;
        new PlayerTask().execute(Variable.getRadioUrl());
    }

    /**
     * clase asincrona para crear la conexion al stream de la ufps
     */
    class PlayerTask extends AsyncTask<String, Void, Boolean> {

        private String msg;

        @Override
        protected Boolean doInBackground(String... strings) {
            Boolean prepared = false;
            if (Metodos.isOnline(context)) {
                try {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(strings[0]);
                    mediaPlayer.prepare();
                    MediaPlayerWrapper.setMediaPlayer(mediaPlayer);
                    msg = getString(R.string.exito);
                    prepared = true;
                } catch (IOException e) {
                    msg = getString(R.string.errorStream);
                }
            } else {
                msg = getString(R.string.errorInternet);
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Class aClass = aBoolean ? MainActivity.class : RestartActivity.class;
            Intent i = new Intent(getApplicationContext(), aClass);
            i.putExtra("msg", msg);
            startActivity(i);
            finish();
        }
    }

}
