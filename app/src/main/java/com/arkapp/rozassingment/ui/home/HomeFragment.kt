package com.arkapp.rozassingment.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arkapp.rozassingment.R
import com.arkapp.rozassingment.data.repository.PrefRepository
import com.arkapp.rozassingment.utils.showSnack
import com.arkapp.rozassingment.utils.signOut
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefRepository = PrefRepository(requireContext())
        parent.showSnack("Welcome! ${prefRepository.getUser().username}")

        btLogout.setOnClickListener {
            parent.showSnack("Logged Out!")
            signOut()
            prefRepository.setLoggedIn(false)
            findNavController().navigate(R.id.action_homeFragment_to_splashFragment)
        }
    }
}