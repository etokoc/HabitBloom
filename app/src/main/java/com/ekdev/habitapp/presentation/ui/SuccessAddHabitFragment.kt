package com.ekdev.habitapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ekdev.habitapp.R
import com.ekdev.habitapp.databinding.FragmentAddHabitBinding
import com.ekdev.habitapp.databinding.FragmentSuccessAddHabitBinding
import com.ekdev.habitapp.presentation.ui.base.BaseFragment

class SuccessAddHabitFragment : BaseFragment() {

    private lateinit var binding: FragmentSuccessAddHabitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSuccessAddHabitBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigationView()
        initUI()
    }

    private fun initUI() {
        binding.apply {
            btnOk.setOnClickListener {
                findNavController().navigate(R.id.action_successAddHabitFragment_to_homeFragment)
            }
        }
    }

    override fun onDestroy() {
        showBottomNavigationView()
        super.onDestroy()
    }
}