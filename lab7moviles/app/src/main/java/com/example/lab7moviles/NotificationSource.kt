package com.example.lab7moviles

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.random.Random

fun generateFakeNotifications(count: Int = 50): List<AppNotification> {
    val list = mutableListOf<AppNotification>()
    val now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)

    val titlesInfo = listOf(
        "Nueva versión disponible",
        "Aviso importante",
        "Mantenimiento programado"
    )

    val titlesCap = listOf(
        "Nueva capacitación",
        "Recordatorio: capacitación",
        "Capacitación confirmada"
    )

    for (i in 1..count) {
        val isCap = Random.nextBoolean()
        val type = if (isCap) NotificationType.CAPACITACION else NotificationType.INFORMATIVA
        val title = if (isCap) titlesCap.random() else titlesInfo.random()
        val body = if (isCap) {
            "El día ${listOf("Lunes","Martes","Miércoles","Jueves","Viernes").random()} tendremos una nueva capacitación en INTECAP, no faltes!"
        } else {
            "La aplicación ha sido actualizada a v${Random.nextInt(1,3)}.${Random.nextInt(0,9)}.${Random.nextInt(0,9)}. Ve a la PlayStore y actualízala!"
        }
        val ts = now.minusMinutes(Random.nextLong(0, 60L * 24 * 30)) // notificaciones en los últimos 30 días

        list += AppNotification(i, title, body, ts, type)
    }

    return list
}
