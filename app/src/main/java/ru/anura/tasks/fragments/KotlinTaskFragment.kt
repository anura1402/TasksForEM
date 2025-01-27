package ru.anura.tasks.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.anura.tasks.databinding.ActivityMainBinding
import ru.anura.tasks.databinding.FragmentKotlinTaskBinding
import ru.anura.tasks.kotlin.TimeCacher
import ru.anura.tasks.kotlin.findInt
import ru.anura.tasks.kotlin.shakerSort
import ru.anura.tasks.rxJava.RxJavaTasks
import java.text.SimpleDateFormat
import java.util.Locale

class KotlinTaskFragment : Fragment() {
    private var _binding: FragmentKotlinTaskBinding? = null
    private val binding: FragmentKotlinTaskBinding
        get() = _binding ?: throw RuntimeException("FragmentKotlinTaskBinding == null")

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKotlinTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val NAME = "KotlinTaskFragment"
        fun newInstance(): KotlinTaskFragment {
            return KotlinTaskFragment()
        }
    }
}

