package com.example.zadanie4

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class Notification : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
       val service = NotificationService(context)
        service.showNotification("Jakis task")
    }
}