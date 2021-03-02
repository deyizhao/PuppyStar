package com.example.androiddevchallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun LoveListTopBar() {
    WeTopBar(title = "我喜爱的puppy")
}

@Preview(showBackground = true)
@Composable
fun LoveListTopBarPreview() {
    LoveListTopBar()
}

@Composable
fun LoveList(viewModel: MainViewModel = viewModel()) {
    Column(Modifier.fillMaxSize()) {
        LoveListTopBar()
        Box(
            Modifier
                .background(MyTheme.colors.background)
                .fillMaxSize()
        ) {
            LoveList(viewModel.puppys.filter { it.like })
        }
    }
}

@Composable
fun LoveList(lovePuppys: List<Puppy>) {
    LazyColumn(
        Modifier
            .background(MyTheme.colors.listItem)
            .fillMaxWidth()
    ) {
        itemsIndexed(lovePuppys) { index, puppy ->
            PuppyListItem(puppy)
            if (index < lovePuppys.size - 1) {
                Divider(
                    startIndent = 68.dp,
                    color = MyTheme.colors.chatListDivider,
                    thickness = 0.8f.dp
                )
            }
        }
    }
}
