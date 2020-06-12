package com.example.ginoamaury.ufpsradio.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ginoamaury.ufpsradio.R;
import com.example.ginoamaury.ufpsradio.service.NotificationService;
import com.example.ginoamaury.ufpsradio.util.Constants;
import com.example.ginoamaury.ufpsradio.util.MediaPlayerWrapper;
import com.example.ginoamaury.ufpsradio.util.MusicCallback;

/**
 * author carlos22ivan
 * author gino
 */
public class RadioFragment extends Fragment implements MusicCallback {

    private Context myContext;
    private Button playButton;
    private MediaPlayer mediaPlayer;
    private boolean started = false;
    private Drawable playIconFragment;
    private Drawable pauseIconFragment;
    private NotificationService notificationService;
    private boolean bound = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getActivity(), NotificationService.class);
        getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_radio, container, false);
        myContext = getActivity();
        mediaPlayer = MediaPlayerWrapper.getInstance();
        inicializarView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (notificationService != null) {
            notificationService.stopForeground(true);
            notificationService.stopSelf();
        }
        if (started) {
            playButton.setBackground(pauseIconFragment);
        } else {
            playButton.setBackground(playIconFragment);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (started)
            startService();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.pause();
        started = false;
        notificationService.stopForeground(true);
        notificationService.stopSelf();
        getActivity().finish();
    }

    @Override
    public void toogleMusic() {
        onResume();
        if (started) {
            pause();
        } else {
            play();
        }
    }

    @Override
    public void destroyMusic() {
        onDestroy();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            NotificationService.LocalBinder binder = (NotificationService.LocalBinder) service;
            notificationService = binder.getService();
            bound = true;
            notificationService.setMusicCallback(RadioFragment.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };

    private void inicializarView(View view) {
        pauseIconFragment = ContextCompat.getDrawable(myContext, R.drawable.pause_circle_outline);
        playIconFragment = ContextCompat.getDrawable(myContext, R.drawable.play_circle_outline);
        playButton = (Button) view.findViewById(R.id.playbutton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });
    }

    private void play() {
        mediaPlayer.start();
        started = true;

    }

    private void pause() {
        mediaPlayer.pause();
        started = false;
    }

    public void toggle() {
        if (started) {
            pause();
            playButton.setBackground(playIconFragment);
        } else {
            play();
            playButton.setBackground(pauseIconFragment);
        }
    }

    public void startService() {
        Intent serviceIntent = new Intent(myContext, NotificationService.class);
        serviceIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        myContext.startService(serviceIntent);
    }

}