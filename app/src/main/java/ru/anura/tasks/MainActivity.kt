package ru.anura.tasks

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.anura.tasks.databinding.ActivityMainBinding
import ru.anura.tasks.kotlin.TimeCacher
import ru.anura.tasks.kotlin.findInt
import ru.anura.tasks.kotlin.shakerSort
import ru.anura.tasks.rxJava.RxJavaTasks
import java.text.SimpleDateFormat
import java.util.Locale


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}