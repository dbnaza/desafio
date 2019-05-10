package com.dbnaza.desafioenjoei.extensions

import android.app.Activity
import android.os.Build
import android.text.TextUtils.replace
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int) {
    supportFragmentManager.transact {
        replace(frameId, fragment)
    }
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

fun AppCompatActivity.configureToolbar(toolbar: Toolbar) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
}

inline fun <reified T> Activity.extra(key: String): Lazy<T> = lazy {
    val value = intent.extras?.get(key)
    if (value is T) {
        value
    } else {
        throw IllegalArgumentException("Couldn't find extra with key \"$key\" from type " +
                T::class.java.canonicalName)
    }
}

inline fun <reified T> Activity.extra(key: String, crossinline default: () -> T): Lazy<T> = lazy {
    val value = intent.extras?.get(key)
    if (value is T) value else default()
}