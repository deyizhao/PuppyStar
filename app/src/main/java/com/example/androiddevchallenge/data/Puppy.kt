/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.androiddevchallenge.R

data class Puppy(
    var name: String,
    var intro: String? = null,
    var desc: String? = null,
    var msg: MutableList<Msg>? = mutableListOf<Msg>(),
    @DrawableRes val avatar: Int = R.drawable.ic_launcher_foreground
) {
    var read: Boolean by mutableStateOf(false)
    var like: Boolean by mutableStateOf(false)
}

data class Msg(val from: User, val text: String)
