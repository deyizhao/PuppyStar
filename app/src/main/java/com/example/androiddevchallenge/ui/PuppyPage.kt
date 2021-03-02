package com.example.androiddevchallenge.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Msg
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.data.User
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlinx.coroutines.delay

@Composable
fun PuppyPage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
    puppy: Puppy?,
    onBack: (() -> Unit)
) {
    if (puppy != null) {
        viewModel.read(puppy)
        var shakingTime by remember { mutableStateOf(0) }
        Scaffold(
            modifier.background(MyTheme.colors.background),
            topBar = {
                WeTopBar(puppy.name, onBack)
            },
            bottomBar = {
                ChatBottomBar(onBombClicked = {
                    viewModel.boom()
                    shakingTime += 1
                })
            }
        ) { paddingValues ->
            val shakingOffset = remember { Animatable(0f) }
            LaunchedEffect(key1 = shakingTime, block = {
                if (shakingTime != 0) {
                    shakingOffset.animateTo(
                        0f,
                        animationSpec = spring(0.3f, 600f),
                        initialVelocity = -2000f
                    )
                }
            })
            Spacer(
                Modifier
                    .background(MyTheme.colors.chatPage)
                    .fillMaxSize()
            )
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .offset(shakingOffset.value.dp, shakingOffset.value.dp)
                    .padding(paddingValues)
            ) {
                items(puppy.msg!!.size) { index ->
                    val msg = puppy.msg!![index]
                    MessageItem(msg, shakingTime, puppy.msg!!.size - index - 1)
                }
            }
        }
    }
}

@Composable
fun ChatBottomBar(onBombClicked: () -> Unit) {
    var editingText by remember { mutableStateOf("") }
    WeBottomBar {
        BasicTextField(
            editingText, onValueChange = { editingText = it },
            Modifier
                .weight(1f)
                .padding(4.dp, 8.dp)
                .height(40.dp)
                .clip(MaterialTheme.shapes.small)
                .background(MyTheme.colors.background)
                .padding(start = 8.dp, top = 10.dp, end = 8.dp),
            cursorBrush = SolidColor(MyTheme.colors.textPrimary)
        )
        Text(
            "\uD83D\uDE0D",
            Modifier
                .clickable(onClick = onBombClicked)
                .padding(4.dp)
                .align(Alignment.CenterVertically),
            fontSize = 24.sp
        )
        Icon(
            painterResource(R.mipmap.ic_add),
            contentDescription = null,
            Modifier
                .align(Alignment.CenterVertically)
                .padding(4.dp)
                .size(28.dp),
            tint = MyTheme.colors.icon
        )
    }
}

@Composable
fun MessageItem(msg: Msg, shakingTime: Int, shakingLevel: Int) {
    val shakingAngleBubble = remember { Animatable(0f) }
    LaunchedEffect(key1 = shakingTime, block = {
        if (shakingTime != 0) {
            delay(shakingLevel.toLong() * 30)
            shakingAngleBubble.animateTo(
                0f,
                animationSpec = spring(0.4f, 500f),
                initialVelocity = 1200f / (1 + shakingLevel * 0.4f)
            )
        }
    })
    if (msg.from == User.Me) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            val bubbleColor = MyTheme.colors.bubbleMe
            Text(msg.text,
                Modifier
                    .graphicsLayer(
                        rotationZ = shakingAngleBubble.value,
                        transformOrigin = TransformOrigin(1f, 0f)
                    )
                    .drawBehind {
                        val bubble = Path().apply {
                            val rect = RoundRect(
                                10.dp.toPx(),
                                0f,
                                size.width - 10.dp.toPx(),
                                size.height,
                                4.dp.toPx(),
                                4.dp.toPx()
                            )
                            addRoundRect(rect)
                            moveTo(size.width - 10.dp.toPx(), 15.dp.toPx())
                            lineTo(size.width - 5.dp.toPx(), 20.dp.toPx())
                            lineTo(size.width - 10.dp.toPx(), 25.dp.toPx())
                            close()
                        }
                        drawPath(bubble, bubbleColor)
                    }
                    .padding(20.dp, 10.dp),
                color = MyTheme.colors.textPrimary)
            Image(
                painterResource(msg.from.avatar),
                contentDescription = "Avatar Me",
                Modifier
                    .graphicsLayer(
                        rotationZ = shakingAngleBubble.value * 0.6f,
                        transformOrigin = TransformOrigin(1f, 0f)
                    )
                    .size(40.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
        }
    } else {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painterResource(msg.from.avatar),
                contentDescription = "Avatar Me",
                Modifier
                    .graphicsLayer(
                        rotationZ = -shakingAngleBubble.value * 0.6f,
                        transformOrigin = TransformOrigin(0f, 0f)
                    )
                    .size(40.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            val bubbleColor = MyTheme.colors.bubbleOthers
            Text(msg.text,
                Modifier
                    .graphicsLayer(
                        rotationZ = -shakingAngleBubble.value,
                        transformOrigin = TransformOrigin(0f, 0f)
                    )
                    .drawBehind {
                        val bubble = Path().apply {
                            val rect = RoundRect(
                                10.dp.toPx(),
                                0f,
                                size.width - 10.dp.toPx(),
                                size.height,
                                4.dp.toPx(),
                                4.dp.toPx()
                            )
                            addRoundRect(rect)
                            moveTo(10.dp.toPx(), 15.dp.toPx())
                            lineTo(5.dp.toPx(), 20.dp.toPx())
                            lineTo(10.dp.toPx(), 25.dp.toPx())
                            close()
                        }
                        drawPath(bubble, bubbleColor)
                    }
                    .padding(20.dp, 10.dp),
                color = MyTheme.colors.textPrimary)
        }
    }
}