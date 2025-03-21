package com.toonandtools.playingwithcompose.data

import android.media.Image
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.toonandtools.playingwithcompose.R


data class AppsData(

    @StringRes val title:Int,
    @DrawableRes val image: Int
)
    val appItems = listOf(
     AppsData(R.string.title_activity_art_space,R.drawable.art),
     AppsData(R.string.title_activity_affirmations,R.drawable.affirmations)
    )

