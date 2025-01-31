package ru.anura.tasks.fragments.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.anura.tasks.databinding.FragmentFirstBinding
import ru.anura.tasks.fragments.MenuFragment

class FirstFragment: Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding
        get() = _binding ?: throw RuntimeException("FragmentFirstBinding == null")



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext1.setOnClickListener {
            (activity as? RouterActivity)?.navigateTo(SecondFragment())
        }
        binding.btnPrevious1.setOnClickListener {
            (activity as? RouterActivity)?.navigateTo(MenuFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val NAME = "FirstFragment"
        fun newInstance(): FirstFragment {
            return FirstFragment()
        }
    }
}