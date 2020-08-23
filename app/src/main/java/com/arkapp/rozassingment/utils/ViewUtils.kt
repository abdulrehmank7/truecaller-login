package com.arkapp.rozassingment.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


fun Activity.openActivity(toActivityClass: Class<*>, finishPreviousScreen: Boolean) {
    val intent = Intent(this, toActivityClass)
    startActivity(intent)

    if (finishPreviousScreen)
        finish()
}

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun Context.showMessageDialog(title: String, message: String, buttonMsg: String) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.setPositiveButton(buttonMsg, null)
    builder.show()
}

fun View.showSnack(msg: String?) {
    try {
        Snackbar.make(
            this,
            msg!!,
            Snackbar.LENGTH_SHORT
        ).show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun View.showSnackLong(msg: String?) {
    try {
        Snackbar.make(
            this,
            msg!!,
            Snackbar.LENGTH_LONG
        ).show()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun View.showIndefiniteSnack(msg: String?): Snackbar? {
    try {
        return Snackbar.make(
            this,
            msg!!,
            Snackbar.LENGTH_INDEFINITE
        ).also { it.show() }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun String?.availableText(): String {
    return if (this.isNullOrEmpty())
        "unavailable"
    else this
}

fun Context.showDialog(
    title: String,
    message: String,
    negativeMsg: String
): MaterialAlertDialogBuilder {

    val dialog = MaterialAlertDialogBuilder(this)
    dialog.setTitle(title)
        .setMessage(message)
        .setNegativeButton(negativeMsg) { dialogInterface, _ -> dialogInterface.dismiss() }

    return dialog
}

private var LAST_CLICK_TIME: Long = 0


fun isDoubleClicked(minimumClickTimeInMilli: Long): Boolean {
    val isClicked: Boolean
    if (getCurrentTimestamp() - LAST_CLICK_TIME < minimumClickTimeInMilli) {
        isClicked = true
    } else {
        LAST_CLICK_TIME = getCurrentTimestamp()
        isClicked = false
    }
    return isClicked
}

fun getCurrentTimestamp(): Long {
    return System.currentTimeMillis()
}