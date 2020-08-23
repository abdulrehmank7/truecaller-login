package com.arkapp.rozassingment.utils

import androidx.fragment.app.Fragment
import com.arkapp.rozassingment.R
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

const val RC_SIGN_IN: Int = 1001

/**
 * Created by Abdul Rehman on 22-08-2020.
 * Contact email - abdulrehman0796@gmail.com
 */

fun Fragment.openFirebasePhoneLogin() {
    val providers = arrayListOf(
        AuthUI.IdpConfig.PhoneBuilder().build()
    )


    startActivityForResult(
        AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTheme(R.style.LoginTheme)
            .build(),
        RC_SIGN_IN
    )
}

fun signOut() {
    if (FirebaseAuth.getInstance().currentUser != null)
        FirebaseAuth.getInstance().signOut()
}