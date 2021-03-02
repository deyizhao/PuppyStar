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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.unread

@Composable
fun PuppyListTopBar() {
    WeTopBar(title = "待领养的puppy")
}

@Preview(showBackground = true)
@Composable
fun ChatListTopBarPreview() {
    PuppyListTopBar()
}

@Composable
fun PuppyListItem(
    puppy: Puppy,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    viewModel.showPuppyDetail(puppy)
                }
            )
    ) {
        Image(
            painterResource(puppy.avatar), "avatar",
            Modifier
                .padding(12.dp, 8.dp, 8.dp, 8.dp)
                .size(60.dp)
                .unread(puppy.read, Color.Red)
                .clip(RoundedCornerShape(4.dp))
        )
        Column(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(puppy.name, fontSize = 20.sp, color = MyTheme.colors.textPrimary)
            Text(
                puppy.intro ?: "还没有自我介绍哦",
                Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
                fontSize = 14.sp, color = MyTheme.colors.textSecondary,
            )
        }
        Text(
            "13:48",
            Modifier.padding(8.dp, 8.dp, 12.dp, 8.dp),
            fontSize = 11.sp, color = MyTheme.colors.textSecondary
        )
    }
}

@Composable
fun PuppyList(viewModel: MainViewModel = viewModel()) {
    Column(Modifier.fillMaxSize()) {
        PuppyListTopBar()
        Box(
            Modifier
                .background(MyTheme.colors.background)
                .fillMaxSize()
        ) {
            PuppyList(puppys = viewModel.puppys)
        }
    }
}

@Composable
fun PuppyList(puppys: List<Puppy>) {
    LazyColumn(
        Modifier
            .background(MyTheme.colors.listItem)
            .fillMaxWidth()
    ) {
        itemsIndexed(puppys) { index, chat ->
            PuppyListItem(chat)
            if (index < puppys.size - 1) {
                Divider(
                    startIndent = 68.dp,
                    color = MyTheme.colors.chatListDivider,
                    thickness = 0.8f.dp
                )
            }
        }
    }
}
