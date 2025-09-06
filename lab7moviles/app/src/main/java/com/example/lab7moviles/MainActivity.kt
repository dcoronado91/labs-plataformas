package com.example.lab7moviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab7moviles.ui.theme.Lab7movilesTheme
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val all = generateFakeNotifications(50)
        setContent {
            Lab7movilesTheme(darkTheme = false) {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NotificationScreen(all)
                }
            }
        }
    }
}

@Composable
fun NotificationScreen(allNotifications: List<AppNotification>) {
    var selectedType by remember { mutableStateOf<NotificationType?>(NotificationType.INFORMATIVA as NotificationType?) }
    val notifications = remember { allNotifications.sortedByDescending { it.timestamp } }

    val filtered = remember(notifications, selectedType) {
        if (selectedType == null) notifications else notifications.filter { it.type == selectedType }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(
            "Tipos de notificaciones",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(4.dp))
            FilterChip(
                label = "Informativas",
                selected = selectedType == NotificationType.INFORMATIVA,
                onClick = {
                    selectedType = (if (selectedType == NotificationType.INFORMATIVA) null else NotificationType.INFORMATIVA)
                },
                leadingCheck = true
            )
            Spacer(modifier = Modifier.width(12.dp))
            FilterChip(
                label = "Capacitaciones",
                selected = selectedType == NotificationType.CAPACITACION,
                onClick = {
                    selectedType = (if (selectedType == NotificationType.CAPACITACION) null else NotificationType.CAPACITACION)
                },
                leadingCheck = false
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 0.dp,
            shadowElevation = 2.dp,
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            if (filtered.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No hay notificaciones", style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filtered, key = { it.id }) { item ->
                        NotificationRow(appNotification = item)
                    }
                }
            }
        }
    }
}

@Composable
fun FilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    leadingCheck: Boolean
) {
    val background = if (selected) {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
    } else {
        Color.Transparent
    }
    val borderColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
    val textColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Surface(
        modifier = Modifier
            .height(44.dp)
            .wrapContentWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
        color = background,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.5.dp, borderColor)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 12.dp)) {
            if (selected && leadingCheck) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                color = textColor,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun NotificationRow(appNotification: AppNotification) {
    val (bgColor, icon) = when (appNotification.type) {
        NotificationType.INFORMATIVA -> Pair(MaterialTheme.colorScheme.secondaryContainer, Icons.Default.Notifications)
        NotificationType.CAPACITACION -> Pair(MaterialTheme.colorScheme.tertiaryContainer, Icons.Default.CalendarToday)
    }
    val contentColor = MaterialTheme.colorScheme.contentColorFor(bgColor)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(bgColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = appNotification.title,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = appNotification.timestamp.format(DateTimeFormatter.ofPattern("dd MMM • h:mm a", Locale.getDefault())),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = appNotification.body,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3
            )
        }
    }
}