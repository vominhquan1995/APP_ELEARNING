package com.elearning.elearning.helper;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.elearning.elearning.R;

/**
 * Created by MinhQuan on 08/07/2017.
 */

public class Sound {
    private Context context;

    public Sound(Context context) {
        this.context = context;
    }

    public void playRingtone() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(context, notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void playBeep() {
        try {
            MediaPlayer mPlayer = MediaPlayer.create(context, R.raw.beep);
            mPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
