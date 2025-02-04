package ru.anura.tasks.android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager

class ChargingReceiver(private val workManager: WorkManager) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
         if (intent?.action == Intent.ACTION_POWER_CONNECTED) {
            startWork()
        }
    }

    private fun startWork() {
        workManager.enqueueUniqueWork(
            MyWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            MyWorker.makeRequest()
        )
    }
}

