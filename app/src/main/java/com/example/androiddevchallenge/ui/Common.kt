package com.example.androiddevchallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
                .clickable(onClick = {
                  viewModel.theme = when (viewModel.theme) {
                    MyTheme.Theme.Light -> MyTheme.Theme.Dark
                    MyTheme.Theme.Dark -> MyTheme.Theme.Light
                  }
                })
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