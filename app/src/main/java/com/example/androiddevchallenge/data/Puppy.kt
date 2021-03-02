package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.androiddevchallenge.R

data class Puppy(
    var name: String,
    var intro: String?=null,
    var desc: String?=null,
    var msg: MutableList<Msg>?= mutableListOf<Msg>(),
    @DrawableRes val avatar: Int = R.drawable.ic_launcher_foreground
) {
    var read: Boolean by mutableStateOf(false)
    var like: Boolean by mutableStateOf(false)
}

data class Msg(val from: User, val text: String) {

}