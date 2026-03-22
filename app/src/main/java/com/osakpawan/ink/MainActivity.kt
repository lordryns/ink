package com.osakpawan.ink

import android.Manifest
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmapOrNull
import com.osakpawan.ink.ui.theme.InkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(innerPadding)
                }
            }
        }
    }
}


@Composable
fun App(innerPadding: PaddingValues) {
    val homeScreenApps = remember { mutableStateListOf<AppInfo>() }
    val context = LocalContext.current
    val wallpaperManager = WallpaperManager.getInstance(context)
    val drawable = wallpaperManager.drawable

    val wallpaper = drawable?.toBitmapOrNull()?.asImageBitmap()

    wallpaper?.let {
        Image(bitmap = it, contentDescription = null, modifier = Modifier.fillMaxHeight(),
            contentScale = ContentScale.Crop)
    }

   Column(modifier = Modifier.padding(top = 30.dp)) {
       // OutlinedTextField(onValueChange = {})
       // LauncherScreen(context)

       HomeScreen(innerPadding, homeScreenApps)
   }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InkTheme {

    }
}