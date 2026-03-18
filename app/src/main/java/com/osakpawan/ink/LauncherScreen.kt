package com.osakpawan.ink

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun LauncherScreen(context: Context) {
    val apps = remember { getInstalledApps(context) }
    LazyColumn() {
        items(apps) {
            AppColumnItem(it)
        }
    }
}
