package com.talib.myapplication.splash

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.talib.myapplication.Constans
import com.talib.myapplication.PreferenceHelper
import com.talib.myapplication.PreferenceHelper.get
import com.talib.myapplication.PreferenceHelper.set
import com.talib.myapplication.R


class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs: SharedPreferences? = activity?.getSharedPreferences(Constans.SHARED_PREF_NAME, MODE_PRIVATE)
        val isLogged: Boolean = prefs?.getBoolean(Constans.IS_LOGGED,false) == true


        val runnable = Runnable {
            if (isLogged) {
                findNavController().navigate(R.id.action_splash_to_main)
            } else {
                findNavController().navigate(R.id.action_splash_to_login)
            }
        }

        Handler(Looper.getMainLooper()).postDelayed(runnable, 3000)
    }

}