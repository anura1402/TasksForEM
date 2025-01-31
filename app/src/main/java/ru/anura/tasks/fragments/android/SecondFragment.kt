package ru.anura.tasks.fragments.android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.anura.tasks.databinding.FragmentFirstBinding
import ru.anura.tasks.databinding.FragmentSecondBinding

class SecondFragment: Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding: FragmentSecondBinding
        get() = _binding ?: throw RuntimeException("FragmentSecondBinding == null")



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext2.setOnClickListener {
            (activity as? RouterActivity)?.navigateTo(ThirdFragment())
        }
        binding.btnPrevious2.setOnClickListener {
            (activity as? RouterActivity)?.navigateTo(FirstFragment())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val NAME = "SecondFragment"
        fun newInstance(): SecondFragment {
            return SecondFragment()
        }
    }
}