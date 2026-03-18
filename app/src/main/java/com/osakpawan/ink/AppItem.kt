package com.osakpawan.ink

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmapOrNull

data class AppInfo (
    val name: String,
    val packageName: String,
    val icon: Drawable,
    val isDefault: Boolean
)

fun getInstalledApps(context: Context): List<AppInfo> {
    val pm = context.packageManager
    val intent = Intent(Intent.ACTION_MAIN, null).apply {
        addCategory(Intent.CATEGORY_LAUNCHER)
    }
    val apps = pm.queryIntentActivities(intent, 0)

    return apps.map {
        AppInfo(it.loadLabel(pm).toString(),
            it.activityInfo.packageName, it.loadIcon(pm), it.isDefault)
    }.sortedBy { it.name }
}

@Composable
fun AppColumnItem (app: AppInfo) {
    val context = LocalContext.current
    Row(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
        .clickable {
            val intent = context.packageManager
                .getLaunchIntentForPackage(app.packageName)
            context.startActivity(intent)
        }) {
        app.icon.toBitmapOrNull()?.
        asImageBitmap()?.let { Image(bitmap = it
            , contentDescription = app.name, modifier = Modifier.width(45.dp)
                .height(45.dp)) }

        Spacer(modifier = Modifier.width(16.dp))
        Text(app.name)
    }
}

@Composable
fun AppIconItem(app: AppInfo) {
    val context = LocalContext.current
    val icon = app.icon.toBitmapOrNull()?.asImageBitmap()
    TextButton(onClick = {
        val intent = context.packageManager.getLaunchIntentForPackage(app.packageName)
        context.startActivity(intent)
    }) {
        icon?.let {
            Image(bitmap = it, contentDescription = app.name,
                modifier = Modifier.size(40.dp))
        }
    }
}