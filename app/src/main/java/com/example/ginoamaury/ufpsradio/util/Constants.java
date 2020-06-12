package com.example.ginoamaury.ufpsradio.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.ginoamaury.ufpsradio.R;

/**
 * author carlos22ivan
 * author gino
 */
public class Constants {

    public static Bitmap getDefaultAlbumArt(Context context) {
        Bitmap bm = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ufps_round, options);
        } catch (Exception e) {
        }
        return bm;
    }

    public interface ACTION {
        static String MAIN_ACTION = "action.main";
        static String PLAY_ACTION = "action.play";
        static String STARTFOREGROUND_ACTION = "startforeground";
        static String STOPFOREGROUND_ACTION = "stopforeground";
    }

    public interface NOTIFICATION_ID {
        static int FOREGROUND_SERVICE = 101;
    }

}

