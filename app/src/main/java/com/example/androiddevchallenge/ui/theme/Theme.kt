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
package com.example.androiddevchallenge.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

private val DarkColorPalette = WeColors(
    background = blackBackground,
    bottomBar = blackBottomBar,
    textPrimary = whiteTextP,
    icon = whiteIcon,
    textSecondary = grey,
    listItem = listBlack,
    divider = dividerBlack,
    iconCurrent = greenIcon,
    chatPage = blackPage,
    bubbleMe = green2,
    bubbleOthers = dividerBlack,
)

private val LightColorPalette = WeColors(
    background = whiteBackground,
    bottomBar = whiteBottomBar,
    textPrimary = blackTextP,
    icon = black,
    textSecondary = grey,
    listItem = white,
    divider = dividerWhite,
    iconCurrent = greenIcon,
    chatPage = whitePage,
    bubbleMe = green1,
    bubbleOthers = white,
)

private val LocalWeColors = compositionLocalOf {
    LightColorPalette
}

@Stable
object MyTheme {
    val colors: WeColors
        @Composable
        get() = LocalWeColors.current

    enum class Theme {
        Light, Dark
    }
}

@Stable
class WeColors(
    background: Color,
    bottomBar: Color,
    listItem: Color,
    divider: Color,
    chatPage: Color,
    textPrimary: Color,
    textSecondary: Color,
    icon: Color,
    iconCurrent: Color,
    bubbleMe: Color,
    bubbleOthers: Color,
) {
    var background: Color by mutableStateOf(background)
        private set
    var bottomBar: Color by mutableStateOf(bottomBar)
        private set
    var listItem: Color by mutableStateOf(listItem)
        private set
    var chatListDivider: Color by mutableStateOf(divider)
        private set
    var chatPage: Color by mutableStateOf(chatPage)
        private set
    var textPrimary: Color by mutableStateOf(textPrimary)
        private set
    var textSecondary: Color by mutableStateOf(textSecondary)
        private set
    var icon: Color by mutableStateOf(icon)
        private set
    var iconCurrent: Color by mutableStateOf(iconCurrent)
        private set
    var bubbleMe: Color by mutableStateOf(bubbleMe)
        private set
    var bubbleOthers: Color by mutableStateOf(bubbleOthers)
        private set
}

@Composable
fun MyTheme(theme: MyTheme.Theme = MyTheme.Theme.Light, content: @Composable () -> Unit) {
    val targetColors = when (theme) {
        MyTheme.Theme.Light -> LightColorPalette
        MyTheme.Theme.Dark -> DarkColorPalette
    }
    val bottomBar = animateColorAsState(targetColors.bottomBar, TweenSpec(600))
    val background = animateColorAsState(targetColors.background, TweenSpec(600))
    val listItem = animateColorAsState(targetColors.listItem, TweenSpec(600))
    val chatListDivider = animateColorAsState(targetColors.chatListDivider, TweenSpec(600))
    val chatPage = animateColorAsState(targetColors.chatPage, TweenSpec(600))
    val textPrimary = animateColorAsState(targetColors.textPrimary, TweenSpec(600))
    val textSecondary = animateColorAsState(targetColors.textSecondary, TweenSpec(600))
    val icon = animateColorAsState(targetColors.icon, TweenSpec(600))
    val iconCurrent = animateColorAsState(targetColors.iconCurrent, TweenSpec(600))
    val bubbleMe = animateColorAsState(targetColors.bubbleMe, TweenSpec(600))
    val bubbleOthers = animateColorAsState(targetColors.bubbleOthers, TweenSpec(600))
    val colors = WeColors(
        bottomBar = bottomBar.value,
        background = background.value,
        listItem = listItem.value,
        divider = chatListDivider.value,
        chatPage = chatPage.value,
        textPrimary = textPrimary.value,
        textSecondary = textSecondary.value,
        icon = icon.value,
        iconCurrent = iconCurrent.value,
        bubbleMe = bubbleMe.value,
        bubbleOthers = bubbleOthers.value,
    )

    CompositionLocalProvider(LocalWeColors provides colors) {
        MaterialTheme(
            shapes = shapes
        ) {
            ProvideWindowInsets(content = content)
        }
    }
}
