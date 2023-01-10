package com.example.e_commerce.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.e_commerce.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        singUp.setOnClickListener {
            singUp.background = resources.getDrawable(R.drawable.switch_trcks, null)
            singUp.setTextColor(resources.getColor(R.color.teal_700, null))
            logIn.background = null
            singUpLayout.visibility = View.VISIBLE
            logInLayout.visibility = View.GONE
            logIn.setTextColor(resources.getColor(R.color.purple_200, null))
        }
        logIn.setOnClickListener {
            singUp.background = null
            singUp.setTextColor(resources.getColor(R.color.purple_500, null))
            logIn.background = resources.getDrawable(R.drawable.switch_trcks, null)
            singUpLayout.visibility = View.GONE
            logInLayout.visibility = View.VISIBLE
            logIn.setTextColor(resources.getColor(R.color.teal_700, null))
        }
        singIn.setOnClickListener {
            var emails = eMail.text.toString()
            var passwords = passwords.text.toString()
            if (logInLayout.visibility == View.VISIBLE) {
                if (emails == "" || passwords == "") {
                    Toast.makeText(
                        activity,
                        "Lütfen E-Mail veya password girişi yapmadınız.Lütfen yapınız.",
                        Toast.LENGTH_LONG

                    ).show()
                }
                else {
                    auth.signInWithEmailAndPassword(emails, passwords).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val guncelKullanici1 = auth.currentUser?.email.toString()
                            Toast.makeText(
                                activity,
                                "Hoşgeldiniz: ${guncelKullanici1}",
                                Toast.LENGTH_LONG
                            ).show()
                            //logFragment(ProductList())
                            activity?.let {
                                val intent=Intent(it,MainActivity::class.java)
                                it.startActivity(intent)
                            }
                        }
                    }.addOnFailureListener { exception ->
                        Toast.makeText(activity, exception.localizedMessage, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            } else if (singUpLayout.visibility == View.VISIBLE) {
                var email = eMails.text.toString()
                var password = passwordss.text.toString()
                if (email == "" || password == "") {
                    Toast.makeText(
                        activity,
                        "Lütfen E-Mail veya password girişi yapmadınız.Lütfen yapınız.",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val guncelKullanici1 = auth.currentUser?.email.toString()
                                //Toast.makeText(activity,"${guncelKullanici1} Oluşturuldu.",Toast.LENGTH_LONG).show()
                                val toast = Toast(activity)
                                toast.setText("${guncelKullanici1} Oluşturuldu.")
                                toast.setGravity(Gravity.CENTER, 0, 0)
                                toast.duration = Toast.LENGTH_LONG
                                toast.show()
                            }
                        }
                }
            }
        }
    }

}