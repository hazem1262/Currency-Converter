package com.hazem.currency_converter.utils.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hazem.currency_converter.R
import com.hazem.currency_converter.utils.network.ApplicationException

@Composable
fun ErrorComposable(
    applicationException: ApplicationException,
    onRetry: () -> Unit
) {
    return Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = applicationException.errorMessage?:"", modifier = Modifier.padding(16.dp))
        Button(onClick = { onRetry.invoke() }) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}