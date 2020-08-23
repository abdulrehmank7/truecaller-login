package com.arkapp.rozassingment.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.arkapp.rozassingment.R
import com.arkapp.rozassingment.data.models.Users
import com.arkapp.rozassingment.data.repository.PrefRepository
import com.arkapp.rozassingment.databinding.FragmentSplashBinding
import com.arkapp.rozassingment.utils.*
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.truecaller.android.sdk.ITrueCallback
import com.truecaller.android.sdk.TrueError
import com.truecaller.android.sdk.TrueProfile
import com.truecaller.android.sdk.TruecallerSDK


class SplashFragment : Fragment(), SplashListener, ITrueCallback {

    private lateinit var prefRepository: PrefRepository
    private lateinit var viewModel: SplashViewModel
    private lateinit var binding: FragmentSplashBinding
    private var topAnimation: Animation? = null
    private var bottomAnimation: Animation? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater)

        prefRepository = PrefRepository((requireContext()))
        val factory = SplashViewModelFactory(prefRepository)
        viewModel = ViewModelProvider(this, factory).get(SplashViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.listener = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.top_anim)
        bottomAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.botton_anim)

        binding.ivTop.animation = topAnimation
        binding.tvAppName.animation = bottomAnimation
        binding.tvSlogan.animation = bottomAnimation

        viewModel.checkLogin()
    }

    override fun showLoginButtons() {
        binding.btPhone.animation = bottomAnimation
        binding.btTrueCaller.animation = bottomAnimation

        binding.groupLogo.hide()
        binding.groupLoginButton.show()

        initTrueCaller(this)

    }

    override fun openApp() {
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
    }

    override fun openPhoneLogin() {
        binding.progressPhone.show()
        openFirebasePhoneLogin()
    }

    override fun openTrueCallerLogin() {
        if (isTrueCallerInstalled())
            openTrueCallerVerification()
        else
            binding.parent.showSnack("Truecaller not installed! Please try different method...")

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        binding.progressPhone.hide()
        binding.progressTrueCaller.hide()

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                prefRepository.setUser(Users("", user?.phoneNumber!!))
                openHome()
            } else
                binding.parent.showSnack("Oops! Something went wrong...")
        } else {
            TruecallerSDK.getInstance()
                .onActivityResultObtained(requireActivity(), resultCode, data)
        }
    }

    private fun openHome() {
        binding.parent.showSnack("Successfully logged in...")
        prefRepository.setLoggedIn(true)
        openApp()
    }

    override fun onSuccessProfileShared(p0: TrueProfile) {
        prefRepository.setUser(Users(p0.firstName, p0.phoneNumber))
        openHome()
    }

    override fun onFailureProfileShared(p0: TrueError) {
        binding.parent.showSnack("Error in Truecaller login!")
    }

    override fun onVerificationRequired() {
        binding.parent.showSnack("Error in Truecaller login!")
    }
}