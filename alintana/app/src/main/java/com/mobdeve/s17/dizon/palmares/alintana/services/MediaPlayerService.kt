package com.mobdeve.s17.dizon.palmares.alintana.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.mobdeve.s17.dizon.palmares.alintana.R

private const val ACTION_PLAY: String = "com.mobdeve.s17.dizon.palmares.alintana.action.PLAY"

class MediaPlayerService: Service() {

    private var mMediaPlayer: MediaPlayer? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("TESTING","MUSICCCCCCCCC")
        mMediaPlayer = MediaPlayer.create(this, R.raw.bgmusic)
        mMediaPlayer?.isLooping = true
        mMediaPlayer?.setVolume(70f, 70f)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MUSIC",mMediaPlayer?.isPlaying.toString() + "")

        mMediaPlayer?.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mMediaPlayer?.release()
    }

}