package mobile.cs205.services

import android.content.Context

interface NotificationService {
    fun showNotification(
        contentTitle: String,
        contentText: String
    )
}