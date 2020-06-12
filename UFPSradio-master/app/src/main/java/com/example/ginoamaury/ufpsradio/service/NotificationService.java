package com.example.ginoamaury.ufpsradio.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.view.View;
import android.widget.RemoteViews;

import com.example.ginoamaury.ufpsradio.R;
import com.example.ginoamaury.ufpsradio.activity.MainActivity;
import com.example.ginoamaury.ufpsradio.util.Constants;
import com.example.ginoamaury.ufpsradio.util.MusicCallback;

/**
 * author carlos22ivan
 * author gino
 */
public class NotificationService extends Service {

    private boolean started = false;
    private RemoteViews views;
    private RemoteViews bigViews;
    private Notification status;
    private final IBinder binder = new LocalBinder();
    private MusicCallback musicCallback;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            togglePlayButton();
        } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
            musicCallback.toogleMusic();
            togglePlayButton();
        } else if (intent.getAction().equals(Constants.ACTION.STOPFOREGROUND_ACTION)) {
            musicCallback.destroyMusic();
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void setMusicCallback(MusicCallback musicCallback) {
        this.musicCallback = musicCallback;
    }

    public void togglePlayButton() {
        if (started) {
            showNotification();
            started = false;
        } else {
            showNotification();
            started = true;
        }
    }

    private void showNotification() {
// Using RemoteViews to bind custom layouts into Notification
        views = new RemoteViews(getPackageName(),
                R.layout.status_bar);
        bigViews = new RemoteViews(getPackageName(),
                R.layout.status_bar_expanded);

// showing default album image
//        views.setViewVisibility(R.id.status_bar_icon, View.VISIBLE);
        views.setViewVisibility(R.id.status_bar_album_art, View.GONE);
        bigViews.setImageViewBitmap(R.id.status_bar_album_art, Constants.getDefaultAlbumArt(this));

        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);


        Intent playIntent = new Intent(this, NotificationService.class);
        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0, playIntent, 0);

        Intent closeIntent = new Intent(this, NotificationService.class);
        closeIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
        PendingIntent pcloseIntent = PendingIntent.getService(this, 0,
                closeIntent, 0);

        views.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);

        views.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);
        bigViews.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);

        if (started) {
            views.setImageViewResource(R.id.status_bar_play, R.drawable.play_circle_outline);
            bigViews.setImageViewResource(R.id.status_bar_play, R.drawable.play_circle_outline);
        } else {
            views.setImageViewResource(R.id.status_bar_play, R.drawable.pause_circle_outline);
            bigViews.setImageViewResource(R.id.status_bar_play, R.drawable.pause_circle_outline);
        }

        views.setTextViewText(R.id.status_bar_track_name, "UFPS Radio");
        bigViews.setTextViewText(R.id.status_bar_track_name, "UFPS Radio");

        views.setTextViewText(R.id.status_bar_artist_name, "95.2 FM");
        bigViews.setTextViewText(R.id.status_bar_artist_name, "95.2 FM");

        bigViews.setTextViewText(R.id.status_bar_album_name, "Acreditación de alta calidad, mi compromiso!!!");

        status = new Notification.Builder(this).build();
        status.contentView = views;
        status.bigContentView = bigViews;
        status.flags = Notification.FLAG_ONGOING_EVENT;
        status.icon = R.drawable.ufps_round;
        status.contentIntent = pendingIntent;
        startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
    }

    public class LocalBinder extends Binder {
        public NotificationService getService() {
            return NotificationService.this;
        }
    }

}
