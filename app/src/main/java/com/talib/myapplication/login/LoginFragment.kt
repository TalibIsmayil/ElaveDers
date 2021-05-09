package com.talib.myapplication.login

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.talib.myapplication.Constans
import com.talib.myapplication.R


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var forgotText: TextView
    private lateinit var btnLogin: Button
    private lateinit var edtMail: EditText
    private lateinit var edtPass: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forgotText = view.findViewById(R.id.forgotPass)
        btnLogin = view.findViewById(R.id.btnLogin)
        edtMail = view.findViewById(R.id.edtMail)
        edtPass = view.findViewById(R.id.edtPassword)

        forgotText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPassFragment)
        }


        btnLogin.setOnClickListener {
            if (edtMail.text.trim().isNotEmpty() && edtPass.text.trim().isNotEmpty()) {
                if (edtPass.text.trim().length > 6) {
                    if (Patterns.EMAIL_ADDRESS.matcher(edtMail.text.trim().toString()).matches()) {
                        doLogin()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Please enter valid mail address",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Password must be greater than 6",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please enter all fields", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }

    fun doLogin() {
        val editor: SharedPreferences.Editor? =
            activity?.getSharedPreferences(Constans.SHARED_PREF_NAME, MODE_PRIVATE)?.edit()
        editor?.putBoolean(Constans.IS_LOGGED, true)
        editor?.apply()

        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
    }

}