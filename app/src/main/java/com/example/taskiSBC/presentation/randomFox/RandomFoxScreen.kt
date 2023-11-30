package com.example.taskiSBC.presentation.randomFox

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.taskiSBC.R
import com.example.taskiSBC.presentation.common.components.CustomProgressLoader
import com.example.taskiSBC.presentation.common.ui.theme.ContainerBackgroundColor
import com.example.taskiSBC.presentation.mainscreen.MainViewModel
import ir.kaaveh.sdpcompose.sdp

@Composable
fun RandomFoxScreen(viewModel: MainViewModel) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ContainerBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        state.receivedResponse?.let {
            FoxScreenContent(viewModel)
        }
        if (state.isLoading) {
            CustomProgressLoader()
        } else if (state.error.isNotEmpty()) {
            Text(
                text = state.error,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun FoxScreenContent(
    viewModel: MainViewModel
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    var scaleType by remember {
        mutableStateOf(ContentScale.Crop)
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clickable(indication = null, interactionSource = MutableInteractionSource()) {
                    scaleType =
                        if (scaleType == ContentScale.Crop) ContentScale.Fit else ContentScale.Crop
                },
            model = state.receivedResponse?.image,
            contentDescription = "random_image",
            contentScale = scaleType,
            loading = {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CustomProgressLoader()
                }
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(8.sdp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.sdp),
                onClick = { viewModel.changeRandomFox() }
            ) {
                Text(text = stringResource(id = R.string.Change))
            }
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.sdp),
                onClick = { viewModel.openWithChromeTab(context = context) }
            ) {
                Text(text = stringResource(id = R.string.Source))
            }
        }
    }
}