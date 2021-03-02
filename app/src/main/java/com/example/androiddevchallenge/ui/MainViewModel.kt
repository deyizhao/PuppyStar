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
package com.example.androiddevchallenge.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Module
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainViewModel : ViewModel() {
    var puppys by mutableStateOf(
        listOf(
            Puppy(
                name = "wangwang",
                intro = "一只半岁大的阿拉斯加",
                avatar = R.mipmap.avatar_dog1
            ),
            Puppy(
                name = "mimi",
                intro = "a happy puppy",
                avatar = R.mipmap.avatar_dog2
            ),
            Puppy(
                name = "hapi",
                intro = "一只安静的狗狗",
                avatar = R.mipmap.avatar_dog3
            ),
            Puppy(
                name = "binbin",
                intro = "我在寻找新主人",
                avatar = R.mipmap.avatar_dog4
            ),
            Puppy(
                name = "xiaohei",
                intro = "快点了解我吧",
                avatar = R.mipmap.avatar_dog5
            ),
            Puppy(
                name = "happy",
                intro = "wait for you",
                avatar = R.mipmap.avatar_dog6
            ),
            Puppy(
                name = "cindy",
                intro = "cindy今天一岁啦",
                avatar = R.mipmap.avatar_dog7
            ),
        )
    )

    var theme by mutableStateOf(MyTheme.Theme.Light)
    var openModule: Module? by mutableStateOf(null)
    var currentPuppy: Puppy? by mutableStateOf(null)
        private set

    fun showPuppyDetail(puppy: Puppy) {
        currentPuppy = puppy
        openModule = Module.Puppy
    }

    fun dismissPuppyDetail() {
        openModule = null
    }

    fun boom() {
        currentPuppy?.like = true
    }

    fun read(puppy: Puppy) {
        puppy.read = true
    }
}
