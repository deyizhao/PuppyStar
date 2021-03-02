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

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.User
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun MeListTopBar() {
    Row(
        Modifier
            .background(MyTheme.colors.listItem)
            .fillMaxWidth()
            .height(224.dp)
            .statusBarsPadding()
    ) {
        Image(
            painterResource(id = R.drawable.abc_ic_clear_material), contentDescription = "Me",
            Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 24.dp)
                .clip(RoundedCornerShape(6.dp))
                .size(64.dp)
        )
        Column(
            Modifier
                .weight(1f)
                .padding(start = 12.dp)
        ) {
            Text(
                User.Me.name,
                Modifier.padding(top = 64.dp),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MyTheme.colors.textPrimary
            )
            Text(
                "我是${User.Me.name}",
                Modifier.padding(top = 16.dp),
                fontSize = 14.sp,
                color = MyTheme.colors.textSecondary
            )
            Text(
                "+ 发布",
                Modifier
                    .padding(top = 16.dp)
                    .border(1.dp, MyTheme.colors.background, RoundedCornerShape(50))
                    .padding(8.dp, 2.dp),
                fontSize = 16.sp,
                color = MyTheme.colors.background
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MeListTopBarPreview() {
    MeListTopBar()
}

@Composable
fun MeListItem(
    @DrawableRes icon: Int?,
    title: String,
    modifier: Modifier = Modifier,
    badge: @Composable (() -> Unit)? = null,
    endBadge: @Composable (() -> Unit)? = null
) {
    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Image(
                painterResource(icon), "title",
                Modifier
                    .padding(12.dp, 8.dp, 8.dp, 8.dp)
                    .size(36.dp)
                    .padding(8.dp)
            )
        }

        Text(
            title,
            Modifier
                .padding(12.dp, 8.dp, 8.dp, 8.dp),
            fontSize = 17.sp,
            color = MyTheme.colors.textPrimary,
        )
        badge?.invoke()
        Spacer(Modifier.weight(1f))
        endBadge?.invoke()
    }
}

@Composable
fun MeList() {
    Box(
        Modifier
            .background(MyTheme.colors.background)
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .background(MyTheme.colors.listItem)
                .fillMaxWidth()
        ) {
            MeListTopBar()
            Spacer(
                Modifier
                    .background(MyTheme.colors.background)
                    .fillMaxWidth()
                    .height(8.dp)
            )
            MeListItem(null, "关于")
            Divider(
                startIndent = 56.dp,
                color = MyTheme.colors.chatListDivider,
                thickness = 0.8f.dp
            )
            Spacer(
                Modifier
                    .background(MyTheme.colors.background)
                    .fillMaxWidth()
                    .height(8.dp)
            )
            MeListItem(null, "设置")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MeListPreview() {
    MyTheme {
        MeList()
    }
}
