package com.osakpawan.ink

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
fun HomeScreen() {1
    val apps = getInstalledApps(LocalContext.current)
    var time by remember { mutableStateOf("") }
    val formatter = remember { DateTimeFormatter.ofPattern("HH:mm:ss") }

    var dateString by remember { mutableStateOf("") }

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
    Column() {
        Text(text = time, modifier = Modifier.fillMaxWidth()
            .padding(top = 50.dp),
            fontSize = 60.sp, textAlign = TextAlign.Center)
    }

    Text(text = dateString, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
}