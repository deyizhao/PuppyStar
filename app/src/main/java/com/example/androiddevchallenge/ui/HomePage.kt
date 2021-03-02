package com.example.androiddevchallenge.ui

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Msg
import com.example.androiddevchallenge.data.User
import com.example.androiddevchallenge.percentOffsetX
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun HomeBottomBar(current: Int, currentChanged: (Int) -> Unit) {
    WeBottomBar {
        HomeBottomItem(
            Modifier
                .weight(1f)
                .clickable { currentChanged(0) },
            R.drawable.ic_home_outlined,
            "首页",
            if (current == 0) MyTheme.colors.iconCurrent else MyTheme.colors.icon
        )
        HomeBottomItem(
            Modifier
                .weight(1f)
                .clickable { currentChanged(1) },
            R.drawable.ic_discover_outlined,
            "喜爱",
            if (current == 1) MyTheme.colors.iconCurrent else MyTheme.colors.icon
        )
        HomeBottomItem(
            Modifier
                .weight(1f)
                .clickable { currentChanged(2) },
            R.drawable.ic_my_outlined,
            "我",
            if (current == 2) MyTheme.colors.iconCurrent else MyTheme.colors.icon
        )
    }
}

@Composable
fun HomeBottomItem(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    title: String,
    tint: Color
) {
    Column(
        modifier.padding(0.dp, 8.dp, 0.dp, 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painterResource(iconId), null, Modifier.size(24.dp), tint = tint)
        Text(title, fontSize = 11.sp, color = tint)
    }
}

@Preview(showBackground = true)
@Composable
fun WeBottomPreview() {
    HomeBottomBar(0) {}
}

@Composable
fun Home() {
    val viewModel: MainViewModel = viewModel()
    Log.i("Home", "Home")
    Box {
        Column(Modifier.fillMaxSize()) {
            val pagerState: PagerState = run {
                remember(viewModel.theme) { PagerState(maxPage = 2) }
            }
            Pager(pagerState, Modifier.weight(1f)) {
                when (page) {
                    0 -> {
                        PuppyList()
                    }
                    1 -> {
                        LoveList()
                    }
                    2 -> {
                        MeList()
                    }
                }
            }
            HomeBottomBar(pagerState.currentPage) {
                pagerState.currentPage = it
            }
        }
        val openOffset by animateFloatAsState(
            if (viewModel.openModule == null) {
                1f
            } else {
                0f
            }
        )
        if (viewModel.currentPuppy != null) {

            if (viewModel.currentPuppy!!.msg.isNullOrEmpty()) {
                viewModel.currentPuppy!!.msg?.add(
                    Msg(
                        User.Me, "hello"
                    )
                )
                viewModel.currentPuppy!!.msg?.add(
                    Msg(
                        User(
                            viewModel.currentPuppy!!.name,
                            viewModel.currentPuppy!!.avatar
                        ), "My Name Is ${viewModel.currentPuppy!!.name}"
                    )
                )
                viewModel.currentPuppy!!.msg?.add(
                    Msg(
                        User(
                            viewModel.currentPuppy!!.name,
                            viewModel.currentPuppy!!.avatar
                        ), " ${viewModel.currentPuppy!!.intro}"
                    )
                )
                viewModel.currentPuppy!!.msg?.add(
                    Msg(
                        User(
                            viewModel.currentPuppy!!.name,
                            viewModel.currentPuppy!!.avatar
                        ), " Do You Love Me?"
                    )
                )
            }
            PuppyPage(
                Modifier.percentOffsetX(openOffset),
                puppy = viewModel.currentPuppy
            ) {
                viewModel.dismissPuppyDetail()
            }
        }
    }
}