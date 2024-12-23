package ru.anura.tasks

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.anura.tasks.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // задача 2
    private val time by TimeCacher {
        getFormattedLaunchTime(System.currentTimeMillis())
    }

    private fun getFormattedLaunchTime(time: Long): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return dateFormat.format(time)
    }
    //

    // задача 3
    private fun createList() = listOf(
        42,
        "Hello",
        true,
        'A',
        3.14,
        100L,
        3.1415f,
        null,
        "Kotlin",
        'Z',
        11,
        3
    )
    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTime.text = time // задача 2

        // задача 3
        val items = createList()
        binding.theSecondButton.setOnClickListener {
            val ifFound = items.findInt {
                Log.d("SearchingInt", it.toString())
            }
            if (!ifFound) {
                Log.d("SearchingInt", "В List нет Int")
            }
        }
        //

        // задача 4
        val listForTheThirdTask = listOf(4, 65, 2, null, -31, 0, null, 99, 2, 83, 782, 1)
        //val listForTheThirdTask = null
        val sortedList = shakerSort(listForTheThirdTask)
        Log.d("SortingList", "$listForTheThirdTask,\n${sortedList.toString()}")
        //
    }
}






