package ru.anura.tasks.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.anura.tasks.R
import ru.anura.tasks.databinding.FragmentMenuBinding

class MenuFragment: Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding: FragmentMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentMenuBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonKotlin.setOnClickListener { launchKotlinTasks() }
        binding.buttonRxJava.setOnClickListener { launchRxJavaTasks() }
    }

    private fun launchKotlinTasks() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, KotlinTaskFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun launchRxJavaTasks() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, RxJavaTaskFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}