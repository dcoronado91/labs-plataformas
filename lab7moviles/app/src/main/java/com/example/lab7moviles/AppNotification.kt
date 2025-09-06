package com.example.lab7moviles

import java.time.LocalDateTime

data class AppNotification(
    val id: Int,
    val title: String,
    val body: String,
    val timestamp: LocalDateTime,
    val type: NotificationType
)
