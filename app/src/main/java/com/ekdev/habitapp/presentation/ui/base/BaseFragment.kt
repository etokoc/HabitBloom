package com.ekdev.habitapp.presentation.ui.base

import android.os.Build
import android.view.View
import androidx.fragment.app.Fragment
import com.ekdev.habitapp.R

open class BaseFragment : Fragment() {

    open fun showFullUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.insetsController?.apply {
                hide(android.view.WindowInsets.Type.systemBars())
                systemBarsBehavior =
                    android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            requireActivity().window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }
    }

    open fun hideBottomNavigationView() {
        val bottomNav = requireActivity().findViewById<View>(R.id.bottomNavigationView)
        bottomNav?.visibility = View.GONE
    }

    open fun showBottomNavigationView() {
        val bottomNav = requireActivity().findViewById<View>(R.id.bottomNavigationView)
        bottomNav?.visibility = View.VISIBLE
    }
}
