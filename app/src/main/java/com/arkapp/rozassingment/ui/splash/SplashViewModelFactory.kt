package com.arkapp.rozassingment.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arkapp.rozassingment.data.repository.PrefRepository

class SplashViewModelFactory(private val prefRepository: PrefRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashViewModel(prefRepository) as T
    }
}