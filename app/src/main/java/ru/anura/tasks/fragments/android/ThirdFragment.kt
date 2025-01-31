package ru.anura.tasks.fragments.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.anura.tasks.databinding.FragmentFirstBinding
import ru.anura.tasks.databinding.FragmentThirdBinding

class ThirdFragment: Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private val binding: FragmentThirdBinding
        get() = _binding ?: throw RuntimeException("FragmentThirdBinding == null")



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext3.setOnClickListener {
            (activity as? RouterActivity)?.navigateTo(FirstFragment())
        }
        binding.btnPrevious3.setOnClickListener {
            (activity as? RouterActivity)?.navigateTo(SecondFragment())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val NAME = "ThirdFragment"
        fun newInstance(): ThirdFragment {
            return ThirdFragment()
        }
    }
}