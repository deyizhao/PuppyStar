package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes
import com.example.androiddevchallenge.R

data class User(
  val name: String,
  @DrawableRes val avatar: Int
) {
  companion object {
    val Me: User = User("deyi_zhao", R.drawable.ic_launcher_foreground)
  }
}