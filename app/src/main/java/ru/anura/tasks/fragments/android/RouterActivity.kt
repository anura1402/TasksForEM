package ru.anura.tasks.fragments.android

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import ru.anura.tasks.R
import ru.anura.tasks.android.ChargingReceiver
import ru.anura.tasks.android.MyWorker
import ru.anura.tasks.databinding.ActivityRouterBinding
import ru.anura.tasks.databinding.FragmentFirstBinding

class RouterActivity : AppCompatActivity() {

    private var _binding: ActivityRouterBinding? = null
    private val binding: ActivityRouterBinding
        get() = _binding ?: throw RuntimeException("ActivityRouterBinding == null")

    private lateinit var chargingReceiver: ChargingReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRouterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("AndTest", "onCreate Router")

        binding.buttonFragments.setOnClickListener {
            navigateTo(FirstFragment())
        }

        val filter = IntentFilter(Intent.ACTION_POWER_CONNECTED)
        val batteryManager =
            applicationContext.getSystemService(Context.BATTERY_SERVICE) as BatteryManager

        binding.buttonWorkManager.setOnClickListener {
            val workManager = WorkManager.getInstance(applicationContext)

            if (batteryManager.isCharging) {
                workManager.enqueueUniqueWork(
                    MyWorker.WORK_NAME,
                    ExistingWorkPolicy.REPLACE,
                    MyWorker.makeRequest()
                )
            }
            chargingReceiver = ChargingReceiver(workManager)
            registerReceiver(chargingReceiver, filter)
            binding.buttonWorkManager.isClickable = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        WorkManager.getInstance(applicationContext)
            .cancelUniqueWork(MyWorker.WORK_NAME)
        unregisterReceiver(chargingReceiver)
    }

    fun navigateTo(fragment: Fragment) {
        binding.buttonFragments.isVisible = false
        binding.buttonRectangle.isVisible = false
        binding.buttonWorkManager.isVisible = false
        supportFragmentManager.beginTransaction()
            .replace(R.id.router_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}