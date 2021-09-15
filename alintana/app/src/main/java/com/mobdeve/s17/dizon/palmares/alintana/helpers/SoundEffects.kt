package com.mobdeve.s17.dizon.palmares.alintana.helpers

import android.content.Context
import android.media.MediaPlayer
import com.mobdeve.s17.dizon.palmares.alintana.R

class SoundEffects {
    var mp : MediaPlayer? = null

    private var context: Context? = null

    constructor(context: Context){
        this.context = context
    }
    fun clickSoundEffect(){
        mp = MediaPlayer.create(context, R.raw.click)
        mp?.setVolume(80f, 80f)
        mp?.start()
    }

    fun clickSwishEffect(){
        mp = MediaPlayer.create(context, R.raw.swipe)
        mp?.setVolume(60f, 60f)
        mp?.start()
    }


}