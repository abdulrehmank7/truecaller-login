package com.arkapp.rozassingment.utils

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.arkapp.rozassingment.R
import com.truecaller.android.sdk.ITrueCallback
import com.truecaller.android.sdk.TruecallerSDK
import com.truecaller.android.sdk.TruecallerSdkScope


/**
 * Created by Abdul Rehman on 23-08-2020.
 * Contact email - abdulrehman0796@gmail.com
 */

fun Fragment.initTrueCaller(callback: ITrueCallback) {
    val trueScope = TruecallerSdkScope.Builder(requireContext(), callback)
        .consentMode(TruecallerSdkScope.CONSENT_MODE_BOTTOMSHEET)
        .buttonColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
        .buttonTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        .loginTextPrefix(TruecallerSdkScope.LOGIN_TEXT_PREFIX_TO_GET_STARTED)
        .loginTextSuffix(TruecallerSdkScope.LOGIN_TEXT_SUFFIX_PLEASE_VERIFY_MOBILE_NO)
        .ctaTextPrefix(TruecallerSdkScope.CTA_TEXT_PREFIX_CONTINUE_WITH)
        .buttonShapeOptions(TruecallerSdkScope.BUTTON_SHAPE_ROUNDED)
        .privacyPolicyUrl("https://www.truecaller.com")
        .termsOfServiceUrl("https://www.truecaller.com")
        .footerType(TruecallerSdkScope.FOOTER_TYPE_NONE)
        .consentTitleOption(TruecallerSdkScope.SDK_CONSENT_TITLE_LOG_IN)
        .sdkOptions(TruecallerSdkScope.SDK_OPTION_WITHOUT_OTP)
        .build()

    TruecallerSDK.init(trueScope)
}

fun isTrueCallerInstalled() = TruecallerSDK.getInstance().isUsable

fun Fragment.openTrueCallerVerification() = TruecallerSDK.getInstance().getUserProfile(this)

