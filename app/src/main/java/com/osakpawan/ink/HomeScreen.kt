package com.osakpawan.ink

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.time.delay
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
fun HomeScreen(innerPadding: PaddingValues, homeScreen: MutableList<AppInfo>) {
    BackHandler(enabled = true) { }
    val apps = getInstalledApps(LocalContext.current)
    var time by remember { mutableStateOf("") }
    val formatter = remember { DateTimeFormatter.ofPattern("HH:mm") }

    var dateString by remember { mutableStateOf("") }

    var searchString by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        while (isActive) {
            time = LocalTime.now().format(formatter)
            val today = LocalDate.now(ZoneId.systemDefault())
            val dayOfWeek = today.dayOfWeek
            val dayName = dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, Locale.ENGLISH)

            dateString = dayName

            delay(1000L)
        }
    }
    Box (modifier = Modifier.fillMaxSize().padding(innerPadding)) {
        Column() {
            Text(text = time, modifier = Modifier.fillMaxWidth()
                .padding(top = 50.dp),
                fontSize = 85.sp, textAlign = TextAlign.Center)

            Text(text = dateString, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

            if (homeScreen.size < 5) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    TextButton (onClick = {}, colors = ButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        containerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color.DarkGray
                    )) {
                        Text("Add to home screen")
                        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                    }
                }
            }
        }

        /* Column(modifier = Modifier.fillMaxWidth()
            .align (Alignment.BottomCenter)
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)) {
            BasicTextField(onValueChange = {searchString = it}, value = searchString,
                modifier = Modifier.fillMaxWidth()
                    .border(width = 1.dp, color = Color.Gray)
                    .padding(10.dp), textStyle = TextStyle(color = Color.White))
        } */
    }



}