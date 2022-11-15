package com.weightwatchers.community.groups.common.views

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.weightwatchers.theme.WWTheme

@VisibleForTesting
internal const val TEST_TAG_LOADING = "loading"

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .testTag(TEST_TAG_LOADING)
    ) {
        CircularProgressIndicator(color = WWTheme.colors.ww100)
    }
}
