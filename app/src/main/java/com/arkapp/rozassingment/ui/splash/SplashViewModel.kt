package com.arkapp.rozassingment.ui.splash

import android.view.View
import androidx.lifecycle.ViewModel
import com.arkapp.rozassingment.data.repository.PrefRepository
import com.arkapp.rozassingment.utils.isDoubleClicked
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Abdul Rehman on 22-08-2020.
 * Contact email - abdulrehman0796@gmail.com
 */
class SplashViewModel(private val prefRepository: PrefRepository) : ViewModel() {
    var listener: SplashListener? = null

    fun checkLogin() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            try {
                if (prefRepository.isLoggedIn())
                    listener?.openApp()
                else
                    listener?.showLoginButtons()
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }

    fun onPhoneClicked(view: View) {
        if (isDoubleClicked(1000)) return
        listener?.openPhoneLogin()
    }

    fun onTrueCallerClicked(view: View) {
        if (isDoubleClicked(1000)) return
        listener?.openTrueCallerLogin()
    }
}