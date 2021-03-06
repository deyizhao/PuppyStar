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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun WeTopBar(title: String, onBack: (() -> Unit)? = null) {
    Box(
        Modifier
            .background(MyTheme.colors.background)
            .fillMaxWidth()
            .statusBarsPadding()
    ) {
        Row(
            Modifier
                .height(48.dp)
        ) {
            if (onBack != null) {
                Icon(
                    painterResource(R.drawable.abc_ic_ab_back_material),
                    null,
                    Modifier
                        .clickable(onClick = onBack)
                        .align(Alignment.CenterVertically)
                        .size(36.dp)
                        .padding(8.dp),
                    tint = MyTheme.colors.icon
                )
            }
            Spacer(Modifier.weight(1f))
            val viewModel: MainViewModel = viewModel()
            Icon(
                painterResource(R.drawable.abc_ic_menu_paste_mtrl_am_alpha),
                "切换主题",
                Modifier
                    .clickable(
                        onClick = {
                            viewModel.theme = when (viewModel.theme) {
                                MyTheme.Theme.Light -> MyTheme.Theme.Dark
                                MyTheme.Theme.Dark -> MyTheme.Theme.Light
                            }
                        }
                    )
                    .align(Alignment.CenterVertically)
                    .size(36.dp)
                    .padding(8.dp),
                tint = MyTheme.colors.icon
            )
        }
        Text(title, Modifier.align(Alignment.Center), color = MyTheme.colors.textPrimary)
    }
}

@Composable
fun WeBottomBar(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    Row(
        modifier
            .fillMaxWidth()
            .background(MyTheme.colors.bottomBar)
            .padding(4.dp, 0.dp)
            .navigationBarsPadding(),
        content = content
    )
}
