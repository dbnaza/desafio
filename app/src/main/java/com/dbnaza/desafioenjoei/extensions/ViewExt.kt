package com.dbnaza.desafioenjoei.extensions

import android.view.View

fun View.setVisibility(hasToShow: Boolean, hideIfNotVisible: Boolean = true) {
    this.visibility = if (hasToShow) View.VISIBLE else if (hideIfNotVisible ) View.GONE else View.INVISIBLE
}